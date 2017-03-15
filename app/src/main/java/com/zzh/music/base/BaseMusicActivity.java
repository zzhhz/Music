package com.zzh.music.base;

import android.os.Bundle;
import android.view.KeyEvent;

import com.zzh.music.MusicApplication;
import com.zzh.zlibs.swipe.SwipeBackLayout;


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
public abstract class BaseMusicActivity extends com.zzh.zlibs.base.BaseActivity {
    //权限
    protected static final int REQUEST_CODE_READ_PERMISSION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MusicApplication.mAllStartActivity.add(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicApplication.mAllStartActivity.remove(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            SwipeBackLayout layout = getSwipeBackLayout();
            layout.scrollToFinishActivity();
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
