package com.zzh.music.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzh.music.R;
import com.zzh.music.activity.DetailActivity;
import com.zzh.music.holder.RecommendViewHolder;
import com.zzh.music.model.Music;

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
public class RecommendAdapter extends RecyclerView.Adapter<RecommendViewHolder> {
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
        Music item = dataList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(DetailActivity.TYPE_FRAGMENT, DetailActivity.TYPE_FRAGMENT_ALBUM_DETAIL);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}