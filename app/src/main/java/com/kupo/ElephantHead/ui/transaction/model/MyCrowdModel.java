package com.kupo.ElephantHead.ui.transaction.model;

import java.math.BigDecimal;
import java.util.List;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 交易模块/头部列表实体（我的求组）
 */
public class MyCrowdModel {
    /**
     * code : 0
     * data : [{"cityCode":"330100","createTime":1578017613000,"days":20,"expireDate":1578153600000,"id":56,"payStatus":1,"price":100,"soldCount":0,"streetCodes":"HZ0063613","totalCount":1,"type":2,"userId":32},{"cityCode":"330100","createTime":1578015945000,"days":2,"expireDate":1578153600000,"id":52,"payStatus":1,"price":1,"soldCount":3,"streetCodes":"HZ00000638,HZ0054295,HZ0017080,HZ0063613,HZ0063613,HZ0002754,HZ0093975","totalCount":7,"type":2,"userId":32}]
     * failCode : 0
     * message :
     */

    private int code;
    private int failCode;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements MultiItemEntity {
        /**
         * cityCode : 330100
         * createTime : 1578017613000
         * days : 20
         * expireDate : 1578153600000
         * id : 56
         * payStatus : 1
         * price : 100
         * soldCount : 0
         * streetCodes : HZ0063613
         * totalCount : 1
         * type : 2
         * userId : 32
         */

        private String cityCode;
        private long createTime;
        private int days;
        private long expireDate;
        private int id;
        private int payStatus;
        private BigDecimal price;
        private int soldCount;
        private String streetCodes;
        private int totalCount;
        private int type;
        private int userId;

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public long getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(long expireDate) {
            this.expireDate = expireDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public int getSoldCount() {
            return soldCount;
        }

        public void setSoldCount(int soldCount) {
            this.soldCount = soldCount;
        }

        public String getStreetCodes() {
            return streetCodes;
        }

        public void setStreetCodes(String streetCodes) {
            this.streetCodes = streetCodes;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public int getItemType() {
            return 0;
        }
    }
}
