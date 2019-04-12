package com.hs.administrator.test.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hs.administrator.test.R;

/**
 * @auther : yanbin
 * @time : 2018/8/15 0015 11:37
 * @describe :
 */

public class LoadingAnimatorView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder holder;
    private Bitmap bitmap;
    private Paint paint;
    private Paint paint1;
    public boolean flag = true;
    private int y = 100;

    public LoadingAnimatorView(Context context) {
        super(context);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        holder = this.getHolder();
        holder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.RED);

        paint1 = new Paint();
        paint1.setColor(Color.GRAY);

        @SuppressLint("ResourceType") Bitmap bitmap1 = BitmapFactory.decodeStream(context.getResources()
                .openRawResource(R.mipmap.chongdian));
        bitmap = bitmap1.extractAlpha();//获取一个透明图片
        y = bitmap.getWidth();//初始化坐标


    }

    //改变裁剪区域
    private void playAnimator() {
        if (y > 0) {
            y -= 3;
        }
    }

    private void drawLoadingAnimator() {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawBitmap(bitmap, 100, 100, null);
                canvas.drawColor(Color.GREEN);
                canvas.drawBitmap(bitmap, 100, 100, paint1);
                canvas.save();
                //裁剪
                canvas.clipRect(100, y + 100, bitmap.getWidth() + 100,
                        bitmap.getHeight() + 100);
                canvas.drawBitmap(bitmap, 100, 100, paint);
                canvas.restore();
            }
            /*
             * Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
             * Rect dst = new Rect(100, 100, bitmap.getWidth()+100, y+100);
             * canvas.drawBitmap(bitmap, src, dst, paint2);
             */
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (holder != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public LoadingAnimatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingAnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LoadingAnimatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        new Thread(this).start();//开启绘制线程
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void run() {
        while (flag) {
            drawLoadingAnimator();
            playAnimator();
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
