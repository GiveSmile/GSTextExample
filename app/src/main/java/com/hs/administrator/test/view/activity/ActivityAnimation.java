package com.hs.administrator.test.view.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hs.administrator.test.R;
import com.hs.administrator.test.widget.SuperLoadingProgress;

public class ActivityAnimation extends FragmentActivity {
    SuperLoadingProgress mSuperLoadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mSuperLoadingProgress = (SuperLoadingProgress) findViewById(R.id.pro);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            mSuperLoadingProgress.setProgress(0);
                            while (mSuperLoadingProgress.getProgress() < 100) {
                                Thread.sleep(10);
                                mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + 1);
                            }
                            mSuperLoadingProgress.finishFail();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            mSuperLoadingProgress.setProgress(0);
                            while (mSuperLoadingProgress.getProgress() < 100) {
                                Thread.sleep(10);
                                mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + 1);
                            }
                            mSuperLoadingProgress.finishSuccess();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
    
}


