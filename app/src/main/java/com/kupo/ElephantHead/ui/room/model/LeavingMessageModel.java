package com.kupo.ElephantHead.ui.room.model;

import java.util.List;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 留言
 */
public class LeavingMessageModel {

    /**
     * code : 0
     * data : {"current":1,"orders":[],"pages":1,"records":[{"id":3,"itemId":87,"message":"测试留言","phone":"*******2520","time":1579140358000,"userId":0,"userLevel":3}],"searchCount":true,"size":10,"total":1}
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
         * records : [{"id":3,"itemId":87,"message":"测试留言","phone":"*******2520","time":1579140358000,"userId":0,"userLevel":3}]
         * searchCount : true
         * size : 10
         * total : 1
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
             * id : 3
             * itemId : 87
             * message : 测试留言
             * phone : *******2520
             * time : 1579140358000
             * userId : 0
             * userLevel : 3
             */

            private int id;
            private int itemId;
            private String message;
            private String phone;
            private long time;
            private int userId;
            private int userLevel;

            public RecordsBean(int id, int itemId, String message, String phone, long time, int userId, int userLevel) {
                this.id = id;
                this.itemId = itemId;
                this.message = message;
                this.phone = phone;
                this.time = time;
                this.userId = userId;
                this.userLevel = userLevel;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getUserLevel() {
                return userLevel;
            }

            public void setUserLevel(int userLevel) {
                this.userLevel = userLevel;
            }

            @Override
            public int getItemType() {
                return 0;
            }
        }
    }
}
