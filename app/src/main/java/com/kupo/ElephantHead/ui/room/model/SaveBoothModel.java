package com.kupo.ElephantHead.ui.room.model;

import java.util.List;

/**
 * 上架成功后返回的实体
 */
public class SaveBoothModel {

    /**
     * code : 0
     * data : {"boothId":0,"boothIds":[88],"enable":false,"id":0,"itemId":1}
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
         * boothId : 0
         * boothIds : [88]
         * enable : false
         * id : 0
         * itemId : 1
         */

        private int boothId;
        private boolean enable;
        private int id;
        private int itemId;
        private List<Integer> boothIds;

        public int getBoothId() {
            return boothId;
        }

        public void setBoothId(int boothId) {
            this.boothId = boothId;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
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

        public List<Integer> getBoothIds() {
            return boothIds;
        }

        public void setBoothIds(List<Integer> boothIds) {
            this.boothIds = boothIds;
        }
    }
}
