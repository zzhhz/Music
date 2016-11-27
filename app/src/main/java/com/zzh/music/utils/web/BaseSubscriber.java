package com.zzh.music.utils.web;

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
public class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Object o) {

    }
}
