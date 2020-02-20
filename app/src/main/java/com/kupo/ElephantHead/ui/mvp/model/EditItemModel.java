package com.kupo.ElephantHead.ui.mvp.model;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 编辑视频，文字，图片文章实体
 */
public class EditItemModel implements MultiItemEntity {

    private String content;
    private String picUrl;
    private int orderNum;
    private String videoUrl;
    private int type;

    public EditItemModel(String content, String picUrl, int orderNum, String videoUrl, int type) {
        this.content = content;
        this.picUrl = picUrl;
        this.orderNum = orderNum;
        this.videoUrl = videoUrl;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EditItemModel{" +
                "content='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", orderNum=" + orderNum +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }

    @Override
    public int getItemType() {
        return type;
    }
}
