package com.zzh.music.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzh.music.R;
import com.zzh.music.model.Music;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 *
 * @Date: 2017/3/15
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: Music
 */

public class ArrayPlayAdapter extends ArrayAdapter<Music> {
    private Context mContext;
    private int resLayoutId;
    private List<Music> mDataList;

    public ArrayPlayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Music> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.resLayoutId = resource;
        this.mDataList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(resLayoutId, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Music music = mDataList.get(position);
        holder.musicName.setText(TextUtils.isEmpty(music.getMusicName()) ? music.getTitle() : music.getMusicName());
        holder.musicAuthor.setText(TextUtils.isEmpty(music.getMusicArtist()) ? music.getAuthor() : music.getMusicArtist());
        return convertView;
    }

    class ViewHolder {
        TextView musicName;
        TextView musicAuthor;
        ImageView play;
        ImageView close;

        public ViewHolder(View view) {
            musicName = (TextView) view.findViewById(R.id.tv_music_name);
            musicAuthor = (TextView) view.findViewById(R.id.tv_music_author);
            play = (ImageView) view.findViewById(R.id.iv_play);
            close = (ImageView) view.findViewById(R.id.iv_close);
        }
    }
}
