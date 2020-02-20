package com.kupo.ElephantHead.ui.transaction.model;

/**
 * 交易刷新使用
 */
public class CrowdRefreshModel {

    private int count;
    private int citiesPosition;

    public CrowdRefreshModel(int count, int citiesPosition) {
        this.count = count;
        this.citiesPosition = citiesPosition;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCitiesPosition() {
        return citiesPosition;
    }

    public void setCitiesPosition(int citiesPosition) {
        this.citiesPosition = citiesPosition;
    }
}
