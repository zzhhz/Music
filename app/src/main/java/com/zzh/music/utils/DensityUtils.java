package com.zzh.music.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by ZZH on 16/4/14.
 * 单位转换工具
 */
public class DensityUtils {


    /**
     * @param ctx 上下文
     * @param px  像素
     * @return 返回值单位是dp
     */
    public static int px2dp(Context ctx, float px) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * @param ctx 上下文
     * @param dp  dp值
     * @return 返回像素值
     */
    public static int dp2px(Context ctx, float dp) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * @param ctx 上下文
     * @param px  像素
     * @return 返回值单位是dp
     */
    public static int px2sp(Context ctx, float px) {
        final float fontScale = ctx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / fontScale + 0.5f);
    }

    /**
     * @param ctx 上下文
     * @param sp  字体大小
     * @return 返回字体像素值
     */
    public static int sp2px(Context ctx, float sp) {
        final float fontScale = ctx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (fontScale * sp + 0.5);
    }

    /**
     * 获取屏幕的宽度
     *
     * @param ctx 上下文
     * @return 屏幕宽度
     */
    public static int getDisplayWidth(Context ctx) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) ctx).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @param ctx 上下文
     * @return 屏幕高度
     */
    public static int getDisplayHeight(Context ctx) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) ctx).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }
}
