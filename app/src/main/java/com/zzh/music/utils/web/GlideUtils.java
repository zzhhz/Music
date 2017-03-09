package com.zzh.music.utils.web;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
     * @param ctx 上下文
     * @param imgUrl 图片url
     * @param iv view的图片
     *
     */
    public static void loadImageIntoView(Context ctx, String imgUrl, ImageView iv) {
        Glide.with(ctx).load(imgUrl).placeholder(R.color.color_accent)
                .crossFade().error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv);
    }
}






















