package com.hs.administrator.test.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hs.administrator.test.R;
import com.hs.administrator.test.model.StackBean;
import com.hs.administrator.test.utils.StackAdapter;
import com.hs.administrator.test.utils.StackLayout;
import com.hs.administrator.test.utils.StackOneAdapter;
import com.hs.administrator.test.widget.SendVoiceNewView;

import java.util.ArrayList;
import java.util.List;

public class ArActivity extends AppCompatActivity {

    private StackLayout mRecyclerView, mSlOne, mSlTwo, mSlThree;
    private SendVoiceNewView mSendVoiceNewView;
    List<StackBean> mData = new ArrayList<>();
    List<StackBean> mOneData = new ArrayList<>();
    private StackAdapter mAdapter;
    private StackOneAdapter mOneAdapter;
    //    private CribAdapter mAdapter;
    //    private D
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        mSendVoiceNewView = findViewById(R.id.mSendVoiceNewView);
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mSlOne = findViewById(R.id.mSlTwo);
        mSlTwo = findViewById(R.id.mSlOne);
        mSlThree = findViewById(R.id.mSlthree);
        if (mSendVoiceNewView != null) {
            mSendVoiceNewView.onStart();
        }
        mAdapter = new StackAdapter(this);
        mOneAdapter = new StackOneAdapter(this);
        for (int i=0;i<5;i++){
            mData.add(new StackBean(2, "我是第"+i+"", 1));
            mOneData.add(new StackBean(2, "我是第"+i+"", 1));
        }


        mAdapter.setData(mData);
        mOneAdapter.setData(mOneData);

        mRecyclerView.setAdapter(mAdapter, mData.size());
        mSlOne.setAdapter(mOneAdapter, mOneData.size());
        mSlTwo.setAdapter(mAdapter, mData.size());
        mSlThree.setAdapter(mAdapter, mData.size());
        mOneAdapter.setVideoBackCallBack(new StackOneAdapter.OnParticularsClickListener() {
            @Override
            public void onClickListener(int position) {
                for (int i = 0; i < mOneData.size(); i++) {
                    if (position == i && mOneData.get(i).getType() !=1) {
                        mOneData.get(i).setType(1);
                    } else {
                        mOneData.get(i).setType(i+6);
                    }
                }
                for (int j = 0;j<mData.size();j++){
                    mData.get(j).setType(j+6);
                }
                Log.d("StackAdapter",position+"");
                mAdapter.notifyDataSetChanged();
                mOneAdapter.notifyDataSetChanged();
            }
        });
        mAdapter.setVideoBackCallBack(new StackAdapter.OnParticularsClickListener() {
            @Override
            public void onClickListener(int position) {
                for (int i = 0; i < mData.size(); i++) {
                    if (position == i && mData.get(i).getType() !=1) {
                        mData.get(i).setType(1);
                    } else {
                        mData.get(i).setType(i+6);
                    }
                }
                for (int j = 0;j<mOneData.size();j++){
                    mOneData.get(j).setType(j+6);
                }
                Log.d("StackAdapter",position+"");
                mAdapter.notifyDataSetChanged();
                mOneAdapter.notifyDataSetChanged();
            }
        });
    }
}
