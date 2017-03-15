package com.zzh.music.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.zzh.music.MusicConstants;
import com.zzh.music.activity.MusicPlayerActivity;
import com.zzh.music.helper.MusicHelper;
import com.zzh.music.model.Music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IMediaPlayer;
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

    public static String TAG = "---MusicService---";

    private IBinder mIBinder = null;
    //    private MediaPlayer mMediaPlayer = null;
    private Music mMusic;
    private IjkMediaPlayer mMediaPlayer;
    public static final List<Music> mCurrentListPlayer;
    private MusicHelper.PlayerType mPlayType = MusicHelper.PlayerType.ONE_LOOP;

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
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: 音乐播放服务启动了");
        return START_REDELIVER_INTENT; //被异常杀死时，再启动服务，并且接收intent
    }

    @Override
    public IBinder onBind(Intent intent) {
        //播放音乐
        mMusic = (Music) intent.getSerializableExtra(MusicPlayerActivity.DATA_MUSIC_PLAYER);
        playMusic(mMusic);
        return mIBinder;
    }

    public void playMusic(Music music) {
        if (!mCurrentListPlayer.contains(music)) {
            mCurrentListPlayer.add(music);
        }
        try {
            String musicUrl = music.getMusicPath();
            if (TextUtils.isEmpty(musicUrl)){
                musicUrl = String.format(MusicConstants.URL_NETWORK_MUSIC_PLAY, "baidu.ting.song.play",""+music.getSongId());
            }
            mMediaPlayer.setDataSource(musicUrl);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.seekTo(0);

            mMediaPlayer.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    Log.d(TAG, "onCompletion: ---------------------");
                    // 播放完成
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上一曲
     */
    public void previousSongs() {

    }

    /**
     * 下一曲
     */
    public void nextSongs() {

    }

    //
    public class MusicBinder extends Binder {
        private MusicService mMusicService;

        public MusicBinder(MusicService service) {
            mMusicService = service;
        }

        public MusicService getMusicService() {
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
    public long getMusicPositionPlayer() {
        return mMediaPlayer.getCurrentPosition();
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    /**
     * 开始播放
     */
    public void startMusicPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.start();
        }
    }

    /**
     * 停止
     */
    public void stopMusicPlayer() {

        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
    }

    /**
     * 暂停
     */
    public void pauseMusicPlayer() {
        if (mMediaPlayer != null) {
            if (isPlaying()) {
                mMediaPlayer.pause();
            } else {
                mMediaPlayer.start();
            }
        }
    }

    public MusicHelper.PlayerType changePlayType() {
        return changePlayType(null);
    }

    public MusicHelper.PlayerType changePlayType(MusicHelper.PlayerType type) {
        if (type == null) {
            switch (mPlayType) {
                case SINGLE_LOOP:
                    mPlayType = MusicHelper.PlayerType.EACH_LOOP;
                    break;
                case EACH_LOOP:
                    mPlayType = MusicHelper.PlayerType.RANDOM_LOOP;
                    break;
                case RANDOM_LOOP:
                    mPlayType = MusicHelper.PlayerType.ONE_LOOP;
                    break;
                case ONE_LOOP:
                    mPlayType = MusicHelper.PlayerType.SINGLE_LOOP;
                    break;
            }
        } else {
            mPlayType = type;
        }
        return mPlayType;
    }

    public MusicHelper.PlayerType getPlayerType() {
        return mPlayType;
    }

    public void clearPlayMusicList(){
        mCurrentListPlayer.clear();
    }
}
