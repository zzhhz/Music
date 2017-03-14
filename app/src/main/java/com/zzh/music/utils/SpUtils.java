package com.zzh.music.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.zzh.music.helper.MusicHelper;

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
 * Created by Administrator on 2017/3/14.
 *
 * @Date: 2017/3/14
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: Music sharedpreferenced 错左
 */

public class SpUtils {

    public static String getString(Context ctx, String key) {
        SharedPreferences sp = ctx.getSharedPreferences("Music", Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static void editString(Context ctx, String key, String value) {
        SharedPreferences sp = ctx.getSharedPreferences("Music", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //修改播放模式
    public static void editMusicType(Context ctx, MusicHelper.PlayerType model) {
        String type = "";
        switch (model) {
            case RANDOM_LOOP:
                type = "RANDOM_LOOP";
                break;
            case SINGLE_LOOP:
                type = "SINGLE_LOOP";
                break;
            case EACH_LOOP:
                type = "EACH_LOOP";
                break;
            case ONE_LOOP:
                type = "ONE_LOOP";
                break;
            default:
                type = "ONE_LOOP";
                break;
        }
        editString(ctx, MusicHelper.PLAYER_TYPE, type);
    }

    public static MusicHelper.PlayerType getPlayerType(Context ctx) {
        String string = getString(ctx, MusicHelper.PLAYER_TYPE);
        if (TextUtils.isEmpty(string)) {
            string = "ONE_LOOP";
        }
        return MusicHelper.PlayerType.valueOf(string);
    }

}
