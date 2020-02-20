package com.kupo.ElephantHead.base.basepresenter;

import com.kupo.ElephantHead.base.baseview.IView;

/**
 * Created by G400 on 2019/8/4.
 * 功能：MVP的P层-基类
 * 作者：
 */
public interface IPresenter<V extends IView> {

    /**
     * 绑定P与V
     *
     * @param view
     */
    void attachView(V view);

    /**
     * 解绑P与V
     *
     * @param view
     */
    void detachView(V view);
}
