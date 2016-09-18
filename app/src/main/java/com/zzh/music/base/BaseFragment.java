package com.zzh.music.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zzh on 2016/3/14.
 */
public abstract class BaseFragment extends Fragment {
    protected BaseHandler mHandler;
    protected Context mContext;
    protected static String TAG;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        TAG = this.getClass().getName();
        mHandler = new BaseHandler();
        mContext = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void initView(View fragment);
    protected abstract void initData();
    public abstract void setViewListener();

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

    //6.0之上的权限管理
    protected void checkPermission(String permission, int requestCode){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int per = getActivity().checkSelfPermission(permission);
            if (PackageManager.PERMISSION_GRANTED != per){
                boolean flag = getActivity().shouldShowRequestPermissionRationale(permission);
                if (flag){
                    //弹窗设置
                    new AlertDialog.Builder(getActivity())
                            .setMessage("app需要开启权限才能使用此功能")
                            .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
                                    getActivity().startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", null)
                            .create()
                            .show();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
                }

            } else {
                doNextPermission();
            }
        } else {
            doNextPermission();
        }
    }

    protected void doNextPermission(){}
}
