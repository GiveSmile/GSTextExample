package com.hs.administrator.test.view.activity;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;


import com.hs.administrator.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class dianjizhankaiActivity extends AppCompatActivity {

    @Bind(R.id.tv_san)
    TextView mTvSan;

    @Bind(R.id.iv1)
    ImageView mIv1;

    private float mDensity;

    private int mHiddenViewMeasuredHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dianjizhankai);
        ButterKnife.bind(this);

        mDensity = getResources().getDisplayMetrics().density;
        mHiddenViewMeasuredHeight = (int)(mDensity*120+0.5);

        mIv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTvSan.getVisibility() == View.GONE){
                    animateOpen(mTvSan);
                    animationIvOpen();
                }else {
                    animateClose(mTvSan);
                    animationIvClose();
                }
            }
        });
    }
    private void animationIvOpen() {
        RotateAnimation animation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        mIv1.startAnimation(animation);
    }

    private void animationIvClose() {
        RotateAnimation animation = new RotateAnimation(180, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        mIv1.startAnimation(animation);
    }

    private void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

        });
        animator.start();
    }
    private void animateOpen(View v) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0,
                mHiddenViewMeasuredHeight);
        animator.start();

    }
    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);

            }
        });
        return animator;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
