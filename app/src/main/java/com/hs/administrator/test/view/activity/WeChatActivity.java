package com.hs.administrator.test.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.adapter.WeChatAdapter;
import com.hs.administrator.test.model.MsgBean;
import com.hs.administrator.test.utils.random.ZData;
import com.hs.administrator.test.utils.random.ZRandom;

import java.util.ArrayList;
import java.util.List;

public class WeChatActivity extends AppCompatActivity {
    private RecyclerView mRvWeChat;
    List<MsgBean> mData = new ArrayList<>();
    private WeChatAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat);
        mRvWeChat = findViewById(R.id.mRv_WeChat);
        for (int i = 0; i < 60; i++) {
            if (i % 10 == 0) {
                mData.add(new MsgBean(2, ""));
                continue;
            }
            mData.add(new MsgBean(ZRandom.rangeInt(0, 1),
                    ZRandom.randomChar(ZData.congcong, 100)));
        }
        mAdapter = new WeChatAdapter(mData);
        mRvWeChat.setAdapter(mAdapter);
        mRvWeChat.setLayoutManager(new LinearLayoutManager(this));

    }
}
