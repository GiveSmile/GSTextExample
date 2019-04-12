package com.hs.administrator.test.view.activity;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.administrator.test.R;
import com.hs.administrator.test.utils.LmiotDialog;

public class AndroidSizeActivity extends AppCompatActivity {

    private TextView textTv;
    private Button mButStop, mButRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_size);
        textTv = findViewById(R.id.textTv);
        textTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sss="";
                try {
                    ApplicationInfo applicationInfo =getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
                    if (applicationInfo !=null && applicationInfo.metaData !=null){
                     sss = (String) applicationInfo.metaData.get("test");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                textTv.setText(sss);
            }
        });
        /*mButRestart = findViewById(R.id.but_restart);
        mButStop = findViewById(R.id.but_stop);
        mButStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LmiotDialog.show(AndroidSizeActivity.this,"加载中");
            }
        });
        mButRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LmiotDialog.hidden();
            }
        });*/
    }

}
