package com.hs.administrator.test.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hs.administrator.test.R;
import com.unity3d.player.UnityPlayer;

public class TestSocketActivity extends AppCompatActivity {

    private EditText mEtMessage;
    private TextView mTvSend, mTvGetMessage;
    private String getMessage;
    //private SocketClient

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_socket);
        mEtMessage = (EditText) findViewById(R.id.et_test_message);
        mTvSend = (TextView) findViewById(R.id.tv_send);
        mTvGetMessage = (TextView) findViewById(R.id.tv_getMessage);
        getMessage = mEtMessage.getText().toString();
        mTvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnityPlayer.UnitySendMessage("center", "CalledFromAndroid", "Hello Unity3D");
                startActivity(new Intent(TestSocketActivity.this, UnityActivity.class));
            }
        });

    }
}
