package com.kupo.ElephantHead.ui.home.model;

/**
 * 获取城市编码实体类
 */
public class CityCode {

    /**
     * code : 0
     * data : {"code":"330100","id":922,"level":2,"name":"杭州市","parentCode":"330000","price":0}
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
         * code : 330100
         * id : 922
         * level : 2
         * name : 杭州市
         * parentCode : 330000
         * price : 0
         */

        private String code;
        private int id;
        private int level;
        private String name;
        private String parentCode;
        private int price;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentCode() {
            return parentCode;
        }

        public void setParentCode(String parentCode) {
            this.parentCode = parentCode;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "code='" + code + '\'' +
                    ", id=" + id +
                    ", level=" + level +
                    ", name='" + name + '\'' +
                    ", parentCode='" + parentCode + '\'' +
                    ", price=" + price +
                    '}';
        }

    }

    @Override
    public String toString() {
        return "CityCode{" +
                "code=" + code +
                ", data=" + data +
                ", failCode=" + failCode +
                ", message='" + message + '\'' +
                '}';
    }
}
