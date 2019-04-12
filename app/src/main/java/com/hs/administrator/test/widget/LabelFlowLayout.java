package com.hs.administrator.test.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

/**
 * @auther : yanbin
 * @time : 2018/6/8 0008 10:55
 * @describe :
 */

public class LabelFlowLayout extends AdapterView<ListAdapter> implements View.OnClickListener {
    private ListAdapter mAdapter;

    /**
     * 上下行间距
     */
    public int veticalSpace = 30;
    /**
     * Item间距
     */
    public int horSpace = 30;

    /**
     * 每一行有多少个view
     */
    private SparseIntArray mLineViews = new SparseIntArray();

    private AdapterDataSetObserver mDataSetObserver;

    /**
     * 一行的高度由最高的item决定
     */
    private int lineHeight;

    //限制行数
    private int mLimitLines = -1;

    private int curLines;

    public LabelFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public ListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }
        mAdapter = adapter;
        if (mAdapter != null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
            makeView();
        } else {
            removeAllViewsInLayout();
            invalidate();
        }
    }

    private void makeView() {
        removeAllViewsInLayout();
        requestLayout();
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View child = mAdapter.getView(i, null, this);
            LayoutParams params = child.getLayoutParams();
            if (params == null)
                params = generateDefaultLayoutParams();
            addViewInLayout(child, i, params);
        }

    }

    /**
     * 设置显示行数,设置完成后会自动刷新
     * 如由限制改为不限制，设为0
     *
     * @param lines
     */
    public void setLimitLines(int lines) {
        this.mLimitLines = lines;
        requestLayout();
    }

    /**
     * 获取当前行数,注意第一次绘制前可能该数未被初始化
     * @return
     */
    public int getCurLines() {
        return curLines;
    }

    private class AdapterDataSetObserver extends DataSetObserver {

        @Override
        public void onChanged() {
            super.onChanged();
            makeView();
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int layoutWidth = 0;
        int layoutHeight = 0;

        int lineWidth = getPaddingLeft() + getPaddingRight();

        if (mAdapter == null)
            return;
        int count = getChildCount();
        mLineViews.clear();
        int lineCount = 0;
        int line = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            lineHeight = Math.max(lineHeight, child.getMeasuredHeight());
            int childWidth = child.getMeasuredWidth();
            if (lineWidth + childWidth > widthSize) {
                mLineViews.put(line, lineCount);
                lineWidth -= horSpace;
                layoutWidth = Math.max(layoutWidth, lineWidth);
                line++;
                lineWidth = getPaddingLeft() + getPaddingRight();
                lineCount = 0;
                //限制行数
                if(mLimitLines > 0 && line == mLimitLines){
                    break;
                }
            }
            lineCount++;
            lineWidth += childWidth + horSpace;

            if (i == count - 1) {
                mLineViews.put(line, lineCount);
                lineWidth -= horSpace;
                layoutWidth = Math.max(layoutWidth, lineWidth);
                lineCount = 0;
            }
        }

        if (modeWidth == MeasureSpec.EXACTLY) {
            layoutWidth = widthSize;
        }

        int lines = mLineViews.size();
        curLines = lines;
        if (lines > 0) {
            if (modeHeight == MeasureSpec.EXACTLY) {
                layoutHeight = heightSize;
            } else {

                layoutHeight = getPaddingTop() + getPaddingBottom() + lines * lineHeight + (lines - 1) * veticalSpace;
            }
        }
        setMeasuredDimension(layoutWidth, layoutHeight);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int leftPos = 0;
        int topPos = 0;

        int lines = mLineViews.size();
        int lineCount;

        int position = 0;

        for (int i = 0; i < lines; i++) {
            lineCount = mLineViews.get(i);
            View child;

            topPos = getPaddingTop() + i * veticalSpace + i * lineHeight;
            leftPos = getPaddingLeft();

            for (int j = 0; j < lineCount; j++) {
                child = getChildAt(position);
                if (child == null)
                    return;
                position++;
                child.setOnClickListener(this);
                int childWidth = child.getMeasuredWidth();
                child.layout(leftPos, topPos, leftPos + childWidth, topPos + lineHeight);

                leftPos += childWidth + horSpace;
            }
        }

    }

    @Override
    public void onClick(View v) {
        int pos = indexOfChild(v);
        performItemClick(v, pos, 0);
    }

    @Override
    public void setSelection(int position) {
        // TODO Auto-generated method stub

    }

    @Override
    public View getSelectedView() {
        // TODO Auto-generated method stub
        return null;
    }

}