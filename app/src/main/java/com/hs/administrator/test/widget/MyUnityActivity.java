package com.hs.administrator.test.widget;

import android.content.ContextWrapper;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * @auther : yanbin
 * @time : 2018/10/15 0015 10:27
 * @describe :
 */

public class MyUnityActivity extends UnityPlayer {

    public MyUnityActivity(ContextWrapper contextWrapper) {
        super(contextWrapper);
    }

    @Override
    protected void kill() {
        super.kill();
    }
}
