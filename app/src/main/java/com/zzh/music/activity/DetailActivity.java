package com.zzh.music.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.zzh.music.R;
import com.zzh.music.base.BaseMusicActivity;
import com.zzh.music.fragment.AlbumDetailFragment;

/**
 * Created by ZZH on 16/7/4
 *
 * @Date: 16/7/4 15:00
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 显示详情的容器, 里面由fragment填充
 */
public class DetailActivity extends BaseMusicActivity {
    /***
     * fragment类型
     ***/
    public static final String TYPE_FRAGMENT = "fragment_type";
    /***
     * 专辑详情
     ****/
    public static final String TYPE_FRAGMENT_ALBUM_DETAIL = "fragment_type_album_detail";

    public AlbumDetailFragment mAlbumDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        if (intent == null)
            return;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String extra = intent.getStringExtra(TYPE_FRAGMENT);
        switch (extra) {
            case TYPE_FRAGMENT_ALBUM_DETAIL:
                if (mAlbumDetailFragment == null) {
                    mAlbumDetailFragment = new AlbumDetailFragment();
                }
                if (mAlbumDetailFragment.isAdded()){
                    fragmentTransaction.show(mAlbumDetailFragment);
                } else {
                    fragmentTransaction.add(R.id.rl_detail_container, mAlbumDetailFragment,"mAlbumDetailFragment");
                }
                break;
        }
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }
}
