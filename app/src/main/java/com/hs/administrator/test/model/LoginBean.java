package com.hs.administrator.test.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @auther : yanbin
 * @time : 2018/8/8 0008 17:24
 * @describe :
 */
@Entity
public class LoginBean {
    @Id(autoincrement = true)
    public Long id;
    public String userName;
    public String passWord;
    public boolean isCheck;
    public String Priority;
    public String getPassWord() {
        return this.passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getIsCheck() {
        return this.isCheck;
    }
    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }
    public String getPriority() {
        return this.Priority;
    }
    public void setPriority(String Priority) {
        this.Priority = Priority;
    }
    @Generated(hash = 790009644)
    public LoginBean(Long id, String userName, String passWord, boolean isCheck,
            String Priority) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.isCheck = isCheck;
        this.Priority = Priority;
    }
    @Generated(hash = 1112702939)
    public LoginBean() {
    }

}
