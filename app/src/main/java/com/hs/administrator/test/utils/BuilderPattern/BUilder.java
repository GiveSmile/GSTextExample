package com.hs.administrator.test.utils.BuilderPattern;

/**
 * @auther : yanbin
 * @time : 2018/11/1 0001 16:55
 * @describe :创造者模式 - 创建抽象Builder接口
 */

public interface BUilder {
    void setSex(String sex);
    void setFace(String face);
    void setClothes(String clothes);
    Character build();
}
