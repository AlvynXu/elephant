package com.kupo.ElephantHead.ui.transaction.model;

import java.math.BigDecimal;
import java.util.List;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 交易模块下面的实体模块
 */
public class CrowdModel {
    /**
     * code : 0
     * data : {"current":1,"orders":[],"pages":1,"records":[{"cityCode":"330100","createTime":1577780949000,"days":2,"expireDate":1577894400000,"id":47,"payStatus":1,"price":1,"soldCount":1,"streetCodes":"HZ0054295,HZ0017080","totalCount":2,"type":2,"userId":1},{"cityCode":"330100","createTime":1577784633000,"days":2,"expireDate":1577894400000,"id":48,"payStatus":1,"price":1,"soldCount":1,"streetCodes":"HZ00000638","totalCount":1,"type":2,"userId":32},{"cityCode":"330100","createTime":1577963956000,"days":2,"expireDate":1577894400000,"id":49,"payStatus":1,"price":1,"soldCount":0,"streetCodes":"","totalCount":0,"type":2,"userId":65},{"cityCode":"330100","createTime":1577963956000,"days":2,"expireDate":1577894400000,"id":50,"payStatus":1,"price":1,"soldCount":0,"streetCodes":"","totalCount":0,"type":2,"userId":7}],"searchCount":true,"size":15,"total":4}
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
         * current : 1
         * orders : []
         * pages : 1
         * records : [{"cityCode":"330100","createTime":1577780949000,"days":2,"expireDate":1577894400000,"id":47,"payStatus":1,"price":1,"soldCount":1,"streetCodes":"HZ0054295,HZ0017080","totalCount":2,"type":2,"userId":1},{"cityCode":"330100","createTime":1577784633000,"days":2,"expireDate":1577894400000,"id":48,"payStatus":1,"price":1,"soldCount":1,"streetCodes":"HZ00000638","totalCount":1,"type":2,"userId":32},{"cityCode":"330100","createTime":1577963956000,"days":2,"expireDate":1577894400000,"id":49,"payStatus":1,"price":1,"soldCount":0,"streetCodes":"","totalCount":0,"type":2,"userId":65},{"cityCode":"330100","createTime":1577963956000,"days":2,"expireDate":1577894400000,"id":50,"payStatus":1,"price":1,"soldCount":0,"streetCodes":"","totalCount":0,"type":2,"userId":7}]
         * searchCount : true
         * size : 15
         * total : 4
         */

        private int current;
        private int pages;
        private boolean searchCount;
        private int size;
        private int total;
        private List<?> orders;
        private List<RecordsBean> records;

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean implements MultiItemEntity {
            /**
             * cityCode : 330100
             * createTime : 1577780949000
             * days : 2
             * expireDate : 1577894400000
             * id : 47
             * payStatus : 1
             * price : 1.0
             * soldCount : 1
             * streetCodes : HZ0054295,HZ0017080
             * totalCount : 2
             * type : 2
             * userId : 1
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
}
