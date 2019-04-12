package com.hs.administrator.test;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.hs.hstechsdklibrary.autosize.AutoSizeConfig;
import com.hs.hstechsdklibrary.commonutil.Utils;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;


/**
 * @auther : yanbin
 * @time : 2018/11/5 0005 17:25
 * @describe :
 */

public class TestApp extends Application {
    private static TestApp instance;


    public static TestApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(instance);
        Utils.initLogUtils(true);
        AutoSizeConfig.getInstance()
                .setLog(true)
                .init((Application) getInstance().getApplicationContext())
                .setUseDeviceSize(false);
        // [可选]设置是否打开debug输出，上线时请关闭，Logcat标签为"MtaSDK"
        StatConfig.setDebugEnable(true);
        // 基础统计API
        StatService.registerActivityLifecycleCallbacks(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        PluginManager.getInstance(base).init();
    }
}
