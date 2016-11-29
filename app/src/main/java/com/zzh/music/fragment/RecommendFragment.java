package com.zzh.music.fragment;


import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.zzh.libs.widget.ZRecyclerView;
import com.zzh.music.R;
import com.zzh.music.adapter.RecommendAdapter;
import com.zzh.music.base.BaseFragment;
import com.zzh.music.model.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZH on 16/6/8
 *
 * @Date: 16/6/8 17:03
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 推荐专辑页面
 */
public class RecommendFragment extends BaseFragment {

    private ZRecyclerView mRecommend;
    private RecommendAdapter mAdapter;
    private StaggeredGridLayoutManager mManager;
    private LRecyclerViewAdapter mViewAdapter;

    public RecommendFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        initView(view);
        initData();
        setViewListener();
        return view;
    }

    //初始化控件
    @Override
    protected void initView(View fragment) {
        mRecommend = (ZRecyclerView) fragment.findViewById(R.id.recommend);
        mManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mAdapter = new RecommendAdapter(mContext);
        mViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mRecommend.setLayoutManager(mManager);
        mRecommend.setAdapter(mViewAdapter);
    }

    @Override
    protected void initData() {
        //
        List<Music> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Music music = new Music();
            list.add(music);
        }
        mAdapter.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setViewListener() {
        mRecommend.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public String getTitle() {
        return "推荐";
    }

}
