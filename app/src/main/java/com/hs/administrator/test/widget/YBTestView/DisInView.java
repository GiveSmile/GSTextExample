package com.hs.administrator.test.widget.YBTestView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hs.administrator.test.R;
import com.hs.administrator.test.utils.MeasureUtil;

/**
 * 2018/12/12 0012.
 */

public class DisInView extends View {
    private Paint paint;
    private Bitmap bitmap,bitmapSrc;

    private PorterDuffXfermode porterDuffXfermode;//图形混合模式

    private int X, Y;//绘制图形时候的起点坐标

    private int screenW, screenH;//屏幕尺寸

    private Context context;

    public DisInView(Context context) {
        super(context);
    }

    public DisInView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);

        initPaint();

        initRes(context);
    }

    private void initRes(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.gudianmeinv);

        bitmapSrc = BitmapFactory.decodeResource(context.getResources(),R.mipmap.lunkuotu);

        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);
        screenH = screenSize[1];
        screenW = screenSize[0];
        X = screenW / 2 - bitmap.getWidth() / 2;
        Y = screenH / 2 - bitmap.getHeight() / 2;
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        int sc = canvas.saveLayer(0,0,screenW,screenH,null,Canvas.ALL_SAVE_FLAG);

//        canvas.drawBitmap(bitmap,X,Y,paint);
        canvas.drawColor(Color.RED);
        paint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(bitmap,X,Y,paint);
        paint.setXfermode(null);
        canvas.restoreToCount(sc);
    }
}
