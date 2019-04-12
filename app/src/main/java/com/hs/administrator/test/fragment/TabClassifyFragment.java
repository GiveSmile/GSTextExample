package com.hs.administrator.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hs.administrator.test.R;

/**
 * @auther : yanbin
 * @time : 2018/7/10 0010 15:17
 * @describe :
 */

public class TabClassifyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify,container);
        return view;
    }


}
