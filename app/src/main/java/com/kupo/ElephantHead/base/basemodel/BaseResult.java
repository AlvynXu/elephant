package com.kupo.ElephantHead.base.basemodel;

/**
 * Created by G400 on 2019/8/4.
 * 功能：接口返回的实体结果信息
 * 作者：
 */
public class BaseResult {

    /**
     * code : 0
     * data : true
     * failCode : 0
     * message :
     */

    private int code;
    private int failCode;
    private String message;

    public BaseResult(String message) {
        this.message = message;
    }

    public BaseResult(int code) {
        this.code = code;
    }

    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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


    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", failCode=" + failCode +
                ", message='" + message + '\'' +
                '}';
    }
}
