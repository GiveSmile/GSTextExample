package com.hs.administrator.test.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @auther : yanbin
 * @time : 2018/8/16 0016 17:07
 * @describe :
 */

public class SendImp extends Thread {
    ConnectionFactory factory;
    Connection connection = null;
    Channel channel;
    Context context;
    private final static String QUEUE_NAME = "yanbin";

    public SendImp(ConnectionFactory factory,Context context) {
        this.factory = factory;
        this.context = context;
    }

    @Override
    public void run() {


        try {
            connection = factory.newConnection();
            /**创建channel*/
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (Exception e) {
            android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "创建队列失败", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    /**
     * 发送消息
     *
     * @param message
     */
    public void sendMessage(final String message) {

        new Thread() {
            @Override
            public void run() {
                try {
                    //把网络访问的代码放在这里
                    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));

                } catch (Exception e) {
                   //
                    android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "连接服务器失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }.start();


    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            if (channel != null)
                channel.close();
            if (connection != null)
                connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

}
