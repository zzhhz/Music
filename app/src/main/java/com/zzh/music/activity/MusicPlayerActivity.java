package com.zzh.music.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.zzh.music.MusicApplication;
import com.zzh.music.R;
import com.zzh.music.base.BaseMusicActivity;
import com.zzh.music.helper.MusicHelper;
import com.zzh.music.model.Music;
import com.zzh.music.service.MusicService;
import com.zzh.music.ui.view.PopWindow;
import com.zzh.music.ui.view.RelativeLayoutBlurredView;
import com.zzh.music.utils.MusicLoader;
import com.zzh.music.widget.CDView;
import com.zzh.music.widget.LrcView;
import com.zzh.zlibs.swipe.SwipeBackLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZZH on 16/9/28
 *
 * @Date: 16/9/28 21:28
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 音乐详情的界面, 单独拿出一个activity, 用来展示播放音乐播放界面。
 */
public class MusicPlayerActivity extends BaseMusicActivity implements Toolbar.OnMenuItemClickListener, PopWindow.OnMusicListClickListener {
    public static final String DATA_MUSIC_PLAYER = "data";
    private Music mMusic;
    private MusicService mMusicService;//播放音乐服务的实例
    private CDView mCDView;//转盘文件
    private LrcView mLrcView;// 歌词显示
    @BindView(R.id.vp_music_slide)
    public ViewGroup mPlayerContainer;
    public static final int AUTO_PLAYER = 0X000;
    private boolean isAutoPlayer = false;
    @BindView(R.id.btn_player_stop)
    public ImageView mStartOrStop;
    @BindView(R.id.activity_music_player)
    public RelativeLayoutBlurredView mAllContainer;
    @BindView(R.id.appBarLayout)
    public View mAppBarLayout;
    @BindView(R.id.iv_player_type)
    public View mPlayTypeView;
    public PopupWindow mPlayMusicList;


    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMusicService = ((MusicService.MusicBinder) service).getMusicService();
            if (isAutoPlayer && !mMusicService.isPlaying()) {
                mMusicService.startMusicPlayer();
                if (mCDView != null)
                    mCDView.start();
            }
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
        ButterKnife.bind(this);
        //toolbars("播放详情");
        Intent intentMusic = getIntent();
        mMusic = (Music) intentMusic.getSerializableExtra(DATA_MUSIC_PLAYER);
        Bitmap art = MusicLoader.getInstance(this).getMusicArt(mMusic.getId(), mMusic.getMusicAlbumId(), false);
        if (art == null) {
            art = BitmapFactory.decodeResource(getResources(), R.mipmap.menu_header_bg);
        }
        setBackgroundBlur(art);
        String title = mMusic.getMusicName();
        if (TextUtils.isEmpty(title)) {
            title = "正在播放";
        } else {
            if (title.contains(".mp3")) {
                title = title.replace(".mp3", "");
            }
        }
        toolbars(R.id.toolbars, R.mipmap.icon_back, title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwipeBackLayout layout = getSwipeBackLayout();
                layout.scrollToFinishActivity();
            }
        });
        mCDView = new CDView(mContext);

        int width = MusicApplication.DISPLAY_WIDTH * 3 / 5;
        mCDView.setWidthAndHeight(width, width);
        mCDView.setImage(art);
        mPlayerContainer.addView(mCDView);
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
        findViewById(R.id.iv_player_type).setOnClickListener(this);
        findViewById(R.id.iv_player_list).setOnClickListener(this);
    }


    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case AUTO_PLAYER:

                break;
        }

    }

    @Override
    public void onClick(View v) {
        playOrStop(v);
    }

    private void playOrStop(View v) {
        /*if (mMusicService == null) {
            return;
        }*/
        switch (v.getId()) {
            case R.id.btn_last_songs:
                mMusicService.previousSongs();
                break;
            case R.id.btn_next_songs:
                mMusicService.nextSongs();
                break;
            case R.id.btn_player_stop:
                pauseOrStop();
                break;
            case R.id.iv_player_list:
                showPlayMusicList();
                break;
            case R.id.iv_player_type:
                changePlayerType(mMusicService.changePlayType());
                break;
            default:
                break;
        }
    }

    /**
     * 显示播放列表
     */
    private void showPlayMusicList() {
        if (mPlayMusicList == null) {
            PopWindow window = new PopWindow();
            mPlayMusicList = window.getPopListWindow(mContext, this);
        }
        mPlayMusicList.showAtLocation(mAllContainer, Gravity.BOTTOM, 0, 0);
    }

    private void changePlayerType(MusicHelper.PlayerType type) {
        switch (type) {
            case SINGLE_LOOP:
                mPlayTypeView.setBackgroundResource(R.mipmap.music_single_loop);
                break;
            case EACH_LOOP:
                mPlayTypeView.setBackgroundResource(R.mipmap.music_repeat_button);
                break;
            case RANDOM_LOOP:
                mPlayTypeView.setBackgroundResource(R.mipmap.music_shuffle_button);
                break;
            case ONE_LOOP:
                mPlayTypeView.setBackgroundResource(R.mipmap.music_one_loop);
                break;
        }
    }

    private void pauseOrStop() {
        if (mMusicService.isPlaying()) {
            //mStartOrStop.setText("暂停");
            mStartOrStop.setBackgroundResource(R.mipmap.music_pause_button);
            mCDView.pause();
        } else {
            //mStartOrStop.setText("开始");
            mStartOrStop.setBackgroundResource(R.mipmap.music_play_button);
            mCDView.start();
        }
        mMusicService.pauseMusicPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //解绑服务
        //unbindService(mServiceConnection);
    }

    /**
     * 设置模糊图片背景
     * @param src
     */
    public void setBackgroundBlur(Bitmap src) {
        mAllContainer.enableBlurredView();
        mAllContainer.setBlurredImg(src);
        mAllContainer.setBlurredLevel(90);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onItemClicked(int position) {

    }
}
