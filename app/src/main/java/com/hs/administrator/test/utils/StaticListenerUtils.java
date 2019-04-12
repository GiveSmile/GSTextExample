package com.hs.administrator.test.utils;

import android.view.View;

/**
 * @auther : yanbin
 * @time : 2018/9/11 0011 11:46
 * @describe :防止recycleView连点两次
 */

public abstract class StaticListenerUtils implements View.OnClickListener {
    private static long lastTimeMillis;
    private static final long MIN_CLICK_INTERVAL = 1000;

    protected boolean isTimeEnabled() {
        long currentTimeMillis = System.currentTimeMillis();
        if ((currentTimeMillis - lastTimeMillis) > MIN_CLICK_INTERVAL) {
            lastTimeMillis = currentTimeMillis;
            return true;
        }
        return false;
    }
}
