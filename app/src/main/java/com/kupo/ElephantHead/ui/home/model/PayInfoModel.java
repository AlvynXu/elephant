package com.kupo.ElephantHead.ui.home.model;

import java.io.Serializable;

/**
 * 支付信息实体
 */
public class PayInfoModel {

    /**
     * code : 0
     * data : {"data":"alipay_sdk=alipay-sdk-java-4.8.62.ALL&app_id=2019052265303713&biz_content=%7B%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE-By+Javen%22%2C%22out_trade_no%22%3A%22113012152815750%22%2C%22passback_params%22%3A%22callback+params%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22App%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95-By+Javen%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F28n003772k.zicp.vip%2Fapi%2FaliPay%2Fnotify_url&sign=Aa%2F9KJXVa31ig4L4aODHLxbwUCVI97Yz2g4odjUgqjVviU1lH8Wd6%2FinNe3zSTcaCLz78jkOR5BHzhmMWZaMvBCRFZjefCBI1k3iC0cl%2FtEQrFX%2BnP2dgLgDl3mGLY15X45IXy8XkClxHXIx7WGBa38wcjbEsE5qaKO1SfOzyXrsAwULPwU1Vt3lU0J1XqlvkzlDmQ4onfyGAZMg69kiQONmbipImdeDH3InJKe5kudK2S3yQk4bn5mVS%2FDbJQX%2BMSVpdEDLAHvVaembyxdPjUC1W8t6KdHs4NHUOdkwREstpWdq9PEkhrzC%2Bt7KPZJQ50SpCk4XtwhQYJcdFMdqOg%3D%3D&sign_type=RSA2&timestamp=2019-11-30+12%3A15%3A28&version=1.0"}
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

    public static class DataBean implements Serializable {
        /**
         * data : alipay_sdk=alipay-sdk-java-4.8.62.ALL&app_id=2019052265303713&biz_content=%7B%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE-By+Javen%22%2C%22out_trade_no%22%3A%22113012152815750%22%2C%22passback_params%22%3A%22callback+params%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22App%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95-By+Javen%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F28n003772k.zicp.vip%2Fapi%2FaliPay%2Fnotify_url&sign=Aa%2F9KJXVa31ig4L4aODHLxbwUCVI97Yz2g4odjUgqjVviU1lH8Wd6%2FinNe3zSTcaCLz78jkOR5BHzhmMWZaMvBCRFZjefCBI1k3iC0cl%2FtEQrFX%2BnP2dgLgDl3mGLY15X45IXy8XkClxHXIx7WGBa38wcjbEsE5qaKO1SfOzyXrsAwULPwU1Vt3lU0J1XqlvkzlDmQ4onfyGAZMg69kiQONmbipImdeDH3InJKe5kudK2S3yQk4bn5mVS%2FDbJQX%2BMSVpdEDLAHvVaembyxdPjUC1W8t6KdHs4NHUOdkwREstpWdq9PEkhrzC%2Bt7KPZJQ50SpCk4XtwhQYJcdFMdqOg%3D%3D&sign_type=RSA2&timestamp=2019-11-30+12%3A15%3A28&version=1.0
         */

        private String data;

        private String orderInfo;

        //微信
//        private String package;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(String orderInfo) {
            this.orderInfo = orderInfo;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "data='" + data + '\'' +
                    ", orderInfo='" + orderInfo + '\'' +
                    ", appid='" + appid + '\'' +
                    ", sign='" + sign + '\'' +
                    ", partnerid='" + partnerid + '\'' +
                    ", prepayid='" + prepayid + '\'' +
                    ", noncestr='" + noncestr + '\'' +
                    ", timestamp='" + timestamp + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PayInfoModel{" +
                "code=" + code +
                ", data=" + data +
                ", failCode=" + failCode +
                ", message='" + message + '\'' +
                '}';
    }
}
