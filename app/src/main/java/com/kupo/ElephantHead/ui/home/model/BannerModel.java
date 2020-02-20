package com.kupo.ElephantHead.ui.home.model;

import java.util.List;

/**
 * 首页banner
 */
public class BannerModel {

    /**
     * code : 0
     * data : [{"areaCode":"-1","createTime":1576496198000,"days":0,"endTime":null,"id":4,"imageUrl":"http://192.168.5.6/image/bbe6b0f6-bbd7-4c97-9941-3b76dd917ced.png","itemId":0,"sort":0,"type":1,"visible":true},{"areaCode":"-1","createTime":1576496198000,"days":0,"endTime":null,"id":5,"imageUrl":"http://127.0.0.1/image/e3ffa2b1-a1da-4edb-b62a-db28dbbc22f8.png","itemId":0,"sort":0,"type":2,"visible":true}]
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

    public static class DataBean {
        /**
         * areaCode : -1
         * createTime : 1576496198000
         * days : 0
         * endTime : null
         * id : 4
         * imageUrl : http://192.168.5.6/image/bbe6b0f6-bbd7-4c97-9941-3b76dd917ced.png
         * itemId : 0
         * sort : 0
         * type : 1
         * visible : true
         */

        private String areaCode;
        private long createTime;
        private int days;
        private Object endTime;
        private int id;
        private String imageUrl;
        private String itemId;
        private int sort;
        private int type;
        private boolean visible;

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
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

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }
    }
}
