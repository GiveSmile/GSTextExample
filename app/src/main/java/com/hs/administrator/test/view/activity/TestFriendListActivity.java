package com.hs.administrator.test.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.widget.LetterView;

public class TestFriendListActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private String[] contactNames;
    private LinearLayoutManager layoutManager;
    private LetterView mLerrerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_friend_list);
    }
}
