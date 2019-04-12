package com.hs.administrator.test.widget.YBTestView;

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
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hs.administrator.test.R;

/**
 * 2018/12/13 0013.
 */

public class GuaTwo extends View {
    private Path mPath;//手刮动的path，过程
    private Paint mOutterPaint;//绘制mPath的画笔
    private Canvas mCanvas;
    private Bitmap mBitmap;

    //记录用户path每次的开始坐标值
    private int mLastX;
    private int mLastY;

    private Bitmap mOutterBitmap;//图片遮罩，就是手刮动，要擦掉的那张图
    private String mText;//刮奖文本信息
    private Rect mTextBound;
    private Paint mBackPaint;//刮奖信息的画笔

    public GuaTwo(Context context) {
        this(context, null);
    }

    public GuaTwo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuaTwo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获得控件的宽高
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //初始化bitmap
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);//用指定的位图构造一个画布来绘制。

        //设置画笔属性
        setupOutPaint();
        setUpBackPaint();

//        mCanvas.drawColor(Color.parseColor("#c0c0c0"));
        mCanvas.drawRoundRect(new RectF(0, 0, width, height), 30, 30,
                mOutterPaint);//用mOutterPaint画圆角矩形
        mCanvas.drawBitmap(mOutterBitmap, null, new Rect(0, 0, width, height),
                null);//在刚刚画的圆角矩形上面再画一个bitmap图片，让图片大小和圆角矩形大小相关联
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mOutterPaint.setStyle(Paint.Style.STROKE);
        mOutterPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));//Mode.DST_OUT改模式就类似橡皮檫，这个属性设置是关键
        canvas.drawText(mText, (getWidth() - mTextBound.width()) / 2, getHeight() / 2 - mTextBound.height() / 2, mBackPaint);//把获奖信息放在正中间
        canvas.drawBitmap(mBitmap, 0, 0, null);
        mCanvas.drawPath(mPath, mOutterPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN://按下
                //记录按下的时候的X和Y值，以便于之后移动的时候绘制
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE://移动
                //拿到用户移动的X绝对值，Y轴绝对值
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);
                //用户滑动超过3像素才会改变，这个可以不做，做只是为了避免很频繁的相应而已。
                if (dx > 3 || dy > 3) {
                    mPath.lineTo(x, y);
                }
                mLastX = x;
                mLastY = y;
                break;
        }
        invalidate();//刷新UI
        return true;
    }

    private void setUpBackPaint() {
        mBackPaint.setColor(Color.RED);
        mBackPaint.setStyle(Paint.Style.FILL);
        mBackPaint.setTextSize(60);
        //获得当前画笔绘制文本的宽和高
        mBackPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
    }

    /**
     * 绘制path（也就是手刮动的path来绘制） 的画笔属性
     * 类似橡皮擦
     */
    private void setupOutPaint() {
        mOutterPaint.setColor(Color.RED);
        mOutterPaint.setAntiAlias(true);
        mOutterPaint.setDither(true);
        mOutterPaint.setStrokeJoin(Paint.Join.ROUND);//设置圆角
        mOutterPaint.setStrokeCap(Paint.Cap.ROUND);
        mOutterPaint.setStyle(Paint.Style.FILL);
        mOutterPaint.setStrokeWidth(60);//设置画笔宽度
    }

    /**
     * 初始化信息
     */
    private void init() {
        mOutterPaint = new Paint();
        mPath = new Path();
        mOutterBitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.gudianmeinv);
        mText = "谢谢惠顾！";
        mTextBound = new Rect();
        mBackPaint = new Paint();
    }
}

