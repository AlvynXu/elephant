package com.kupo.ElephantHead.ui.mvp.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.mvp.contract.MineContract;
import com.kupo.ElephantHead.ui.mvp.contract.MyRentContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 我的求租
 */
public class MyRentPresenter extends BasePresenter implements MyRentContract.IMyRentPresenter {

    private MyRentContract.IMyRentView myRentView;

    @Override
    public void getLocationNet(String token, String type, Map<String, Object> map) {
        LoginRequest.getLocationNet(token, type, map, new CallBack<ProvincesModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (myRentView != null) {
                    myRentView.showLoading();
                }
            }

            @Override
            public void thread(ProvincesModel zwItemModel) {
                super.thread(zwItemModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (myRentView != null) {
                    try {
                        myRentView.getLocationSuccess(new Gson().fromJson(responseBody.string(), ProvincesModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    myRentView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (myRentView != null) {
                    myRentView.getLocationFailed(retCode, description);
                    myRentView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });

    }

    @Override
    public void postSeekRentNt(String token, RequestBody body) {
        LoginRequest.postSeekRent(token, body, new CallBack<PayInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (myRentView != null) {
                    myRentView.showLoading();
                }
            }

            @Override
            public void thread(PayInfoModel payInfoModel) {
                super.thread(payInfoModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (myRentView != null) {
                    try {
                        myRentView.postSeekRentSuccess(new Gson().fromJson(responseBody.string(), PayInfoModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    myRentView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (myRentView != null) {
                    myRentView.postSeekRentFailed(retCode, description);
                    myRentView.hideLoading();
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
        if (view instanceof MyRentContract.IMyRentView) {
            myRentView = (MyRentContract.IMyRentView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof MyRentContract.IMyRentView) {
            myRentView = null;
        }
    }
}
