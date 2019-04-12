package com.hs.administrator.test.view.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.administrator.test.R;
import com.hs.administrator.test.model.EventMsgBean;
import com.hs.administrator.test.utils.Observer.Kettle;
import com.hs.administrator.test.utils.Observer.Peopel;
import com.hs.administrator.test.utils.RxBus;

import java.util.List;
import java.util.Observable;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxBusActivity extends AppCompatActivity {

    @Bind(R.id.tv)
    TextView mTv;
    @Bind(R.id.tv_rxbus_content)
    TextView mTvContent;
    @Bind(R.id.but_observer)
    Button mButObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);
        ButterKnife.bind(this);

        final Kettle<String> kettle = new Kettle<>();
        Peopel peopel = new Peopel() {
            @Override
            public void dealWithEvent() {
                Log.d("test== ", "已拔");
            }
        };
        kettle.registObserver(peopel);

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(RxBusActivity.this, TestRxbusActivity.class), TestRxbusActivity.CASHAC_CCOUNT);
            }
        });

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsgBean>() {
            @Override
            public EventMsgBean apply(Object o) throws Exception {
                return (EventMsgBean) o;
            }
        }).subscribe(new Consumer<EventMsgBean>() {
            @Override
            public void accept(EventMsgBean eventMsgBean) throws Exception {
                if (eventMsgBean != null) {
                    mTvContent.setText(eventMsgBean.getSetMsg());
                }
            }
        });
        mButObserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kettle.publishEent("水已烧开~~");
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == TestRxbusActivity.CASHAC_CCOUNT) {
            Bundle build = data.getExtras();
            if (build != null) {
                String text = build.getString("testBundle");
                mTvContent.setText(text);
            }
        }
    }
}

