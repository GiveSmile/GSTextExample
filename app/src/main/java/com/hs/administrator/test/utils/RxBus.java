package com.hs.administrator.test.utils;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @auther : yanbin
 * @time : 2018/7/30 0030 11:32
 * @describe :有异常处理的
 */

public class RxBus {
    private static volatile RxBus instance;
    private final Relay<Object> mBus;

    public RxBus() {
        this.mBus = PublishRelay.create().toSerialized();
    }

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = Holder.BUS;
                }
            }
        }
        return instance;
    }
    public void post(Object obj) {
        mBus.accept(obj);
    }

    public <T> Observable<T> toObservable(Class<T> tClass) {
        return  mBus.ofType(tClass);
    }

    public Observable<Object> toObservable() {
        return mBus;
    }

    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }


    /**
     * 普通订阅解绑
     * @param disposable
     */
    public static   void  rxBusUnbund(CompositeDisposable disposable){
        if (null != disposable && !disposable.isDisposed()) {
            disposable.clear();
        }
    }
}
