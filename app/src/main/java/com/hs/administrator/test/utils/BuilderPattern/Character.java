package com.hs.administrator.test.utils.BuilderPattern;

/**
 * @auther : yanbin
 * @time : 2018/11/1 0001 16:49
 * @describe :创造者模式 - 创建产品类
 */

public class Character {
    private String sex;
    private String face;
    private String clothes;
    void setSex(String sex){
        this.sex = sex;
    }
    void setFace(String face){ this.face = face;}
    void setClothes(String clothes){this.clothes = clothes;}
   public String show(){
        return "你创造了一个穿着"+ clothes  +"长的"+face+"的"+sex;
    }
}
