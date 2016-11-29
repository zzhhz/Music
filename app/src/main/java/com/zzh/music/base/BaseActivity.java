package com.zzh.music.base;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.zzh.music.BuildConfig;
import com.zzh.music.MusicApplication;
import com.zzh.music.R;
import com.zzh.music.utils.SystemStatusManager;

import cn.zzh.lib.app.SwipeBackActivity;

/**
 * Created by zzh on 2016/1/29.
 * 对BaseActivity的一些封装：<br />
 * 全局Context<br />
 * Handler<br />
 * Toast<br />
 * 初始化View控件<br />
 * 初始化数据<br />
 * 给控件设置监听事件<br />
 * 滑动退出当前页面<br />
 */
public abstract class BaseActivity extends SwipeBackActivity implements View.OnClickListener {
    protected static String TAG;
    protected Context mContext;
    protected BaseHandler mHandler;
    protected Toast mToast;
    protected BaseReceiver mReceiver;
    protected IntentFilter mFilter;
    protected Toolbar mToolbar;
    protected TextView toolBarTitle;
    //权限
    protected static final int REQUEST_CODE_READ_PERMISSION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getLocalClassName();
        MusicApplication.mAllStartActivity.add(this);
        mContext = this;
        if (mHandler == null)
            mHandler = new BaseHandler();
        if (mReceiver == null)
            mReceiver = new BaseReceiver();
        if (mFilter == null)
            mFilter = new IntentFilter();
        initBroadCast();
        mContext.registerReceiver(mReceiver, mFilter);
        //setStatusBarTint();
    }

    //
    protected void setStatusBarTint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
//            getWindow().addFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemStatusManager tintManager = new SystemStatusManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // 设置状态栏的颜色
            tintManager.setStatusBarTintResource(R.color.theme_color);
            getWindow().getDecorView().setFitsSystemWindows(true);
        }
    }
    //设置Toolbar
    protected void toolbars(String title){
        toolbars(title, null);
    }
    protected void toolbars(String title, Toolbar.OnClickListener clickListener) {
        try {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
        } catch (Exception ex) {
            loge("没有设置toolbar");
        }
        toolBarTitle = (TextView) findViewById(R.id.toolbar_title);
        if (mToolbar == null)
            return;
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.mipmap.icon_back);
        if (toolBarTitle != null) {
            toolBarTitle.setText(title);
        }
        if (clickListener == null) {
            mToolbar.setNavigationOnClickListener(new Toolbar.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            mToolbar.setNavigationOnClickListener(clickListener);
        }
    }


    private void initBroadCast() {
        //mFilter.addAction();
    }

    protected void init() {
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
     *
     * @param context 上下文
     * @param intent  数据
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
     *
     * @param msg 日志信息
     */
    protected void loge(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "------" + msg + "---");
        }
    }
    protected void logd(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "------" + msg + "---");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicApplication.mAllStartActivity.remove(this);
        if (mReceiver != null)
            mContext.unregisterReceiver(mReceiver);
    }

    //6.0处理申请权限
    protected void requestPermission(String[] permission, int code) {
        if (permission == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, permission, code);
        }
    }

    protected void requestReadStoragePermission() {
        String[] permission = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        boolean result = true;//默认已经都授权了
        outer :
        for (String per:permission){
            result = verifyGrantPermission(per);
            if (!result){
                break outer;
            }
        }
        if (!result) {
            requestPermission(permission, REQUEST_CODE_READ_PERMISSION);
        }
    }

    //判断是否授予了权限
    protected boolean verifyGrantPermission(String permission){
        boolean result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            result = (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
        } else {
            result = PermissionChecker.checkSelfPermission(this, permission)
                    == PermissionChecker.PERMISSION_GRANTED;
        }
        return result;
    }

    //验证是否申请了权限
    public static boolean verifyPermissions(int[] grantResults) {
        if (grantResults.length < 1) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    //申请到权限
    protected void notifyPermission(int code){

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (verifyPermissions(grantResults)){
            notifyPermission(requestCode);
            loge("--");
        } else {
            showMessage("请在手机设置中授予应用权限");
        }
        /*switch (requestCode){
            case REQUEST_CODE_READ_PERMISSION:
                boolean verifyPermissions = verifyPermissions(grantResults);
                loge("-----------"+requestCode+"---"+verifyPermissions);
                if (verifyPermissions){
                    notifyPermission(requestCode);
                }
                break;
        }*/
    }
}
