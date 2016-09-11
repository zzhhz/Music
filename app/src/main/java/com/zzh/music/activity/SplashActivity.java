package com.zzh.music.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.zzh.music.MainActivity;
import com.zzh.music.R;
import com.zzh.music.base.BaseNoSwipeBackActivity;
/**
 * Created by ZZH on 16/6/22
 *
 * @Date: 16/6/22 19:14
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 闪屏
 */
public class SplashActivity extends BaseNoSwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    @Override
    protected void initView() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 1000);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }
}
