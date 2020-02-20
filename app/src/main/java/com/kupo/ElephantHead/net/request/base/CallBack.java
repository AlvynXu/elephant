package com.kupo.ElephantHead.net.request.base;

import okhttp3.ResponseBody;

/**
 * Created by G400 on 2019/8/3.
 * 功能：网络请求返回实现类
 * 作者：
 */
public abstract class CallBack<T> implements ICallBack<T> {

    @Override
    public void prepare(String url) {

    }

    @Override
    public void thread(T t) {

    }

    @Override
    public void success(ResponseBody responseBody) {

    }

    @Override
    public void failure(int retCode, String description) {

    }

    @Override
    public void end() {

    }
}
