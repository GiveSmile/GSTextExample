package com.hs.administrator.test.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.model.TestRBBean;
import com.hs.administrator.test.utils.ButtonUtils;
import com.hs.hstechsdklibrary.commonutil.LogUtil;
import com.hs.hstechsdklibrary.widget.MyOneLineView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @Bind(R.id.tv_gson)
    TextView mTvGson;
    @Bind(R.id.rg)
    RadioGroup mTvMyGson;
    @Bind(R.id.tv_gson_view)
    TextView mTvGsonView;
    @Bind(R.id.tv2)
    TextView mTmTvGsonTwo;
    @Bind(R.id.iv1)
    TextView mIv1;
    @Bind(R.id.iv2)
    ImageView mIv2;
    @Bind(R.id.iv3)
    ImageView mIv3;
    @Bind(R.id.iv4)
    ImageView mIv4;
    @Bind(R.id.mIvLogin)
    ImageView mIvLogin;
    @Bind(R.id.one_line)
    MyOneLineView mOneLine;

    //animationList的ID
    private int mResid;

    private AnimationDrawable animationDrawable;

    private boolean isGson = true;

    private List<TestRBBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        initData();
        //   mIvLogin.setImageResource(mResid);
        //     animationDrawable = (AnimationDrawable) mIvLogin.getBackground();
     /*   mIvLogin.post(new Runnable() {
            @Override
            public void run() {
                animationDrawable.start();
            }
        });*/

        mOneLine.initMine(R.mipmap.icon_mark2, "测试文本", "", "", true);

        mIv1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        LogUtil.d("test==", "down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtil.d("test==", "move");
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtil.d("test==", "up");
                        break;
                }
                return false;
            }
        });
        mIv2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        LogUtil.d("test==", "down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtil.d("test==", "move");
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtil.d("test==", "up");
                        break;
                }
                return true;
            }
        });


        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.img_animation);
        LinearInterpolator lin1 = new LinearInterpolator();//设置动画匀速运动
        animation1.setInterpolator(lin1);
        mIv1.startAnimation(animation1);
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.id_rotate);
        mIv2.startAnimation(rotate);
        // mIv2.animate().setStartDelay(3000).rotation(360).start();

        Animation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, mIv3.getWidth() / 2, Animation.RELATIVE_TO_SELF, mIv3.getHeight() / 2);
        animation.setFillAfter(true);
        animation.setDuration(1000);
        animation.setRepeatCount(-1);
        animation.setInterpolator(new LinearInterpolator());
        mIv3.startAnimation(animation);

        ObjectAnimator animator = ObjectAnimator.ofFloat(mIv4, "rotationY", 0.0f, 359.0f);
        animator.setRepeatCount(-1);
        animator.setDuration(1000);
        AccelerateInterpolator interpolator = new AccelerateInterpolator();//设置加速旋转
        LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
        animator.setInterpolator(lin);
        animator.start();


        final TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        final TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);

        mTmTvGsonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGson) {
                    view.startAnimation(mShowAction);
                    mTvGsonView.setVisibility(View.VISIBLE);
                    isGson = false;
                } else {
                    view.startAnimation(mHiddenAction);
                    mTvGsonView.setVisibility(View.GONE);
                    isGson = true;
                }
            }
        });

        mTvGson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ButtonUtils.isFastDoubleClick()) {
                    if (mTvGson.getText().toString().equals("显示")) {
                        mTvGson.setText("隐藏");
                        int height = getLongHeight();
                        ValueAnimator valueAnimator = createDropAnim(view, 0, height);
                        mTvMyGson.setVisibility(View.VISIBLE);

                        valueAnimator.start();

                    } else {
                        mTvGson.setText("显示");
                        int height = view.getHeight();

                        ValueAnimator valueAnimator = createDropAnim(view, height, 0);
                        valueAnimator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                mTvMyGson.setVisibility(View.GONE);
                            }
                        });
                        valueAnimator.start();
                    }
                    Log.d("test==", "第一下");
                } else {
                    Log.d("test==", "不会生效的");
                }
            }
        });
    }

    private void initData() {

        mData.add(new TestRBBean("100"));
        mData.add(new TestRBBean("50"));
        mData.add(new TestRBBean("20"));
        mData.add(new TestRBBean("10"));
        mData.add(new TestRBBean("5"));
        mData.add(new TestRBBean("1"));


        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < mData.size(); i++) {
            RadioButton radioButton = (RadioButton) View.inflate(Main2Activity.this, R.layout.item_classify_radiogroup, null);
            radioButton.setText(mData.get(i).getTitle());
            radioButton.setId(i);
            if (i == 0) {
                radioButton.setChecked(true);
            }
            mTvMyGson.addView(radioButton, layoutParams);

        }

    }


    private int getLongHeight() {
        //测量拉伸与收缩的高度
        mTvMyGson.measure(0, 0);
        return mTvMyGson.getMeasuredHeight();
    }

    private ValueAnimator createDropAnim(final View view, int start, int end) {
        ValueAnimator va = ValueAnimator.ofInt(start, end);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ViewGroup.LayoutParams layoutParams = mTvMyGson.getLayoutParams();
                layoutParams = mTvMyGson.getLayoutParams();
                int height = (int) valueAnimator.getAnimatedValue();
                layoutParams.height = height;
                mTvMyGson.setLayoutParams(layoutParams);
            }
        });
        return va;
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString("test", "第二条消息");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        requestPermissions(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("test");
        }
    }

    private void requestPermissions(int featureNoTitle) {

    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
