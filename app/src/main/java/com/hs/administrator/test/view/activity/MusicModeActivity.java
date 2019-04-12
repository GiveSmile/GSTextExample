package com.hs.administrator.test.view.activity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.hs.administrator.test.R;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * 音乐模式界面
 */
public class MusicModeActivity extends AppCompatActivity implements View.OnClickListener {
    private UUID serviceUuid;
    private UUID characteristicUuid;
    private int[] mWaveArray = new int[]{262, 277, 294, 311, 330, 349, 370, 392, 415, 440, 466, 494, 523};
    private int[] mEffectArray = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    private SoundPool mSoundPool;
    private int[] soundPoolid;
    private boolean flag = true;
    int i = 0;
    private PopupWindow popupWindow;
    private int from = 0;
    TextView effect_list_click, song_list_click;
    private Button connection, buttonPushed, mode1, mode2, mode3, surprise, OhOoh, OhOoh2, cuddly, sleeping,
            happy, happy_short, superHappy, sad, confused, fart1, fart2, fart3, didi, disconnection, music_birthday, music_star, music_happy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_mode);
        UIinit();
    }

    private void setActionBar() {
        setFullScreen();
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        assert actionBar != null;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.effect_list_click:
                from = Location.LEFT.ordinal();
                initPopupWindow(effect_list_click);

                break;
            case R.id.song_list_click:
                from = Location.LEFT.ordinal();
                initPopupWindow(song_list_click);
                break;
            default:
                break;
        }
    }

    public void UIinit() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(14)
                    .build();
        } else {
            mSoundPool = new SoundPool(14, AudioManager.STREAM_MUSIC, 0);
        }
        soundPoolid = new int[14];

        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
            }
        });


//        mPopWindowView = getLayoutInflater().inflate(R.layout.pop_window_layout, null);
//        mPopWindow = new PopupWindow(mPopWindowView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
//        mPopWindow.setOutsideTouchable(false);
//        mPopWindow.setFocusable(false);
//        mPopWindow.setTouchable(true);
        effect_list_click = findViewById(R.id.effect_list_click);
        song_list_click = findViewById(R.id.song_list_click);
        effect_list_click.setOnClickListener(this);
        song_list_click.setOnClickListener(this);
    }

    protected void initPopupWindow(View view) {
        View popupWindowView ;
        Log.d("popwindow","调用一次试试");
        if (view == effect_list_click) {
            popupWindowView = getLayoutInflater().inflate(R.layout.pop, null);
            if (connection == null)
            {
                initPopView(popupWindowView);
            }
        } else {
            popupWindowView = getLayoutInflater().inflate(R.layout.pop_window_layout, null);
            if (music_birthday == null)
            {
                music_birthday = popupWindowView.findViewById(R.id.music_birthday);
                music_birthday.setOnClickListener(this);
                music_star = popupWindowView.findViewById(R.id.music_star);
                music_star.setOnClickListener(this);
                music_happy = popupWindowView.findViewById(R.id.music_happy);
                music_happy.setOnClickListener(this);
            }
        }
        //内容，高度，宽度
        popupWindow = new PopupWindow(popupWindowView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);

        //动画效果
        if (Location.LEFT.ordinal() == from) {
        }
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color
                .transparent));
        int local[] = new int[2];
        view.getLocationOnScreen(local);
        popupWindowView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int height = popupWindowView.getMeasuredHeight();
        int width = popupWindowView.getMeasuredWidth();
        int x = local[0] - (getLayoutInflater().inflate(R.layout.activity_music_mode, null).getWidth() / 2) - width / 2;
        int y = local[1] - height;
        Log.e("SSS",x+"|||||||"+y);
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_music_mode, null), Gravity.NO_GRAVITY, x, y);
        Log.e("kkk",popupWindowView.isFocusable()+"|||||||"+popupWindowView.isFocusableInTouchMode()+"||||"+popupWindow.isFocusable());


        popupWindowView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }

    private void initPopView(View popupWindowView) {
        connection = popupWindowView.findViewById(R.id.connection);
        buttonPushed = popupWindowView.findViewById(R.id.buttonPushed);
        mode1 = popupWindowView.findViewById(R.id.mode1);
        mode2 = popupWindowView.findViewById(R.id.mode2);
        mode3 = popupWindowView.findViewById(R.id.mode3);
        surprise = popupWindowView.findViewById(R.id.surprise);
        OhOoh = popupWindowView.findViewById(R.id.OhOoh);
        OhOoh2 = popupWindowView.findViewById(R.id.OhOoh2);
        cuddly = popupWindowView.findViewById(R.id.cuddly);
        sleeping = popupWindowView.findViewById(R.id.sleeping);
        happy = popupWindowView.findViewById(R.id.happy);
        superHappy = popupWindowView.findViewById(R.id.superHappy);
        happy_short = popupWindowView.findViewById(R.id.happy_short);
        sad = popupWindowView.findViewById(R.id.sad);
        confused = popupWindowView.findViewById(R.id.confused);
        fart1 = popupWindowView.findViewById(R.id.fart1);
        fart2 = popupWindowView.findViewById(R.id.fart2);
        fart3 = popupWindowView.findViewById(R.id.fart3);
        didi = popupWindowView.findViewById(R.id.didi);
        disconnection = popupWindowView.findViewById(R.id.disconnection);

        connection.setOnClickListener(this);
        disconnection.setOnClickListener(this);
        buttonPushed.setOnClickListener(this);
        mode1.setOnClickListener(this);
        mode2.setOnClickListener(this);
        mode3.setOnClickListener(this);
        surprise.setOnClickListener(this);
        OhOoh.setOnClickListener(this);
        OhOoh2.setOnClickListener(this);
        cuddly.setOnClickListener(this);
        sleeping.setOnClickListener(this);
        happy.setOnClickListener(this);
        happy_short.setOnClickListener(this);
        superHappy.setOnClickListener(this);
        sad.setOnClickListener(this);
        confused.setOnClickListener(this);
        fart1.setOnClickListener(this);
        fart2.setOnClickListener(this);
        fart3.setOnClickListener(this);
        didi.setOnClickListener(this);
    }

    /**
     * 菜单弹出方向
     */
    public enum Location {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM;
    }

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        public void run() {
            sendMusicSing();
            // 每3秒执行一次

        }
    };

    private void stopSing() {
        //将线程销毁掉
        mHandler.removeCallbacks(mRunnable);
        i = 0;
    }

    private void sendMusicSing() {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void sendNoteSing(int speed) {
        byte[] bytes = new byte[3];
        bytes[1] = (byte) ((speed >> 8) & 0xff);
        bytes[2] = (byte) (speed & 0xff);
        System.out.println("==========此时speed是========" + Arrays.toString(bytes));
       try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendSoundSing(byte sound) {
           byte[] bytes = new byte[2];
        bytes[1] = sound;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    /**
     * set the activity display in full screen
     */
    private void setFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {      //点击返回键退出
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        if (mSoundPool != null) {
            mSoundPool.autoPause();
            mSoundPool.release();
            mSoundPool = null;
        }
        //将线程销毁掉
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;                                                  //返回true,表示允许创建的菜单显示出来
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (flag) {
            menu.clear();
        } else {
            menu.clear();
        }
        return true;
    }
}
