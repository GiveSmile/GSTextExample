package com.hs.administrator.test.testObserver;

import android.util.Log;

/**
 * @auther : yanbin
 * @time : 2018/11/13 0013 9:41
 * @describe :具体观察类
 */

public class Bee implements Insect {

    private int bId;//蜜蜂编号

    public Bee(int bId){
        this.bId = bId;
    }

    @Override
    public void work() {
        Log.d("test==","蜜蜂"+bId+"采蜜");
    }

    @Override
    public void unWork() {
        Log.d("test==","蜜蜂"+bId+"回家");
    }
}
