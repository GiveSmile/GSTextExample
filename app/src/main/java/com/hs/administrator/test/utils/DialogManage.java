package com.hs.administrator.test.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hs.administrator.test.R;

/**
 * @auther : yanbin
 * @time : 2019/4/15 0015 11:01
 * @describe : Dialog管理类用来放置所有的自定义dialog
 */
public class DialogManage {
    public static AlertDialog TestDialog(Context context, String title, String content, String liftButText, String rightButText, final onCallBack onCallBack) {
        LayoutInflater inflater = LayoutInflater.from(context);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.dialog_logout, null);
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(layout);
        TextView Dialog_title = layout.findViewById(R.id.tv_title);
        TextView Dialog_content = layout.findViewById(R.id.tv_message);
        Button Dialog_left = layout.findViewById(R.id.but_left);
        Button Dialog_right = layout.findViewById(R.id.but_right);
        View view = layout.findViewById(R.id.view);
        if (title.equals("")) {
            Dialog_title.setVisibility(View.GONE);
        } else {
            Dialog_title.setText(title);
        }
        if (content.equals("")) {
            Dialog_content.setVisibility(View.GONE);
        } else {
            Dialog_content.setText(content);
        }
        if (liftButText.equals("")) {
            Dialog_left.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        } else {
            Dialog_left.setText(liftButText);
        }
        if (rightButText.equals("")) {
            view.setVisibility(View.GONE);
            Dialog_right.setVisibility(View.GONE);
        } else {
            Dialog_right.setText(rightButText);
        }
        Dialog_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCallBack.onLeftBut();
            }
        });
        Dialog_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCallBack.onRightBut();
            }
        });

        return dialog;
    }

    public interface onCallBack {
        void onLeftBut();

        void onRightBut();
    }

    private onCallBack onCallBack;

    public void setOnCallBack(onCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }
}
