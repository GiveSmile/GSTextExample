package com.hs.administrator.test.model;

/**
 * @auther : yanbin
 * @time : 2019/5/7 0007 15:44
 * @describe : TODO
 */
public class StackBean {
    private  int type;
    private String content;
    private int location;

    public StackBean(int type, String content, int location) {
        this.type = type;
        this.content = content;
        this.location = location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
