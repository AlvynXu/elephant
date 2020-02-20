package com.kupo.ElephantHead.ui.home.model;

import java.util.List;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 我购买的地主或展位
 */
public class MinePayDzOrZwModel {

    /**
     * code : 0
     * data : {"current":1,"orders":[],"pages":1,"records":[{"city":"杭州市","code":"HZ0054295","days":0,"district":"拱墅区","expireDate":null,"name":"","street":"米市巷街道"},{"city":"杭州市","code":"HZ0093975","days":0,"district":"上城区","expireDate":null,"name":"","street":"小营街道"},{"city":"杭州市","code":"HZ0076831","days":0,"district":"上城区","expireDate":null,"name":"","street":"望江街道"}],"searchCount":true,"size":10,"total":3}
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
         * records : [{"city":"杭州市","code":"HZ0054295","days":0,"district":"拱墅区","expireDate":null,"name":"","street":"米市巷街道"},{"city":"杭州市","code":"HZ0093975","days":0,"district":"上城区","expireDate":null,"name":"","street":"小营街道"},{"city":"杭州市","code":"HZ0076831","days":0,"district":"上城区","expireDate":null,"name":"","street":"望江街道"}]
         * searchCount : true
         * size : 10
         * total : 3
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
             * city : 杭州市
             * code : HZ0054295
             * days : 0
             * district : 拱墅区
             * expireDate : null
             * name :
             * street : 米市巷街道
             */

            private String city;
            private String code;
            private int days;
            private String district;
            private Object expireDate;
            private String name;
            private String street;
            private int id;
            private String leftDate;
            private int useStatus;
            private String description;
            private int rentHours;
            private int itemId;

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

            public int getDays() {
                return days;
            }

            public void setDays(int days) {
                this.days = days;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public Object getExpireDate() {
                return expireDate;
            }

            public void setExpireDate(Object expireDate) {
                this.expireDate = expireDate;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLeftDate() {
                return leftDate;
            }

            public void setLeftDate(String leftDate) {
                this.leftDate = leftDate;
            }

            public int getUseStatus() {
                return useStatus;
            }

            public void setUseStatus(int useStatus) {
                this.useStatus = useStatus;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getRentHours() {
                return rentHours;
            }

            public void setRentHours(int rentHours) {
                this.rentHours = rentHours;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            @Override
            public int getItemType() {
                return 0;
            }

            @Override
            public String toString() {
                return "RecordsBean{" +
                        "city='" + city + '\'' +
                        ", code='" + code + '\'' +
                        ", days=" + days +
                        ", district='" + district + '\'' +
                        ", expireDate=" + expireDate +
                        ", name='" + name + '\'' +
                        ", street='" + street + '\'' +
                        ", id=" + id +
                        ", leftDate='" + leftDate + '\'' +
                        '}';
            }
        }

    }
}
