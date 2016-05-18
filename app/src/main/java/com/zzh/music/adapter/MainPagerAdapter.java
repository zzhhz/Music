package com.zzh.music.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zzh.music.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZH on 16/5/17.
 *
 * @Date: 16/5/17
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> dataList;

    private Context mContext;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        dataList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((BaseFragment)getItem(position)).getTitle();
    }

    public void add(Fragment fragment){
        dataList.add(fragment);
    }

    public void clear(){
        dataList.clear();
    }
    public void delete(Fragment fragment){
        dataList.remove(fragment);
    }
    public void addAll(List<Fragment> list){
        dataList.addAll(list);
    }
}
