package com.hs.administrator.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<String> list;
    private List<Fragment> fragments;

    public MyPagerAdapter(FragmentManager fm, List<String> list, List<Fragment> fragments) {
        super(fm);
        this.list = list;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
