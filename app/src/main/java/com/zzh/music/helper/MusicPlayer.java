package com.zzh.music.helper;

import android.media.MediaPlayer;

/**
 * Created by ZZH on 16/9/28.
 *
 * @Date: 16/9/28
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 播放音乐,将这个工具设置成单例模式
 */

public class MusicPlayer {
    private String musicPath;
    private static MediaPlayer sMediaPlayer;
    private MusicPlayer(){}

    public static MediaPlayer getInstance(){
        if (sMediaPlayer == null){
            synchronized (MediaPlayer.class){
                if (sMediaPlayer == null)
                    sMediaPlayer = new MediaPlayer();
            }
        }
        return sMediaPlayer;
    }

}
