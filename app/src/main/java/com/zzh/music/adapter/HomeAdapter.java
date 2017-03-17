package com.zzh.music.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.zzh.music.R;
import com.zzh.music.activity.MusicPlayerActivity;
import com.zzh.music.holder.HomeViewHolder;
import com.zzh.music.loader.ImageLoader;
import com.zzh.music.model.Music;
import com.zzh.music.utils.DensityUtils;

import java.io.Serializable;
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
public class HomeAdapter extends LRecyclerView.Adapter<HomeViewHolder> {
    private final Context mContext;
    private List<Music> dataList;
    private static final Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

    private int DISPLAY_IMAGE_WIDTH;

    public HomeAdapter(Context ctx) {
        mContext = ctx;
        dataList = new ArrayList<>();
        DISPLAY_IMAGE_WIDTH = (DensityUtils.getDisplayWidth(ctx) - DensityUtils.dp2px(ctx, 20)) / 2;
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
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_songs, parent, false);
        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, int position) {
        final Music item = dataList.get(position);
        holder.musicMsg.setText(item.getMusicDuration() + "");
        holder.musicTitle.setText(item.getMusicTitle());
        ImageLoader.getInstance(mContext).loadImageUri(item.getId(), item.getMusicAlbumId(), holder.musicAlbum);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MusicPlayerActivity.class);
                intent.putExtra(MusicPlayerActivity.DATA_MUSIC_PLAYER, item);
//                intent.putExtra(MusicPlayerActivity.DATA_LIST_MUSIC_PLAYER, (ArrayList) dataList);
                Bundle bundle = new Bundle();
                bundle.putSerializable(MusicPlayerActivity.DATA_LIST_MUSIC_PLAYER, (ArrayList) dataList);
                intent.putExtra(MusicPlayerActivity.DATA_LIST_MUSIC_PLAYER, bundle);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}