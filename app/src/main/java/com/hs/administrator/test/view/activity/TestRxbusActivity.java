package com.hs.administrator.test.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hs.administrator.test.R;
import com.hs.administrator.test.model.EventMsgBean;
import com.hs.administrator.test.utils.RxBus;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TestRxbusActivity extends AppCompatActivity {

    @Bind(R.id.but_post)
    Button mButPost;
    @Bind(R.id.tv)
    Button mButTwo;


    public Kettle kettle;
    public static final int CASHAC_CCOUNT = 1003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rxbus);
        ButterKnife.bind(this);
        mButPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventMsgBean eventMsgBean = new EventMsgBean();
                eventMsgBean.setSetMsg("RxBus真好用");
                RxBus.getInstance().post(eventMsgBean);
                finish();
            }
        });
        mButTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("testBundle","我三年前就不应该吃那口饭");
                intent.putExtras(bundle);
                setResult(CASHAC_CCOUNT,intent);
                finish();*/
                kettle.notifyPeople("吾皇万岁万岁万万岁");
            }
        });


        kettle = new Kettle();
        PeopleLookkettle peopleLookkettle = new PeopleLookkettle();
        kettle.addObserver(peopleLookkettle);


    }

    public class Kettle extends Observable {
        public void notifyPeople(String s) {
            Log.d("test==", "我是XXX前来觐见");
            setChanged();
            notifyObservers(s);
        }
    }

    public class PeopleLookkettle implements Observer {
        @Override
        public void update(Observable observable, Object o) {
            Log.d("test==", (String) o);
            Log.d("test==", "退下");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
