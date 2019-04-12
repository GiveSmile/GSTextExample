package com.hs.administrator.test.widget.YBTestView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hs.administrator.test.R;
import com.hs.administrator.test.utils.MeasureUtil;

/**
 * 2018/12/11 0011.
 */

public class YBView extends View implements Runnable {
    private Paint mPaint;
    private Context context;
    private int radiu;//圆的半径
    private Bitmap bitmap;//位图
    private int x, y;//位图绘制的起点坐标
    private boolean isClick;//用来标识控件是否被点击过

    public YBView(Context context) {
        super(context);
    }

    public YBView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
        initRes(context);
      /*  setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClick) {
                    mPaint.setColorFilter(null);
                    isClick = false;
                } else {
                    isClick = true;
                    mPaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x000000000));
                }
                invalidate();
            }
        });*/
    }

    public YBView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public YBView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /*
        * 设置画笔样式为描边
        *
        * 画笔三种样式：
        * 1:paint.Style.STROKE 描边
        * 2:paint.style.fill_and_stroke 描边并填充
        * 3:paint.style.fill 填充
        * */
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStrokeWidth(20);
        //mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));
   /*     ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0, 0, 0, 1, 0
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();


        x = 0 - rect.left + (getWidth() - rect.width()) / 2;
        y = 0 - rect.top + (getHeight() - rect.height()) / 2;
        canvas.drawCircle(x, y, radiu, mPaint);
        //  canvas.drawBitmap(bitmap, x, y, mPaint);


    }

    private void initRes(Context context) {

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.testimage);

    }

    public synchronized void setRadiu(int radiu) {
        this.radiu = radiu;
        invalidate();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (radiu <= 200) {
                    radiu += 20;
                    postInvalidate();
                    if (isClick) {
                        mPaint.setColor(Color.RED);
                        isClick = false;
                    } else {
                        mPaint.setColor(Color.BLACK);

                        isClick = true;
                    }
                } else {
                    radiu = 0;
                }
                Thread.sleep(500);
            } catch (Exception e) {
                Log.d("YBView:", e.toString());
            }
        }
    }
}
