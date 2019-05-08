package com.hs.administrator.test.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.model.StackBean;

import java.util.List;

/**
 * @auther : yanbin
 * @time : 2019/5/6 0006 17:46
 * @describe : TODO
 */
public class StackOneAdapter extends ArrayAdapter {
    private List<StackBean> mData;

    private Context context;

    public StackOneAdapter(Context context) {
        super(context, R.layout.crib_item_one);
        this.context = context;
    }

    public void setData(List<StackBean> mData) {
        this.mData = mData;
    }


    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crib_item_one, parent, false);
        if (mData.get(position).getType() == 1) {
            setLeftIcon(view, position, 3);

        } else {
            setLeftIcon(view, position);
        }
//        switch (position) {
//            case 0:
//                view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.crib_item_one, parent, false);
//
//                break;
//            case 1:
//                view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.crib_item_two, parent, false);
//
//                break;
//            case 2:
//                view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.crib_item_three, parent, false);
//                break;
//            case 3:
//                view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.crib_item_four, parent, false);
//
//                break;
//            case 4:
//                view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.crib_item_five, parent, false);
//                break;
//        }


//        final String name = (String) getItem(position);
        String name = mData.get(position).getContent();
        ((TextView) view.findViewById(R.id.name)).setText(name);


        final View completeView = view.findViewById(R.id.completeCommand);
        completeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onClickListener != null) {
                    onClickListener.onClickListener(position);
                }
            }

        });
        return view;
    }


    /**
     * 设置分类按钮向左移动动画
     *
     * @param img
     */
    private void setLeftIcon(View img, int i, int size) {
        int marginTop = i * 10 + size;
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(img.getLayoutParams());
        margin.rightMargin = DensityUtil.dip2px(context, size * 2);
        margin.topMargin = DensityUtil.dip2px(context, marginTop);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        img.setLayoutParams(layoutParams);
    }
    private void setLeftIcon(View img, int i) {
        int marginTop = i * 10 ;
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(img.getLayoutParams());
//        margin.rightMargin = DensityUtil.dip2px(context, size * 2);
        margin.topMargin = DensityUtil.dip2px(context, marginTop);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        img.setLayoutParams(layoutParams);
    }

    public interface OnParticularsClickListener {

        void onClickListener(int position);
    }

    private OnParticularsClickListener onClickListener;

    public void setVideoBackCallBack(OnParticularsClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}

