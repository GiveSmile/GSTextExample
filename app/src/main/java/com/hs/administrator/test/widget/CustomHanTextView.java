package com.hs.administrator.test.widget;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @auther : yanbin
 * @time : 2018/7/24 0024 10:48
 * @describe :
 */

public class CustomHanTextView extends TextView {
    public CustomHanTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    /**
     * 初始化字体
     * @param context
     */
    private void init(Context context) {
        //设置字体样式
        setTypeface(FontCache.setFont(context));
    }
}
