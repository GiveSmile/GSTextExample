package com.hs.administrator.test.utils;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


import com.hs.hstechsdklibrary.commonutil.ToastUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Handler;

import static android.util.Log.i;

/**
 * @auther : yanbin
 * @time : 2018/10/31 0031 13:50
 * @describe :socket通信层
 */

public class SocketClient {
    private Socket client;
    private Context context;
    private int port;
    private String site;
    private Thread thread;
    private static Handler mHandler;
    private boolean isClient = false;
    private PrintWriter out;
    private InputStream inputStream;
    private String string;

    public void openClientThread(){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client = new Socket(site,port);
                    client.setSoTimeout(3000);//设置超时时间
                    if (client != null){
                        isClient =true;

                    }
                } catch (Exception e) {
                    ToastUtils.showShort(e.toString()+"");
                }
            }
        });
    }

    /**
     * @effect 得到输出字符串
     */
    public void forOut(){
        try {
            out = new PrintWriter(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * @steps read();
     * @effect 得到输入的字符串
     */
    public void forIn(){
        while (isClient){
            try {
                inputStream = client.getInputStream();
                byte[] bt = new byte[50];
                inputStream.read(bt);
                string = new String(bt,"UTF-8");
            } catch (Exception e) {
                ToastUtils.showShort(e.toString()+"");
            }
            if (string != null){
                Message message = new Message();
                message.obj = string;
             //   mHandler.sendMessage(message);
            }
        }
    }
    /**
     * @steps write();
     * @effect 发送消息
     * */
    public void sendMsg(final String str) {
        new Thread ( new Runnable ( ) {
            @Override
            public void run() {
                if (client!=null) {
                    out.print ( str );
                    out.flush ();
                    Log.i ( "outtt",out+"" );
                }else
                {
                    isClient=false;
                    Toast.makeText ( context,"网络连接失败",Toast.LENGTH_LONG ).show ();
                }
            }
        } ).start ();

    }

}
