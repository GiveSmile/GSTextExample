package com.hs.administrator.test.widget.YBTestView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hs.administrator.test.R;
import com.hs.administrator.test.utils.MeasureUtil;

/**
 * 2018/12/12 0012.
 */

public class EraserView extends View {
    private static final int MIN_MOVE_DIS = 5;//最小的移动距离如果手指滑动的距离小于这个值就不会生效

    private Bitmap fgBitmap, bgBitmap;//橡皮擦的bitmap和背景图的bitmap


    private boolean isCompkete = false;
    private Canvas mCanvas;//绘制橡皮檫路劲的画布
    private Paint mPaint, mTextPaint;//橡皮檫路劲画布
    private Path mPath;//橡皮檫的绘制路劲
    private int screenW, screenH;//屏幕宽高
    private float preX, preY;//记录上一个触摸事件的位置坐标
    private Paint.FontMetrics mFontMetrics;// 文本测量对象


    private void init(Context context) {
        //实例化路劲对象
        mPath = new Path();

        //中奖等级的画笔
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(70);
        mTextPaint.setStrokeWidth(60);

        //遮盖图的笔


        //实例化画笔并开启他的抗锯齿和抗抖动
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        //设置画笔透明度为0让我们绘制的路劲是透明的
        mPaint.setARGB(0, 0, 0, 0);
        //设置图形混合模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        //设置画笔风格为描边
        mPaint.setStyle(Paint.Style.STROKE);

        //设置路劲结合处样式
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        //设置笔触类型
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mFontMetrics = mPaint.getFontMetrics();
        //设置描边宽度
        mPaint.setStrokeWidth(50);
        //生成前景图的bitmap
        fgBitmap = Bitmap.createBitmap(screenW, screenH, Bitmap.Config.ARGB_8888);

        //将前进图注入画布
        mCanvas = new Canvas(fgBitmap);
        //绘制画布背景图为灰色
        //    mCanvas.drawColor(0xFF808080);


        //获取背景图Bitmap


        //  mCanvas.drawRoundRect(new RectF(0,0,width,height),30,30,mPaint);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect rect = new Rect();
        String text = "一等奖";
        mTextPaint.getTextBounds(text, 0, text.length(), rect);
        //    canvas.drawBitmap(bgBitmap, 0, 0, null);
//        canvas.drawText("一等奖", screenW/2, screenH/2, mTextPaint);
        canvas.drawText(text, 0 - rect.left + (getWidth() - rect.width()) / 2, 0 - rect.top + (getHeight() - rect.height()) / 2, mTextPaint);//文字居中显示

        mCanvas.drawPath(mPath, mPaint);
        if (!isCompkete) {
            canvas.drawBitmap(fgBitmap, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://手指接触屏幕重置路劲
                mPath.reset();
                mPath.moveTo(x, y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE://手指移动时连接路劲
                float dx = Math.abs(x - preX);
                float dy = Math.abs(y - preY);
                if (dx >= MIN_MOVE_DIS || dy >= MIN_MOVE_DIS) {
                    mPath.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2);
                    preX = x;
                    preY = y;
                }
                break;
            case MotionEvent.ACTION_UP:
                new Thread(runnable).start();
                break;
        }

        invalidate();
        return true;
    }

    private void cal(Context context) {
        //获取屏幕尺寸数组
        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);
        //获取屏幕宽高
        screenW = screenSize[0];
        screenH = screenSize[1];
    }

    public EraserView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        cal(context);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        bgBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.testtoux);
        //  bgBitmap = Bitmap.createBitmap(screenW,screenH,R.mipmap.gudianmeinv,Bitmap.Config.ARGB_8888);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //缩放被进图Bitmap之屏幕大小
//        bgBitmap = Bitmap.createScaledBitmap(bgBitmap, screenW, screenH, true);
        mCanvas.drawBitmap(bgBitmap, null, new Rect(0, 0, width, height),
                null);//在刚刚画的圆角矩形上面再画一个bitmap图片，让图片大小和圆角矩形大小相关联
    }

    private int[] mPixels;


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int w = getWidth();
            int h = getHeight();
            float wipeArea = 0;
            float totalArea = w * h;

            Bitmap bitmap = fgBitmap;

            mPixels = new int[w * h];

            bitmap.getPixels(mPixels, 0, w, 0, 0, w, h);

            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int index = i + j * w;
                    if (mPixels[index] == 0) {
                        wipeArea++;
                    }
                }
            }
            if (wipeArea > 0 && totalArea > 0) {
                int percent = (int) (wipeArea * 100 / totalArea);


                if (percent > 30) {
                    isCompkete = true;
                    postInvalidate();
                }
            }

        }
    };

}
