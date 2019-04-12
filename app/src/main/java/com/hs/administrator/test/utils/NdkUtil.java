package com.hs.administrator.test.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther : yanbin
 * @time : 2018/10/15 0015 10:55
 * @describe :
 */


public class NdkUtil {
    private static Context context;
    private static NdkUtil instance;
    private List<NdkMessageListener> listenerList = new ArrayList<>();
    String s;

    static {
        System.loadLibrary("androidplugin");
    }

    private NdkUtil(Context context) {
        this.context = context;
        if (1 != initNDK()) {
            Toast.makeText(context, "初始化失败", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "初始化成功", Toast.LENGTH_SHORT).show();
        }
    }

    public static NdkUtil getInstance(Context context) {
        if (null == instance) {
            instance = new NdkUtil(context);
        }
        return instance;
    }

    public static boolean setOnNdkMessageListener(Context context, NdkMessageListener listener) {
        if (getInstance(context).listenerList.contains(listener)) {
            return false;
        } else {
            getInstance(context).listenerList.add(listener);
            return true;
        }
    }


    public native int initNDK();

    public void messageFromNDK(String str) {
      /*  for (NdkMessageListener listener : listenerList) {
            listener.OnMessageNotify(str);
        }*/
        Log.d("test==","3D模块已经启动");
    }

}
