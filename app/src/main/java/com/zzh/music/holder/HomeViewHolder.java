package com.zzh.music.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzh.music.R;

/**
 * Created by ZZH on 16/9/13.
 *
 * @Date: 16/9/13
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class HomeViewHolder extends RecyclerView.ViewHolder {

    public TextView musicTitle; //名称
    public TextView musicMsg;//简介
    public ImageView musicAlbum; //封面

    public HomeViewHolder(View itemView) {
        super(itemView);
        musicTitle = (TextView) itemView.findViewById(R.id.tv_music_title);
        musicMsg = (TextView) itemView.findViewById(R.id.tv_music_msg);
        musicAlbum = (ImageView) itemView.findViewById(R.id.iv_music_album);
    }
}
