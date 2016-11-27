package com.zzh.music.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.zzh.music.R;
import com.zzh.music.base.BaseActivity;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {

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

    }

    @Override
    protected void notifyPermission(int code) {
        loge(code+"");
    }
}
