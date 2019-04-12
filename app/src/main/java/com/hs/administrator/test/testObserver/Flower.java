package com.hs.administrator.test.testObserver;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther : yanbin
 * @time : 2018/11/13 0013 9:46
 * @describe :
 */

public class Flower implements Plant {
    private List<Insect> insects = new ArrayList<>();

    @Override
    public void registerInsect(Insect insect) {
        insects.add(insect);
    }

    @Override
    public void unregisterInsect(Insect insect) {
        insects.add(insect);
    }

    @Override
    public void notifyInsect(boolean b) {
        if (b) {
            Log.d("test==","花开了 快来玩嘛");
            for (Insect insect:insects){
                insect.work();
            }
        } else {
            Log.d("test==","花要谢了 下次再来");
            for (Insect insect : insects){
                insect.unWork();
            }
        }
    }
}
