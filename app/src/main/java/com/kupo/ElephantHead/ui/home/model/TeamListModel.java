package com.kupo.ElephantHead.ui.home.model;


import java.util.List;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 我的团队列表
 */
public class TeamListModel {


    /**
     * code : 0
     * data : {"current":1,"orders":[],"pages":1,"records":[{"directCount":2,"indirectCount":0,"name":"*******6997","promoterCountDTOList":[],"time":"2019/12/02 14:00","totalCount":3},{"directCount":0,"indirectCount":0,"name":"*******5656","promoterCountDTOList":[],"time":"2019/12/02 14:00","totalCount":0},{"directCount":0,"indirectCount":0,"name":"*******2520","promoterCountDTOList":[],"time":"2019/12/02 14:00","totalCount":0},{"directCount":0,"indirectCount":0,"name":"*******2521","promoterCountDTOList":[],"time":"2019/12/02 14:00","totalCount":0},{"directCount":0,"indirectCount":0,"name":"*******2522","promoterCountDTOList":[],"time":"2019/12/02 14:00","totalCount":0}],"searchCount":true,"size":10,"total":5}
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
         * records : [{"directCount":2,"indirectCount":0,"name":"*******6997","promoterCountDTOList":[],"time":"2019/12/02 14:00","totalCount":3},{"directCount":0,"indirectCount":0,"name":"*******5656","promoterCountDTOList":[],"time":"2019/12/02 14:00","totalCount":0},{"directCount":0,"indirectCount":0,"name":"*******2520","promoterCountDTOList":[],"time":"2019/12/02 14:00","totalCount":0},{"directCount":0,"indirectCount":0,"name":"*******2521","promoterCountDTOList":[],"time":"2019/12/02 14:00","totalCount":0},{"directCount":0,"indirectCount":0,"name":"*******2522","promoterCountDTOList":[],"time":"2019/12/02 14:00","totalCount":0}]
         * searchCount : true
         * size : 10
         * total : 5
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
             * directCount : 2
             * indirectCount : 0
             * name : *******6997
             * promoterCountDTOList : []
             * time : 2019/12/02 14:00
             * totalCount : 3
             */

            private int directCount;
            private int indirectCount;
            private String name;
            private String time;
            private int totalCount;
            private double amount;
            private String type;
            private List<?> promoterCountDTOList;
            private String typeString;

            public int getDirectCount() {
                return directCount;
            }

            public void setDirectCount(int directCount) {
                this.directCount = directCount;
            }

            public int getIndirectCount() {
                return indirectCount;
            }

            public void setIndirectCount(int indirectCount) {
                this.indirectCount = indirectCount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<?> getPromoterCountDTOList() {
                return promoterCountDTOList;
            }

            public void setPromoterCountDTOList(List<?> promoterCountDTOList) {
                this.promoterCountDTOList = promoterCountDTOList;
            }

            public String getTypeString() {
                return typeString;
            }

            public void setTypeString(String typeString) {
                this.typeString = typeString;
            }

            @Override
            public int getItemType() {
                return 0;
            }
        }
    }
}
