package com.zzh.music.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.zzh.music.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZZH on 16/6/22.
 *
 * @Date: 16/6/22
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 推荐页的holder
 */
public class RecommendViewHolder extends LRecyclerView.ViewHolder {

    @BindView(R.id.tv_music_title)
    public TextView musicTitle;
    @BindView(R.id.tv_music_msg)
    public TextView musicAutor;
    @BindView(R.id.iv_music_album)
    public ImageView musicImage;

    public RecommendViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}