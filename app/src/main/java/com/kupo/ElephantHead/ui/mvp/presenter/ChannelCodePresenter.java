package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.mvp.contract.ChannelCodeContract;
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
public class ChannelCodePresenter extends BasePresenter implements ChannelCodeContract.IChannelCodePresenter {

    private ChannelCodeContract.IChannelCodeView channelCodeView;

    /**
     * 请求登陆接口
     */
    @Override
    public void getChannelCodeNet(RequestBody body) {
        LoginRequest.setUserPromoterNet(body, new CallBack<BaseResult>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (channelCodeView != null) {
                    channelCodeView.showLoading();
                }
            }

            @Override
            public void thread(BaseResult loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (channelCodeView != null) {
                    try {
                        channelCodeView.getChannelCodeNetSuccess(new Gson().fromJson(responseBody.string(), BaseResult.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    channelCodeView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (channelCodeView != null) {
                    channelCodeView.getChannelCodeNetFailed(retCode, description);
                    channelCodeView.hideLoading();
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
        if (view instanceof ChannelCodeContract.IChannelCodeView) {
            channelCodeView = (ChannelCodeContract.IChannelCodeView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof ChannelCodeContract.IChannelCodeView) {
            channelCodeView = null;
        }
    }


}
