package com.hs.administrator.test.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.videoplayer.VideoPlayer;
import com.hs.administrator.test.widget.RequestPermissionType;
import com.hs.hstechsdklibrary.imageLoader.ImageLoaderUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlayPhoneActivity extends AppCompatActivity implements Animation.AnimationListener {

    @Bind(R.id.et)
    EditText mEt;
    @Bind(R.id.but_playPhone)
    Button mButPlayPhone;
    @Bind(R.id.et_two)
    EditText mEtTwo;
    @Bind(R.id.but_playPhone_two)
    Button mButPlayPhoneTwo;
    @Bind(R.id.iv_one)
    ImageView mIvOne;
    @Bind(R.id.iv_two)
    ImageView mIvTwo;
    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private TranslateAnimation animationFirst;

    private TranslateAnimation animationSecond;

    private String phone;

    private String phoneTwo;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_phone);
        ButterKnife.bind(this);
        this.context = this;
        initData();
        // mIvOne.startAnimation(animationFirst);
        mIvTwo.startAnimation(animationSecond);
        //第一种方式需要用户权限申请以及处理第二种不需要申请权限处理
        mButPlayPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEt != null) {
                    phone = mEt.getText().toString().trim();
                }
                if (phone.equals("")) {
                    Toast.makeText(context, "请输入电话号码", Toast.LENGTH_SHORT).show();
                    mEt.setError("请输入电话号码");
                    return;
                }


                if (mEtTwo != null) {
                    phoneTwo = mEtTwo.getText().toString().trim();
                } else {
                    Toast.makeText(context, "请输入电话号码", Toast.LENGTH_SHORT).show();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int check = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
                    if (check != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(PlayPhoneActivity.this, new String[]{Manifest.permission.CALL_PHONE},
                                RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE);
                        return;
                    } else {
                        callPhone();
                    }
                } else {
                    callPhone();
                }
            }
        });
        mButPlayPhoneTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEtTwo != null) {
                    phoneTwo = mEtTwo.getText().toString().trim();
                } else {
                    Toast.makeText(context, "请输入电话号码", Toast.LENGTH_SHORT).show();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int check = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
                    if (check != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(PlayPhoneActivity.this, new String[]{Manifest.permission.CALL_PHONE},
                                RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE);
                        return;
                    } else {
                        diallPhone();
                    }
                } else {
                    diallPhone();
                }
            }
        });
    }

    private void initData() {
        String url = "http://221.228.226.23/9/n/s/i/b/nsibhbkffizwbmymnuogqhdbkgvota/hc.yinyuetai.com/10BF015D362A1D1BE13B06B95C41E615.mp4?sc=9e5b5dfd06cf3ab1&br=789&vid=2908926&aid=28047&area=ML&vst=0&ptp=mv&rd=yinyuetai.com";
      //  mTestVp.setUp(url,"水狗",true);
      //ImageLoaderUtils.display(PlayPhoneActivity.this,mTestVp.ivThumb,"http://pic19.nipic.com/20120308/4970979_102637717125_2.jpg",R.mipmap.appiontment_button_blue);

        animationFirst = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
        animationFirst.setDuration(5000);
        animationFirst.setInterpolator(new LinearInterpolator());
        animationFirst.setAnimationListener(this);
        animationSecond = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
        animationSecond.setDuration(5000);
        animationSecond.setInterpolator(new LinearInterpolator());
        animationSecond.setAnimationListener(this);

    }



    /**
     * 注册权限申请回调
     *
     * @param requestCode  申请码
     * @param permissions  申请的权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                } else {
                    Toast.makeText(PlayPhoneActivity.this, "CALL_PHONE Denied", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        startActivity(intent);
    }

    private void diallPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneTwo);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mIvOne.startAnimation(animationFirst);
        mIvTwo.startAnimation(animationSecond);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
