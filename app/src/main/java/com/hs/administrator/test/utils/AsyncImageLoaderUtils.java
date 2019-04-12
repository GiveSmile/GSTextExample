package com.hs.administrator.test.utils;

/**
 * @Time 9:24
 * @Name: AsyncImageLoaderUtils
 * @Description: java类作用描述
 */
import java.io.InputStream;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Log;

public class AsyncImageLoaderUtils {

    /**
     * 使用新式的LruCache取代SoftReference缓存图片，异步加载图片。
     *
     * 备注：图片的URL作为缓存图片时LruCache的 Key
     * */

    private final int CACHE_SIZE = 4 * 1024 * 1024; // 4MiB
    private final int MESSAGE_WHAT = 201;

    private LruCache<String, Bitmap> mMemoryCache;

    public AsyncImageLoaderUtils() {
        mMemoryCache = new LruCache<String, Bitmap>(CACHE_SIZE) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }};
    }


    public Bitmap loadBitmap(final String imageUrl, final ImageLoadedListener imageLoadedListener)
    {
        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message message) {

                switch(message.what)
                {
                    case MESSAGE_WHAT:
                        imageLoadedListener.imageLoaded((Bitmap) message.obj, imageUrl);
                }
            }
        };

        if (mMemoryCache.get(imageUrl)!=null) {
            Log.d(this.getClass().getName(),"发现图片缓存，从缓存中读取！");

            Bitmap bmp = mMemoryCache.get(imageUrl);

            Message message = handler.obtainMessage();
            message.what=MESSAGE_WHAT;
            message.obj=bmp;
            handler.sendMessage(message);

            return bmp;
        }

        Log.d(this.getClass().getName(),"图片没有缓存，从网络加载！");

        new Thread() {
            @Override
            public void run() {
                Bitmap bmp = loadBitmapFromUrl(imageUrl);
                mMemoryCache.put(imageUrl, bmp);

                Message message = handler.obtainMessage();
                message.what=MESSAGE_WHAT;
                message.obj=bmp;
                handler.sendMessage(message);
            }
        }.start();

        return null;
    }


    public static Bitmap loadBitmapFromUrl(String url) {

        Log.d("As.loadBitmapFromUrl()","Loading -> "+url);

        InputStream is = null;
        try {
            is = (InputStream) new URL(url).getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bitmap bmp=BitmapFactory.decodeStream(is);
        return bmp;
    }

    public interface ImageLoadedListener {
        public void imageLoaded(Bitmap bitmap, String url);
    }
}

