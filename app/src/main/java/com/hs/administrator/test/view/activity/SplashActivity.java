package com.hs.administrator.test.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.hs.administrator.test.R;

import butterknife.Bind;

/**
 * 函数名：启动页Activity
 */

public class SplashActivity extends AppCompatActivity {

    private ImageView mIvSplash;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {


            startActivity(new Intent(SplashActivity.this, MainActivity.class));

            finish();

        }
    };



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);
        handler.sendEmptyMessageDelayed(0, 1000);

    }


}
