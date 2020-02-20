package com.kupo.ElephantHead.ui.home.model;

import java.util.List;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 首页头条页
 */
public class HomeTopModel {

    /**
     * code : 0
     * data : {"current":1,"orders":[],"pages":1,"records":[{"addressDetail":"这是详细地址","auditTime":null,"bannerPath":"http://127.0.0.1/image/0631057d-50b8-4acb-9bbe-46352ac6f8b4.png","category":null,"categoryId":1,"cityName":"","createTime":"2019-12-16T16:46:25","description":"这是描述问题","detailList":[],"districtCode":"111","districtName":"","hasReport":false,"id":5,"itemCode":"c596e048-7473-42e2-9e11-54f529749bc3","phone":"10000000000","provinceCode":"11","provinceName":"","publishTime":null,"reportStatus":2,"shareCount":0,"sort":10000,"status":0,"streetCode":"11111","streetName":"","userId":32,"views":3}],"searchCount":true,"size":10,"total":1}
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
         * records : [{"addressDetail":"这是详细地址","auditTime":null,"bannerPath":"http://127.0.0.1/image/0631057d-50b8-4acb-9bbe-46352ac6f8b4.png","category":null,"categoryId":1,"cityName":"","createTime":"2019-12-16T16:46:25","description":"这是描述问题","detailList":[],"districtCode":"111","districtName":"","hasReport":false,"id":5,"itemCode":"c596e048-7473-42e2-9e11-54f529749bc3","phone":"10000000000","provinceCode":"11","provinceName":"","publishTime":null,"reportStatus":2,"shareCount":0,"sort":10000,"status":0,"streetCode":"11111","streetName":"","userId":32,"views":3}]
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
             * addressDetail : 这是详细地址
             * auditTime : null
             * bannerPath : http://127.0.0.1/image/0631057d-50b8-4acb-9bbe-46352ac6f8b4.png
             * category : null
             * categoryId : 1
             * cityName :
             * createTime : 2019-12-16T16:46:25
             * description : 这是描述问题
             * detailList : []
             * districtCode : 111
             * districtName :
             * hasReport : false
             * id : 5
             * itemCode : c596e048-7473-42e2-9e11-54f529749bc3
             * phone : 10000000000
             * provinceCode : 11
             * provinceName :
             * publishTime : null
             * reportStatus : 2
             * shareCount : 0
             * sort : 10000
             * status : 0
             * streetCode : 11111
             * streetName :
             * userId : 32
             * views : 3
             */

            private String addressDetail;
            private Object auditTime;
            private String bannerPath;
            private Object category;
            private int categoryId;
            private String cityName;
            private String createTime;
            private String description;
            private String districtCode;
            private String districtName;
            private boolean hasReport;
            private int id;
            private String itemCode;
            private String phone;
            private String provinceCode;
            private String provinceName;
            private Object publishTime;
            private int reportStatus;
            private int shareCount;
            private int sort;
            private int status;
            private String streetCode;
            private String streetName;
            private int userId;
            private int views;
            private List<?> detailList;

            public String getAddressDetail() {
                return addressDetail;
            }

            public void setAddressDetail(String addressDetail) {
                this.addressDetail = addressDetail;
            }

            public Object getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(Object auditTime) {
                this.auditTime = auditTime;
            }

            public String getBannerPath() {
                return bannerPath;
            }

            public void setBannerPath(String bannerPath) {
                this.bannerPath = bannerPath;
            }

            public Object getCategory() {
                return category;
            }

            public void setCategory(Object category) {
                this.category = category;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDistrictCode() {
                return districtCode;
            }

            public void setDistrictCode(String districtCode) {
                this.districtCode = districtCode;
            }

            public String getDistrictName() {
                return districtName;
            }

            public void setDistrictName(String districtName) {
                this.districtName = districtName;
            }

            public boolean isHasReport() {
                return hasReport;
            }

            public void setHasReport(boolean hasReport) {
                this.hasReport = hasReport;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getItemCode() {
                return itemCode;
            }

            public void setItemCode(String itemCode) {
                this.itemCode = itemCode;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(String provinceCode) {
                this.provinceCode = provinceCode;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public Object getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(Object publishTime) {
                this.publishTime = publishTime;
            }

            public int getReportStatus() {
                return reportStatus;
            }

            public void setReportStatus(int reportStatus) {
                this.reportStatus = reportStatus;
            }

            public int getShareCount() {
                return shareCount;
            }

            public void setShareCount(int shareCount) {
                this.shareCount = shareCount;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getViews() {
                return views;
            }

            public void setViews(int views) {
                this.views = views;
            }

            public List<?> getDetailList() {
                return detailList;
            }

            public void setDetailList(List<?> detailList) {
                this.detailList = detailList;
            }

            @Override
            public int getItemType() {
                return 0;
            }
        }
    }
}
