package com.hs.administrator.test.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hs.administrator.test.R;

/**
 * @auther : yanbin
 * @time : 2018/7/31 0031 16:37
 * @describe :
 */

public class UpdateSuccessfullyDialog extends Dialog implements View.OnClickListener {

    private TextView mTvTitle;//标题
    private Button mButSure;//确定
    private String mTitle;//标题内容

    private String mSure;//确定

    public UpdateSuccessfullyDialog(@NonNull Context context,String title) {
        super(context);
        this.mTitle = title;
    }

    public UpdateSuccessfullyDialog(@NonNull Context context, int themeResId,String title) {
        super(context, themeResId);
        this.mTitle = title;
    }

    protected UpdateSuccessfullyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_successfully_dialog);
        initView();
    }

    private void initView() {
      //  mTvTitle = findViewById(R.id.dialog_title);

      //  mTvTitle.setText(mTitle);

        mButSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but_sure:
                dismiss();
                break;
        }
    }
}
