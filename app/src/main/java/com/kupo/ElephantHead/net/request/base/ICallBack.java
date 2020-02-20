package com.kupo.ElephantHead.net.request.base;

import okhttp3.ResponseBody;

/**
 * Created by G400 on 2019/8/3.
 * 功能：网络请求返回
 * 作者：
 */
public interface ICallBack<T> {

    /**
     * 请求前
     *
     * @param url
     */
    public void prepare(String url);

    /**
     * 请求完成还处于线程当中
     *
     * @param t
     */
    public void thread(T t);

    /**
     * 请求成功
     *
     * @param responseBody
     */
    public void success(ResponseBody responseBody);

    /**
     * 请求失败
     *
     * @param retCode
     * @param description
     */
    public void failure(int retCode, String description);

    /**
     * 请求结束
     */
    public void end();

}
