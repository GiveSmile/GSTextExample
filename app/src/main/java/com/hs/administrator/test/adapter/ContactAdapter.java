package com.hs.administrator.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.hs.hstechsdklibrary.baseadapter.BaseRecylerAdapter;
import com.hs.hstechsdklibrary.baseadapter.BaseRecylerViewHolder;

import java.util.List;

/**
 * @auther : yanbin
 * @time : 2018/10/12 0012 10:05
 * @describe :
 */

public class ContactAdapter extends BaseRecylerAdapter {
private LayoutInflater mLayoutInflater;
private Context context;
private String[] mContactNames;
    public ContactAdapter(Context context, List mDatas, int layoutId) {
        super(context, mDatas, layoutId);
    }

    @Override
    public void convert(BaseRecylerViewHolder baseRecylerViewHolder, List list, int i) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
