package com.hs.administrator.test.model;

/**
 * @auther : yanbin
 * @time : 2019/4/24 0024 15:58
 * @describe : TODO
 */
public class GoodsBean {
    private int imgId; //资源id
    private String info; //详情
    private float price;//价格
    private int buyNum;//购买人数
    private String ticket;//优惠券

    public GoodsBean() {
    }

    public GoodsBean(int imgId, String info, float price, int buyNum, String ticket) {
        this.imgId = imgId;
        this.info = info;
        this.price = price;
        this.buyNum = buyNum;
        this.ticket = ticket;
    }
    public GoodsBean(int imgId, String info, float price, int buyNum) {
        this.imgId = imgId;
        this.info = info;
        this.price = price;
        this.buyNum = buyNum;
    }
    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
