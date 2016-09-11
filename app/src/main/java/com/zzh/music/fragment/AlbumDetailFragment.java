package com.zzh.music.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzh.music.R;
import com.zzh.music.base.BaseFragment;

/**
 * Created by ZZH on 16/7/4
 * A simple {@link BaseFragment} subclass.
 *
 * @Date: 16/7/4 14:58
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 专辑详情页
 */
public class AlbumDetailFragment extends BaseFragment {


    public AlbumDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_detail, container, false);
        initView(view);
        initData();
        setViewListener();
        return view;
    }

    @Override
    protected void initView(View fragment) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void setViewListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public String getTitle() {
        return "";
    }

}
