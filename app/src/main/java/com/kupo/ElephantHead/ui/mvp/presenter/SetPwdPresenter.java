package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.mvp.contract.LoginContract;
import com.kupo.ElephantHead.ui.mvp.contract.SetUserPwdContract;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.utils.JsonUtil;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @ClassName: LoginPresenter
 * @Description: 处理设置用户密码的业务逻辑
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class SetPwdPresenter extends BasePresenter implements SetUserPwdContract.ISetUserPwdPresenter {

    private SetUserPwdContract.ISetUserPwdView setUserPwdView;

    /**
     * 请求登陆接口
     */
    @Override
    public void getSetUserPwdNet(String phone, String pwd, String userId) {
        LoginRequest.setUserPwdNet(phone, pwd, userId, new CallBack<LoginModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (setUserPwdView != null) {
                    setUserPwdView.showLoading();
                }
            }

            @Override
            public void thread(LoginModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (setUserPwdView != null) {
                    try {
                        setUserPwdView.getSetUserPwdNetSuccess(JsonUtil.JsonLoginModel(responseBody.string()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    setUserPwdView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (setUserPwdView != null) {
                    setUserPwdView.getSetUserPwdFailed(retCode, description);
                    setUserPwdView.hideLoading();
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
        if (view instanceof SetUserPwdContract.ISetUserPwdView) {
            setUserPwdView = (SetUserPwdContract.ISetUserPwdView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof SetUserPwdContract.ISetUserPwdView) {
            setUserPwdView = null;
        }
    }


}
