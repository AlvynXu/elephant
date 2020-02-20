package com.kupo.ElephantHead.ui.home.model;

import java.util.List;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 展位实体类
 */
public class ZwItemModel {

    /**
     * code : 0
     * data : {"current":1,"orders":[],"pages":6,"records":[{"boothCode":"8QGaUzfE","boothName":"","id":37820,"status":0,"streetId":1270},{"boothCode":"0VP8i2bi","boothName":"","id":37840,"status":0,"streetId":1270},{"boothCode":"1R9Eg6qB","boothName":"","id":37858,"status":0,"streetId":1270},{"boothCode":"UoeypjAo","boothName":"","id":37860,"status":0,"streetId":1270},{"boothCode":"M6otj85Q","boothName":"","id":37868,"status":0,"streetId":1270},{"boothCode":"oAgDIP8e","boothName":"","id":37882,"status":0,"streetId":1270},{"boothCode":"fnsGGrjg","boothName":"","id":37893,"status":0,"streetId":1270},{"boothCode":"7UMl5OAQ","boothName":"","id":37909,"status":0,"streetId":1270},{"boothCode":"jqNbr2ec","boothName":"","id":37947,"status":0,"streetId":1270},{"boothCode":"61Vizoja","boothName":"","id":37970,"status":0,"streetId":1270}],"searchCount":true,"size":10,"total":60}
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
         * pages : 6
         * records : [{"boothCode":"8QGaUzfE","boothName":"","id":37820,"status":0,"streetId":1270},{"boothCode":"0VP8i2bi","boothName":"","id":37840,"status":0,"streetId":1270},{"boothCode":"1R9Eg6qB","boothName":"","id":37858,"status":0,"streetId":1270},{"boothCode":"UoeypjAo","boothName":"","id":37860,"status":0,"streetId":1270},{"boothCode":"M6otj85Q","boothName":"","id":37868,"status":0,"streetId":1270},{"boothCode":"oAgDIP8e","boothName":"","id":37882,"status":0,"streetId":1270},{"boothCode":"fnsGGrjg","boothName":"","id":37893,"status":0,"streetId":1270},{"boothCode":"7UMl5OAQ","boothName":"","id":37909,"status":0,"streetId":1270},{"boothCode":"jqNbr2ec","boothName":"","id":37947,"status":0,"streetId":1270},{"boothCode":"61Vizoja","boothName":"","id":37970,"status":0,"streetId":1270}]
         * searchCount : true
         * size : 10
         * total : 60
         */

        private int current;
        private int pages;
        private int notSoldCount;
        private boolean searchCount;
        private int soldCount;
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

        public int getSoldCount() {
            return soldCount;
        }

        public void setSoldCount(int soldCount) {
            this.soldCount = soldCount;
        }

        public static class RecordsBean implements MultiItemEntity {
            /**
             * boothCode : 8QGaUzfE
             * boothName :
             * id : 37820
             * status : 0
             * streetId : 1270
             */

            private String boothCode;
            private String boothName;
            private int id;
            private int status;
            private int streetId;
            private double price;
            private boolean saved;


            public String getBoothCode() {
                return boothCode;
            }

            public void setBoothCode(String boothCode) {
                this.boothCode = boothCode;
            }

            public String getBoothName() {
                return boothName;
            }

            public void setBoothName(String boothName) {
                this.boothName = boothName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getStreetId() {
                return streetId;
            }

            public void setStreetId(int streetId) {
                this.streetId = streetId;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public boolean isSaved() {
                return saved;
            }

            public void setSaved(boolean saved) {
                this.saved = saved;
            }

            @Override
            public int getItemType() {
                return 0;
            }
        }
    }

    @Override
    public String toString() {
        return "ZwItemModel{" +
                "code=" + code +
                ", data=" + data +
                ", failCode=" + failCode +
                ", message='" + message + '\'' +
                '}';
    }
}
