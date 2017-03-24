package com.zzh.music.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.view.View;

import com.zzh.music.R;
import com.zzh.music.base.BaseMusicActivity;
import com.zzh.zlibs.utils.ZUtils;

import java.io.File;

public class TestActivity extends BaseMusicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {

        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        requestReadStoragePermission();
        requestPermission(new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE_READ_PERMISSION);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {
        requestReadStoragePermission();
    }

    @Override
    protected void notifyPermission(int code, boolean flag) {
        showMessage(ZUtils.getSDCardDirectory(Environment.DIRECTORY_MUSIC) + File.separator + "537792141.mp3");
    }
}
