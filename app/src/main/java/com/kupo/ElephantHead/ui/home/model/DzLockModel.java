package com.kupo.ElephantHead.ui.home.model;

/**
 * 获取地主解锁展位数量
 */
public class DzLockModel {


    /**
     * code : 0
     * data : {"total":10,"canUsed":9}
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
         * total : 10
         * canUsed : 9
         */

        private int total;
        private int canUsed;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCanUsed() {
            return canUsed;
        }

        public void setCanUsed(int canUsed) {
            this.canUsed = canUsed;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "total=" + total +
                    ", canUsed=" + canUsed +
                    '}';
        }

    }

    @Override
    public String toString() {
        return "DzLockModel{" +
                "code=" + code +
                ", data=" + data +
                ", failCode=" + failCode +
                ", message='" + message + '\'' +
                '}';
    }
}
