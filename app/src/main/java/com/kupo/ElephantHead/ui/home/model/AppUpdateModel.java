package com.kupo.ElephantHead.ui.home.model;

/**
 * app更新数据
 */
public class AppUpdateModel {

    /**
     * code : 0
     * data : {"appVersion":"1.0","description":"这是android介绍","downloadPth":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1518682579,4064448354&fm=26&gp=0.jpg","enable":true,"ext1":"","ext2":"","ext3":"","id":1,"time":"2019-12-04T19:42:53","type":0}
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
         * appVersion : 1.0
         * description : 这是android介绍
         * downloadPth : https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1518682579,4064448354&fm=26&gp=0.jpg
         * enable : true
         * ext1 :
         * ext2 :
         * ext3 :
         * id : 1
         * time : 2019-12-04T19:42:53
         * type : 0
         */

        private String appVersion;
        private String description;
        private String downloadPth;
        private boolean enable;
        private String ext1;
        private String ext2;
        private String ext3;
        private int id;
        private String time;
        private int type;
        private boolean forceUpdate;

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDownloadPth() {
            return downloadPth;
        }

        public void setDownloadPth(String downloadPth) {
            this.downloadPth = downloadPth;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getExt1() {
            return ext1;
        }

        public void setExt1(String ext1) {
            this.ext1 = ext1;
        }

        public String getExt2() {
            return ext2;
        }

        public void setExt2(String ext2) {
            this.ext2 = ext2;
        }

        public String getExt3() {
            return ext3;
        }

        public void setExt3(String ext3) {
            this.ext3 = ext3;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public boolean isForceUpdate() {
            return forceUpdate;
        }

        public void setForceUpdate(boolean forceUpdate) {
            this.forceUpdate = forceUpdate;
        }
    }
}
