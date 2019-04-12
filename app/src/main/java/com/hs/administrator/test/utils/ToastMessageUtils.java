package com.hs.administrator.test.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.administrator.test.R;

/**
 * @auther : yanbin
 * @time : 2018/9/12 0012 16:25
 * @describe :
 */

public class ToastMessageUtils {
    /*Toast对象*/
   /* private static Toast toast ;
    private static ToastMessageUtils mToastUtils;


    @SuppressLint("WrongConstant")
    public static void ToastMessage(String title, Activity context) {
        //LayoutInflater的作用：对于一个没有被载入或者想要动态载入的界面，都需要LayoutInflater.inflate()来载入，LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化
        LayoutInflater inflater = context.getLayoutInflater();//调用Activity的getLayoutInflater()
       View view = inflater.inflate(R.layout.toast_home_statre_business, null); //加載layout下的布局

        TextView mTitle = view.findViewById(R.id.tv_title);
        if (toast == null){
            toast = new Toast(context.getApplicationContext());
        }
        mTitle.setText(title);
        toast.setGravity(Gravity.CENTER, 12, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setDuration(500);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.setView(view); //添加视图文件
        toast.show();
    }*/
}
