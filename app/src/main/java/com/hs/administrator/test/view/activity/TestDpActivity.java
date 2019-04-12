package com.hs.administrator.test.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.utils.DensityUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestDpActivity extends AppCompatActivity {
    @Bind(R.id.dp)
    EditText dp;//dp转px输入的值
    @Bind(R.id.dpzhuanpx)
    TextView mTvDpzhuangpx;//dp转px输入的按钮
    @Bind(R.id.tv_px)
    TextView mTvPx;//dp转px获得的px得值
    @Bind(R.id.px)
    EditText dx;//
    @Bind(R.id.tv_pxzhuangdp)
    TextView mTvPxzhuangDp;//dp转px输入的按钮
    @Bind(R.id.tv_dp)
    TextView mTvDp;//dp转px获得的px得值


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dp);
        ButterKnife.bind(this);
        setData();
    }

    private void setData() {
        final String mDP = dp.getText().toString();
        final String mPX = dx.getText().toString();
        mTvDpzhuangpx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int px = DensityUtil.px2dip(TestDpActivity.this, 300);
                Log.d("test==",px+"");
                int px2 = DensityUtil.px2dip(TestDpActivity.this, 220);
                Log.d("test==",px2+"");
            }
        });
        mTvPxzhuangDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvDp.setText(DensityUtil.px2dip(TestDpActivity.this, 268));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
