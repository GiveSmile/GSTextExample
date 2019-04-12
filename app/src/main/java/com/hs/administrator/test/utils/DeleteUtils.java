package com.hs.administrator.test.utils;

import java.io.File;

/**
 * @auther : yanbin
 * @time : 2018/9/27 0027 17:16
 * @describe :
 */

public class DeleteUtils {
    /**
     * 删除本地文件
     */
    public boolean deleteLocal(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();//如果为文件，直接删除
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File file1 : files) {
                    deleteLocal(file1);//如果为文件夹，递归调用
                }
            }
            file.delete();
            return true;
        }
        return false;
    }
}
