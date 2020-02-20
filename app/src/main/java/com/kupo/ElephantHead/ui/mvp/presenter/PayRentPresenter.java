package com.kupo.ElephantHead.ui.mvp.presenter;

import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.mvp.contract.MineContract;
import com.kupo.ElephantHead.ui.mvp.contract.PayOrderContract;
import com.kupo.ElephantHead.ui.mvp.contract.PayRentContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.transaction.model.RentStreetModel;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 获取可以出租的街道
 */
public class PayRentPresenter extends BasePresenter implements PayRentContract.IPayRentPresenter {

    private PayRentContract.IPayRentView payRentView;

    @Override
    public void getCanRentStreet(String token, RequestBody body) {
        LoginRequest.getCanRentStreet(token, body, new CallBack<RentStreetModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (payRentView != null) {
                    payRentView.showLoading();
                }
            }

            @Override
            public void thread(RentStreetModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (payRentView != null) {
                    try {
                        payRentView.getCanRentStreetSuccess(new Gson().fromJson(responseBody.string(), RentStreetModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    payRentView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (payRentView != null) {
                    payRentView.getCanRentStreetFailed(retCode, description);
                    payRentView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });

    }

    @Override
    public void attachView(IView view) {
        if (view instanceof PayRentContract.IPayRentView) {
            payRentView = (PayRentContract.IPayRentView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof PayRentContract.IPayRentView) {
            payRentView = null;
        }
    }

    @Override
    public void postRentToOther(String token, RequestBody body) {
        LoginRequest.postRentToOther(token, body, new CallBack<RentStreetModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (payRentView != null) {
                    payRentView.showLoading();
                }
            }

            @Override
            public void thread(RentStreetModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (payRentView != null) {
                    try {
                        payRentView.postRentToOtherSuccess(new Gson().fromJson(responseBody.string(), RentStreetModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    payRentView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (payRentView != null) {
                    payRentView.postRentToOtherFailed(retCode, description);
                    payRentView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }
}
