package com.zzh.music.activity.guide;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.zzh.music.MainActivity;
import com.zzh.music.R;
import com.zzh.music.service.MusicService;
import com.zzh.zlibs.base.BaseNoSwipeBackActivity;

/**
 * Created by ZZH on 16/11/22
 *
 * @Date: 16/11/22 21:21
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 程序的闪屏页
 */
public class SplashActivity extends BaseNoSwipeBackActivity {
    private ImageView mSplash;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        mSplash = (ImageView) findViewById(R.id.riv_splash);
        PropertyValuesHolder valuesHolderX = PropertyValuesHolder.ofFloat("scaleX", 1, 1.5f);
        PropertyValuesHolder valuesHolderY = PropertyValuesHolder.ofFloat("scaleY", 1, 1.5f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mSplash, valuesHolderX, valuesHolderY);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setDuration(2000);//两秒
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "onAnimationStart: start-----------");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onAnimationEnd: end---------------");
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG, "onAnimationCancel: cancel---------");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG, "onAnimationRepeat: repeat---------");
            }
        });
        animator.start();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initSetListener() {
    }

    @Override
    protected void handlerMessage(Message msg) {

        switch (msg.what) {
            case 0:
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                this.finish();
                break;
        }

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
