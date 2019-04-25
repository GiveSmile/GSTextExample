package com.hs.administrator.test.view.activity;

import android.app.AlertDialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hs.administrator.test.R;
import com.hs.administrator.test.utils.DialogManage;
import com.hs.administrator.test.widget.SuperLoadingProgress;

public class ActivityAnimation extends FragmentActivity {
    SuperLoadingProgress mSuperLoadingProgress;
    private AlertDialog mExitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mSuperLoadingProgress = (SuperLoadingProgress) findViewById(R.id.pro);

        findViewById(R.id.but3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialog();
            }
        });
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

    private void initDialog() {
        mExitDialog = DialogManage.TestDialog(this, "", "内容", "左边按钮", "右边按钮", new DialogManage.onCallBack() {
            @Override
            public void onLeftBut() {
                Toast.makeText(ActivityAnimation.this, "我点击了LeftBut", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRightBut() {
                Toast.makeText(ActivityAnimation.this, "我点击了right", Toast.LENGTH_SHORT).show();
            }
        });
    }

}


