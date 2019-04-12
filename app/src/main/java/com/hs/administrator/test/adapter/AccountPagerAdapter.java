package com.hs.administrator.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @auther : yanbin
 * @time : 2018/4/28 10:55
 * @describe :viewpage适配器
 */
public class AccountPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentData;
    private List<String> mTitlesData;
    /**
     * 构造方法
     * @param manager
     * @param fragments
     */
    public AccountPagerAdapter(FragmentManager manager, List<Fragment> fragments, List<String> titles){
        super(manager);
        this.mFragmentData =fragments;
        this.mTitlesData=titles;
    }

    @Override
    public int getCount() {
        if (mFragmentData !=null){
            return mFragmentData.size();
        }
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragmentData !=null){
            return mFragmentData.get(position);
        }
        return null;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitlesData!=null){
            return mTitlesData.get(position);
        }
        return "";
    }
}
