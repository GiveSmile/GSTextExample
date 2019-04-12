package com.hs.administrator.test.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @auther : yanbin
 * @time : 2018/8/8 0008 10:19
 * @describe :
 */
@Entity
public class TestGreeDaoBean {
    @Id(autoincrement = true)
    public Long id;
    public String testTitle;
    @Generated(hash = 142805138)
    public TestGreeDaoBean(Long id, String testTitle) {
        this.id = id;
        this.testTitle = testTitle;
    }
    @Generated(hash = 1594377223)
    public TestGreeDaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTestTitle() {
        return this.testTitle;
    }
    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

}
