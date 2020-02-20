package com.kupo.ElephantHead.ui.room.model;

import java.util.List;

/**
 * 展厅类目实体
 */
public class CategoryModel {

    /**
     * code : 0
     * data : [{"createTime":"2019-12-15T10:30:39","icon":"http://127.0.0.1/image/cdcdcfb6-7ecc-4efd-980a-4c236ff67377.png","id":1,"level":0,"parentId":0,"sort":10,"title":"测试1","visible":true},{"createTime":"2019-12-15T10:31:19","icon":"http://127.0.0.1/image/3ba8a33b-28c6-4556-90e6-57dc054af759.jpg","id":2,"level":0,"parentId":0,"sort":11,"title":"测试2","visible":true},{"createTime":"2019-12-15T10:32:01","icon":"http://127.0.0.1/image/04f77ac3-f79d-4347-a1c8-c24a33acfc0d.png","id":3,"level":0,"parentId":0,"sort":9,"title":"测试3","visible":true},{"createTime":"2019-12-15T10:33:06","icon":"http://127.0.0.1/image/92a7c9b7-f336-4dfa-9020-aea295d26bac.jpg","id":4,"level":0,"parentId":0,"sort":-1,"title":"测试4","visible":true}]
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
         * createTime : 2019-12-15T10:30:39
         * icon : http://127.0.0.1/image/cdcdcfb6-7ecc-4efd-980a-4c236ff67377.png
         * id : 1
         * level : 0
         * parentId : 0
         * sort : 10
         * title : 测试1
         * visible : true
         */

        private String createTime;
        private String icon;
        private int id;
        private int level;
        private int parentId;
        private int sort;
        private String title;
        private boolean visible;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "createTime='" + createTime + '\'' +
                    ", icon='" + icon + '\'' +
                    ", id=" + id +
                    ", level=" + level +
                    ", parentId=" + parentId +
                    ", sort=" + sort +
                    ", title='" + title + '\'' +
                    ", visible=" + visible +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "code=" + code +
                ", failCode=" + failCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
