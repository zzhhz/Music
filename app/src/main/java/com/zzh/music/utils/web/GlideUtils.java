package com.zzh.music.utils.web;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zzh.music.MusicApplication;
import com.zzh.music.R;

/**
 * Created by ZZH on 17/3/9.
 *
 * @Date: 17/3/9
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: glide 封装一次
 */
public class GlideUtils {

    /**
     * 加载图片
     *
     * @param ctx    上下文
     * @param imgUrl 图片url
     * @param iv     view的图片
     */
    public static void loadImageIntoView(Context ctx, String imgUrl, final ImageView iv) {
        Glide.with(ctx).load(imgUrl).placeholder(R.color.color_accent)
                .crossFade().error(R.mipmap.ic_launcher).fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        float scale = (float) (MusicApplication.DISPLAY_WIDTH / 2) / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        ViewGroup.LayoutParams params = iv.getLayoutParams();
                        params.height = vh;
                        iv.setLayoutParams(params);
                        iv.setImageDrawable(resource);
                    }
                });
    }
}






















