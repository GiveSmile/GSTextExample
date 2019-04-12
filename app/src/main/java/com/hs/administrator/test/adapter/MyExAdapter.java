package com.hs.administrator.test.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.model.GroupBean;
import com.hs.administrator.test.model.itemBean;

import java.util.List;

/**
 * @auther : yanbin
 * @time : 2018/7/27 0027 10:20
 * @describe :
 */

public class MyExAdapter implements ExpandableListAdapter {

    List<GroupBean> groupBeen;//父布局
    List<List> childrenList;//子布局
    Context context;
    int group;
    int childrem;

    public MyExAdapter(Context context,int group,int childrem,List<GroupBean> groupBeen,List<List> childrenList){
        this.context = context;
        this.group=group;
        this.childrem = childrem;
        this.groupBeen = groupBeen;
        this.childrenList = childrenList;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }
     /*
     * 设置一级条目的数量
     * */
    @Override
    public int getGroupCount() {
        if (groupBeen != null){
        return groupBeen.size();
        }else {
         return 0;
        }
    }

    /*
    * 二级条目的数量
    * */
    @Override
    public int getChildrenCount(int i) {
        List<itemBean> itemBeen = childrenList.get(i);
        return itemBeen.size();
    }

    @Override
    public Object getGroup(int i) {
        return groupBeen.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        List<itemBean> itemBeen = childrenList.get(i);
        return itemBeen.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        //加载Group 布局
        View view1;
        if (view == null){
            //view是布局的缓存，如果缓存为空就加载布局到view
            view1 = LayoutInflater.from(context).inflate(group,viewGroup,false);
        }else {
            //如果view不为空说明有缓存直接把缓存布局赋值给view
            view1 = view;
        }
        //读取groupBean对象也就是一级列表
        GroupBean groupBean = (GroupBean) getGroup(i);
        //为group 布局中添加数据 先读取布局中的textview
        TextView mTv = view1.findViewById(R.id.view_title);
        //
        mTv.setText(groupBean.getTitle());

        return view1;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        //加载Group 布局
        View view1;
        if (view == null){
            //view是布局的缓存，如果缓存为空就加载布局到view
            view1 = LayoutInflater.from(context).inflate(childrem,viewGroup,false);
        }else {
            //如果view不为空说明有缓存直接把缓存布局赋值给view
            view1 = view;
        }
        //读取groupBean对象也就是一级列表
        itemBean groupBean = (itemBean) getChild(i,i1);
        //为group 布局中添加数据 先读取布局中的textview
        TextView mTv = view1.findViewById(R.id.content);
        //
        mTv.setText(groupBean.getContent());

        return view1;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }
}
