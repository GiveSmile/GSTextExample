package com.hs.administrator.test.utils;

import com.hs.hstechsdklibrary.commonutil.LogUtil;
import com.hs.hstechsdklibrary.commonutil.ToastUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.http.POST;

/**
 * @auther : yanbin
 * @time : 2018/11/1 0001 9:27
 * @describe :
 */

public class SocketTest {
    private static final int PORT = 8080;
    private List<Socket> mList = new ArrayList<Socket>();
    private ServerSocket serverSocket = null;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;
    private String sendMsg;

    public static void main(String[] args) {
        new SocketTest();
    }

    public SocketTest() {
        try {
            serverSocket = new ServerSocket(PORT);
            mExecutorService = Executors.newCachedThreadPool();
            LogUtil.d("test==", "服务器已启动");
            Socket client = null;
            while (true) {
                client = serverSocket.accept();
                mList.add(client);
                mExecutorService.execute(new Service(client));
            }
        } catch (Exception e) {
            ToastUtils.showShort(e.toString() + "");
        }
    }

    class Service implements Runnable {
        private Socket socket;
        private BufferedReader in = null;
        private PrintWriter printWriter = null;

        public Service(Socket socket) {
            this.socket = socket;
            try {
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                printWriter.print("成功连接到服务器" + "服务器发送成功");
                LogUtil.d("test==", "成功连接到服务器");
            } catch (IOException e) {
                ToastUtils.showShort(e.toString() + "");
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if ((receiveMsg = in.readLine()) != null) {
                        if (receiveMsg.equals("0")) {
                            printWriter.print("客户端请求断开连接");
                            mList.remove(socket);
                            in.close();
                            socket.close();
                            break;
                        } else {
                            sendMsg = "我已发送：" + receiveMsg + "(服务器发送)";
                            printWriter.print(sendMsg);
                        }
                    }
                }
            } catch (Exception e) {
                ToastUtils.showShort(e.toString());
            }
        }
    }

}
