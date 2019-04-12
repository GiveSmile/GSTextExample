package com.hs.administrator.test.utils;

import android.animation.TimeInterpolator;

/**
 * @auther : yanbin
 * @time : 2018/8/30 0030 16:24
 * @describe :弹跳动画dialog
 */

public class EaseInOutCubicInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        if ((input *= 2) < 1.0f) {
            return 0.5f * input * input * input;
        }
        input -= 2;
        return 0.5f * input * input * input + 1;
    }
}