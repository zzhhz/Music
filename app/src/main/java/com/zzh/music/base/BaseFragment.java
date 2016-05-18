package com.zzh.music.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zzh on 2016/3/14.
 */
public abstract class BaseFragment extends Fragment {
    protected BaseHandler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mHandler = new BaseHandler();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void initView(View fragment);
    protected abstract void initData();

    private class BaseHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            handlerMessage(msg);
        }
    }

    /**
     * 处理消息
     * @param msg 发送消息
     */
    protected abstract void handlerMessage(Message msg);
    public abstract String getTitle();

}
