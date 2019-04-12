package com.hs.administrator.test.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hs.administrator.test.R;



public class LifecycleTwoActivity extends AppCompatActivity {

    private String msg = "LifecycletTwoActivity";
    private Button mButBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_two);
        mButBack = findViewById(R.id.but_back);
        mButBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Log.d(msg, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(msg, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(msg, "onRestart");
    }
}
