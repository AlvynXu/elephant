package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.AppShareModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mvp.contract.AppShareContract;
import com.kupo.ElephantHead.ui.mvp.contract.PayOrderContract;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @ClassName: AppSharePresenter
 * @Description: App分享数据获取操作
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class AppSharePresenter extends BasePresenter implements AppShareContract.IAppSharePresenter {

    private AppShareContract.IAppShareInfoView appShareInfoView;


    @Override
    public void getAppShareInfoNet(String token) {
        LoginRequest.getAppShare(token, new CallBack<AppShareModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (appShareInfoView != null) {
                    appShareInfoView.showLoading();
                }
            }

            @Override
            public void thread(AppShareModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (appShareInfoView != null) {
                    try {
                        appShareInfoView.getAppShareNetSuccess(new Gson().fromJson(responseBody.string(), AppShareModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    appShareInfoView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (appShareInfoView != null) {
                    appShareInfoView.getAppShareNetFailed(retCode, description);
                    appShareInfoView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }


    /**
     * 绑定View
     *
     * @param view
     */
    @Override
    public void attachView(IView view) {
        if (view instanceof AppShareContract.IAppShareInfoView) {
            appShareInfoView = (AppShareContract.IAppShareInfoView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof AppShareContract.IAppShareInfoView) {
            appShareInfoView = null;
        }
    }


}
