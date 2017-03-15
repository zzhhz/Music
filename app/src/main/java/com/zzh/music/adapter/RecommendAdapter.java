package com.zzh.music.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.zzh.music.R;
import com.zzh.music.activity.MusicPlayerActivity;
import com.zzh.music.holder.RecommendViewHolder;
import com.zzh.music.model.Music;
import com.zzh.music.utils.web.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZH on 16/6/22.
 *
 * @Date: 16/6/22
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 推荐页的文件
 */
public class RecommendAdapter extends LRecyclerView.Adapter<RecommendViewHolder> {
    private final Context mContext;
    private List<Music> dataList;

    public RecommendAdapter(Context ctx) {
        mContext = ctx;
        dataList = new ArrayList<>();
    }

    public void clear() {
        dataList.clear();
    }

    public void add(Music item) {
        dataList.add(item);
    }

    public void addAll(List<Music> list) {
        dataList.addAll(list);
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        return new RecommendViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {
        final Music item = dataList.get(position);GlideUtils.loadImageIntoView(mContext, item.getPicBig(), holder.musicImage);

        holder.musicAutor.setText(item.getAuthor());
        holder.musicTitle.setText(item.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MusicPlayerActivity.class);
                intent.putExtra(MusicPlayerActivity.DATA_MUSIC_PLAYER, item);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}