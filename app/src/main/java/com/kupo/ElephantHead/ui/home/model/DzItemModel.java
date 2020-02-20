package com.kupo.ElephantHead.ui.home.model;

import com.google.gson.annotations.SerializedName;
import com.kupo.ElephantHead.base.basemodel.BaseResult;

import java.util.List;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 抢地主条目的实体类
 */
public class DzItemModel extends BaseResult {

    /**
     * data : {"current":1,"notSoldCount":1,"orders":[],"pages":1,"records":[{"areaCode":"630223","code":"1001270","id":1270,"name":"巴扎藏族乡","price":980,"status":0}],"searchCount":true,"size":10,"soldCount":0,"total":1,"totalCount":1}
     */

    private DataBean data;

    public DzItemModel(String message) {
        super(message);
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * current : 1
         * notSoldCount : 1
         * orders : []
         * pages : 1
         * records : [{"areaCode":"630223","code":"1001270","id":1270,"name":"巴扎藏族乡","price":980,"status":0}]
         * searchCount : true
         * size : 10
         * soldCount : 0
         * total : 1
         * totalCount : 1
         */

        private int current;
        private int notSoldCount;
        private int pages;
        private boolean searchCount;
        private int size;
        private int soldCount;
        private int total;
        private int totalCount;
        private List<?> orders;
        private List<RecordsBean> records;

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getNotSoldCount() {
            return notSoldCount;
        }

        public void setNotSoldCount(int notSoldCount) {
            this.notSoldCount = notSoldCount;
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

        public int getSoldCount() {
            return soldCount;
        }

        public void setSoldCount(int soldCount) {
            this.soldCount = soldCount;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
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
             * areaCode : 630223
             * code : 1001270
             * id : 1270
             * name : 巴扎藏族乡
             * price : 980.0
             * status : 0
             */

            private String areaCode;
            @SerializedName("code")
            private String codeX;
            private int id;
            private String name;
            private double price;
            private int status;

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
            }

            public String getCodeX() {
                return codeX;
            }

            public void setCodeX(String codeX) {
                this.codeX = codeX;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
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
    }
}
