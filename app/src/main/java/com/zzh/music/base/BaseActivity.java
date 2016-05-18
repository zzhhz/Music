package com.zzh.music.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by zzh on 2016/1/29.
 * 对BaseActivity的一些封装：
 * 全局Context
 * Handler
 * Toast
 * 初始化View控件
 * 初始化数据
 * 给控件设置监听事件
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected static String TAG;
    protected Context mContext;
    protected BaseHandler mHandler;
    protected Toast mToast;
    protected BaseReceiver mReceiver;
    protected IntentFilter mFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getLocalClassName();
        mContext = this;
        if (mHandler == null)
            mHandler = new BaseHandler();
        if (mReceiver == null)
            mReceiver = new BaseReceiver();
        if (mFilter == null)
            mFilter = new IntentFilter();
        initBroadCast();
        mContext.registerReceiver(mReceiver, mFilter);
    }

    private void initBroadCast() {
        //mFilter.addAction();
    }

    protected void init(){
        initView();
        initData();
        initSetListener();
    }

    /**
     * 初始化控件使用
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听事件
     */
    protected abstract void initSetListener();

    /**
     * 在子Activity中处理handler
     *
     * @param msg 发送过来的msg
     */
    protected abstract void handlerMessage(Message msg);

    /**
     * 处理广播
     * @param context 上下文
     * @param intent 数据
     */
    protected void onBroadCastReceiver(Context context, Intent intent) {
    }

    public class BaseHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            handlerMessage(msg);
        }
    }

    /**
     * 广播
     */
    private class BaseReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            onBroadCastReceiver(context, intent);
        }
    }

    /**
     * 显示的文字提示信息
     *
     * @param str 显示文字
     */
    protected void showMessage(String str) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            mToast.setText(str);
        }
        mToast.show();
    }

    /***
     * 记录日志
     * @param msg 日志信息
     */
    protected void loge(String msg){
            Log.e(TAG, "------"+msg+"---");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null)
            mContext.unregisterReceiver(mReceiver);
    }
}
