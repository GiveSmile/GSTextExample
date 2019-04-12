package com.hs.administrator.test.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hs.administrator.test.R;

/**
 * 2018/11/27 0027.
 */

public class TwoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two,null);
        Log.d("twoFragment","onCreateView");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("twoFragment","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("twoFragment","onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("twoFragment","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("twoFragment","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("twoFragment","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("twoFragment","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("twoFragment","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("twoFragment","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("twoFragment","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("twoFragment","onDestroy");
    }
}
