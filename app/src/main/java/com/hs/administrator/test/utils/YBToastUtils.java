package com.hs.administrator.test.utils;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.administrator.test.R;

/**
 * @auther : yanbin
 * @time : 2018/8/3 0003 11:43
 * @describe :
 */

public class YBToastUtils {
    private Toast toast;
    private LinearLayout toastView;

    /**
     * 完全自定义布局Toast
     */
    public YBToastUtils(Context context, View view, int duration) {
        toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(duration);
    }

    /**
     * 向Toast中添加自定义View
     */
    public YBToastUtils addView(View view, int position) {
        toastView = (LinearLayout) toast.getView();
        toastView.addView(view, position);
        return this;
    }

    /**
     * 设置Toast字体及背景
     */
    public YBToastUtils setToastBackground(int messageColor, int background) {
        View view = toast.getView();
        if (view != null) {
            TextView message = (TextView) view.findViewById(R.id.message);
            message.setBackgroundResource(background);
            message.setTextColor(messageColor);
        }

        return this;
    }

    /**
     * 短时间显示Toast
     */
    public YBToastUtils Short(Context context, CharSequence message) {
        if (toast == null
                || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        return this;
    }

    /**
     * 长时间显示toast
     */
    public YBToastUtils Long(Context context, CharSequence message) {
        if (toast == null
                || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        return this;
    }

    /**
     * 自定义显示Toast的时长
     */
    public YBToastUtils Indefinite(Context context, CharSequence message,
                                 int duration) {
        if (toast == null
                || (toastView != null && toastView.getChildCount() > 1)) {
            toast = Toast.makeText(context, message, duration);
            toastView = null;
        } else {
            toast.setText(message);
            toast.setDuration(duration);
        }

        return this;
    }

    /**
     * 显示Toast
     */
    public YBToastUtils show() {
        toast.show();
        return this;
    }

    /**
     * 获取Toast
     */
    public Toast getToast() {
        return toast;
    }
}
