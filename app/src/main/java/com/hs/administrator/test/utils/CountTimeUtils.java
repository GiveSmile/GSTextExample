package com.hs.administrator.test.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;

/**
 * @auther : lwb
 * @time : 2018/9/4 9:48
 * @describe : 倒计时工具类
 */
public abstract class CountTimeUtils {

    private final long mMillisInFuture;

    private long mStopTimeInFuture;

    private View view;

    private boolean mCancelled = false;


    public CountTimeUtils(View mView , long secondInFuture) {
        mMillisInFuture = secondInFuture;
        view = mView;
    }

    /**
     * 取消倒计时
     */
    public synchronized final void cancel() {
        mCancelled = true;
        mHandler.removeMessages(MSG);
    }

    /**
     * 开始倒计时
     */
    public synchronized final CountTimeUtils start() {
        mCancelled = false;
        if (mMillisInFuture <= 0) {
            onFinish();
            return this;
        }
        mStopTimeInFuture = (SystemClock.elapsedRealtime()/1000) + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        return this;
    }


    /**
     * 倒计时中回调函数
     *
     */
    public abstract void onTick(View mTvMsg, long millisUntilFinished);

    /**
     * 倒计时完成回调函数
     */
    public abstract void onFinish();

    private static final int MSG = 1;

    // handles counting down
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            synchronized (CountTimeUtils.this) {
                if (mCancelled) {
                    return;
                }
                final long millisLeft = mStopTimeInFuture - (SystemClock.elapsedRealtime()/1000);
                if (millisLeft < 0) {
                    onFinish();
                } else {
                    onTick(view,millisLeft);
                    sendMessageDelayed(obtainMessage(MSG), 1000);
                }
            }
        }
    };
}

