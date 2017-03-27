package com.zzh.music;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.zzh.zlibs.utils.ZUtils;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by ZZH on 16/5/18.
 *
 * @Date: 16/5/18
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class MusicApplication extends Application {
    //保存应用程序启动的Activity
    public static final Set<Activity> mAllStartActivity = new LinkedHashSet<>();

    @Override
    public void onCreate() {
        super.onCreate();
        DISPLAY_WIDTH = getDisplayWidth();
        DISPLAY_HEIGHT = getDisplayHeight();
    }

    public static int DISPLAY_WIDTH;
    public static int DISPLAY_HEIGHT;


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    public int getDisplayWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        return width;
    }

    public int getDisplayHeight() {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels; // 屏幕宽度（像素）
    }
}
