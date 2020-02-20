package com.kupo.ElephantHead.ui.mvp.presenter;

import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mvp.contract.CollectContract;
import com.kupo.ElephantHead.ui.mvp.contract.MineContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * 获取我的收藏列表
 */
public class CollectPresenter extends BasePresenter implements CollectContract.ICollectPresenter {

    private CollectContract.ICollectView collectView;

    @Override
    public void getCollectListInfo(String token, Map<String, Object> map) {
        LoginRequest.getCollectionPageList(token, map, new CallBack<HomeTopModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (collectView != null) {
                    collectView.showLoading();
                }
            }

            @Override
            public void thread(HomeTopModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (collectView != null) {
                    try {
                        collectView.getCollectListSuccess(new Gson().fromJson(responseBody.string(), HomeTopModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    collectView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (collectView != null) {
                    collectView.getCollectListFailed(retCode, description);
                    collectView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });

    }

    @Override
    public void attachView(IView view) {
        if (view instanceof CollectContract.ICollectView) {
            collectView = (CollectContract.ICollectView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof CollectContract.ICollectView) {
            collectView = null;
        }
    }
}
