package com.hs.administrator.test.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.hs.administrator.test.INetEvent;
import com.hs.administrator.test.TestBaseActivity;
import com.hs.administrator.test.utils.NetUtils;

/**
 * 创建时间： 2018/12/19 0019.
 * 创建人：  yanbin
 * 功能：
 */

public class NetStateReceiver extends BroadcastReceiver {
    private INetEvent iNetEvent = TestBaseActivity.iNetEvent;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            if (iNetEvent != null){
                iNetEvent.onNetChang(NetUtils.getNetWorkState(context));
            }
        }
    }
}
