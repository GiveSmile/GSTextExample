package com.hs.administrator.test.model;

import java.util.Observable;

/**
 * @auther : yanbin
 * @time : 2018/9/29 0029 16:52
 * @describe :
 */

public class Gamedaily extends Observable {
    public void postNewArticle(String content){
        //内容发生改变
        setChanged();
        //通知所有订阅者改变的内容
        notifyObservers(content);
    }

}
