package com.hs.administrator.test.utils.Observer;

import android.util.Log;

/**
 * @auther : yanbin
 * @time : 2018/11/9 0009 11:06
 * @describe :
 */

public abstract class Peopel implements Observer<String> {
    @Override
    public void receiverEvent(String s) {
        Log.d("test==",s);
        dealWithEvent();
    }
    public abstract void dealWithEvent();
}
