package com.hs.administrator.test.view.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.utils.BuilderPattern.BUilder;
import com.hs.administrator.test.utils.BuilderPattern.Character;
import com.hs.administrator.test.utils.BuilderPattern.ConcreteBuilder;
import com.hs.administrator.test.utils.BuilderPattern.Director;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketActivity extends AppCompatActivity {
    private TextView mTvSend, mTvGetMessage, mTvDisconnect, mTvConnect;
    private EditText mEtMessage;
    private static final String HOST = "192.168.1.171";
    private static final int PORT = 8080;
    private PrintWriter printWriter;
    private BufferedReader in;
    private ExecutorService executorService = null;
    private String receiveMsg;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mTvGetMessage.setText(msg.obj + "");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        mEtMessage = (EditText) findViewById(R.id.edittext);
        mTvConnect = (TextView) findViewById(R.id.tv_connect);
        mTvSend = (TextView) findViewById(R.id.tv_send);
        mTvGetMessage = (TextView) findViewById(R.id.tv_getMessage);
        mTvDisconnect = (TextView) findViewById(R.id.tv_disconnect);
        BUilder bUilder = new ConcreteBuilder();
        Director director = new Director(bUilder);
        Character character = director.character("基佬", "秀美", "花衬衫");
        //mTvGetMessage.setText(character.show());
        new Thread() {
            @Override
            public void run() {
                try {

                    double db = 2.0 / 0.0;
                    for (int i = 5000; i <db; i++) {
                        Message message = new Message();
                        message.what = 1;
                        message.obj = i;
                        Thread.sleep(1000);
                        handler.sendMessage(message);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


        executorService = Executors.newCachedThreadPool();
        mTvConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executorService.execute(new connectService());
            }
        });
        mTvDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executorService.execute(new sendService("0"));
            }
        });
        mTvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendMsg = mEtMessage.getText().toString();
                executorService.execute(new sendService(sendMsg));
            }
        });

    }

    private class sendService implements Runnable {
        private String msg;

        sendService(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            printWriter.println(this.msg);
        }
    }

    private class connectService implements Runnable {
        @Override
        public void run() {
            try {
                Socket socket = new Socket(HOST, PORT);
                socket.setSoTimeout(60000);
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                receiveMsg();
            } catch (Exception e) {
                Log.e("test==", ("connectService:" + e.getMessage()));
            }
        }
    }

    private void receiveMsg() {
        try {
            while (true) {
                if ((receiveMsg = in.readLine()) != null) {
                    Log.d("test==", "receiveMsg:" + receiveMsg);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTvGetMessage.setText(receiveMsg + "\n\n" + mTvGetMessage.getText());
                        }
                    });
                }
            }
        } catch (IOException e) {
            Log.e("test==", "receiveMsg: ");
            e.printStackTrace();
        }
    }

}
