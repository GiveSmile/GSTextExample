package com.hs.administrator.test.utils;

import android.content.Intent;

/**
 * @auther : yanbin
 * @time : 2019/4/12 0012 17:51
 * @describe : 单例模式
 */
public class SingleInstanceDemo {

    private SingleInstanceDemo() {

    }

    //懒汉式
    private static SingleInstanceDemo singleInstanceDemo;


    public synchronized static SingleInstanceDemo getInstance() {
        if (singleInstanceDemo == null) {
            singleInstanceDemo = new SingleInstanceDemo();
        }
        return singleInstanceDemo;
    }

    //饿汉式

    private static SingleInstanceDemo singleInstanceDemo2 = new SingleInstanceDemo();

    private synchronized static SingleInstanceDemo getInstanceTwo() {
        return singleInstanceDemo2;
    }

    //dcl写法
    private static SingleInstanceDemo mSingleInstanceDemo;

    public static SingleInstanceDemo getmSingleInstanceDemo() {
        if (mSingleInstanceDemo == null) {
            synchronized (SingleInstanceDemo.class) {
                mSingleInstanceDemo = new SingleInstanceDemo();
            }
        }
        return mSingleInstanceDemo;
    }

    //静态内部类写法
    public static class InnerClass{
        private static final SingleInstanceDemo singLeInstance = new SingleInstanceDemo();

    }

    public static SingleInstanceDemo getInstances(){
        return InnerClass.singLeInstance;
    }

    //枚举类写法

}
