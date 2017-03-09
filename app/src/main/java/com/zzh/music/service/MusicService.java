package com.zzh.music.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.zzh.music.activity.MusicPlayerActivity;
import com.zzh.music.model.Music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

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
//    private MediaPlayer mMediaPlayer = null;
    private Music mMusic;
    private IjkMediaPlayer mMediaPlayer;
    public static final List<Music> mCurrentListPlayer;

    static {
        //初始化
        mCurrentListPlayer = new ArrayList<>();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new IjkMediaPlayer();
        mIBinder = new MusicBinder(this);

    }

    public MusicService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        //播放音乐
        mMusic = (Music) intent.getSerializableExtra(MusicPlayerActivity.DATA_MUSIC_PLAYER);
        if (!mCurrentListPlayer.contains(mMusic)){
            mCurrentListPlayer.add(mMusic);
        }
        try {
            mMediaPlayer.setDataSource(mMusic.getMusicPath());
            mMediaPlayer.prepareAsync();
            mMediaPlayer.seekTo(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    //音乐播放的位置
    public long getMusicPositionPlayer(){
        return mMediaPlayer.getCurrentPosition();
    }

    public boolean isPlaying()
    {
        return mMediaPlayer.isPlaying();
    }

    /**
     * 开始播放
     */
    public void startMusicPlayer()
    {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.start();
        }
    }
    /**
     * 停止
     */
    public void stopMusicPlayer(){

        if (mMediaPlayer != null){
            mMediaPlayer.stop();
        }
    }

    /**
     * 暂停
     */
    public void pauseMusicPlayer(){
        if (mMediaPlayer != null){
            if (isPlaying()) {
                mMediaPlayer.pause();
            } else {
                mMediaPlayer.start();
            }
        }
    }
}
