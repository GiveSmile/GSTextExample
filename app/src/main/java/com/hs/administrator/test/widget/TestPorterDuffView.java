package com.hs.administrator.test.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hs.administrator.test.utils.MeasureUtil;

/**
 * 2018/12/12 0012.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class TestPorterDuffView extends View {

    /*
    * porterDuff模式常量
    * 可以在此更改不同的模式测试
    * */

    private static final PorterDuff.Mode MODE = PorterDuff.Mode.ADD;

    private static final int RET_SIZE_SMALL = 400;//左右上方示例渐变正方形大小
    private static final int RECT_SIZE_BIG = 800;//中间测试渐变正方形尺寸

    private Paint paint;//画笔

    private PorterDuff porterDuff;//porterduffView类的业务对象

    private PorterDuffXfermode porterDuffXfermode;//图像混合模式

    private int screenw,screenh;//屏幕尺寸;

    private int s_l,s_t;//左上方正方形的原点坐标

    private int d_l,d_t;//右上方正方形的原点坐标

    private int rectx,rexty;//中间正方形的原点坐标



    public TestPorterDuffView(Context context) {
        super(context);
    }

    public TestPorterDuffView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        porterDuff = new PorterDuff();

        porterDuffXfermode = new PorterDuffXfermode(MODE);

        calu(context);

    }

    private void calu(Context context) {
        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);
        //获取屏幕尺寸
        screenw = screenSize[0];
        screenh = screenSize[1];

        //计算左上方正方形原点坐标
        s_l = 0;
        s_t = 0;

        //计算右上方正方形原点坐标

        d_l = screenw -RET_SIZE_SMALL;
        d_t = 0;

        //计算中间正方形原点坐标
        rectx = screenw/2 - RECT_SIZE_BIG/2;
        rexty = RET_SIZE_SMALL +(screenh - RET_SIZE_SMALL)/2-RECT_SIZE_BIG/2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画布颜色为黑色以便我们更好地观察
        canvas.drawColor(Color.BLACK);
        //设置业务对象尺寸值计算生成左右上方的渐变方形
      //ddddddddddddddddddddddddddd  porterDuff.
    }
}
