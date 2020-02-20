package com.kupo.ElephantHead.ui.mvp.model;

/**
 * eventBus刷新/钱包刷新
 */
public class RefreshModel {

    private String CityName;
    private String CityCode;
    private String isRefresh;

    public RefreshModel(String isRefresh) {
        this.isRefresh = isRefresh;
    }

    public String getIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(String isRefresh) {
        this.isRefresh = isRefresh;
    }

    public RefreshModel(String cityName, String cityCode) {
        CityName = cityName;
        CityCode = cityCode;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }
}
