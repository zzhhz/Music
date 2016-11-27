package com.zzh.libs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZH on 16/10/16.
 *
 * @Date: 16/10/16
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */

public class SwipeRefreshAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //RecyclerView使用到的Adapter
    private RecyclerView.Adapter mAdapter;
    //头布局
    private List<View> mHeaderViews;
    //尾布局
    private List<View> mFooterViews;
//
private int mCurrentPosition;

    public SwipeRefreshAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        if (mHeaderViews == null){
            mHeaderViews = new ArrayList<>();
        }
        if (mFooterViews == null){
            mFooterViews = new ArrayList<>();
        }
        
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public int getFooterViews() {
        return mFooterViews.size();
    }

    public int getHeaderViews(){
        return mHeaderViews.size();
    }
}
