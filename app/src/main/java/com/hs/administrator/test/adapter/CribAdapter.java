package com.hs.administrator.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hs.administrator.test.R;

import java.util.List;

/**
 * @auther : yanbin
 * @time : 2019/5/6 0006 14:35
 * @describe : TODO
 */
public class CribAdapter extends RecyclerView.Adapter<CribAdapter.MyViewHolder> {
    private List<String> mData;
    private Context context;

    public  CribAdapter(List<String> mData){
     this.mData = mData;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = null;
        switch (viewType){
            case 5:
                view = LayoutInflater.from(context).inflate(R.layout.crib_item_five,parent,false);
                break;
            case 4:
              view = LayoutInflater.from(context).inflate(R.layout.crib_item_four,parent,false);
                break;
            case 3:
              view = LayoutInflater.from(context).inflate(R.layout.crib_item_three,parent,false);
                break;
            case 2:
              view = LayoutInflater.from(context).inflate(R.layout.crib_item_two,parent,false);
                break;
            case 1:
              view = LayoutInflater.from(context).inflate(R.layout.crib_item_one,parent,false);
                break;
        }

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return position+1;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
