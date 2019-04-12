package com.hs.administrator.test.widget;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * @auther : yanbin
 * @time : 2018/5/31 0031 17:42
 * @describe :设置edittext输入范围
 */

public class InoutFilterMinMax implements InputFilter {
    private int min,max;
    public InoutFilterMinMax(int min,int max){
        this.min = min;
        this.max = max;
    }
    public InoutFilterMinMax(String min,String max){
        this.max = Integer.parseInt(max);
        this.min = Integer.parseInt(min);
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (Exception nfe) { }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
