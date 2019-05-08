package com.hs.administrator.test.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;

/**
 * @auther : yanbin
 * @time : 2019/5/6 0006 16:25
 * @describe : TODO
 */
public class StackLayout extends FrameLayout {


    private ArrayAdapter adapter;

    private View[] viewsBuffer;

    private int itemSize;


    private DataSetObserver dataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            attachChildViews();
        }
    };


    public StackLayout(Context context) {
        this(context,null);
    }

    public StackLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setAdapter(ArrayAdapter adapter,int itemSize){
        if (adapter == null){
            throw  new IllegalArgumentException("adapter not null");
        }
        if (this.adapter != null){
            this.adapter.unregisterDataSetObserver(dataSetObserver);
        }
        this.itemSize = itemSize;
        this.adapter = adapter;
        this.adapter.registerDataSetObserver(dataSetObserver);
        viewsBuffer = new View[itemSize];
        attachChildViews();

    }

    private void attachChildViews(){
        removeAllViews();
        for (int position = 0 ; position < itemSize; position ++ ){
                viewsBuffer[position] = adapter.getView(position,viewsBuffer[position],this);

                addViewInLayout(viewsBuffer[position], 0, viewsBuffer[position].getLayoutParams());
               // initEvent(adapter.getView(position,viewsBuffer[position],this));
        }
        requestLayout();
    }

}
