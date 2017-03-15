package com.zzh.music.ui.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.zzh.music.MusicApplication;
import com.zzh.music.R;
import com.zzh.music.model.Music;
import com.zzh.music.ui.adapter.ArrayPlayAdapter;

import java.util.List;

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

    public PopupWindow getPopListWindow(Context ctx, List<Music> list) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.play_list_view, null);
        if (popupWindow == null) {
            popupWindow = new PopupWindow(ctx);
        }
        popupWindow.setContentView(view);
        popupWindow.setHeight(MusicApplication.DISPLAY_HEIGHT * 3 / 5);
        ListView playListView = (ListView) view.findViewById(R.id.lv_play_list);
        if (mAdapter == null) {
            mAdapter = new ArrayPlayAdapter(ctx, R.layout.play_list_view, list);
        }
        playListView.setAdapter(mAdapter);
        popupWindow.setAnimationStyle(R.style.Pop_Bottom_In);

        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });

        playListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return popupWindow;
    }

    public ArrayPlayAdapter getAdapter() {
        return mAdapter;
    }
}
