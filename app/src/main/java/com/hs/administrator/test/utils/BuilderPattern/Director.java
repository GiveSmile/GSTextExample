package com.hs.administrator.test.utils.BuilderPattern;

/**
 * @auther : yanbin
 * @time : 2018/11/1 0001 17:03
 * @describe :创造者模式 - 构建过程类
 */

public class Director {
    private BUilder bUilder = null;

    public Director(BUilder bUilder){this.bUilder = bUilder;}

   public  Character character(String sex,String face,String clothes){
        this.bUilder.setSex(sex);
        this.bUilder.setClothes(clothes);
        this.bUilder.setFace(face);
        return bUilder.build();
    }
}
