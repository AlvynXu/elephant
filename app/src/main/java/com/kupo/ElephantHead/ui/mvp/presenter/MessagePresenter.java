package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.MessageModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mvp.contract.MessageContract;
import com.kupo.ElephantHead.ui.mvp.contract.PayOrderContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @ClassName: MessagePresenter
 * @Description: 处理获取消息列表的业务逻辑
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class MessagePresenter extends BasePresenter implements MessageContract.IMessagePresenter {

    private MessageContract.IMessageView messageView;


    @Override
    public void getMessageNet(String token, String type, Map<String, Object> map) {
        LoginRequest.getMessageListNet(token, type, map, new CallBack<MessageModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (messageView != null) {
                    //     messageView.showLoading();
                }
            }

            @Override
            public void thread(MessageModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (messageView != null) {
                    try {
                        messageView.getMessageNetSuccess(new Gson().fromJson(responseBody.string(), MessageModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    messageView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (messageView != null) {
                    messageView.getMessageNetFailed(retCode, description);
                    messageView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void setMessageIsReadNet(String token, String type, Map<String, Object> map) {
        LoginRequest.setMessageIsReadNet(token, type, map, new CallBack<MessageModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (messageView != null) {
                    messageView.showLoading();
                }
            }

            @Override
            public void thread(MessageModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (messageView != null) {
                    try {
                        messageView.setMessageIsReadNetSuccess(new Gson().fromJson(responseBody.string(), MessageModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    messageView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (messageView != null) {
                    messageView.setMessageIsReadNetFailed(retCode, description);
                    messageView.hideLoading();
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
        if (view instanceof MessageContract.IMessageView) {
            messageView = (MessageContract.IMessageView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof MessageContract.IMessageView) {
            messageView = null;
        }
    }


}
