package com.kupo.ElephantHead.base.baseview;

/**
 * Created by G400 on 2019/8/3.
 * 功能：MVP之V层 是所有VIEW的基类
 * 作者：
 */
public interface IView<T> {

    /**
     * 显示加载框
     */
   void showLoading();

    /**
     * 显示文字的加载框
     *
     * @param msg
     */
    void showLoading(String msg);

    /**
     * 隐藏加载框
     */
    void hideLoading();

}
