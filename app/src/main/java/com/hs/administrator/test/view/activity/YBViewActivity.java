package com.hs.administrator.test.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hs.administrator.test.R;
import com.hs.administrator.test.widget.YBTestView.DisInView;
import com.hs.administrator.test.widget.YBTestView.EraserView;
import com.hs.administrator.test.widget.YBTestView.YBView;

public class YBViewActivity extends AppCompatActivity {
    private YBView mView;
    private EraserView TestPorterDuffView;
    private int radiu;//半径值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ybview);
        mView =findViewById(R.id.YBview);
       // TestPorterDuffView = findViewById(R.id.TestPorterDuffView);
        new Thread(mView).start();
    }

}
