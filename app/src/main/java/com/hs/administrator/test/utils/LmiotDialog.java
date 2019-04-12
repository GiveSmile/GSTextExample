package com.hs.administrator.test.utils;


import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.administrator.test.R;


/**
 * 菊花加载对话框
 */
public class LmiotDialog {

    private static Dialog loadingDialog;

    public static void show(Context context) {
        try {
            if (loadingDialog != null) {
                loadingDialog = null;
            }
            loadingDialog = new Dialog(context);
            loadingDialog.setCancelable(false);
            loadingDialog.setCanceledOnTouchOutside(false);

            loadingDialog.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Dialog show(Context context, String msg) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog, null);
        TextView loadingText = (TextView) view.findViewById(R.id.id_text);
        if (!TextUtils.isEmpty(msg)) {
            loadingText.setVisibility(View.VISIBLE);
            loadingText.setText(msg);
        } else {
            loadingText.setVisibility(View.GONE);
        }


        if (loadingDialog == null) {
            loadingDialog = new Dialog(context, R.style.custom_dialog_style);
        }
        loadingDialog.setCancelable(true);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        loadingDialog.show();

        return loadingDialog;
    }

    public static void hidden() {

        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }

    }


}
