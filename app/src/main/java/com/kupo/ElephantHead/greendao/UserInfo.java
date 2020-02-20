package com.kupo.ElephantHead.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 用户成功之后保存的信息
 */
@Entity
public class UserInfo {
    /**
     * balance :  LoginModel.DataBean.UserBean
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
     * levelName：等级
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
    private String token;
    private String levelName;
    private int chooseBoothCount;

    @Generated(hash = 54161503)
    public UserInfo(double balance, long createTime, boolean deleted, int id,
                    boolean isVip, int level, String password, String phone, double profit,
                    int promoterId, String regCode, double teamProfit, long updateTime,
                    String token, String levelName, int chooseBoothCount) {
        this.balance = balance;
        this.createTime = createTime;
        this.deleted = deleted;
        this.id = id;
        this.isVip = isVip;
        this.level = level;
        this.password = password;
        this.phone = phone;
        this.profit = profit;
        this.promoterId = promoterId;
        this.regCode = regCode;
        this.teamProfit = teamProfit;
        this.updateTime = updateTime;
        this.token = token;
        this.levelName = levelName;
        this.chooseBoothCount = chooseBoothCount;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsVip() {
        return this.isVip;
    }

    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getProfit() {
        return this.profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getPromoterId() {
        return this.promoterId;
    }

    public void setPromoterId(int promoterId) {
        this.promoterId = promoterId;
    }

    public String getRegCode() {
        return this.regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public double getTeamProfit() {
        return this.teamProfit;
    }

    public void setTeamProfit(double teamProfit) {
        this.teamProfit = teamProfit;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLevelName() {
        return this.levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getChooseBoothCount() {
        return this.chooseBoothCount;
    }

    public void setChooseBoothCount(int chooseBoothCount) {
        this.chooseBoothCount = chooseBoothCount;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
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
                ", token='" + token + '\'' +
                ", levelName='" + levelName + '\'' +
                ", chooseBoothCount=" + chooseBoothCount +
                '}';
    }
}
