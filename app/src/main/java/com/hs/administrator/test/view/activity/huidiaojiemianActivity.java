package com.hs.administrator.test.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hs.administrator.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class huidiaojiemianActivity extends AppCompatActivity {

    @Bind(R.id.tv_huhansan)
    TextView mTvHuhansna;
    @Bind(R.id.tv_huitailang)
    TextView mTvHuitailang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_huidiaojiemian);
        mTvHuhansna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(huidiaojiemianActivity.this,DialogActivity.class).putExtra("hahaha","我胡汉三回来了");
                setResult(10,intent);
                finish();
            }
        });
        mTvHuitailang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(huidiaojiemianActivity.this,DialogActivity.class).putExtra("hahaha","我灰太狼回来了");
                setResult(20,intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
