package com.hs.administrator.test.view.activity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hs.administrator.test.R;
import com.hs.administrator.test.utils.service.MyService;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestServiceActivity extends AppCompatActivity {

    @Bind(R.id.but_statr)
    Button mButStatr;
    @Bind(R.id.but_stop)
    Button mButStop;
    @Bind(R.id.but_bind)
    Button mButBind;
    @Bind(R.id.but_unbind)
    Button mButUnBind;


    private MyService myService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder binder = (MyService.MyBinder) iBinder;
            myService = binder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);
        ButterKnife.bind(this);
        mButStatr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(new Intent(TestServiceActivity.this, MyService.class));
                } else {
                    startService(new Intent(TestServiceActivity.this, MyService.class));
                }
            }
        });
        mButStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(TestServiceActivity.this, MyService.class));

            }
        });
        mButUnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestServiceActivity.this, MyService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });
        mButUnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(connection);
            }
        });

    }

    private Notification getNotification() {
        Notification.Builder mBuilder = new Notification.Builder(TestServiceActivity.this);
        mBuilder.setShowWhen(false);
        mBuilder.setAutoCancel(false);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setLargeIcon(((BitmapDrawable) getDrawable(R.mipmap.testtoux)).getBitmap());
        mBuilder.setContentText("thisiscontent");
        mBuilder.setContentTitle("this is title");
        return mBuilder.build();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
