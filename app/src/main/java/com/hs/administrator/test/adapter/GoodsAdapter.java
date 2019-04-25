package com.hs.administrator.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.adapter.itf.AdapterItemOp;
import com.hs.administrator.test.adapter.itf.ItemTouchCallback;
import com.hs.administrator.test.model.GoodsBean;

import java.util.Collections;
import java.util.List;

/**
 * @auther : yanbin
 * @time : 2019/4/24 0024 16:04
 * @describe : TODO
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHolder> implements AdapterItemOp<GoodsBean> {
    private List<GoodsBean> mBean;
    private Context context;
    private RecyclerView recyclerView;

    public GoodsAdapter(List<GoodsBean> mData) {
        mBean = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_goods_list, parent, false);
        recyclerView = (RecyclerView) parent;
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final GoodsBean str = mBean.get(position);
        holder.mIdIvGoods.setImageResource(str.getImgId());
        holder.mIdGoodsPrice.setText(str.getPrice() + "");
        holder.mIdIvInfo.setText(str.getInfo());
        holder.mIdGoodsBuyNum.setText(str.getBuyNum() + "人已付款");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(position, str);
            }
        });
//        holder.itemView.setOnClickListener(v -> {
//            addItem(position, str);
//        });
    }

    public void addItem(int position, GoodsBean bean) {
        mBean.add(position, bean);
        notifyItemInserted(position);//刷新插入数据---将不刷新position
        if (position == 0) {
            recyclerView.scrollToPosition(0);
        }
    }



    @Override
    public int getItemCount() {
        return mBean.size();
    }

    @Override
    public void onItemMove(int from, int to) {
        Collections.swap(mBean, from, to);
        notifyItemMoved(from, to);//刷新移动数据---将不刷新position

    }

    @Override
    public void onItemDelete(int position) {
        mBean.remove(position);
        notifyItemRemoved(position);//刷新移除数据---将不刷新position

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIdIvGoods;
        public TextView mIdYang;
        public TextView mIdGoodsPrice;
        public TextView mIdIvInfo;
        public TextView mIdGoodsBuyNum;
        public ImageView mIdIvBtnMore;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIdIvGoods = itemView.findViewById(R.id.id_iv_goods);
            mIdYang = itemView.findViewById(R.id.id_yang);
            mIdGoodsPrice = itemView.findViewById(R.id.id_goods_price);
            mIdIvInfo = itemView.findViewById(R.id.id_iv_info);
            mIdGoodsBuyNum = itemView.findViewById(R.id.id_goods_buy_num);
            mIdIvBtnMore = itemView.findViewById(R.id.id_iv_btn_more);

        }
    }
}
