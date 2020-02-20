package com.kupo.ElephantHead.ui.home.model;

import java.util.List;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 消息实体
 */
public class MessageModel {

    /**
     * code : 0
     * data : {"current":1,"orders":[],"pages":1,"records":[{"content":"这是内容","createTime":"2019-11-28T19:51:58","deleted":false,"fromUserId":0,"header":"000","id":1,"sender":"系统消息","status":0,"theme":"000","toUserId":1}],"searchCount":true,"size":10,"total":1}
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
         * records : [{"content":"这是内容","createTime":"2019-11-28T19:51:58","deleted":false,"fromUserId":0,"header":"000","id":1,"sender":"系统消息","status":0,"theme":"000","toUserId":1}]
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
             * content : 这是内容
             * createTime : 2019-11-28T19:51:58
             * deleted : false
             * fromUserId : 0
             * header : 000
             * id : 1
             * sender : 系统消息
             * status : 0
             * theme : 000
             * toUserId : 1
             */

            private String content;
            private long createTime;
            private boolean deleted;
            private int fromUserId;
            private String header;
            private int id;
            private String sender;
            private int status;
            private String theme;
            private int toUserId;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public int getFromUserId() {
                return fromUserId;
            }

            public void setFromUserId(int fromUserId) {
                this.fromUserId = fromUserId;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSender() {
                return sender;
            }

            public void setSender(String sender) {
                this.sender = sender;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTheme() {
                return theme;
            }

            public void setTheme(String theme) {
                this.theme = theme;
            }

            public int getToUserId() {
                return toUserId;
            }

            public void setToUserId(int toUserId) {
                this.toUserId = toUserId;
            }

            @Override
            public int getItemType() {
                return 0;
            }
        }
    }
}
