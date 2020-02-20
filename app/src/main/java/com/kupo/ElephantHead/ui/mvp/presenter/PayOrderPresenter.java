package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mvp.contract.PayOrderContract;
import com.kupo.ElephantHead.ui.mvp.model.BuySuccessModel;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @ClassName: LoginPresenter
 * @Description: 处理获取订单的业务逻辑
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class PayOrderPresenter extends BasePresenter implements PayOrderContract.IQdzItemOrderInfoPresenter {

    private PayOrderContract.IQdzItemOrderInfoView qdzItemOrderInfoView;


    @Override
    public void getQdzItemOrderInfoNet(String token, String type, String payType, Map<String, Object> map) {
        LoginRequest.getQdzItemOrderInfoNet(token, type, payType, map, new CallBack<PayInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (qdzItemOrderInfoView != null) {
                    qdzItemOrderInfoView.showLoading();
                }
            }

            @Override
            public void thread(PayInfoModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (qdzItemOrderInfoView != null) {
                    try {
                        qdzItemOrderInfoView.getQdzItemOrderInfoNetSuccess(new Gson().fromJson(responseBody.string(), PayInfoModel.class));
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
                    qdzItemOrderInfoView.getQdzItemOrderInfoNetFailed(retCode, description);
                    qdzItemOrderInfoView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getPaySuccessInfo(String token, String centerType, String footerType, Map<String, Object> map) {
        LoginRequest.getPaySuccessInfo(token, centerType, footerType, map, new CallBack<BuySuccessModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (qdzItemOrderInfoView != null) {
                    qdzItemOrderInfoView.showLoading();
                }
            }

            @Override
            public void thread(BuySuccessModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (qdzItemOrderInfoView != null) {
                    try {
                        qdzItemOrderInfoView.getBuySuccessNetSuccess(new Gson().fromJson(responseBody.string(), BuySuccessModel.class));
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
                    qdzItemOrderInfoView.getBuySuccessNetFailed(retCode, description);
                    qdzItemOrderInfoView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getCancelPayInfo(String token, Map<String, Object> map) {
        LoginRequest.getCancelPay(token, map, new CallBack<PayInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (qdzItemOrderInfoView != null) {
                    qdzItemOrderInfoView.showLoading();
                }
            }

            @Override
            public void thread(PayInfoModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (qdzItemOrderInfoView != null) {
                    try {
                        qdzItemOrderInfoView.getCancelPaySuccess(new Gson().fromJson(responseBody.string(), PayInfoModel.class));
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
                    qdzItemOrderInfoView.getCancelPayFailed(retCode, description);
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
        if (view instanceof PayOrderContract.IQdzItemOrderInfoView) {
            qdzItemOrderInfoView = (PayOrderContract.IQdzItemOrderInfoView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof PayOrderContract.IQdzItemOrderInfoView) {
            qdzItemOrderInfoView = null;
        }
    }


}
