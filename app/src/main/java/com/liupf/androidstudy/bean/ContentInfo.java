package com.liupf.androidstudy.bean;


/**
 * Created by liupf on 2016/12/29.
 */

public class ContentInfo extends BaseInfo{

    String name;

    public ContentInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
