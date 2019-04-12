package com.hs.administrator.test.widget;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * @auther : yanbin
 * @time : 2018/9/10 0010 18:32
 * @describe :
 */

public class zhuzhuangView extends View {
    private Paint mPaint;
    public zhuzhuangView(Context context) {
        super(context);
    }

    public zhuzhuangView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public zhuzhuangView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public zhuzhuangView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private void init(){

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
       /* mRectCount = 40;   //设置条形图个数
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int) (mWidth*0.9/mRectCount);//设置每个条形图宽度
        offset = (int) (mWidth*0.1/mRectCount);//设置相邻两个条形图之间的间距
        mLinearGradient = new LinearGradient(0,0,mRectWidth,mRectHeight,Color.YELLOW,Color.BLUE, Shader.TileMode.MIRROR);
        mPaint.setShader(mLinearGradient);*/
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidthAndroidHeight(widthMeasureSpec);
        int height = measureWidthAndroidHeight(heightMeasureSpec);
        setMeasuredDimension(width,height);    }

    private int measureWidthAndroidHeight(int inputMeasureSpec){
        int result = 0;
        int mode = MeasureSpec.getMode(inputMeasureSpec);
        int size = MeasureSpec.getSize(inputMeasureSpec);
        if (mode == MeasureSpec.EXACTLY){
            result = size;
        }else {
            result = 200;
            if (mode == MeasureSpec.AT_MOST){
                result = Math.min(result,size);
            }
        }
        return result;
    }
}
