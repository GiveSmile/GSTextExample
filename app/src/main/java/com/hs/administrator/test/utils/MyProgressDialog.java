package com.hs.administrator.test.utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.widget.CircleProgress;

/**
 * @auther : yanbin
 * @time : 2018/8/30 0030 16:34
 * @describe :旋转动画dialog
 */

public class MyProgressDialog extends ProgressDialog {

    protected CircleProgress mProgress;
    protected TextView mLoadingTv;
    //private AnimationDrawable mAnimation;

    public MyProgressDialog(Context context) {
        super(context);
        //点击提示框外面是否取消提示框
        setCanceledOnTouchOutside(false);
        //点击返回键是否取消提示框
        setCancelable(false);
        setIndeterminate(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_rotate);
        initView();
        //弹出dialog
        mProgress.post(new Runnable() {
            @Override
            public void run() {
                mProgress.startAnim();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getOwnerActivity().getMenuInflater().inflate(R.menu.menu_rotate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void initView() {
        mProgress = (CircleProgress)findViewById(R.id.progress);
        mLoadingTv = (TextView)findViewById(R.id.loadingTv);
    }
}
