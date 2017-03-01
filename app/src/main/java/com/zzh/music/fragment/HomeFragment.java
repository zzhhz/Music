package com.zzh.music.fragment;

import android.Manifest;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zzh.libs.widget.ZRecyclerView;
import com.zzh.music.R;
import com.zzh.music.adapter.HomeAdapter;
import com.zzh.music.base.BaseFragment;
import com.zzh.music.model.Music;
import com.zzh.music.utils.MusicLoader;

import java.util.List;

/**
 * Created by ZZH on 16/9/11
 *
 * @Date: 16/9/11 20:06
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 首页, 瀑布流式的展示效果
 */
public class HomeFragment extends BaseFragment {
    private static final int NO_MORE = 1001;
    private ZRecyclerView mRecommend;
    private HomeAdapter mAdapter;
    private LRecyclerViewAdapter zAdapter;
    private StaggeredGridLayoutManager mManager;
    private static final int REFRESH_COMPLETE = 1000;
    private int page = 0;
    private boolean isLoadComplete = false;//是否加载完成

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        setViewListener();
        return view;
    }

    //初始化控件
    @Override
    protected void initView(View fragment) {
        mRecommend = (ZRecyclerView) fragment.findViewById(R.id.recyclerView_home);
        mManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new HomeAdapter(mContext);
        zAdapter = new LRecyclerViewAdapter(mAdapter);
        mRecommend.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecommend.setLayoutManager(mManager);
        mRecommend.setAdapter(zAdapter);
    }

    @Override
    protected void initData() {
        //请求权限
        super.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void doNextPermission(int requestCode) {
        List<Music> musicList = MusicLoader.getInstance(mContext).getMusicList(page);
        mAdapter.clear();
        mAdapter.addAll(musicList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setViewListener() {
        mRecommend.setLoadMoreEnabled(true);
        mRecommend.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (isLoadComplete) {
                    return;
                }
                page++;
                reloadMusic(page);
            }
        });

        mRecommend.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh() {
                page = 0;
                mRecommend.setNoMore(false);
                reloadMusic(page);
            }
        });
    }

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case REFRESH_COMPLETE:
                if (page == 0) {
                    mAdapter.clear();
                }
                int start = mAdapter.getItemCount();
                mAdapter.addAll((List<Music>) msg.obj);
                isLoadComplete = false;
                if (page == 0){
                    mAdapter.notifyDataSetChanged();
                }else {
                    mAdapter.notifyItemRangeChanged(start, mAdapter.getItemCount());
                }
                mRecommend.refreshComplete(8);
                mRecommend.setNoMore(isLoadComplete);
                break;
            case NO_MORE:
                isLoadComplete = true;
                mRecommend.setNoMore(isLoadComplete);
                break;
        }

    }

    private void reloadMusic(final int page) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                List<Music> musicList = MusicLoader.getInstance(mContext).getMusicList(page);
                Message msg = Message.obtain();
                msg.what = REFRESH_COMPLETE;
                msg.obj = musicList;
                if (musicList == null || musicList.size() == 0){
                    mHandler.sendEmptyMessage(NO_MORE);
                } else {
                    mHandler.sendMessage(msg);
                }
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public String getTitle() {
        return "首页";
    }
}
