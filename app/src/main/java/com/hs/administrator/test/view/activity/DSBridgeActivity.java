package com.hs.administrator.test.view.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.hs.administrator.test.R;

public class DSBridgeActivity extends AppCompatActivity {
    private Button mBut;
    private ImageView imageView;

    public static final String BIG_IMAGE_PATH = Environment.getExternalStorageDirectory().getPath() + "/big.jpg";
    public static final String SMALL_IMAGE_PATH = Environment.getExternalStorageDirectory().getPath() + "/small.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsbridge);
        mBut = findViewById(R.id.but_info);
        imageView = findViewById(R.id.imageView);
    }
}
