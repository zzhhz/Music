package com.zzh.music.utils;

import android.content.Intent;

import com.zzh.music.MusicConstants;

import org.greenrobot.eventbus.EventBus;

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━永无BUG━━━━━
 * Created by Administrator on 2017/3/16.
 *
 * @Date: 2017/3/16
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: Music 发送通知工具类
 */

public class EventUtils {
    public static void sendEventMusicPlayChangeStatus() {
        Intent intent = new Intent();
        intent.setAction(MusicConstants.EVENT_MUSIC_PLAY_CHANGE_STATUS);
        EventBus.getDefault().post(intent);
    }
}
