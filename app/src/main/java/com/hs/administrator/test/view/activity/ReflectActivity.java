package com.hs.administrator.test.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hs.administrator.test.R;
import com.hs.administrator.test.security.Constructors;

import java.lang.reflect.Constructor;

public class ReflectActivity extends AppCompatActivity {

    private Class aClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        try {
            aClass = Class.forName("com.hs.administrator.test.security.Student");
            Log.d("testReflect","*************************所有公有构造方法*********************************");
            Constructor[] constructors = aClass.getConstructors();
            for(Constructor constructor :constructors){
                Log.d("testReflect",constructor+"");
            }
            Log.d("testReflect","*************************所有构造方法*********************************");
            constructors = aClass.getDeclaredConstructors();

            for (Constructor c : constructors){
                Log.d("testReflect",c+"");
            }
            Log.d("testReflect","*************************所有公有无参构造方法*********************************");

//           Constructor con = aClass.getConstructor(null);
//
//            Log.d("testReflect",con+"");
//            //调用构造方法
//            Object object =con.newInstance();
//            Log.d("testReflect","*************************获取私有构造方法，并调用*********************************");
//            con = aClass.getDeclaredConstructor(char.class);
//            Log.d("testReflect",con+"");
//
//            con.setAccessible(true);//暴力访问忽略掉你的修饰符
//            object = con.newInstance("nan");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
