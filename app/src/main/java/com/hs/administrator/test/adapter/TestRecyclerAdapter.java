package com.hs.administrator.test.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

import com.hs.administrator.test.R;
import com.hs.administrator.test.model.TestRBBean;
import com.hs.administrator.test.model.TestRecyclerBean;
import com.hs.administrator.test.model.TestTwoRecyclerBean;
import com.hs.hstechsdklibrary.baseadapter.BaseRecylerAdapter;
import com.hs.hstechsdklibrary.baseadapter.BaseRecylerViewHolder;

import java.util.List;

/**
 * @auther : yanbin
 * @time : 2018/7/31 0031 10:45
 * @describe :
 */

public class TestRecyclerAdapter extends BaseRecylerAdapter<TestTwoRecyclerBean> {

    public TestRecyclerAdapter(Context context, List<TestTwoRecyclerBean> mDatas) {
        super(context, mDatas, R.layout.item_test_recycler);
    }

    @Override
    public void convert(BaseRecylerViewHolder mHolder, List<TestTwoRecyclerBean> list, int i) {
        mHolder.setText(R.id.id,list.get(i).getId());
        mHolder.setText(R.id.tuopangID,list.get(i).getTuopangId());
        mHolder.setText(R.id.taobie,list.get(i).getTaobie());
        mHolder.setText(R.id.bangbie,list.get(i).getBanbie());
        mHolder.setText(R.id.quanbie,list.get(i).getQuanbie());
        mHolder.setText(R.id.shuoming,list.get(i).getShuoming());
        mHolder.setText(R.id.duoqu,list.get(i).getDuoqu());
        mHolder.setText(R.id.duowei,list.get(i).getDuowei());
        mHolder.setText(R.id.duoceng,list.get(i).getDuoceng());
        mHolder.setText(R.id.xiangxixinxi,list.get(i).getXianxiweizhi());
        mHolder.setText(R.id.rukushijian,list.get(i).getRukuTime());
        LinearLayout mll = mHolder.getView(R.id.LL);
        if (i % 2 !=0)
        {
            mll.setBackgroundColor(Color.parseColor("#ff464e"));
        }

    }

}
