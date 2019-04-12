package com.hs.administrator.test.view.activity;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hs.administrator.test.R;

import net.frakbot.jumpingbeans.JumpingBeans;

import java.util.Observable;

public class tiaodongdewenziActivity extends AppCompatActivity {
    private JumpingBeans jumpingBeans1, jumpingBeans2, jumpingBeans3;
    private TextView mTextView;
    private AudioManager audioMa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiaodongdewenzi);
        final TextView textView = (TextView) findViewById(R.id.jumping_text_1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置为最大音量

                audioMa = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audioMa.setStreamVolume(AudioManager.STREAM_MUSIC, audioMa.getStreamMaxVolume
                        (AudioManager.STREAM_MUSIC), AudioManager.FLAG_SHOW_UI);

            }
        });
        jumpingBeans1 = JumpingBeans.with(textView)
                .appendJumpingDots()
                .build();
        final TextView textView1 = (TextView) findViewById(R.id.jumping_text_2);
        jumpingBeans2 = JumpingBeans.with(textView1)
                .makeTextJump(0, textView1.getText().toString().indexOf(" "))
                .setIsWave(false)
                .setLoopDuration(1000)
                .setWavePerCharDelay(3)
                .setAnimatedDutyCycle(0.99f)//跳跃周期停顿
                .build();
        final TextView textView2 = (TextView) findViewById(R.id.jumping_text_3);
        jumpingBeans3 = JumpingBeans.with(textView2)
                .makeTextJump(1, textView2.getText().toString().indexOf("三"))
                .setIsWave(false)//不想让这些点在波中跳跃
                .setLoopDuration(1000)//循环时间
                .setAnimatedDutyCycle(0.99f)//跳跃周期停顿
                .build();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        jumpingBeans1.stopJumping();
        jumpingBeans2.stopJumping();
    }
}
