package com.zzh.music.utils.web;

import android.app.Activity;

import java.lang.ref.WeakReference;

import rx.Subscriber;

/**
 * Created by ZZH on 16/11/22.
 *
 * @Date: 16/11/22
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private final WeakReference<Activity> mReference;
    private String apiUrl;

    public BaseSubscriber(Activity activity) {
        mReference = new WeakReference<>(activity);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
