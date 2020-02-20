package com.kupo.ElephantHead.ui.transaction.model;

import java.math.BigDecimal;
import java.util.List;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 出租的街道
 */
public class RentStreetModel {


    /**
     * code : 0
     * data : {"count":1,"userStreetDTOList":[{"cityName":"杭州市","districtName":"上城区","hasBooth":true,"streetCode":"HZ0063613","streetName":"清波街道"}]}
     * failCode : 0
     * message :
     */

    private int code;
    private DataBean data;
    private int failCode;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getFailCode() {
        return failCode;
    }

    public void setFailCode(int failCode) {
        this.failCode = failCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * count : 1
         * userStreetDTOList : [{"cityName":"杭州市","districtName":"上城区","hasBooth":true,"streetCode":"HZ0063613","streetName":"清波街道"}]
         */

        private int count;
        private BigDecimal price;
        private BigDecimal rentRate;
        private List<UserStreetDTOListBean> userStreetDTOList;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getRentRate() {
            return rentRate;
        }

        public void setRentRate(BigDecimal rentRate) {
            this.rentRate = rentRate;
        }

        public List<UserStreetDTOListBean> getUserStreetDTOList() {
            return userStreetDTOList;
        }

        public void setUserStreetDTOList(List<UserStreetDTOListBean> userStreetDTOList) {
            this.userStreetDTOList = userStreetDTOList;
        }

        public static class UserStreetDTOListBean implements MultiItemEntity {
            /**
             * cityName : 杭州市
             * districtName : 上城区
             * hasBooth : true
             * streetCode : HZ0063613
             * streetName : 清波街道
             */

            private String cityName;
            private String districtName;
            private boolean hasBooth;
            private String streetCode;
            private String streetName;
            private BigDecimal price;
            private int days;

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getDistrictName() {
                return districtName;
            }

            public void setDistrictName(String districtName) {
                this.districtName = districtName;
            }

            public boolean isHasBooth() {
                return hasBooth;
            }

            public void setHasBooth(boolean hasBooth) {
                this.hasBooth = hasBooth;
            }

            public String getStreetCode() {
                return streetCode;
            }

            public void setStreetCode(String streetCode) {
                this.streetCode = streetCode;
            }

            public String getStreetName() {
                return streetName;
            }

            public void setStreetName(String streetName) {
                this.streetName = streetName;
            }

            public BigDecimal getPrice() {
                return price;
            }

            public void setPrice(BigDecimal price) {
                this.price = price;
            }

            public int getDays() {
                return days;
            }

            public void setDays(int days) {
                this.days = days;
            }

            @Override
            public int getItemType() {
                return 0;
            }
        }
    }
}
