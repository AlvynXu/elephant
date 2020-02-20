package com.kupo.ElephantHead.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 检测是否出现蒙版
 */
@Entity
public class MaskInfo {
    private int isShow;
    private String desc;
    private String province;
    private String city;

    @Generated(hash = 174351309)
    public MaskInfo(int isShow, String desc, String province, String city) {
        this.isShow = isShow;
        this.desc = desc;
        this.province = province;
        this.city = city;
    }

    @Generated(hash = 447048263)
    public MaskInfo() {
    }

    public int getIsShow() {
        return this.isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "MaskInfo{" +
                "isShow=" + isShow +
                ", desc='" + desc + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
