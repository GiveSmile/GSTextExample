package com.hs.administrator.test.widget.UpdataDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hs.administrator.test.R;


/**
 * @auther : SJC
 * @time : 2018/6/29 20:47
 * @describe : 更新Dialog
 */

public class UpdateDialog extends Dialog {

    private Context mContext; // 上下文
    private TextView mTvVersion; // 版本号
    private TextView mTvContent; // 内容
    private ImageView mIvCancel; // 取消
    private Button mBtnUpdate; // 更新
    private String mVersion; // 版本号
    private String mContent; // 内容
    private String mCancel;

    public UpdateDialog(@NonNull Context context, @StyleRes int themeResId, String version, String content, String cancel, String sure) {
        super(context, themeResId);
        this.mContext = context;
        this.mVersion = version;
        this.mContent = content;
        this.mCancel = cancel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update);
        setCanceledOnTouchOutside(false);
        setCancelable(false);


        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        win.setAttributes(lp);

        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mTvVersion = findViewById(R.id.tv_version);
        mTvContent = findViewById(R.id.tv_content);
        mIvCancel = findViewById(R.id.iv_cancel);
        mBtnUpdate = findViewById(R.id.btn_update);

        mTvVersion.setText("版本：V" + mVersion);
        mTvContent.setText(mContent);

        mIvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (onUpdate == null) return;
                onUpdate.cancelUpdate();
            }
        });

        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (onUpdate == null) return;
                onUpdate.sureUpdate();
            }
        });

        if (mCancel.equals("取消")){
            mIvCancel.setVisibility(View.GONE);
        }else {
            mIvCancel.setVisibility(View.VISIBLE);
        }

    }

    public interface OnUpdate {
        void cancelUpdate();
        void sureUpdate();
    }

    private OnUpdate onUpdate;

    public void setOnUpdate(OnUpdate onUpdate) {
        this.onUpdate = onUpdate;
    }
}
