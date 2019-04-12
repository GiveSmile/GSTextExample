package com.hs.administrator.test.utils;

/**
 * Created by Administrator on 2019/4/10 0010.
 */

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

/*
 * 使用Android新式LruCache缓存图片，基于线程池异步加载图片。
 * 基本思路：开辟一个线程池下载网络图片，同时创建一个LruCache作为Android内存缓存混存图片。
 * 上层应用传递过来一个URL要求从该URL下载图片时，首先检查LruCache中是否存在以该URL为索引的缓存图片，
 * 若有，则直接从缓存中读出来返回给上层应用；若没有，此时再开辟线程下载，下载完成后将此bitmap埋入缓存。
 * 备注：图片的url作为缓存图片时LruCache的 Key。
 * LruCache在内存中的缓存模型为<K,V>。
 */

public class AsyncImageLoader {

    private ExecutorService pool;
    private Handler handler;
    private ImageLoadedListener listener;
    private static String LruCachekey = "";
    private static String LogHead = "AsynclmageLoader";

    private final int WHAT = 0xe001;

    // 默认的线程池容量
    private int DEFAULT_TASK_NUMBER = 10;

    // 网络超时时间：30秒
    private static int TIMEOUT = 30 * 1000;

    private LruCache<String, Bitmap> mMemoryCache;
    // 4MB缓存大小
    private final int CACHE_SIZE = 4 * 1024 * 1024;

    public AsyncImageLoader(int asyncTaskNumber) {
        initialization(asyncTaskNumber);
    }

    public AsyncImageLoader() {
        // 默认的构造函数初始化线程池容量为：TASK_NUMBER
        initialization(DEFAULT_TASK_NUMBER);
    }

    // 初始化
    private void initialization(int asyncTaskNumber) {
        mMemoryCache = new LruCache<String, Bitmap>(CACHE_SIZE) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

        // 创建容量为 asyncTaskNumber 的线程池。
        pool = Executors.newFixedThreadPool(asyncTaskNumber);

        handler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                switch (message.what) {
                    case WHAT:
                        listener.imageLoaded((Bitmap) message.obj);

                }
            }
        };
    }


    // 异步设置一个ImagView的Bitmap（该ImageView的Bitmap从网络加载）
    // 该方法为public，作为该工具类的外部调用接口。
    public void asyncSetImageBitmap(String url, final ImageView view) {
        download(url, new ImageLoadedListener() {
            @Override
            public void imageLoaded(Bitmap bitmap) {
                view.setImageBitmap(bitmap);
            }
        });
    }

    // 异步的从一个URL下载一个Bitmap。
    // 下载成功后，回调imageLoaded(Bitmap bitmap, String url)；
    // 该方法为public，作为该工具类的外部调用接口。
    public void download(String url, ImageLoadedListener listener) {
        this.listener = listener;
        // 首先从缓存中检查是否存在以url为key的bitmap。
        // 若有，则直接从缓存中读取使用，不再使用线程重复加载。
        String[] strs = url.split("/");
        LruCachekey = strs[strs.length - 1].toString();

        Bitmap bmp = mMemoryCache.get(LruCachekey);
        Log.d(LogHead+"bitmap","我是查找的值："+ LruCachekey);
        if (bmp != null) {
            Log.d(LogHead, "读取缓存" + url + " 已经缓存，无须重复下载！");
            sendResult(bmp);
            return;
        }

        Thread t = new DownloadThread(url);
        pool.execute(t);
    }


    // 开辟一个下载线程
    private class DownloadThread extends Thread {

        private String url;

        public DownloadThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            Bitmap bmp = loadBitmapFromURL(url);
            // 将新的bitmap埋入缓存

            if (bmp != null) {
                Log.d(LogHead, "下载后返回的bitmap为：" + bmp);
                mMemoryCache.put(LruCachekey, bmp);
                Log.d(LogHead+"bitmap","我是插入的值："+ LruCachekey);
              //03D2697,
            }
            sendResult(bmp);

        }
    }

    // 发送消息通知：bitmap已经下载完成。
    private void sendResult(Bitmap bitmap) {
        Message message = handler.obtainMessage();
        message.what = WHAT;
        message.obj = bitmap;
        handler.sendMessage(message);
    }

    // 回调函数
    public interface ImageLoadedListener {
        public void imageLoaded(Bitmap bitmap);
    }

    // 给定一个URL，从这个URL下载Bitmap
    public static Bitmap loadBitmapFromURL(String url) {
        if (!url.contains("http://")) {
            url = "http://" + url;
        }

        Log.d(LogHead, "线程：" + Thread.currentThread().getId() + "开始下载: " + url);

        Bitmap bmp = null;
        try {
            byte[] imageBytes = loadRawDataFromURL(url);
            bmp = BitmapFactory.decodeByteArray(imageBytes, 0,
                    imageBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bmp;
    }

    // 给定一个URL，从这个URL下载原始数据块。
    public static byte[] loadRawDataFromURL(String u) throws Exception {
        URL url = new URL(u);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 配置基础网络链接参数
        conn.setConnectTimeout(TIMEOUT);
        conn.setReadTimeout(TIMEOUT);

        InputStream is = conn.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final int BUFFER_SIZE = 1024 * 5;
        final int EOF = -1;

        int c;
        byte[] buf = new byte[BUFFER_SIZE];

        while (true) {
            c = bis.read(buf);
            if (c == EOF)
                break;

            baos.write(buf, 0, c);
        }

        conn.disconnect();
        is.close();

        byte[] data = baos.toByteArray();
        baos.flush();

        return data;
    }
}
