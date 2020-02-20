package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.main.model.AgreementModel;
import com.kupo.ElephantHead.ui.mvp.contract.AgreementContract;
import com.kupo.ElephantHead.ui.mvp.contract.RegisterContract;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;
import com.kupo.ElephantHead.utils.JsonUtil;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @ClassName: LoginPresenter
 * @Description: 用户协议
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class AgreementPresenter extends BasePresenter implements AgreementContract.IAgreementPresenter {

    private AgreementContract.IAgreementView agreementView;

    @Override
    public void getRegisterInfos() {
        LoginRequest.getRegisterInfos(new CallBack<AgreementModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (agreementView != null) {
//                    registerView.showLoading();
                }
            }

            @Override
            public void thread(AgreementModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (agreementView != null) {
                    try {
                        agreementView.getRegisterInfosSuccess(new Gson().fromJson(responseBody.string(), AgreementModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    agreementView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (agreementView != null) {
                    agreementView.getRegisterInfosFailed(retCode, description);
                    agreementView.hideLoading();
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
        if (view instanceof AgreementContract.IAgreementView) {
            agreementView = (AgreementContract.IAgreementView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof AgreementContract.IAgreementView) {
            agreementView = null;
        }
    }


}
