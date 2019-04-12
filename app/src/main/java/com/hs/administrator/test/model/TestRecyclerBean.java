package com.hs.administrator.test.model;

import java.lang.ref.PhantomReference;

/**
 * @auther : yanbin
 * @time : 2018/7/31 0031 10:46
 * @describe :
 */

public class TestRecyclerBean {
    private String Id;
    private String tuopangId;
    private String taobie;
    private String banbie;
    private String quanbie;
    private String shuoming;
    private String duoqu;
    private String duowei;
    private String duoceng;
    private String xianxiweizhi;
    private String rukuTime;

    public TestRecyclerBean(String id, String tuopangId, String taobie, String banbie, String quanbie, String shuoming, String duoqu, String duowei,String duoceng, String xianxiweizhi, String rukuTime) {
        this.Id = id;
        this.tuopangId = tuopangId;
        this.taobie = taobie;
        this.banbie = banbie;
        this.quanbie = quanbie;
        this.shuoming = shuoming;
        this.duoqu = duoqu;
        this.duowei = duowei;
        this.duoceng = duoceng;
        this.xianxiweizhi = xianxiweizhi;
        this.rukuTime = rukuTime;
    }

    public String getDuoceng() {
        return duoceng;
    }

    public void setDuoceng(String duoceng) {
        this.duoceng = duoceng;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTuopangId() {
        return tuopangId;
    }

    public void setTuopangId(String tuopangId) {
        this.tuopangId = tuopangId;
    }

    public String getTaobie() {
        return taobie;
    }

    public void setTaobie(String taobie) {
        this.taobie = taobie;
    }

    public String getBanbie() {
        return banbie;
    }

    public void setBanbie(String banbie) {
        this.banbie = banbie;
    }

    public String getQuanbie() {
        return quanbie;
    }

    public void setQuanbie(String quanbie) {
        this.quanbie = quanbie;
    }

    public String getShuoming() {
        return shuoming;
    }

    public void setShuoming(String shuoming) {
        this.shuoming = shuoming;
    }

    public String getDuoqu() {
        return duoqu;
    }

    public void setDuoqu(String duoqu) {
        this.duoqu = duoqu;
    }

    public String getDuowei() {
        return duowei;
    }

    public void setDuowei(String duowei) {
        this.duowei = duowei;
    }

    public String getXianxiweizhi() {
        return xianxiweizhi;
    }

    public void setXianxiweizhi(String xianxiweizhi) {
        this.xianxiweizhi = xianxiweizhi;
    }

    public String getRukuTime() {
        return rukuTime;
    }

    public void setRukuTime(String rukuTime) {
        this.rukuTime = rukuTime;
    }
}
