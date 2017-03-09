package com.zzh.music.fragment;

import android.os.Message;
import android.view.View;

import com.zzh.music.R;
import com.zzh.music.base.BaseFragment;
/**
 * Created by ZZH on 16/6/22
 *
 * @Date: 16/6/22 19:04
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class AlbumFragment extends BaseFragment {

    public AlbumFragment() {
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_album;
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
        return "专辑";
    }

}
