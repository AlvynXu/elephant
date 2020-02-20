package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mvp.contract.PayOrderContract;
import com.kupo.ElephantHead.ui.mvp.contract.QuestionBackContract;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @ClassName: LoginPresenter
 * @Description: 处理获取订单的业务逻辑
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class QuestionBackPresenter extends BasePresenter implements QuestionBackContract.IQuestionBackPresenter {

    private QuestionBackContract.IQuestionBackView qdzItemOrderInfoView;


    @Override
    public void saveFeedBack(String token, RequestBody body) {
        LoginRequest.saveFeedBack(token, body, new CallBack<BaseResult>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (qdzItemOrderInfoView != null) {
                    qdzItemOrderInfoView.showLoading();
                }
            }

            @Override
            public void thread(BaseResult loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (qdzItemOrderInfoView != null) {
                    try {
                        qdzItemOrderInfoView.getQuestionBackNetSuccess(new Gson().fromJson(responseBody.string(), BaseResult.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    qdzItemOrderInfoView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (qdzItemOrderInfoView != null) {
                    qdzItemOrderInfoView.getQuestionBackNetFailed(retCode, description);
                    qdzItemOrderInfoView.hideLoading();
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
        if (view instanceof QuestionBackContract.IQuestionBackView) {
            qdzItemOrderInfoView = (QuestionBackContract.IQuestionBackView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof QuestionBackContract.IQuestionBackView) {
            qdzItemOrderInfoView = null;
        }
    }


}
