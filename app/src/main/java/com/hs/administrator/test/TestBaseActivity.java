package com.hs.administrator.test;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


/**
 * 创建时间： 2018/12/19 0019.
 * 创建人：  yanbin
 * 功能：
 */

public class TestBaseActivity extends AppCompatActivity implements INetEvent {
    public static INetEvent iNetEvent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        iNetEvent = this;
    }

    @Override
    public void onNetChang(int netWorkState) {
        Toast.makeText(this, "1111111", Toast.LENGTH_SHORT).show();
    }
}
