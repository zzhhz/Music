package com.zzh.music;

import android.animation.Animator;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import com.zzh.music.activity.ContactActivity;
import com.zzh.music.adapter.MainPagerAdapter;
import com.zzh.music.base.BaseMusicNoSwipeActivity;
import com.zzh.music.fragment.AlbumFragment;
import com.zzh.music.fragment.HomeFragment;
import com.zzh.music.fragment.RecommendFragment;
import com.zzh.music.utils.ColorUtils;
import com.zzh.music.widget.ZZHDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMusicNoSwipeActivity implements ViewPager.OnPageChangeListener{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MainPagerAdapter mAdapter;

    //抽屉
    private DrawerLayout mDrawerLayout;
    //抽屉菜单
    private NavigationView mNavigationView;
    //推荐
    private RecommendFragment mRecommendFragment;
    private AlbumFragment mAlbumFragment;
    private HomeFragment mHomeFragment;
    //侧滑菜单的头布局
    private RelativeLayout mHeaderViewLayout;
    private AppBarLayout appBarLayout;
    private int mAppBarLayoutBgColor = -1;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.menuView);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        //去掉Toolbar的标题
        toolbars(R.id.toolbar, -1, "音乐", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        //抽屉
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(toggle);
        mHeaderViewLayout = (RelativeLayout) mNavigationView.getHeaderView(0);
    }

    @Override
    protected void initData() {
        mRecommendFragment = new RecommendFragment();
        mAlbumFragment = new AlbumFragment();
        mHomeFragment = new HomeFragment();
        mAdapter.add(mRecommendFragment);
        mAdapter.add(mHomeFragment);
        mAdapter.add(mAlbumFragment);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //默认是第2个选中
        mViewPager.setCurrentItem(1);
    }

    @Override
    protected void initSetListener() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(mNavigationView)) {
                    mDrawerLayout.closeDrawer(mNavigationView);
                } else {
                    mDrawerLayout.openDrawer(mNavigationView);
                }
            }
        });
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_library:
                        ZZHDialog dialog = new ZZHDialog();
                        dialog.show(getFragmentManager(), "zzh");
                        break;
                    case R.id.navigation_original:
                        Intent intentContact = new Intent(mContext, ContactActivity.class);
                        startActivity(intentContact);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        mViewPager.addOnPageChangeListener(this);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                float x = 0;
                float y = 0;
                appBarLayout.setBackgroundColor(mAppBarLayoutBgColor);
                final int width = mTabLayout.getWidth();
                final int height = mTabLayout.getHeight();
                final double radio = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(appBarLayout,
                        (int) x,
                        (int) y, 0, (float) radio);
                circularReveal.setInterpolator(new AccelerateInterpolator());
                circularReveal.setDuration(375);
                circularReveal.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mAppBarLayoutBgColor = Color.parseColor(ColorUtils.random());
                        appBarLayout.setBackgroundColor(mAppBarLayoutBgColor);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        appBarLayout.setDrawingCacheBackgroundColor(mAppBarLayoutBgColor);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                circularReveal.start();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        mToolbar.getMenu().getItem(0).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (hasFocus){
            Log.e("", "onGlobalLayout: 4-------------hui zhi wan cheng -------"+mViewPager.getHeight());
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
