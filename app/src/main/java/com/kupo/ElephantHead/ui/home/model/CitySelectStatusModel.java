package com.kupo.ElephantHead.ui.home.model;

/**
 * 城市选择状态
 */
public class CitySelectStatusModel {

    private int provincesPosition;
    private int citiesPosition;
    private int districtsPosition;
    private int streetsPosition;
    private int otherPosition;

    public CitySelectStatusModel(int citiesPosition, int districtsPosition, int streetsPosition) {
        this.citiesPosition = citiesPosition;
        this.districtsPosition = districtsPosition;
        this.streetsPosition = streetsPosition;
    }

    public CitySelectStatusModel(int citiesPosition, int districtsPosition, int streetsPosition, int otherPosition) {
        this.citiesPosition = citiesPosition;
        this.districtsPosition = districtsPosition;
        this.streetsPosition = streetsPosition;
        this.otherPosition = otherPosition;
    }

    public int getProvincesPosition() {
        return provincesPosition;
    }

    public void setProvincesPosition(int provincesPosition) {
        this.provincesPosition = provincesPosition;
    }

    public int getCitiesPosition() {
        return citiesPosition;
    }

    public void setCitiesPosition(int citiesPosition) {
        this.citiesPosition = citiesPosition;
    }

    public int getDistrictsPosition() {
        return districtsPosition;
    }

    public void setDistrictsPosition(int districtsPosition) {
        this.districtsPosition = districtsPosition;
    }

    public int getStreetsPosition() {
        return streetsPosition;
    }

    public void setStreetsPosition(int streetsPosition) {
        this.streetsPosition = streetsPosition;
    }

    public int getOtherPosition() {
        return otherPosition;
    }

    public void setOtherPosition(int otherPosition) {
        this.otherPosition = otherPosition;
    }

    @Override
    public String toString() {
        return "CitySelectStatusModel{" +
                "provincesPosition=" + provincesPosition +
                ", citiesPosition=" + citiesPosition +
                ", districtsPosition=" + districtsPosition +
                ", streetsPosition=" + streetsPosition +
                ", otherPosition=" + otherPosition +
                '}';
    }
}
