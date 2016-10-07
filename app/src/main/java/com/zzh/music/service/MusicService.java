package com.zzh.music.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.zzh.music.helper.MusicPlayer;

/**
 * Created by ZZH on 16/9/28
 *
 * @Date: 16/9/28 20:53
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 音乐播放的服务。用于音乐后台的播放
 */
public class MusicService extends Service {

    private IBinder mIBinder = null;
    private MediaPlayer mMediaPlayer = null;


    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = MusicPlayer.getInstance();
        mIBinder = new MusicBinder(this);
    }

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        //播放音乐
        return mIBinder;
    }

    //
    public class MusicBinder extends Binder {
        private MusicService mMusicService;
        public MusicBinder (MusicService service){
            mMusicService = service;
        }
        public MusicService getMusicService(){
            return mMusicService;
        }
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


}
