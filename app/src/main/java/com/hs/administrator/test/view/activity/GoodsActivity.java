package com.hs.administrator.test.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.baidu.mapsdkplatform.comapi.map.A;
import com.hs.administrator.test.R;
import com.hs.administrator.test.adapter.GoodsAdapter;
import com.hs.administrator.test.adapter.animator.RItemAnimator;
import com.hs.administrator.test.adapter.itf.ItemTouchCallback;
import com.hs.administrator.test.model.GoodsBean;

import java.util.ArrayList;
import java.util.List;

public class GoodsActivity extends AppCompatActivity {
    RecyclerView mRvGoods;
    List<GoodsBean> mGoodsBean = new ArrayList<>();
    private GoodsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        mRvGoods = findViewById(R.id.mRv_Goods);
        mGoodsBean.add(new GoodsBean(R.mipmap.pic4, "混沌战士,等比例人形,附加刀及盔甲,2030年爆款,限额三百件,先到先得,售完为止", 6666, 277, "店铺优惠,满100送10"));
        mGoodsBean.add(new GoodsBean(R.mipmap.pic1, "蓝夜皮肤,2030年爆款,限额三件,先到先得,售完为止", 99999, 2));

        mGoodsBean.add(new GoodsBean(R.mipmap.pic3, "古典美女,等比例人形,2030年爆款,限额三百件,先到先得,售完为止", 999, 177, "店铺优惠,满100送1000"));
        mGoodsBean.add(new GoodsBean(R.mipmap.pic6, "珍藏,非卖品", 9999999, 1));
        mGoodsBean.add(new GoodsBean(R.mipmap.pic2, "黑夜皮肤,附加魔法加成,2030年爆款,限额三百件,先到先得,售完为止", 8888, 277, "店铺优惠,满100送100000"));
        mGoodsBean.add(new GoodsBean(R.mipmap.pic5, "买洞爷湖送银时,只要998,绝对良心价,2030年爆款,限额三百件,先到先得,售完为止", 998, 277, "店铺优惠,满100送100000"));

        mAdapter = new GoodsAdapter(mGoodsBean);
        mRvGoods.setAdapter(mAdapter);
        ItemTouchHelper touchHelper =new ItemTouchHelper(new ItemTouchCallback(mAdapter));
        touchHelper.attachToRecyclerView(mRvGoods);
        mRvGoods.setItemAnimator(new RItemAnimator());
        mRvGoods.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }
}
