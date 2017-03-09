package com.zzh.music.fragment;


import android.os.Message;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.zzh.libs.widget.ZRecyclerView;
import com.zzh.music.R;
import com.zzh.music.adapter.RecommendAdapter;
import com.zzh.music.base.BaseFragment;
import com.zzh.music.model.BaseModel;
import com.zzh.music.model.Music;
import com.zzh.music.utils.params.ZCurHashMap;
import com.zzh.music.utils.web.BaseSubscriber;
import com.zzh.music.utils.web.RetrofitUtils;

import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZZH on 16/6/8
 *
 * @Date: 16/6/8 17:03
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 推荐专辑，音乐页面。
 */
public class RecommendFragment extends BaseFragment {

    private ZRecyclerView mRecommend;
    private RecommendAdapter mAdapter;
    private StaggeredGridLayoutManager mManager;
    private LRecyclerViewAdapter mViewAdapter;
    private int page = 0;
    public RecommendFragment() {
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_recommend;
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
        reloadData(page);
    }

    @Override
    public void setViewListener() {
        mRecommend.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                reloadData(page);
            }
        });

        mRecommend.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                reloadData(page);
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

    private void reloadData(final int page) {
        Map<String, String> params = new ZCurHashMap();
        params.put("method", "baidu.ting.billboard.billList");
        params.put("type", "1");
        params.put("size", "10");
        params.put("offset", RetrofitUtils.nextPage(page));
        RetrofitUtils.Api().getRecommendType(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseSubscriber<BaseModel<Music>>(getActivity()) {
            @Override
            public void onNext(BaseModel<Music> baseModel) {
                if (page == 0){
                    mAdapter.clear();
                }
                List<Music> musicList = baseModel.getContents();
                mAdapter.addAll(musicList);
                mViewAdapter.notifyDataSetChanged();
                mRecommend.refreshComplete(10);
            }
        });
    }

}
