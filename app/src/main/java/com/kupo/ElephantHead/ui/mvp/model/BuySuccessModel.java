package com.kupo.ElephantHead.ui.mvp.model;

/**
 * 支付完成后跳转到成功界面的数据
 */
public class BuySuccessModel {

    /**
     * code : 0
     * data : {"city":"杭州市","code":"HZ0093975","district":"上城区","province":"浙江省","street":"小营街道","time":"永久","type":2}
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
         * city : 杭州市
         * code : HZ0093975
         * district : 上城区
         * province : 浙江省
         * street : 小营街道
         * time : 永久
         * type : 2
         */

        private String city;
        private String code;
        private String district;
        private String province;
        private String street;
        private String time;
        private int type;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
