package com.hs.administrator.test.testObserver;

/**
 * @auther : yanbin
 * @time : 2018/11/13 0013 9:44
 * @describe :
 */

public interface Plant {
    public void registerInsect(Insect insect);
    public void unregisterInsect(Insect insect);
    public void notifyInsect(boolean isOpen);
}
