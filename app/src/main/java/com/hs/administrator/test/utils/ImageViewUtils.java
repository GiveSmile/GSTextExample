package com.hs.administrator.test.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/10 0010.
 */

public class ImageViewUtils {
    //用来保存软引用的bitmap对象，用来防止OOM
    private Map<String, SoftReference<Bitmap>> imageCaches = Collections.synchronizedMap(new HashMap<String, SoftReference<Bitmap>>());

    //按照指定大小加载图片到内存，实际上大熊基本不可能是指定的这个大小，因为图片会按照自己的规则进行缩放
    public Bitmap decodeSampledBitmapFromFile(String pathName, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        options.inSampleSize = calculateinSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName, options);
    }

    //在保证解析出的bitmap宽高分别大于目标尺寸宽高的前提下，取可能的inSampleSize的最大值
    public int calculateinSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    //添加软引用bitmap到map中，使其缓存
    public void addBitmapToCache(String path) {
        imageCaches.put(path, new SoftReference<Bitmap>(BitmapFactory.decodeFile(path)));
    }



    /*从缓存中取出软引用的bitmap对象*/
    public Bitmap getBitmapFronCache(String path){
        SoftReference<Bitmap> softReference = imageCaches.get(path);
        if (softReference != null) {
            //如果软引用还存在，那么直接就可以获取这个对象的相关数据这样就实现了cache
            return softReference.get();
        }else {
            //如果不存在则表示GC已经将其回收，我们需要重新实例化对象，获取数据信息
            return null;
        }
    }

    public void clearCache(){
        imageCaches.clear();
    }

}
