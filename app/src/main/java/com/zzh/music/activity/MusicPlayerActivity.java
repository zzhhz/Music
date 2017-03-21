package com.zzh.music.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zzh.music.MusicApplication;
import com.zzh.music.MusicConstants;
import com.zzh.music.R;
import com.zzh.music.base.BaseMusicActivity;
import com.zzh.music.helper.MusicHelper;
import com.zzh.music.model.Album;
import com.zzh.music.model.BaseModel;
import com.zzh.music.model.Music;
import com.zzh.music.model.Song;
import com.zzh.music.service.MusicService;
import com.zzh.music.ui.view.PopWindow;
import com.zzh.music.ui.view.RelativeLayoutBlurredView;
import com.zzh.music.utils.MusicLoader;
import com.zzh.music.utils.web.BaseSubscriber;
import com.zzh.music.utils.web.GlideUtils;
import com.zzh.music.utils.web.RetrofitUtils;
import com.zzh.music.widget.CDView;
import com.zzh.music.widget.LrcView;
import com.zzh.zlibs.swipe.SwipeBackLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    public static final String DATA_MUSIC_PLAYER = "data_music";
    public static final String DATA_ALBUM_PLAYER = "data_album";
    public static final String DATA_TYPE_PLAYER = "data_type";
    public static final String DATA_IS_AUTO_PLAYER = "auto_play";
    public static final int DATA_TYPE_MUSIC = 0;
    public static final int DATA_TYPE_ALBUM = 1;
    public int playType = 0;
    public static final String DATA_LIST_MUSIC_PLAYER = "dataList";
    private Music mMusic; //播放的音乐
    private Album mAlbum;//
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
    public List<Music> mPlayList;


    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMusicService = ((MusicService.MusicBinder) service).getMusicService();
            if (isAutoPlayer && !mMusicService.isPlaying()) {
                mMusicService.playMusic(mMusic);
            }
            mCDView.start();

            if (mPlayList != null) {//网络播放的音乐不传入mPlayList
                mMusicService.appendMusic(mPlayList);
            }

            changePlayerType(mMusicService.getPlayerType());
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
        playType = intentMusic.getIntExtra(DATA_TYPE_PLAYER, 0);
        isAutoPlayer = intentMusic.getBooleanExtra(DATA_IS_AUTO_PLAYER, false);
        if (playType == DATA_TYPE_MUSIC) {//本地音乐
            mMusic = (Music) intentMusic.getSerializableExtra(DATA_MUSIC_PLAYER);
        } else {//网络专辑
            mAlbum = (Album) intentMusic.getSerializableExtra(DATA_ALBUM_PLAYER);
            loadMusic();
        }
        Bundle bundle = intentMusic.getBundleExtra(DATA_LIST_MUSIC_PLAYER);
        if (bundle != null && bundle.containsKey(DATA_LIST_MUSIC_PLAYER))
            mPlayList = (List<Music>) bundle.getSerializable(DATA_LIST_MUSIC_PLAYER);
        Bitmap art = null;
        String title = null;
        if (mMusic != null) {
            title = mMusic.getMusicName();
            MusicLoader.getInstance(this).getMusicArt(mMusic.getId(), mMusic.getMusicAlbumId(), false);
        }
        if (art == null) {
            art = BitmapFactory.decodeResource(getResources(), R.mipmap.menu_header_bg);
        }
        setBackgroundBlur(art);

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

        Log.d(TAG, "--Play Music--: " + mMusic);
        int width = MusicApplication.DISPLAY_WIDTH * 3 / 5;
        mCDView.setWidthAndHeight(width, width);
        mCDView.setImage(art);
        mPlayerContainer.addView(mCDView);
    }

    /**
     * 加载网络音乐详情
     */
    private void loadMusic() {
        RetrofitUtils.Api().getSongsInfo(mAlbum.getSongId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseSubscriber<BaseModel<Song>>(this) {
            @Override
            public void onNext(BaseModel<Song> baseModel) {
                if ("22000".equals(baseModel.getErrorCode())) {
                    mMusic = new Music();
                    mMusic.setMusicPath(baseModel.getBitrate().getFileLink());
                    mMusic.setMusicName(baseModel.getContent().getTitle());
                    mMusic.setMusicArtist(baseModel.getContent().getAuthor());
                    mMusic.setMusicTitle(baseModel.getContent().getAlbumTitle());
                    mMusic.setMusicUrl(baseModel.getContent().getPicBig());
                    mMusic.setMusicDuration(baseModel.getBitrate().getFileDuration());
                    mMusic.setMusicLrcUrl(baseModel.getContent().getLrclink());
                    GlideUtils.loadImageBitmap(mContext, mMusic.getMusicUrl(), new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            if (resource != null) {
                                setBackgroundBlur(resource);
                                mCDView.setImage(resource);
                            }
                        }
                    });
                    if (mMusicService != null) {
                        mMusicService.playMusic(mMusic);
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }
        });
    }

    @Override
    protected void initData() {
        //
        Intent intentMusic = getIntent();
        if (intentMusic.hasExtra(DATA_LIST_MUSIC_PLAYER)) {
            Bundle bundle = intentMusic.getBundleExtra(DATA_LIST_MUSIC_PLAYER);
            mPlayList = (List<Music>) bundle.getSerializable(DATA_LIST_MUSIC_PLAYER);
        }
        //启动音乐播放服务
        Intent intent = new Intent(mContext, MusicService.class);
        intent.putExtra(DATA_MUSIC_PLAYER, mMusic);
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
                pauseOrStop(true);
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

    /**
     * 改变循环按钮的状态。
     *
     * @param type 播放模式。
     */
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

    /**
     * 改变播放，暂停按钮的状态.
     * 主动点击播放，暂停按钮时，这个逻辑是可以的。但是播放完成后，按钮图片就改变不过来了。
     *
     * @param flag 判断，是否操作音乐。false只改变显示状态
     */
    private void pauseOrStop(boolean flag) {
        if (mMusicService.isPlaying()) {
            //mStartOrStop.setText("暂停");
            mStartOrStop.setBackgroundResource(R.mipmap.music_play_button);
            mCDView.pause();
        } else {
            mStartOrStop.setBackgroundResource(R.mipmap.music_pause_button);
            mCDView.start();
            //mStartOrStop.setText("开始");
        }
        if (flag)
            mMusicService.pauseMusicPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //解绑服务
        unbindService(mServiceConnection);
    }

    /**
     * 设置模糊图片背景
     *
     * @param src
     */
    public void setBackgroundBlur(Bitmap src) {
        mAllContainer.enableBlurredView();
        mAllContainer.setBlurredImg(src);
        mAllContainer.setBlurredLevel(95);
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

    @Override
    public void onEventMessage(Intent intent) {
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        switch (intent.getAction()) {
            case MusicConstants.EVENT_MUSIC_PLAY_CHANGE_STATUS:
                //判断循环方式
                changeMusic();
                break;
        }
    }

    private void changeMusic() {
        switch (mMusicService.getPlayerType()) {
            case ONE_LOOP://播放一次
                pauseOrStop(false);
                switchPlayButton();//手动改变播放按钮状态。
                break;
            case EACH_LOOP://顺序循环
                mMusicService.nextSongs();
                break;
            case SINGLE_LOOP://单曲循环
                pauseOrStop(true);
                break;
            case RANDOM_LOOP://随机播放
                mMusicService.randomSongs();
                break;
        }
    }

    private void switchPlayButton() {
        if (mMusicService.isPlaying()) {
            mStartOrStop.setBackgroundResource(R.mipmap.music_pause_button);
        } else {
            mStartOrStop.setBackgroundResource(R.mipmap.music_play_button);
        }
    }
}
