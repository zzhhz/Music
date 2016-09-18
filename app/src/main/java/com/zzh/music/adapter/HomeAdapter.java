package com.zzh.music.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zzh.music.R;
import com.zzh.music.activity.DetailActivity;
import com.zzh.music.holder.HomeViewHolder;
import com.zzh.music.loader.ImageLoader;
import com.zzh.music.model.Music;
import com.zzh.music.utils.DensityUtils;

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
public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    private final Context mContext;
    private List<Music> dataList;

    private int DISPLAY_IMAGE_WIDTH;

    public HomeAdapter(Context ctx) {
        mContext = ctx;
        dataList = new ArrayList<>();
        DISPLAY_IMAGE_WIDTH = (DensityUtils.getDisplayWidth(ctx) - DensityUtils.dp2px(ctx, 20)) / 2 ;
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
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        Music item = dataList.get(position);
        holder.musicMsg.setText(item.describeContents()+"");
        holder.musicTitle.setText(item.getMusicTitle());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(item.getWidth(), item.getHeight());
        holder.musicAlbum.setLayoutParams(params);
//        holder.musicAlbum.setImageBitmap(item.getBitmapAlbum());
        ImageLoader.getInstance(mContext).loadImageUri(item.getId(),item.getMusicAlbumId(), holder.musicAlbum);
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