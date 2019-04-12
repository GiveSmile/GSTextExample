package com.hs.administrator.test.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018/12/10 0010.
 */

public class TestBeanUtrls {
   public static <T> void  getTestdata(List<T> mData, List<T> list){
       list = new ArrayList<>();
       for (int i = 0;i<mData.size();i++){
           list.add(mData.get(i));
       }
   }
}
