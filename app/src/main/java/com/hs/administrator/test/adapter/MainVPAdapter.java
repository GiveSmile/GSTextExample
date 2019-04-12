package com.hs.administrator.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @auther : yanbin
 * @time : 2018/7/25 0025 16:36
 * @describe :Viewpage 适配器
 */

public class MainVPAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public MainVPAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
