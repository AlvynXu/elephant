package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mvp.contract.BuySuccessContract;
import com.kupo.ElephantHead.ui.mvp.contract.PayOrderContract;
import com.kupo.ElephantHead.ui.mvp.model.BuySuccessModel;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @ClassName: LoginPresenter
 * @Description: 处理获取订单的业务逻辑
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class BuySuccessPresenter extends BasePresenter implements BuySuccessContract.IBuySuccessPresenter {

    private BuySuccessContract.IBuySuccessView buySuccessView;


    @Override
    public void getPaySuccessInfo(String token, String centerType, String footerType, Map<String, Object> map) {
        LoginRequest.getPaySuccessInfo(token, centerType, footerType, map, new CallBack<BuySuccessModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (buySuccessView != null) {
                    buySuccessView.showLoading();
                }
            }

            @Override
            public void thread(BuySuccessModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (buySuccessView != null) {
                    try {
                        buySuccessView.getBuySuccessNetSuccess(new Gson().fromJson(responseBody.string(), BuySuccessModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    buySuccessView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (buySuccessView != null) {
                    buySuccessView.getBuySuccessNetFailed(retCode, description);
                    buySuccessView.hideLoading();
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
        if (view instanceof BuySuccessContract.IBuySuccessView) {
            buySuccessView = (BuySuccessContract.IBuySuccessView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof BuySuccessContract.IBuySuccessView) {
            buySuccessView = null;
        }
    }


}
