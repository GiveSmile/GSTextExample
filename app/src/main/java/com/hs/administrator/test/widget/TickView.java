package com.hs.administrator.test.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hs.administrator.test.R;

import static momo.cn.edu.fjnu.androidutils.utils.SizeUtils.dp2px;


/**
 * @auther : yanbin
 * @time : 2018/8/15 0015 11:04
 * @describe :
 */

public class TickView extends View {
    private Context context;
    //设置打钩的画笔
    private Paint mPaintRing;
    //控件中心的xy坐标
    private int centerX;
    private int centerY;
    private float[] mPoints = new float[8];
    private float tickRadius;

    public TickView(Context context) {
        super(context);
    }

    public TickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CheckView);
             a.recycle();
        tickRadius = dp2px(context, 12);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
