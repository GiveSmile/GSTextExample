package com.hs.administrator.test.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hs.administrator.test.R;
import com.hs.administrator.test.widget.MyUnityActivity;
import com.unity3d.player.*;

public class UnityActivity extends com.unity3d.player.UnityPlayerActivity {
    private RelativeLayout mRl;
    private Button mButSendMassage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity);
        mUnityPlayer = new UnityPlayer(this);
        mRl = findViewById(R.id.rl);
        mUnityPlayer = new MyUnityActivity(this);
        mButSendMassage = findViewById(R.id.but_send_massage);
        mRl.addView(mUnityPlayer.getView());
        mButSendMassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnityPlayer.UnitySendMessage("center", "CalledFromAndroid", "Hello Unity3D");
            }
        });
    }
}
