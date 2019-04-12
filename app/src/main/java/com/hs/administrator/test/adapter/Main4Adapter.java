package com.hs.administrator.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hs.administrator.test.R;

import java.util.List;

/**
 * @auther : yanbin
 * @time : 2018/6/8 0008 9:47
 * @describe :
 */

public class Main4Adapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<String> list;

    public Main4Adapter(Context context, List<String> list) {
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    static class ViewHolder{
        TextView TextView;
    }

    @Override
    public int getCount() {
       /* if (list.size() == 0) {
           return 0;
        }else {*/
            return list.size();
        //}
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            view = mInflater.inflate(R.layout.main4_item,viewGroup,false);
            holder = new ViewHolder();
            holder.TextView = view.findViewById(R.id.tv_category);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.TextView.setText(list.get(i));
        holder.TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCheckClickListener.onTextViewClick(list,i);
            }
        });
        return view;
    }
    public interface ItemCheckClickListener {

          void onTextViewClick(List<String> data, int position);

    }

    public ItemCheckClickListener itemCheckClickListener;

    public void setOnCheckClickListener(ItemCheckClickListener onCheckClickListener) {
        this.itemCheckClickListener = onCheckClickListener;
    }
}
