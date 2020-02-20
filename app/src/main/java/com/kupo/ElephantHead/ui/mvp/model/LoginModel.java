package com.kupo.ElephantHead.ui.mvp.model;


import com.kupo.ElephantHead.base.basemodel.IModel;

/**
 * @ClassName: LoginModel
 * @Description: 登录成功的实体
 * @Author:
 * @CreateDate: 2019/8/16 13:49
 * @Version: 1.0
 */
public class LoginModel implements IModel {

    /**
     * code : 0
     * data : {"flag":true,"token":"","user":{"balance":0,"createTime":1574920792680,"deleted":false,"id":31,"isVip":false,"level":0,"password":"","phone":"13691212520","profit":0,"promoterId":0,"regCode":"176715","teamProfit":0,"updateTime":1574920792700}}
     * failCode : 0
     * message :
     */
    private transient int code;
    private DataBean data;
    private transient int failCode;
    private transient String message;


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
         * flag : true
         * token :
         * user : {"balance":0,"createTime":1574920792680,"deleted":false,"id":31,"isVip":false,"level":0,"password":"","phone":"13691212520","profit":0,"promoterId":0,"regCode":"176715","teamProfit":0,"updateTime":1574920792700}
         */

        private boolean flag;
        private String token;
        private UserBean user;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * balance : 0
             * createTime : 1574920792680
             * deleted : false
             * id : 31
             * isVip : false
             * level : 0
             * password :
             * phone : 13691212520
             * profit : 0
             * promoterId : 0
             * regCode : 176715
             * teamProfit : 0
             * updateTime : 1574920792700
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

            public int getChooseBoothCount() {
                return chooseBoothCount;
            }

            public void setChooseBoothCount(int chooseBoothCount) {
                this.chooseBoothCount = chooseBoothCount;
            }

            @Override
            public String toString() {
                return "UserBean{" +
                        "balance=" + balance +
                        ", createTime=" + createTime +
                        ", deleted=" + deleted +
                        ", id=" + id +
                        ", isVip=" + isVip +
                        ", level=" + level +
                        ", password='" + password + '\'' +
                        ", phone='" + phone + '\'' +
                        ", profit=" + profit +
                        ", promoterId=" + promoterId +
                        ", regCode='" + regCode + '\'' +
                        ", teamProfit=" + teamProfit +
                        ", updateTime=" + updateTime +
                        ", chooseBoothCount=" + chooseBoothCount +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "flag=" + flag +
                    ", token='" + token + '\'' +
                    ", user=" + user +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "code=" + code +
                ", data=" + data +
                ", failCode=" + failCode +
                ", message='" + message + '\'' +
                '}';
    }
}
