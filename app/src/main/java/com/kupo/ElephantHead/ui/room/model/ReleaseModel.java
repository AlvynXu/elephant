package com.kupo.ElephantHead.ui.room.model;

import java.util.List;

/**
 * 编辑信息后返回的实体
 */
public class ReleaseModel {

    /**
     * code : 0
     * data : {"addressDetail":"测试","auditTime":null,"bannerPath":"http://192.168.5.6/image/bf611d05-8917-48de-9a84-607d96ababcf.jpg","category":null,"categoryId":1,"cityName":"","createTime":1576835197419,"description":"测试","detailList":[],"districtCode":"","districtName":"","hasReport":false,"id":19,"itemCode":"cdce6169-7d5e-45ff-ac02-8bf0bdf59988","phone":"123456789011","provinceCode":"","provinceName":"","publishTime":null,"reportStatus":2,"shareCount":0,"sort":0,"status":0,"streetCode":"","streetName":"","user":null,"userId":6,"views":0}
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
         * addressDetail : 测试
         * auditTime : null
         * bannerPath : http://192.168.5.6/image/bf611d05-8917-48de-9a84-607d96ababcf.jpg
         * category : null
         * categoryId : 1
         * cityName :
         * createTime : 1576835197419
         * description : 测试
         * detailList : []
         * districtCode :
         * districtName :
         * hasReport : false
         * id : 19
         * itemCode : cdce6169-7d5e-45ff-ac02-8bf0bdf59988
         * phone : 123456789011
         * provinceCode :
         * provinceName :
         * publishTime : null
         * reportStatus : 2
         * shareCount : 0
         * sort : 0
         * status : 0
         * streetCode :
         * streetName :
         * user : null
         * userId : 6
         * views : 0
         */

        private String addressDetail;
        private Object auditTime;
        private String bannerPath;
        private Object category;
        private int categoryId;
        private String cityName;
        private long createTime;
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
        private Object user;
        private int userId;
        private int views;
        private List<?> detailList;
        private int mallItemInfoId;

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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
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

        public Object getUser() {
            return user;
        }

        public void setUser(Object user) {
            this.user = user;
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

        public int getMallItemInfoId() {
            return mallItemInfoId;
        }

        public void setMallItemInfoId(int mallItemInfoId) {
            this.mallItemInfoId = mallItemInfoId;
        }
    }
}
