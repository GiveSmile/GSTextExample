package com.hs.administrator.test.utils.MyOneLine;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.administrator.test.R;

/**
 * Created by Administrator on 2018/11/20 0020.
 */

public class MyOneLineView extends LinearLayout {
    //各各控件
    private View dividerTop, dividerBottom;
    private LinearLayout llRoot;
    private ImageView ivLeftIcon;
    private TextView tvTextContent;
    private EditText editContent;
    private TextView tvRightText;
    private ImageView ivRightIcon;

    public MyOneLineView(Context context) {  //该构造函数使其，可以在Java代码中创建
        super(context);
    }

    public MyOneLineView(Context context, AttributeSet attrs) {//该构造函数使其可以在XML布局中使用
        super(context, attrs);
    }

    /**
     * 初始化各个控件
     */
    public MyOneLineView init() {
        //引入之前的xml布局
        LayoutInflater.from(getContext()).inflate(R.layout.activity_my_one_line, this, true);
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        dividerTop = findViewById(R.id.divider_top);
        dividerBottom = findViewById(R.id.divider_bottom);
        ivLeftIcon = (ImageView) findViewById(R.id.iv_left_icon);
        tvTextContent = (TextView) findViewById(R.id.tv_text_content);
        editContent = (EditText) findViewById(R.id.edit_content);
        tvRightText = (TextView) findViewById(R.id.tv_right_text);
        ivRightIcon = (ImageView) findViewById(R.id.iv_right_icon);
        return this;

    }
}
