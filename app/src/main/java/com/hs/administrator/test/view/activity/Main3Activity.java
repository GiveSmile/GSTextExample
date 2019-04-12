package com.hs.administrator.test.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hs.administrator.test.R;
import com.hs.administrator.test.adapter.TestRecyclerAdapter;
import com.hs.administrator.test.model.TestRecyclerBean;
import com.hs.administrator.test.model.TestTwoRecyclerBean;
import com.hs.administrator.test.utils.AsyncImageLoader;
import com.hs.administrator.test.utils.CommonUtils;
import com.hs.administrator.test.utils.StaticListenerUtils;
import com.hs.administrator.test.widget.MyRecyclerView;
import com.hs.administrator.test.widget.RoundImageView;
import com.hs.hstechsdklibrary.baseadapter.BaseRecylerAdapter;
import com.hs.hstechsdklibrary.commonutil.LogUtil;
import com.tencent.stat.StatService;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity {

    @Bind(R.id.recycler)
    RecyclerView mRecycle;
    @Bind(R.id.iv)
    RoundImageView roundImageView;
    String string = "http://img0.imgtn.bdimg.com/it/u=3498484554,779468958&fm=214&gp=0.jpg";
    AsyncImageLoader asyncImageLoader = new AsyncImageLoader();
    private static long lastClickTime;

    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 2000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    private TestRecyclerAdapter mAdapter;

    private List<TestRecyclerBean> mData = new ArrayList<>();
    private List<TestTwoRecyclerBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        initData();
        asyncImageLoader.asyncSetImageBitmap(string,roundImageView);
        roundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Properties properties = new Properties();
                properties.setProperty("three","testThreeBut");
                StatService.trackCustomKVEvent(Main3Activity.this,"button_click_three",properties);
                Intent action = new Intent(Intent.ACTION_VIEW);
                StringBuilder builder = new StringBuilder();
                builder.append("myapp://baidu:8080/news?system=pc&id=45464");
                action.setData(Uri.parse(builder.toString()));
                startActivity(action);


            }
        });
      /*  for (int i = 0;i<mData.size();i++){
            mList.add(TestRecyclerBean);
        }*/
        //  mRecycle.setHeaderListData(getResources().getStringArray());
        LinearLayoutManager layoutManager = new LinearLayoutManager(Main3Activity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycle.setLayoutManager(layoutManager);
        mAdapter = new TestRecyclerAdapter(Main3Activity.this, mList);
        LogUtil.d("test==", mData.size() + "");
        mRecycle.setNestedScrollingEnabled(false);
        mRecycle.setAdapter(mAdapter);

        mAdapter.setOnItemClickLitener(new BaseRecylerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int i) {
                if (!isFastClick()) {
                    Log.d("test== ", "您点击了第" + (i + 1) + "条item");
                }
            }
        });
    }

    private void initData() {
        // mData.add(new TestRecyclerBean("id","托板id","套别","版别","券别","说明或品名","垛区","垛位","垛层","详细位置","入库时间"));
        mData.add(new TestRecyclerBean("1", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥" + CommonUtils.getDcimalFormat(2000000000), "2018-02-02 17:02"));
        mData.add(new TestRecyclerBean("2", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥" + CommonUtils.getDcimalFormat(500000000), "2018-02-02 17:02"));
        mData.add(new TestRecyclerBean("3", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥" + CommonUtils.getDcimalFormat(30000000), "2018-02-02 17:02"));
        mData.add(new TestRecyclerBean("4", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥" + CommonUtils.getDcimalFormat(600000000), "2018-02-02 17:02"));
        mData.add(new TestRecyclerBean("5", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥80,000,000.00", "2018-02-02 17:02"));
        mData.add(new TestRecyclerBean("6", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥80,000,000.00", "2018-02-02 17:02"));
        mData.add(new TestRecyclerBean("7", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥80,000,000.00", "2018-02-02 17:02"));
        mData.add(new TestRecyclerBean("8", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥80,000,000.00", "2018-02-02 17:02"));
        mData.add(new TestRecyclerBean("9", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥80,000,000.00", "2018-02-02 17:02"));
        mData.add(new TestRecyclerBean("10", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥80,000,000.00", "2018-02-02 17:02"));
        mData.add(new TestRecyclerBean("11", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥80,000,000.00", "2018-02-02 17:02"));
        mData.add(new TestRecyclerBean("12", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥80,000,000.00", "2018-02-02 17:02"));
        mData.add(new TestRecyclerBean("13", "01", "第五套人民币", "2000版", "100", "无", "1区", "2位", "3层", "¥80,000,000.00", "2018-02-02 17:02"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
