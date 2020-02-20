package com.kupo.ElephantHead.ui.home.model;

import java.util.List;

import genealogy.jczb.com.rvlibrary.entity.MultiItemEntity;

/**
 * 省实体
 */
public class ProvincesModel {

    /**
     * code : 0
     * data : [{"code":"110000","id":2,"level":1,"name":"北京市","parentCode":"86","price":0},{"code":"120000","id":20,"level":1,"name":"天津市","parentCode":"86","price":0},{"code":"130000","id":38,"level":1,"name":"河北省","parentCode":"86","price":0},{"code":"140000","id":219,"level":1,"name":"山西省","parentCode":"86","price":0},{"code":"150000","id":350,"level":1,"name":"内蒙古自治区","parentCode":"86","price":0},{"code":"210000","id":466,"level":1,"name":"辽宁省","parentCode":"86","price":0},{"code":"220000","id":581,"level":1,"name":"吉林省","parentCode":"86","price":0},{"code":"230000","id":651,"level":1,"name":"黑龙江省","parentCode":"86","price":0},{"code":"310000","id":793,"level":1,"name":"上海市","parentCode":"86","price":0},{"code":"320000","id":811,"level":1,"name":"江苏省","parentCode":"86","price":0},{"code":"330000","id":921,"level":1,"name":"浙江省","parentCode":"86","price":0},{"code":"340000","id":1023,"level":1,"name":"安徽省","parentCode":"86","price":0},{"code":"350000","id":1145,"level":1,"name":"福建省","parentCode":"86","price":0},{"code":"360000","id":1240,"level":1,"name":"江西省","parentCode":"86","price":0},{"code":"370000","id":1352,"level":1,"name":"山东省","parentCode":"86","price":0},{"code":"410000","id":1507,"level":1,"name":"河南省","parentCode":"86","price":0},{"code":"420000","id":1684,"level":1,"name":"湖北省","parentCode":"86","price":0},{"code":"430000","id":1801,"level":1,"name":"湖南省","parentCode":"86","price":0},{"code":"440000","id":1938,"level":1,"name":"广东省","parentCode":"86","price":0},{"code":"450000","id":2079,"level":1,"name":"广西壮族自治区","parentCode":"86","price":0},{"code":"460000","id":2205,"level":1,"name":"海南省","parentCode":"86","price":0},{"code":"500000","id":2233,"level":1,"name":"重庆市","parentCode":"86","price":0},{"code":"510000","id":2274,"level":1,"name":"四川省","parentCode":"86","price":0},{"code":"520000","id":2479,"level":1,"name":"贵州省","parentCode":"86","price":0},{"code":"530000","id":2577,"level":1,"name":"云南省","parentCode":"86","price":0},{"code":"540000","id":2723,"level":1,"name":"西藏自治区","parentCode":"86","price":0},{"code":"610000","id":2805,"level":1,"name":"陕西省","parentCode":"86","price":0},{"code":"620000","id":2923,"level":1,"name":"甘肃省","parentCode":"86","price":0},{"code":"630000","id":3024,"level":1,"name":"青海省","parentCode":"86","price":0},{"code":"640000","id":3076,"level":1,"name":"宁夏回族自治区","parentCode":"86","price":0},{"code":"650000","id":3104,"level":1,"name":"新疆维吾尔自治区","parentCode":"86","price":0},{"code":"710000","id":3220,"level":1,"name":"台湾省","parentCode":"86","price":0},{"code":"810000","id":3221,"level":1,"name":"香港特别行政区","parentCode":"86","price":0},{"code":"820000","id":3240,"level":1,"name":"澳门特别行政区","parentCode":"86","price":0}]
     * msg : Ok
     * success : true
     */

    private int code;
    private String msg;
    private boolean success;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements MultiItemEntity {
        /**
         * code : 110000
         * id : 2
         * level : 1
         * name : 北京市
         * parentCode : 86
         * price : 0
         */

        private String code;
        private int id;
        private int level;
        private String name;
        private String parentCode;
        private int price;
        private boolean isSelected;
        private String areaCode;

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

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
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
                    ", isSelected=" + isSelected +
                    ", areaCode='" + areaCode + '\'' +
                    '}';
        }

        @Override
        public int getItemType() {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "ProvincesModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
