package com.zzh.music.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zzh.music.R;
import com.zzh.music.base.BaseMusicActivity;
import com.zzh.music.model.Music;
import com.zzh.music.service.MusicService;
import com.zzh.music.widget.CDView;
import com.zzh.music.widget.LrcView;

/**
 * Created by ZZH on 16/9/28
 *
 * @Date: 16/9/28 21:28
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 音乐详情的界面,单独拿出一个activity,用来展示播放音乐播放界面。
 */
public class MusicPlayerActivity extends BaseMusicActivity implements Toolbar.OnMenuItemClickListener{
    public static final String DATA_MUSIC_PLAYER = "data";
    private Music mMusic;
    private MusicService mMusicService;//播放音乐服务的实例
    private ViewPager mViewPager;
    private CDView mCDView;//转盘文件
    private LrcView mLrcView;// 歌词显示


    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMusicService = ((MusicService.MusicBinder) service).getMusicService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMusicService = null;
        }
    };


    @Override
    protected int setLayoutId() {
        return R.layout.activity_music_player;
    }

    @Override
    protected void initView() {
        //toolbars("播放详情");
        Intent intentMusic = getIntent();
        mMusic = (Music) intentMusic.getSerializableExtra(DATA_MUSIC_PLAYER);
        toolbars(R.id.toolbars,  R.mipmap.icon_back, mMusic.getMusicName(),null);

    }

    @Override
    protected void initData() {
        //启动音乐播放服务
        Intent intent = new Intent(mContext, MusicService.class);
        intent.putExtra("data", mMusic);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void initSetListener() {
        findViewById(R.id.btn_last_songs).setOnClickListener(this);
        findViewById(R.id.btn_next_songs).setOnClickListener(this);
        findViewById(R.id.btn_player_stop).setOnClickListener(this);
    }


    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_last_songs:
                break;
            case R.id.btn_next_songs:
                break;
            case R.id.btn_player_stop:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //解绑服务
        //unbindService(mServiceConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
