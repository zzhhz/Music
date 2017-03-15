package com.zzh.music.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.zzh.music.MusicApplication;
import com.zzh.music.R;
import com.zzh.music.service.MusicService;
import com.zzh.music.ui.adapter.ArrayPlayAdapter;

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━永无BUG━━━━━
 * Created by Administrator on 2017/3/15.
 *
 * @Date: 2017/3/15
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: Music
 */

public class PopWindow {

    private ArrayPlayAdapter mAdapter;
    private PopupWindow popupWindow;
    private ListView mPlayList;

    public PopupWindow getPopListWindow(Context ctx, final OnMusicListClickListener clickListener) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.play_list_view, null);
        if (popupWindow == null) {
            popupWindow = new PopupWindow(ctx);
        }
        popupWindow.setContentView(view);
        popupWindow.setHeight(MusicApplication.DISPLAY_HEIGHT * 3 / 5);
        popupWindow.setWidth(MusicApplication.DISPLAY_WIDTH);
        mPlayList = (ListView) view.findViewById(R.id.lv_play_list);
        if (mAdapter == null) {
            mAdapter = new ArrayPlayAdapter(ctx, R.layout.item_play_list_music, MusicService.mCurrentListPlayer);
        }
        mPlayList.setAdapter(mAdapter);
        popupWindow.setAnimationStyle(R.style.Pop_Bottom_In);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });

        mPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (clickListener != null){
                    clickListener.onItemClicked(position);
                }
            }
        });
        return popupWindow;
    }

    public interface OnMusicListClickListener {

        void onItemClicked(int position);
    }

    public ArrayPlayAdapter getAdapter() {
        return mAdapter;
    }
}
