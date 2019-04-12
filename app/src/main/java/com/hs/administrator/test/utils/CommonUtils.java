package com.hs.administrator.test.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * @auther : lwb
 * @time : 2018/5/9 9:40
 * @describe : 常用工具类
 */
public class CommonUtils {

    public static String getDouble(double n){
        int l = 0;
        if ( n % 1 == 0) {
            l = (int) n;
            return l+"";
        }else {
            int a=(n+"").length()-(n+"").indexOf(".")-1;
            if (a == 1) {
                return n+"";
            }else {
                DecimalFormat df   =new DecimalFormat("#.00");
                n = Double.parseDouble(df.format(n));
                if ( n % 1 == 0) {
                    l = (int) n;
                    return l+"";
                }else {
                    return n+"";
                }
            }
        }
    }

      //数字转换为人民币形式  带¥符号
    public static String getNumberFormat(long i){
        return NumberFormat.getCurrencyInstance().format(i);
    }
    //数字转换为人民币形式不带符号
    public static String getDcimalFormat(long i){
        DecimalFormat decimalFormat = new DecimalFormat("##,###,###,###,##0.00");
        return decimalFormat.format(i);
    }

    public static String time(int hour){
        String mTvTime = null;
        if (hour == 1 || hour ==2 || hour ==3 || hour == 4 || hour == 5){
            mTvTime=("凌晨好大兄弟");

        }
        if (hour == 6 || hour ==7 || hour ==8 || hour == 9 || hour == 10 ||hour == 11){
            mTvTime=("早上好大兄弟");

        }
        if (hour == 14 || hour ==15 || hour ==16 || hour == 17|| hour == 18 ){
            mTvTime=("下午好大兄弟");

        }
        if (hour == 19 || hour ==20 || hour ==21 || hour == 22 ){
            mTvTime=("晚上好大兄弟");

        }
        if (hour == 12 || hour ==13  ){
            mTvTime=("中午好大兄弟");

        }
        if (hour == 0 || hour ==23  ){
            mTvTime=("深夜好大兄弟");

        }
        return mTvTime;
    }
    /*防止按钮连续点击*/


    /*public s tatic List<AddressListBean.RECORDSBean> getAddressData(Activity activity){
        try {
            String json = ConvertUtils.toString(activity.getAssets().open("t_region.json"));
            AddressListBean bean = GsonUtil.get().fromJson(json, AddressListBean.class);
            List<AddressListBean.RECORDSBean> mListData = bean.getRECORDS();

            return mListData;
        }catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }*/

    public static int getScreenDensity(Activity activity){
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器
        activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int width = mDisplayMetrics.widthPixels;
        return width;
    }
    public static void closeKeyboard(Activity activity) {

        View view = activity.getWindow().peekDecorView();
        if (view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    /*Edittext 空格不换行*/
    public static void LimitsEditEnter(EditText et){
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
            }
        });
    }

    /*防止按钮连续点击*/
    private static long lastClickTime;
    public synchronized static boolean isFastClick(){
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000){
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static Drawable getNetErrorDrawable(Context context,int id){
        Drawable drawable = context.getResources().getDrawable(id);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }
}
