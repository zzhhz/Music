package com.zzh.music.utils;

import android.os.Environment;
import android.support.annotation.NonNull;

import com.zzh.zlibs.utils.ZUtils;

import java.io.File;

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
 * Created by Administrator on 2017/3/24.
 *
 * @Date: 2017/3/24
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: Music 获取一些路径
 */

public class ZHUtils extends ZUtils {
    /**
     * 程序的文件路径
     *
     * @return
     */
    @NonNull
    public static String getCurrentPath() {
        StringBuilder builder = new StringBuilder(ZUtils.getSDCardDirectory(Environment.DIRECTORY_MUSIC));
        builder.append(File.separator).append("ZZH");
        return builder.toString();
    }

    /**
     * 存放音乐的路径
     *
     * @return
     */
    @NonNull
    public static String getCurrentMusicPath() {
        StringBuilder builder = new StringBuilder(getCurrentPath());
        builder.append(File.separator).append("songs");
        return builder.toString();
    }

    /**
     * 存放歌词的路径
     *
     * @return
     */
    @NonNull
    public static String getCurrentLrcPath() {
        StringBuilder builder = new StringBuilder(getCurrentPath());
        builder.append(File.separator).append("lrc");
        return builder.toString();
    }

    /**
     * 初始化接口
     */
    public static void init() {
        String musicPath = getCurrentMusicPath();
        String lrcPath = getCurrentLrcPath();
        File musicFile = new File(musicPath);
        File lrcFile = new File(lrcPath);
        if (!musicFile.exists()) {
            musicFile.mkdirs();
        }
        if (!lrcFile.exists()) {
            lrcFile.mkdirs();
        }
    }
}
