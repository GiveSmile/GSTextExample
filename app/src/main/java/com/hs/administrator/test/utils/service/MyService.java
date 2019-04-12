package com.hs.administrator.test.utils.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.hs.administrator.test.R;
import com.hs.administrator.test.view.activity.MainActivity;
import com.hs.administrator.test.view.activity.TestServiceActivity;

/**
 * @auther : yanbin
 * @time : 2018/11/2 0002 15:57
 * @describe :测试 服务的使用
 */

public class MyService extends Service {

    private static final String TAG = "test==";

    private static final String CHANNEL_ID = "com.appname";

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setForegroundService();
        } else {
            Intent intent = new Intent(this, TestServiceActivity.class);
            PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("标题")
                    .setContentText("内容")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentIntent(pi)
                    .build();
            startForeground(1, notification);
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void setForegroundService() {
        NotificationChannel channel = new NotificationChannel("fore_service", "前台服务", NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
        Intent intentForeSerive = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentForeSerive, 0);
        Notification notification = new NotificationCompat.Builder(this) .setContentTitle("This is content title") .setContentText("This is content text") .setWhen(System.currentTimeMillis()) .setSmallIcon(R.mipmap.ic_launcher) .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)) .setContentIntent(pendingIntent) .build();
        startForeground(1, notification);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private void createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //设置通知渠道的名称
            String channeInNmae = "testChannelName";
            //设置通知的重要程度
            int importance = NotificationManager.IMPORTANCE_LOW;
            //构建通知渠道
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channeInNmae, importance);
            //      channel.setDescription(descr);
        }
    }

    private MyBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public class MyBinder extends Binder {
        public MyService startDownload() {
            Log.d(TAG, "startDownload() executed");
            return MyService.this;
        }
    }
}
