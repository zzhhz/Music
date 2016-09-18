package com.zzh.music.fragment;

import android.Manifest;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzh.libs.widget.HRecyclerView;
import com.zzh.music.R;
import com.zzh.music.adapter.HomeAdapter;
import com.zzh.music.base.BaseFragment;
import com.zzh.music.model.Music;
import com.zzh.music.utils.MusicLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZH on 16/9/11
 *
 * @Date: 16/9/11 20:06
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 首页
 */
public class HomeFragment extends BaseFragment {

    private HRecyclerView mRecommend;
    private HomeAdapter mAdapter;
    private StaggeredGridLayoutManager mManager;

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
        mRecommend = (HRecyclerView) fragment.findViewById(R.id.recyclerView_home);
        mManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new HomeAdapter(mContext);
        mRecommend.setLayoutManager(mManager);
        mRecommend.setAdapter(mAdapter);
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
        //请求权限
        super.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 10001);
    }

    @Override
    protected void doNextPermission() {
        List<Music> musicList = MusicLoader.getInstance(mContext).getMusicList();
        Log.d(TAG, "------musicList-----: "+musicList.size());
        mAdapter.clear();
        mAdapter.addAll(musicList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void checkPermission(String permission, int requestCode) {
        super.checkPermission(permission, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("---", "onRequestPermissionsResult: -----------------" );
    }

    @Override
    public void setViewListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public String getTitle() {
        return "首页";
    }
}
