package com.hs.administrator.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.model.MsgBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @auther : yanbin
 * @time : 2019/4/24 0024 15:01
 * @describe : TODO
 */
public class WeChatAdapter extends RecyclerView.Adapter<WeChatAdapter.MyViewHolder> {

    private List<MsgBean> mDatas;
    private Context context;

    public WeChatAdapter(List<MsgBean> mData) {
        mDatas = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = null;
        switch (viewType) {//对不同的viewType加载不同的布局
            case 0:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.item_wechat_reght, parent, false);
                break;
            case 1:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.item_wechat_left, parent, false);
                break;
            case 2:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.item_wechat_tiem, parent, false);
                break;
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        switch (mDatas.get(position).getType()) {
            case 0:
            case 1:
                holder.mItemTV.setText(mDatas.get(position).getMsg());
                break;
            case 2:
//
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//                Date date = new Date(System.currentTimeMillis());
//                if (simpleDateFormat.format(date) != null) {
//
//                }
//                holder.mItemTvTime.setText("用date获取当前系统时间:");
                String time = new SimpleDateFormat("MM月dd日 a HH:mm", Locale.CHINA)
                        .format(System.currentTimeMillis());
                holder.mItemTvTime.setText(time);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getType();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mItemTV;
        private TextView mItemTvTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            mItemTV = itemView.findViewById(R.id.id_tv_chat_msg);
            mItemTvTime = itemView.findViewById(R.id.id_tv_time);

        }
    }
}
