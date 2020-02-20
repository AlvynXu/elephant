package com.kupo.ElephantHead.ui.home.model;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 收益/我的钱包
 */
public class ProfitItemModel implements MultiItemEntity {
    private String time;
    private String content;
    private String price;

    public ProfitItemModel(String time, String content, String price) {
        this.time = time;
        this.content = content;
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
