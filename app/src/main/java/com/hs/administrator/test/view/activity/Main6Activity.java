package com.hs.administrator.test.view.activity;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hs.administrator.test.R;

import com.hs.administrator.test.picker.AutoScrollViewPager;
import com.hs.administrator.test.utils.AsyncImageLoader;
import com.hs.hstechsdklibrary.autoscrollviewpager.BaseViewPagerAdapter;
import com.hs.hstechsdklibrary.imageLoader.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main6Activity extends AppCompatActivity implements AsyncImageLoader.ImageLoadedListener {

    @Bind(R.id.mRg)
    RadioGroup mRg;
    @Bind(R.id.but)
    TextView mBut;
    @Bind(R.id.mRb_one)
    RadioButton mRbOne;
    @Bind(R.id.mRb_two)
    RadioButton mRbTwo;
    @Bind(R.id.mRb_three)
    RadioButton mRbThree;
    @Bind(R.id.mRb_four)
    RadioButton mRbFour;
    @Bind(R.id.mRb_five)
    RadioButton mRbFive;
    @Bind(R.id.testAutoScroll)
    AutoScrollViewPager mViewParger;
    private int cishu = 0;
    private static final int RIGHT_MOVE = 1;
    private boolean isClick = false;
    private boolean isRBClic = false;
    private ObjectAnimator animator;
    private TranslateAnimation translateAnimation, overAnimation;
    private List<Fragment> fragments;
    private List<String> list = new ArrayList<>();
    private AsyncImageLoader asyncImageLoader = new AsyncImageLoader();


    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RIGHT_MOVE:
                    int l = mRbOne.getLeft();
                    int r = mRbOne.getRight();
                    int t = mRbOne.getTop();
                    int b = mRbOne.getBottom();
                    mRbOne.layout(l + 100, t, r + 100, b);
                    break;
            }
        }
    };
    private CountDownTimer timer = new CountDownTimer(15 * 60 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            // mBut.setText("sdfasdfas" + formatTime(4) + "sadfasdfasdf");
        }

        @Override
        public void onFinish() {
            mBut.setText("hahah");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        ButterKnife.bind(this);
        initOnClick();
        timer.start();
        list.add("http://img0.imgtn.bdimg.com/it/u=3498484554,779468958&fm=214&gp=0.jpg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/503d269759ee3d6d1c9c596d43166d224f4ade3a.jpg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/0d0e657b844d2e798618bf9e.jpg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/b999a9014c086e0629cae1ed02087bf40ad1cb32.jpg");

        BaseViewPagerAdapter<String> adapter = new BaseViewPagerAdapter<String>(Main6Activity.this) {
            @Override
            public void loadImage(final ImageView imageView, int i, String s) {
//                ImageLoaderUtils.displaySmallPhoto(Main6Activity.this, imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher, s);
//                AsyncImageLoader.loadRawDataFromURL(s);
//                AsyncImageLoader.loadBitmapFromURL(s);
                //asyncImageLoader.asyncSetImageBitmap(s,imageView);
                asyncImageLoader.download(s, new AsyncImageLoader.ImageLoadedListener() {
                    @Override
                    public void imageLoaded(Bitmap bitmap) {

                        imageView.setImageBitmap(bitmap);
                    }
                });

                //  Glide.with(AdvertisingActivity.this).load(s).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic_image_loading).error(R.drawable.ic_empty_picture).thumbnail(0.5F).into(imageView);
            }
        };
        mViewParger.setAdapter(adapter);
        adapter.add(list);
        mViewParger.setIsShowSign(true);
        mViewParger.setcheckColor(R.mipmap.ic_launcher);
        mViewParger.setcheckColor(R.mipmap.appiontment_button_blue);

        mBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClick != true) {
                    int l = mBut.getLeft();
                    int r = mBut.getRight();
                    int t = mBut.getTop();
                    int b = mBut.getBottom();
                    mBut.layout(l + 100, t, r + 100, b);
                    isClick = true;
                } else {
                    isClick = false;
                    int l = mBut.getLeft();
                    int r = mBut.getRight();
                    int t = mBut.getTop();
                    int b = mBut.getBottom();
                    mBut.layout(l - 100, t, r - 100, b);
                }
            }
        });
    }

    private void initOnClick() {
        //往右动画还需要修改
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                LinearLayout.LayoutParams lp_left = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp_left.gravity = Gravity.LEFT;

                switch (i) {
                    case R.id.mRb_one:
                        if (isRBClic) {
                            mHander.sendEmptyMessage(RIGHT_MOVE);
                            isRBClic = true;
                        } else {
                            isRBClic = false;
                            mRbOne.setLayoutParams(lp_left);
                        }
                        mRbTwo.setLayoutParams(lp_left);
                        mRbThree.setLayoutParams(lp_left);
                        mRbFour.setLayoutParams(lp_left);
                        mRbFive.setLayoutParams(lp_left);
                        break;  //Toast.makeText(Main6Activity.this, , Toast.LENGTH_SHORT).show();
                    case R.id.mRb_two:

                        //  if (mRbTwo.isChecked()!= true){
                        if (isRBClic) {
                           /* int mRbTwol = mRbTwo.getLeft();
                            int mRbTwor = mRbTwo.getRight();
                            int mRbTwot = mRbTwo.getTop();
                            int mRbTwob = mRbTwo.getBottom();
                            mRbTwo.layout(mRbTwol + 100, mRbTwot, mRbTwor + 100, mRbTwob);*/
                            startTranslateAnimation();
                            mRbTwo.startAnimation(translateAnimation);
                            isRBClic = true;
                        } else {
                            isRBClic = false;
                            mRbTwo.setLayoutParams(lp_left);
                        }
                        mRbOne.setLayoutParams(lp_left);
                        mRbThree.setLayoutParams(lp_left);
                        mRbFour.setLayoutParams(lp_left);
                        mRbFive.setLayoutParams(lp_left);
                        //}
                        break;
                    case R.id.mRb_three:
                        if (!isRBClic) {
                            int mRbThreel = mRbThree.getLeft();
                            int mRbThreer = mRbThree.getRight();
                            int mRbThreet = mRbThree.getTop();
                            int mRbThreeb = mRbThree.getBottom();
                            mRbThree.layout(mRbThreel + 100, mRbThreet, mRbThreer + 100, mRbThreeb);
                            isRBClic = false;
                        } else {
                            isRBClic = true;
                            mRbThree.setLayoutParams(lp_left);
                        }
                        mRbTwo.setLayoutParams(lp_left);
                        mRbOne.setLayoutParams(lp_left);
                        mRbFour.setLayoutParams(lp_left);
                        mRbFive.setLayoutParams(lp_left);

                        break;
                    case R.id.mRb_four:
                        if (!isRBClic) {
                            int mRbFourl = mRbFour.getLeft();
                            int mRbFourr = mRbFour.getRight();
                            int mRbFourt = mRbFour.getTop();
                            int mRbFourb = mRbFour.getBottom();
                            mRbFour.layout(mRbFourl + 100, mRbFourt, mRbFourr + 100, mRbFourb);
                            isRBClic = false;
                        } else {
                            isRBClic = true;
                            mRbFour.setLayoutParams(lp_left);
                        }
                        mRbTwo.setLayoutParams(lp_left);
                        mRbOne.setLayoutParams(lp_left);
                        mRbThree.setLayoutParams(lp_left);
                        mRbFive.setLayoutParams(lp_left);

                        break;
                    case R.id.mRb_five:
                        if (!isRBClic) {
                            int mRbFivel = mRbFive.getLeft();
                            int mRbFiver = mRbFive.getRight();
                            int mRbFivet = mRbFive.getTop();
                            int mRbFiveb = mRbFive.getBottom();
                            mRbFive.layout(mRbFivel + 100, mRbFivet, mRbFiver + 100, mRbFiveb);
                            isRBClic = false;
                        } else {
                            isRBClic = true;
                            mRbFive.setLayoutParams(lp_left);
                        }
                        mRbTwo.setLayoutParams(lp_left);
                        mRbOne.setLayoutParams(lp_left);
                        mRbThree.setLayoutParams(lp_left);
                        mRbFour.setLayoutParams(lp_left);
                        break;
                }
            }
        });
    }

    /**
     * 将毫秒转化为 分钟：秒 的格式
     *
     * @param millisecond 毫秒
     * @return
     */
    public String formatTime(long millisecond) {
        int minute;//分钟
        int second;//秒数
        minute = (int) ((millisecond / 1000) / 60);
        second = (int) ((millisecond / 1000) % 60);
        if (minute < 10) {
            if (second < 10) {
                return "0" + minute + ":" + "0" + second;
            } else {
                return "0" + minute + ":" + second;
            }
        } else {
            if (second < 10) {
                return minute + ":" + "0" + second;
            } else {
                return minute + ":" + second;
            }
        }
    }

     /*   mRbOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mRbTwo.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                startTranslateAnimation();
                mRbTwo.startAnimation(translateAnimation);
                isClick = true;

            }
        });
        mRbThree.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                startTranslateAnimation();
                mRbThree.startAnimation(translateAnimation);
            }
        });
        mRbFour.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
               startTranslateAnimation();
                mRbFour.startAnimation(translateAnimation);
            }
        });
        mRbFive.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                startTranslateAnimation();
                mRbFive.startAnimation(translateAnimation);
            }
        });*/


    public void startTranslateAnimation() {
        translateAnimation = new TranslateAnimation(0, mRbOne.getWidth() / 2, 0, 0);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillBefore(true);
       /* //translateAnimation.setRepeatMode(ScaleAnimation.REVERSE);
        translateAnimation.setRepeatMode(ScaleAnimation.REVERSE);
        //设置动画播放次数
        translateAnimation.setRepeatCount(ScaleAnimation.INFINITE);*/
        translateAnimation.cancel();
    }

    public void backTranslateAnimation() {
        overAnimation = new TranslateAnimation(0, -mRbOne.getWidth() / 2, 0, 0);
        overAnimation.setDuration(1000);
        overAnimation.setFillAfter(true);
        overAnimation.setFillBefore(false);
       /* //translateAnimation.setRepeatMode(ScaleAnimation.REVERSE);
        translateAnimation.setRepeatMode(ScaleAnimation.REVERSE);
        //设置动画播放次数
        translateAnimation.setRepeatCount(ScaleAnimation.INFINITE);*/
        overAnimation.cancel();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @Override
    public void imageLoaded(Bitmap bitmap) {

    }
}
