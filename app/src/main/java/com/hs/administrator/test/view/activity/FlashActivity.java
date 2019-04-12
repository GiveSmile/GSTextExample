package com.hs.administrator.test.view.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.hs.administrator.test.R;
import com.hs.administrator.test.utils.RecvImp;
import com.hs.administrator.test.utils.SendImp;
import com.hs.hstechsdklibrary.commonutil.LogUtil;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FlashActivity extends AppCompatActivity {

    @Bind(R.id.et_content)
    EditText mEtContent;//发送的消息
    @Bind(R.id.btn_send)
    Button mButSend;//发送按钮
    @Bind(R.id.tv_receive)
    TextView mTvReceive;//接收到的内容
    ConnectionFactory factory;
    StringBuilder builder = new StringBuilder();
    SendImp send;
    RecvImp recv;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mEtContent.setText("");
            builder.append(msg.obj).append("\n");
            mTvReceive.setText("接收到的消息：" + builder.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        ButterKnife.bind(this);
        initRabClient();

        mButSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  String msg = mEtContent.getText().toString();
                HashMap<String,Object> page = new HashMap<>();
                page.put("reservId","001");
            //    page.put("data",)
               //     send.sendMessage(msg);
            }
        });
    }


    private void initRabClient() {
        // Address[] addresses = new Address[]{new Address("192.168.1.103",5672),new Address("192.168.1.99:8080",5672)};
       // Connection connection = factory.newConnection();
        factory = new ConnectionFactory();
        factory.setHost("192.168.1.111");
        factory.setPort(5672);
        factory.setUsername("lin");
        factory.setPassword("123456");

       send = new SendImp(factory,this);
        send.start();

        recv = new RecvImp(factory,handler);
        recv.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        send.close();
        recv.close();
    }


}
