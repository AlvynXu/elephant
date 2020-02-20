package com.kupo.ElephantHead.ui.room.model;


import java.io.Serializable;
import java.util.List;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 展厅详情
 */
public class RoomIDetailsModel implements Serializable {

    /**
     * code : 0
     * data : {"addressDetail":"","auditTime":1576397022000,"bannerPath":"http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg","category":null,"categoryId":3,"cityName":"","createTime":1576398232000,"description":"这是一个banner介绍","detailList":[{"description":"这是里面的第一个","filePath":"http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=2769810647,360229144&os=3875487069,1393702368&simid=4158035241,530490921&pn=24&rn=1&di=160930&ln=1657&fr=&fmq=1576397041710_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fb.hiphotos.baidu.com%2Fexp%2Fw%3D500%2Fsign%3Dc0a4969e942bd40742c7d3fd4b889e9c%2F728da9773912b31b79d1c59f8518367adbb4e1c3.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined","id":1,"mallItemInfoId":1,"seq":1,"type":1},{"description":"这是里面的第二个","filePath":"http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1272414155,944592664&os=2754568209,443785633&simid=4199578477,573962739&pn=104&rn=1&di=162140&ln=1657&fr=&fmq=1576397041710_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.blog.163.com%2Fphoto%2FLeZXlWSaP6mVCgJS_5BV8Q%3D%3D%2F1993968735018503720.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined","id":2,"mallItemInfoId":1,"seq":1,"type":2}],"districtCode":"","districtName":"","hasReport":false,"id":1,"itemCode":"e44f14bf-8f65-4b37-928b-637f8174193a","phone":"","provinceCode":"","provinceName":"","publishTime":1576397022000,"reportStatus":2,"shareCount":0,"sort":100,"status":1,"streetCode":"","streetName":"","userId":32,"views":13}
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
         * addressDetail :
         * auditTime : 1576397022000
         * bannerPath : http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg
         * category : null
         * categoryId : 3
         * cityName :
         * createTime : 1576398232000
         * description : 这是一个banner介绍
         * detailList : [{"description":"这是里面的第一个","filePath":"http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=2769810647,360229144&os=3875487069,1393702368&simid=4158035241,530490921&pn=24&rn=1&di=160930&ln=1657&fr=&fmq=1576397041710_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fb.hiphotos.baidu.com%2Fexp%2Fw%3D500%2Fsign%3Dc0a4969e942bd40742c7d3fd4b889e9c%2F728da9773912b31b79d1c59f8518367adbb4e1c3.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined","id":1,"mallItemInfoId":1,"seq":1,"type":1},{"description":"这是里面的第二个","filePath":"http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1272414155,944592664&os=2754568209,443785633&simid=4199578477,573962739&pn=104&rn=1&di=162140&ln=1657&fr=&fmq=1576397041710_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.blog.163.com%2Fphoto%2FLeZXlWSaP6mVCgJS_5BV8Q%3D%3D%2F1993968735018503720.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined","id":2,"mallItemInfoId":1,"seq":1,"type":2}]
         * districtCode :
         * districtName :
         * hasReport : false
         * id : 1
         * itemCode : e44f14bf-8f65-4b37-928b-637f8174193a
         * phone :
         * provinceCode :
         * provinceName :
         * publishTime : 1576397022000
         * reportStatus : 2
         * shareCount : 0
         * sort : 100
         * status : 1
         * streetCode :
         * streetName :
         * userId : 32
         * views : 13
         */

        private String addressDetail;
        private long auditTime;
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
        private long publishTime;
        private int reportStatus;
        private int shareCount;
        private int sort;
        private int status;
        private String streetCode;
        private String streetName;
        private int userId;
        private int views;
        private List<DetailListBean> detailList;
        private boolean collect;

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public long getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(long auditTime) {
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

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
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

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public static class DetailListBean implements MultiItemEntity,Serializable {
            /**
             * description : 这是里面的第一个
             * filePath : http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=2769810647,360229144&os=3875487069,1393702368&simid=4158035241,530490921&pn=24&rn=1&di=160930&ln=1657&fr=&fmq=1576397041710_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fb.hiphotos.baidu.com%2Fexp%2Fw%3D500%2Fsign%3Dc0a4969e942bd40742c7d3fd4b889e9c%2F728da9773912b31b79d1c59f8518367adbb4e1c3.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined
             * id : 1
             * mallItemInfoId : 1
             * seq : 1
             * type : 1
             */

            private String description;
            private String filePath;
            private int id;
            private int mallItemInfoId;
            private int seq;
            private int type;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMallItemInfoId() {
                return mallItemInfoId;
            }

            public void setMallItemInfoId(int mallItemInfoId) {
                this.mallItemInfoId = mallItemInfoId;
            }

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            @Override
            public int getItemType() {
                return type;
            }
        }
    }
}
