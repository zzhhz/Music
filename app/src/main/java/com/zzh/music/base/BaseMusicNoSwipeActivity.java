package com.zzh.music.base;

import android.content.res.Resources;
import android.util.Log;

/**
 * Created by zzh on 2016/1/29.
 * 对BaseActivity的一些封装：<br />
 * 全局Context<br />
 * Handler<br />
 * Toast<br />
 * 初始化View控件<br />
 * 初始化数据<br />
 * 给控件设置监听事件<br />
 * 不支持滑动返回<br />
 */
public abstract class BaseMusicNoSwipeActivity extends com.zzh.zlibs.base.BaseNoSwipeBackActivity {

    /**
     * 底部nav的高度
     *
     * @return
     */
    private int getNavigationBarHeight() {
        Resources resources = this.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;
    }
}
