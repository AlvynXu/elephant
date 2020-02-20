package com.kupo.ElephantHead.ui.mvp.presenter;



import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.mvp.contract.LoginContract;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @ClassName: LoginPresenter
 * @Description: 处理登录的业务逻辑
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class LoginPresenter extends BasePresenter implements LoginContract.ILoginPresenter {

    private LoginContract.ILoginView loginView;

    /**
     * 请求登陆接口
     */
    @Override
    public void getLoginNet(RequestBody body) {
        LoginRequest.getLoginNet(body, new CallBack<LoginModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (loginView != null) {
                    loginView.showLoading();
                }
            }

            @Override
            public void thread(LoginModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (loginView != null) {
                    try {
                        loginView.getLoginNetSuccess(responseBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    loginView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (loginView != null) {
                    loginView.getLoginNetFailed(retCode, description);
                    loginView.hideLoading();
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
        if (view instanceof LoginContract.ILoginView) {
            loginView = (LoginContract.ILoginView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof LoginContract.ILoginView) {
            loginView = null;
        }
    }


}
