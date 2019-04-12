package com.hs.administrator.test.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.utils.MyDialog;
import com.hs.administrator.test.utils.MyProgressDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DialogActivity extends AppCompatActivity {

    @Bind(R.id.but_bounce)
    Button mButBounce;//弹跳的动画
    @Bind(R.id.but_rotate)
    Button mButRotate;//旋转的动画
    @Bind(R.id.but_one)
    Button mButOne;
    @Bind(R.id.but_two)
    Button mButTwo;
    @Bind(R.id.but_three)
    Button mButThree;
    @Bind(R.id.but_four)
    Button mButFour;
    @Bind(R.id.tv_one)
    TextView mTvOne;
    @Bind(R.id.tv_two)
    TextView mTvTwo;
    @Bind(R.id.tv_three)
    TextView mTvThree;
    @Bind(R.id.tv_four)
    TextView mTvFour;

    int a = 1;
    int b = 1;
    int c = 10;
    int d = 10;


    public final static int REQUEST_CODE_FOR_REGISTER = 3;
    /**
     * 打钩百分比
     *
     * @param context
     */
    float tickPrecent = 0;
    private Paint tickPaint = new Paint();
    /**
     * 测量打钩
     */
    private PathMeasure tickPathMeasure;
    private ValueAnimator mTickAnimation;
    /**
     * 画笔宽度
     */
    private int strokeWidth = 20;
    /**
     *
     */
    private float radius = 0;

    private RectF mRectF = new RectF();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        tickPaint.setColor(Color.argb(255, 0, 150, 136));
        tickPaint.setAntiAlias(true);
        tickPaint.setStrokeWidth(strokeWidth);
        tickPaint.setStyle(Paint.Style.STROKE);
        mButBounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(DialogActivity.this, huidiaojiemianActivity.class);
                startActivityForResult(intent, 1);
             /*   mTickAnimation = ValueAnimator.ofFloat(0f, 1f);
                mTickAnimation.setDuration(500);
                mTickAnimation.setInterpolator(new AccelerateInterpolator());
                mTickAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        tickPrecent = (float) animation.getAnimatedValue();
                        //invalidate();
                    }
                });

                mTickAnimation.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        // status = 4;
                    }
                });
                Path tickPath = new Path();
                tickPath.moveTo(1.5f * radius+strokeWidth, 2 * radius+strokeWidth);
                tickPath.lineTo(1.5f * radius + 0.3f * radius+strokeWidth, 2 * radius + 0.3f * radius+strokeWidth);
                tickPath.lineTo(2*radius+0.5f * radius+strokeWidth,2*radius-0.3f * radius+strokeWidth);
                tickPathMeasure = new PathMeasure(tickPath,false);

                Path path = new Path();
        *//*
         * On KITKAT and earlier releases, the resulting path may not display on a hardware-accelerated Canvas.
         * A simple workaround is to add a single operation to this path, such as dst.rLineTo(0, 0).
         *//*
                tickPathMeasure.getSegment(0, tickPrecent * tickPathMeasure.getLength(), path, true);
                path.rLineTo(0, 0);
              *//*  canvas.drawPath(path, tickPaint);
                canvas.drawArc(mRectF, 0, 360, false, tickPaint);*/
            }
        });


        mButRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MyProgressDialog myProgressDialog = new MyProgressDialog(DialogActivity.this);//=============上下文
                myProgressDialog.show();
                myProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myProgressDialog.dismiss();
                    }
                }, 15000);//================动画的时间
            }
        });

        initTest();
    }

    private void initTest() {
        mButOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a++;
                mTvOne.setText("I的初始值为：1    "+"现在的值为"+ a);
            }
        });
        mButTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++b;
                mTvTwo.setText("I的初始值为：1    "+"现在的值为"+ b);
            }
        });
        mButThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c--;
                mTvThree.setText("I的初始值为：10    "+"现在的值为"+ c);

            }
        });
        mButFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --d;
                mTvFour.setText("I的初始值为：10    "+"现在的值为"+ d);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if (resultCode == 10) {
            mButBounce.setText(data.getStringExtra("hahaha"));
        } else if (resultCode == 10) {
            mButBounce.setText(data.getStringExtra("hahaha"));
        }
    }
}
