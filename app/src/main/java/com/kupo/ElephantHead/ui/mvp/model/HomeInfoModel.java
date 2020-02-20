package com.kupo.ElephantHead.ui.mvp.model;

import java.math.BigDecimal;

/**
 * 首页（公用实体,首页头部,地主,展位/我的团队头部/获取提现设置最大值最小值及费率/获取详情分享链接/保存留言）
 */
public class HomeInfoModel {

    /**
     * code : 0
     * data : {"soldCount":0,"totalCount":0}
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
         * soldCount : 0
         * totalCount : 0
         */

        private int soldCount;
        private int totalCount;
        private int message;
        private double profit;
        private int team;
        private int totalMessage;
        private double vipPrice;
        private int directCount;
        private int boothCount;
        private int streetCount;
        private BigDecimal min;
        private BigDecimal max;
        private BigDecimal rate;
        private BigDecimal balance;
        private String sharePath;
        private boolean deleted;
        private int id;
        private int itemId;
        private String time;
        private int userId;

        public DataBean(int message, boolean deleted, int id, int itemId, String time, int userId) {
            this.message = message;
            this.deleted = deleted;
            this.id = id;
            this.itemId = itemId;
            this.time = time;
            this.userId = userId;
        }

        public int getSoldCount() {
            return soldCount;
        }

        public void setSoldCount(int soldCount) {
            this.soldCount = soldCount;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getMessage() {
            return message;
        }

        public void setMessage(int message) {
            this.message = message;
        }

        public double getProfit() {
            return profit;
        }

        public void setProfit(double profit) {
            this.profit = profit;
        }

        public int getTeam() {
            return team;
        }

        public void setTeam(int team) {
            this.team = team;
        }

        public int getTotalMessage() {
            return totalMessage;
        }

        public void setTotalMessage(int totalMessage) {
            this.totalMessage = totalMessage;
        }

        public double getVipPrice() {
            return vipPrice;
        }

        public void setVipPrice(double vipPrice) {
            this.vipPrice = vipPrice;
        }

        public int getDirectCount() {
            return directCount;
        }

        public void setDirectCount(int directCount) {
            this.directCount = directCount;
        }

        public int getBoothCount() {
            return boothCount;
        }

        public void setBoothCount(int boothCount) {
            this.boothCount = boothCount;
        }

        public int getStreetCount() {
            return streetCount;
        }

        public void setStreetCount(int streetCount) {
            this.streetCount = streetCount;
        }

        public BigDecimal getMin() {
            return min;
        }

        public void setMin(BigDecimal min) {
            this.min = min;
        }

        public BigDecimal getMax() {
            return max;
        }

        public void setMax(BigDecimal max) {
            this.max = max;
        }

        public BigDecimal getRate() {
            return rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }

        public String getSharePath() {
            return sharePath;
        }

        public void setSharePath(String sharePath) {
            this.sharePath = sharePath;
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

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "soldCount=" + soldCount +
                    ", totalCount=" + totalCount +
                    ", message=" + message +
                    ", profit=" + profit +
                    ", team=" + team +
                    ", totalMessage=" + totalMessage +
                    ", vipPrice=" + vipPrice +
                    ", directCount=" + directCount +
                    ", boothCount=" + boothCount +
                    ", streetCount=" + streetCount +
                    ", min=" + min +
                    ", max=" + max +
                    ", rate=" + rate +
                    ", balance=" + balance +
                    ", sharePath='" + sharePath + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomeInfoModel{" +
                "code=" + code +
                ", data=" + data +
                ", failCode=" + failCode +
                ", message='" + message + '\'' +
                '}';
    }
}
