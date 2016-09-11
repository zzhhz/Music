package com.zzh.music;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.zzh.music.activity.ContactActivity;
import com.zzh.music.adapter.MainPagerAdapter;
import com.zzh.music.base.BaseNoSwipeBackActivity;
import com.zzh.music.fragment.AlbumFragment;
import com.zzh.music.fragment.RecommendFragment;
import com.zzh.music.widget.ZZHDialog;

public class MainActivity extends BaseNoSwipeBackActivity {
    private Toolbar mToolbar;
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
    //侧滑菜单的头布局
    private RelativeLayout mHeaderViewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.menuView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //去掉Toolbar的标题
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        //初始化Toolbar
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        //抽屉
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(toggle);
        mHeaderViewLayout = (RelativeLayout) mNavigationView.getHeaderView(0);
    }

    @Override
    protected void initData() {
        mRecommendFragment = new RecommendFragment();
        mAlbumFragment = new AlbumFragment();
        mAdapter.add(mRecommendFragment);
        mAdapter.add(mAlbumFragment);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initSetListener() {

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showMessage("-------------");
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
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }
}
