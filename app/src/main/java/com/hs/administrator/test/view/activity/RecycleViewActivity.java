package com.hs.administrator.test.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hs.administrator.test.R;
import com.hs.administrator.test.adapter.RecycleViewAdapter;
import com.hs.administrator.test.security.Constructors;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private RecycleViewAdapter mAdapter;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        mRv = findViewById(R.id.mRv);
        mData.add("仿微信聊天界面布局");
        mData.add("仿淘宝布局");
        mAdapter = new RecycleViewAdapter(mData);
        mRv.setAdapter(mAdapter);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setContext(new RecycleViewAdapter.ItemCheckListener() {
            @Override
            public void OnItemClick(String conten, int i) {
                switch (conten) {
                    case "仿QQ布局":
                        break;
                    case "仿微信聊天界面布局":
                        startActivity(new Intent(RecycleViewActivity.this,WeChatActivity.class));
                        break;
                    case "仿淘宝布局":
                        startActivity(new Intent(RecycleViewActivity.this,GoodsActivity.class));
                        break;
                }
            }
        });
    }
}
