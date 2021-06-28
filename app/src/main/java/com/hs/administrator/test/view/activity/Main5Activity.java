package com.hs.administrator.test.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.administrator.test.R;
import com.hs.administrator.test.adapter.AccountPagerAdapter;
import com.hs.administrator.test.utils.CommonUtils;
import com.hs.administrator.test.utils.CountTimeUtils;
import com.hs.administrator.test.view.fragment.TwoFragment;
import com.hs.administrator.test.view.fragment.oneFragment;
import com.hs.administrator.test.widget.CustomHanTextView;
import com.hs.administrator.test.widget.ShineTextView;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main5Activity extends AppCompatActivity {

    private String TAG;
    private ShineTextView mBut;
    private TextView mTestShine, mTest;
   // private XTabLayout mXTabLayout;
    private ViewPager mVpAllOrder;
    private WindowManager windowManager;

    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();
    private AccountPagerAdapter mAccountPagerAdapter;
    @Bind(R.id.tv_time)
    CustomHanTextView mTvTime;
    boolean isGood = false;
    boolean isTwo = true;


    CountTimeUtils  StartInventoryTimeUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ButterKnife.bind(this);


        mBut = (ShineTextView) findViewById(R.id.but);
        mTestShine = (TextView) findViewById(R.id.tv_test_shine);
        mTest = (TextView) findViewById(R.id.tv_test);
      //  mXTabLayout = findViewById(R.id.test_XTabLayout);
        mVpAllOrder = findViewById(R.id.mVp);
        //用calenadr获取当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);//年
        int month = calendar.get(Calendar.MONTH) + 1;//月
        int day = calendar.get(Calendar.DAY_OF_MONTH);//日
        int hour = calendar.get(Calendar.HOUR_OF_DAY);//时
        int minute = calendar.get(Calendar.MINUTE);//分
        int second = calendar.get(Calendar.SECOND);//秒
        initData();


        //第三种timehuoqu1
        //Time time = new Time();

        TimeThread timeThread = new TimeThread();
        timeThread.start();

        //设置外发光
        mBut.addOuterShadow(12, 0, 0, Color.parseColor("#000000"));
        mBut.addOuterShadow(12, 0, 0, Color.parseColor("#2b88f6"));
        mBut.invalidate();

        mTestShine.setText("Calendar所获取的时间：" + year + "年" + month + "月" + day + "日" + hour + ":" + (minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second));
        /*mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i, j, k, l, m;
                char c = 3;
                for (i = 1; i <= 5; i++) {
                    Log.d("test==", i + "");
                }
                for (i = 1; i <= 3; i++) {
                    for (j = 1; j <= 32 - 2 * i; i++) {
                        Log.d("test==", j + "");
                    }
                }
            }
        });*/

          StartInventoryTimeUtil = new CountTimeUtils(mTest, 9) {
            @Override
            public void onTick(View mTvMsg, long millisUntilFinished) {
                Log.d("Test==",millisUntilFinished+"");
            }

            @Override
            public void onFinish() {
                //五秒后倒数操作
                //onTimeBackwards();
                //   setFail("下发清点数据失败，请手动新增清点数据","确定");
             Log.d("Test==","倒数完成");
            }
        };

        mTvTime.setText(CommonUtils.time(hour));

       /* if (hour == 1 || hour ==2 || hour ==3 || hour == 4 || hour == 5){
            mTvTime.setText("凌晨好大兄弟");

        }
        if (hour == 6 || hour ==7 || hour ==8 || hour == 9 || hour == 10 ||hour == 11){
            mTvTime.setText("早上好大兄弟");

        }
        if (hour == 14 || hour ==15 || hour ==16 || hour == 17|| hour == 18 ){
            mTvTime.setText("下午好大兄弟");

        }
        if (hour == 19 || hour ==20 || hour ==21 || hour == 22 ){
            mTvTime.setText("晚上好大兄弟");

        }
        if (hour == 12 || hour ==13  ){
            mTvTime.setText("中午好大兄弟");

        }
        if (hour == 0 || hour ==23  ){
            mTvTime.setText("深夜好大兄弟");

        }*/


        //防止二次点击
        mBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartInventoryTimeUtil.cancel();
            }
        });
    }

    //handler更新时间
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //用date获取时间
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());
                    if (simpleDateFormat.format(date) != null) {
                        mBut.setText("用date获取当前系统时间:" + simpleDateFormat.format(date));
                    }
                    //mTest.setText(msg.obj + "");
                    break;
            }
        }
    };

    //监听物理返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();//注释了这一行  这无法返回  fragment 还需另找方法
        Toast.makeText(this, "返回成功", Toast.LENGTH_SHORT).show();
    }

    //开启一个线程 每隔一秒发一个消息给Handel更新页面
    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {

                    for (int i = 0; i < 2.00 / 0.00; i++) {
                        Thread.sleep(1000);
                        Message msg = new Message();
                        msg.what = 1;//消息
                        msg.obj = i;
                        if (i>5){
                            TimeThread.interrupted();
                        }
                        mHandler.sendMessage(msg);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }


    private void initData() {
        String url = "";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("loginId", "黄")
                .add("password", "123456")
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.1.121:8888/login/login?loginId=黄&password=123456")
          /*      .addHeader("loginId","黄")
                .addHeader("password","123456")
                .post(requestBody)
                .header("loginId", "黄")
                .header("password", "123456")*/
                .build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    Log.d("test== ", "请求：" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("test==", "请求2：" + e.toString());
                }
            }
        }).start();

        mTestShine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartInventoryTimeUtil.start();

            }
        });
        mTvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isTwo) {
                    Toast.makeText(Main5Activity.this, "1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Main5Activity.this, "2", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mFragment.add(new oneFragment());
        mFragment.add(new TwoFragment());

        mTitles.add("第一个头部");
        mTitles.add("第二个头部");

        mAccountPagerAdapter = new AccountPagerAdapter(getSupportFragmentManager(), mFragment, mTitles);
        mVpAllOrder.setAdapter(mAccountPagerAdapter);//设置Adapter
        //mXTabLayout.setupWithViewPager(mVpAllOrder);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
