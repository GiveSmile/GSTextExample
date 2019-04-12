package com.hs.administrator.test.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @auther : yanbin
 * @time : 2018/7/20 0020 15:18
 * @describe :自定义点击弹出多个小布局
 */

public class CircleList extends ViewGroup {
    public CircleList(Context context) {
        super(context);
    }

    public CircleList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleList(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    }
}
