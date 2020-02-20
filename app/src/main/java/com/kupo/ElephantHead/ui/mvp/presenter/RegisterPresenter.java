package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.main.model.AgreementModel;
import com.kupo.ElephantHead.ui.mvp.contract.RegisterContract;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;
import com.kupo.ElephantHead.utils.JsonUtil;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @ClassName: LoginPresenter
 * @Description: 处理注册验证码登录的业务逻辑
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class RegisterPresenter extends BasePresenter implements RegisterContract.IRegisterPresenter {

    private RegisterContract.IRegisterView registerView;

    @Override
    public void getRegisterNet(String body) {
        LoginRequest.getRegisterCodeNet(body, new CallBack<BaseResult>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (registerView != null) {
                    registerView.showLoading();
                }
            }

            @Override
            public void thread(BaseResult loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (registerView != null) {
                    try {
                        registerView.getRegisterNetSuccess(new Gson().fromJson(responseBody.string(), BaseResult.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    registerView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (registerView != null) {
                    registerView.getRegisterNetFailed(retCode, description);
                    registerView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });

    }

    @Override
    public void validRegisterCodeNet(RequestBody body) {
        LoginRequest.validRegisterCodeNet(body, new CallBack<LoginModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (registerView != null) {
                    registerView.showLoading();
                }
            }

            @Override
            public void thread(LoginModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (registerView != null) {
                    try {
                        registerView.validRegisterCodeNetSuccess(JsonUtil.JsonLoginModel(responseBody.string()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    registerView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (registerView != null) {
                    registerView.validRegisterCodeNetFailed(retCode, description);
                    registerView.hideLoading();
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
        if (view instanceof RegisterContract.IRegisterView) {
            registerView = (RegisterContract.IRegisterView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof RegisterContract.IRegisterView) {
            registerView = null;
        }
    }


}
