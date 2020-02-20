package com.kupo.ElephantHead.ui.home.model;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 字段使用
 */
public class SingleModel implements MultiItemEntity {

    private String title;
    private int icon;
    private boolean flag;
    private int status;
    private String city;
    private String code;

    public SingleModel(String title) {
        this.title = title;
    }

    public SingleModel(String city, String code) {
        this.city = city;
        this.code = code;
    }

    public SingleModel(int status) {
        this.status = status;
    }

    public SingleModel(String title, int icon, boolean flag) {
        this.title = title;
        this.icon = icon;
        this.flag = flag;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
