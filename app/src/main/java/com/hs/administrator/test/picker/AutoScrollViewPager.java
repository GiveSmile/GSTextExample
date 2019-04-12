package com.hs.administrator.test.picker;

/**
 * 创建时间： 2019/1/17 0017.
 * 创建人：  yanbin
 * 功能：
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hs.hstechsdklibrary.R;
import com.hs.hstechsdklibrary.autoscrollviewpager.AutoViewPager;
import com.hs.hstechsdklibrary.autoscrollviewpager.BaseViewPagerAdapter;

import static com.hs.hstechsdklibrary.R.id.pointLayout;

/**
 * 自定义轮播小圆点及标题文字控件
 */
public class AutoScrollViewPager extends RelativeLayout {

    private final static String RIGHT_POINT = "right";
    private final static String CENTER_POINT = "center";
    private final static int RIGHT_INT = 0;
    private final static int CENTER_INT = 1;
    private final static boolean isSlide = true;
    private AutoViewPager mViewPager;
    private Context mContext;
    private LinearLayout layout;
    private View view;//底部文字和小圆点
    private TextView mSubTitle;//标题文字
    private int checkbackground = R.drawable.point_checked;  //默认选中圆点颜色
    private int normalbackground = R.drawable.point_normal; //默认不选中圆点颜色
    private int wideSize = 20;  //设置圆点宽大小
    private int highSize = 20;   //设置圆点高大小
    private int leftMargin = 8;  //设置圆点相距距离
    private int TIME = 2000;//轮播时间

    public TextView getSubTitle() {
        return mSubTitle;
    }

    public AutoViewPager getViewPager() {
        return mViewPager;
    }

    public AutoScrollViewPager(Context context) {
        this(context, null);
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoScrollViewPager, defStyleAttr, 0);
        String pointLayoutStr = typedArray.getString(R.styleable.AutoScrollViewPager_point_layout);
        if (pointLayoutStr != null) {
            switch (pointLayoutStr) {
                case RIGHT_POINT:
                    view = LayoutInflater.from(context).inflate(R.layout.point_right_text, null);
                    break;
                case CENTER_POINT:
                    view = LayoutInflater.from(context).inflate(R.layout.point_center_text, null);
                    break;
                default:
                    view = LayoutInflater.from(context).inflate(R.layout.point_center_text, null);
                    break;
            }
        }

        typedArray.recycle();

        init(context);

    }

    private void init(Context context) {
        mContext = context;
        mViewPager = new AutoViewPager(context, TIME);
        addView(mViewPager);

        if (view != null) {
            mSubTitle = (TextView) view.findViewById(R.id.subTitle);
            layout = (LinearLayout) view.findViewById(pointLayout);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(ALIGN_PARENT_BOTTOM);
            view.setLayoutParams(params);
            addView(view);
        }

    }

    public void setAdapter(BaseViewPagerAdapter adapter) {
        if (mViewPager != null) {
            mViewPager.init(mViewPager, adapter);
        }
    }

    public void initPointView(int size) {
        initPointView(size, 0);
    }

    /**
     * 初始化小圆点长度及当前位置id
     *
     * @param size         小圆点长度
     * @param currentPoint 当前小圆点位置id
     */
    public void initPointView(int size, int currentPoint) {

        layout.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(wideSize, highSize);
            params.leftMargin = leftMargin;
            imageView.setLayoutParams(params);
            if (i == currentPoint) {
                imageView.setBackgroundResource(checkbackground);
            } else {
                imageView.setBackgroundResource(normalbackground);
            }

            layout.addView(imageView);
        }
    }


    public void updatePointView(int position) {
        int size = layout.getChildCount();
        for (int i = 0; i < size; i++) {
            ImageView imageView = (ImageView) layout.getChildAt(i);
            if (i == position) {
                imageView.setBackgroundResource(checkbackground);
            } else {
                imageView.setBackgroundResource(normalbackground);
            }

        }
    }

    /**
     * 设置当前选中圆点颜色
     *
     * @param color
     */
    public void setcheckColor(int color) {
        checkbackground = color;
    }

    /**
     * 设置当前不选中圆点颜色
     *
     * @param color
     */
    public void setnormalColor(int color) {
        normalbackground = color;
    }

    /**
     * 设置圆点大小
     *
     * @param wide
     * @param high
     */
    public void setDotSize(int wide, int high) {
        this.wideSize = wide;
        this.highSize = high;
    }

    public void setDotLeftMargin(int left) {
        this.leftMargin = left;
    }

    public void onDestroy() {
        if (mViewPager != null) {
            mViewPager.onDestroy();
        }
    }

    /**
     * 设置是否需要自动滚动
     *
     * @param isSlide
     */
    public void setIsNeedSlide(boolean isSlide) {
        if (mViewPager != null) {
            mViewPager.setIsSlide(isSlide);
        }
    }

    /**
     * 是否需要显示底部圆
     *
     * @param isSlide
     */
    public void setIsShowSign(boolean isSlide) {
        if (mViewPager != null) {
            mViewPager.setShowSign(isSlide);
        }
    }

    public void setIsTime(int time) {
        if (time != 0) {
            TIME = time;
        } else {
            TIME = 2000;
        }

    }


    public void onResume() {
        if (mViewPager != null && !mViewPager.isStart()) {
            mViewPager.start();
        }
    }


    public void onPause() {
        if (mViewPager != null) {
            mViewPager.onStop();
        }
    }

}
