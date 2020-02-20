package com.kupo.ElephantHead.ui.home.model;

/**
 * 当前登录用户信息
 */
public class CurrentUserModel {

    /**
     * code : 0
     * data : {"balance":0,"createTime":1575216000000,"deleted":false,"id":6,"isVip":false,"level":0,"password":"00e2e3579a5083fcb9a4199b7b4de6dd","phone":"13691212520","profit":0,"promoterId":1,"regCode":"979095","teamProfit":0,"updateTime":1575381054000}
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
         * balance : 0.0
         * createTime : 1575216000000
         * deleted : false
         * id : 6
         * isVip : false
         * level : 0
         * password : 00e2e3579a5083fcb9a4199b7b4de6dd
         * phone : 13691212520
         * profit : 0.0
         * promoterId : 1
         * regCode : 979095
         * teamProfit : 0.0
         * updateTime : 1575381054000
         */

        private double balance;
        private long createTime;
        private boolean deleted;
        private int id;
        private boolean isVip;
        private int level;
        private String password;
        private String phone;
        private double profit;
        private int promoterId;
        private String regCode;
        private double teamProfit;
        private long updateTime;
        private String levelName;
        private int chooseBoothCount;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsVip() {
            return isVip;
        }

        public void setIsVip(boolean isVip) {
            this.isVip = isVip;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public double getProfit() {
            return profit;
        }

        public void setProfit(double profit) {
            this.profit = profit;
        }

        public int getPromoterId() {
            return promoterId;
        }

        public void setPromoterId(int promoterId) {
            this.promoterId = promoterId;
        }

        public String getRegCode() {
            return regCode;
        }

        public void setRegCode(String regCode) {
            this.regCode = regCode;
        }

        public double getTeamProfit() {
            return teamProfit;
        }

        public void setTeamProfit(double teamProfit) {
            this.teamProfit = teamProfit;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public int getChooseBoothCount() {
            return chooseBoothCount;
        }

        public void setChooseBoothCount(int chooseBoothCount) {
            this.chooseBoothCount = chooseBoothCount;
        }
    }
}
