package com.hs.administrator.test.view.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.administrator.test.R;;
import com.hs.hstechsdklibrary.commonutil.ToastUtils;
import com.hs.hstechsdklibrary.commonutil.Utils;
import com.zia.toastex.ToastEx;
import com.zia.toastex.anim.InfoAnim;
import com.zia.toastex.anim.ToastImage;


import butterknife.Bind;
import butterknife.ButterKnife;

public class TestToastActivity extends AppCompatActivity {

    @Bind(R.id.but_toast_one)
    Button mButOne;
    private PathMeasure tickPathMeasure;
    //打钩动画
    private ValueAnimator mTickAnimation;

    private final static String TOAST = "this is a toast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mLvGou = new LoadingAnimatorView(this);
        //setContentView(mLvGou);
        setContentView(R.layout.activity_test_toast);
        ButterKnife.bind(this);
        Utils.init(this);
        final Context context = this;
        //打钩动画
        mTickAnimation = ValueAnimator.ofFloat(0f, 1f);
        mTickAnimation.setStartDelay(1000);
        mTickAnimation.setDuration(500);
        mTickAnimation.setInterpolator(new AccelerateInterpolator());


        mButOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastMessage("非异步操作", R.mipmap.outsideroom_success);
            }
        });

        findViewById(R.id.toast_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastEx.Config.getInstance().setUseAnim(false).tintIcon(false).apply();
                ToastEx.normal(context, TOAST).show();
            }
        });
        findViewById(R.id.toast_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastEx.Config.getInstance().setUseAnim(false).tintIcon(false).apply();
                ToastEx.success(context, TOAST).show();
            }
        });
        findViewById(R.id.toast_warning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastEx.Config.getInstance().setUseAnim(false).tintIcon(false).apply();
                ToastEx.warning(context, TOAST).show();
            }
        });
        findViewById(R.id.toast_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastEx.Config.getInstance().setUseAnim(false).tintIcon(false).apply();
                ToastEx.info(context, TOAST).show();
            }
        });
        findViewById(R.id.toast_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastEx.Config.getInstance().setUseAnim(false).tintIcon(false).apply();
                ToastEx.error(context, TOAST).show();
            }
        });

        findViewById(R.id.toast_anim_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastEx.Config.reset();//恢复至默认带动画的效果
                ToastEx.success(context, TOAST).show();

                ToastEx.Config.reset();
                ToastImage toastImage = new ToastImage(context);
                toastImage.setImageResource(R.mipmap.outsideroom_success);
                ToastEx.custom(context, TOAST, Toast.LENGTH_SHORT, ToastEx.NO_COLOR, toastImage).show();
            }
        });
        findViewById(R.id.toast_anim_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastEx.Config.reset();//恢复至默认带动画的效果
                ToastEx.error(context, TOAST).show();
            }
        });
        findViewById(R.id.toast_anim_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastEx.Config.reset();//恢复至默认带动画的效果
                ToastEx.info(context, TOAST).show();
            }
        });
        findViewById(R.id.toast_anim_warning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastEx.Config.reset();//恢复至默认带动画的效果
                ToastEx.warning(context, TOAST).show();
            }
        });
        findViewById(R.id.toast_custom_logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastEx.Config.reset();
                ToastImage toastImage = new ToastImage(context);
                toastImage.setImageResource(R.mipmap.outsideroom_success);
                ToastEx.custom(context, TOAST, Toast.LENGTH_SHORT, ToastEx.NO_COLOR, toastImage).show();
            }
        });
        findViewById(R.id.toast_custom_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
   /*             ToastEx.Config.reset();
                CustomText text = new CustomText(context);
                text.setText(TOAST);
                ToastEx.custom(context, text, Toast.LENGTH_SHORT
                        , ToastEx.NO_COLOR, new InfoAnim(context)).show();*/
            }
        });
    }

    @Override
    public void onBackPressed() {
        // mLvGou.flag = false;
        super.onBackPressed();
    }

    /**
     * 69      * 将Toast封装在一个方法中，在其它地方使用时直接输入要弹出的内容即可
     * 70
     */
    @SuppressLint("WrongConstant")
    private void ToastMessage(String titles, int mipmap) {
        //LayoutInflater的作用：对于一个没有被载入或者想要动态载入的界面，都需要LayoutInflater.inflate()来载入，LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化
        LayoutInflater inflater = getLayoutInflater();//调用Activity的getLayoutInflater()
        View view = inflater.inflate(R.layout.style_toast, null); //加載layout下的布局
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                ToastUtils.showShort("1");
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                ToastUtils.showShort("2");
            }
        });
        ImageView iv = view.findViewById(R.id.tv_image);
        iv.setImageResource(mipmap);//显示的图片
        TextView title = view.findViewById(R.id.tv_title);
        title.setText(titles); //toast的标题
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 12, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setDuration(500);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.setView(view); //添加视图文件
        toast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

