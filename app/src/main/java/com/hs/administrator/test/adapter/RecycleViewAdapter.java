package com.hs.administrator.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hs.administrator.test.R;

import java.util.List;

/**
 * @auther : yanbin
 * @time : 2019/4/24 0024 14:11
 * @describe : TODO
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private List<String> mData;

    private Context context;

    public RecycleViewAdapter(List<String> list) {
        mData = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycleview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mBut.setText(mData.get(position));
        holder.mBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCheckListener.OnItemClick(mData.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size() == 0 ? 0 : mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button mBut;

        public MyViewHolder(View itemView) {
            super(itemView);
            mBut = itemView.findViewById(R.id.title_content);
        }
    }

    public interface ItemCheckListener {
        void OnItemClick(String conten, int i);
    }

    public ItemCheckListener itemCheckListener;

    public void setContext(ItemCheckListener itemCheckListener) {
        this.itemCheckListener = itemCheckListener;
    }
}
