package com.hs.administrator.test.security;

import android.util.Log;

/**
 * 创建时间： 2019/1/4 0004.
 * 创建人：  yanbin
 * 功能：
 */

public class Student {
    //默认的构造方法
    Student(String string){
        Log.d("testReflect:  ","默认的构造方法s == "+string);
    }
    //无参的构造方法
    public Student(){
        Log.d("testReflect:  ","调用了公有丶无参数的构造方法");
    }
    //有一个参数的构造方法
    public Student(char name){
        Log.d("testReflect","姓名: "+name);
    }
    //有多个参数的构造方法
    public Student(String name,int ege){
        Log.d("testReflect","姓名："+name+" 年龄: "+ege);
    }

    //受保护的构造方法
    protected Student(boolean n){
        Log.d("testReflect","受保护的构造方法:"+n);
    }
    //私有的构造方法
    private Student(int age) {
        Log.d("testReflect","私有的构造方法  年龄: "+age);
    }
}
