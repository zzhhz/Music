package com.zzh.music;

import android.app.Activity;
import android.app.Application;

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
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
