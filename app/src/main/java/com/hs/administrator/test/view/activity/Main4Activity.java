package com.hs.administrator.test.view.activity;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.administrator.test.widget.LabelFlowLayout;
import com.hs.administrator.test.R;
import com.hs.administrator.test.adapter.Main4Adapter;
import com.hs.hstechsdklibrary.commonutil.ToastUtils;
import com.hs.hstechsdklibrary.commonutil.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main4Activity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    @Bind(R.id.flow_label)
    LabelFlowLayout mFlowLabel;
    @Bind(R.id.tv_open)
    TextView mTvOpen;
    @Bind(R.id.play)
    TextView mTvPlay;
    @Bind(R.id.Rl_box)
    RelativeLayout mRlBox;
    @Bind(R.id.iv_line)
    ImageView mQrlineView;
    @Bind(R.id.et_shuru)
    EditText mEtShuRu;
    @Bind(R.id.play_voice)
    TextView mTvPlayVoice;

    private TextToSpeech tts;

    private ArrayList<String> mData;
    private boolean isExport = false;//判断展开收起的标志
    private Main4Adapter mAdapter;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);
        mFlowLabel.setLimitLines(2);
        Utils.init(Main4Activity.this);
        initData();
        initListener();

        tts = new TextToSpeech(Main4Activity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
             if (i == TextToSpeech.SUCCESS){
                 ToastUtils.showShort("初始化成功请进行下一步操作");
             }else {
                 ToastUtils.showShort("初始化失败");
             }
            }
        });


        mAdapter = new Main4Adapter(Main4Activity.this, mData);
        mFlowLabel.setAdapter(mAdapter);
        mAdapter.setOnCheckClickListener(new Main4Adapter.ItemCheckClickListener() {
            @Override
            public void onTextViewClick(List<String> data, int position) {
                int i = position + 1;
                Toast.makeText(Main4Activity.this, "你点击了第" + i + "条", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void speak(View view){
        String content = mEtShuRu.getText().toString();
        if (content.isEmpty()){
            ToastUtils.showShort("输入的值为空");
        }else {
            tts.speak(content,TextToSpeech.QUEUE_ADD,null);
        }
    }

    private void initData() {
        mData = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            mData.add("帅啊" + i);
        }
        for (int i = 0; i < 14; i++) {
            mData.add("美啊" + i);
        }
    }

    private void initListener() {
        mediaPlayer = new MediaPlayer();
        // mediaPlayer.setOnCompletionListener(this);
        try {
            mediaPlayer.setDataSource("http://boscdn.bpc.baidu.com/v1/developer/99ce09cd-84de-4f7d-8eba-bc633440bc9c.mp3");
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            ToastUtils.showShort(e.getMessage());
        }

        mTvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
        mTvOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isExport = !isExport;
                refreshLabel();
            }
        });



    }


    private void refreshLabel() {
        mTvPlayVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  tts.speak(content,TextToSpeech.QUEUE_ADD,null);

                // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                tts.setPitch(1.0f);
                // 设置语速
                tts.setSpeechRate(1.0f);
                //播放语音
                tts.speak("dasfasdfasdfas", TextToSpeech.QUEUE_ADD, null,null);
            }
        });

        if (mFlowLabel.getCurLines() < 2) {
            mTvOpen.setVisibility(View.GONE);
        } else {
            mTvOpen.setVisibility(View.VISIBLE);
        }
        if (mTvOpen.getText().equals("收起")) {
            mFlowLabel.setLimitLines(2);
            mTvOpen.setText("展开");
        } else {
            mFlowLabel.setLimitLines(0);
            mTvOpen.setText("收起");
        }
        mFlowLabel.postInvalidate();
    }

    private void chang(String string, Drawable drawable) {
        mTvOpen.setText(string);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getIntrinsicHeight());
        mTvOpen.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            int result1 = tts.setLanguage(Locale.US);
            int result2 = tts.setLanguage(Locale.CHINESE);
            if (result1 == TextToSpeech.LANG_MISSING_DATA || result1 == TextToSpeech.LANG_NOT_SUPPORTED
                    || result2 == TextToSpeech.LANG_MISSING_DATA || result2 == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}


