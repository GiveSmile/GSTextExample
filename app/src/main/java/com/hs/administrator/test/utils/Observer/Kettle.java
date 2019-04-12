package com.hs.administrator.test.utils.Observer;



/**
 * @auther : yanbin
 * @time : 2018/11/9 0009 10:53
 * @describe :观察者模式
 */

public class Kettle<T> {
    Observer<T> observer;

    public void publishEent(T t) {
        if (observer == null) {
            throw new NullPointerException("I MISS YOU");
        }
        notifyData(t);
    }

    private void notifyData(T t) {
        observer.receiverEvent(t);
    }

    public void registObserver(Observer<T> observer) {
        this.observer = observer;
    }

    public void unregistObserver() {
        this.observer = null;
    }

}
