package com.hs.administrator.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

/**
 * 创建时间： 2019/1/11 0011.
 * 创建人：  yanbin
 * 功能：
 */

public class WindowService extends Service implements View.OnClickListener {

    private WindowManager manager;

    private WindowManager.LayoutParams mParams;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onClick(View view) {

    }
}
