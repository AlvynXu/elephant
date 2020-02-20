package com.kupo.ElephantHead.ui.mvp.presenter;

import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.mvp.contract.CrowdContract;
import com.kupo.ElephantHead.ui.transaction.model.CrowdModel;
import com.kupo.ElephantHead.ui.transaction.model.MyCrowdModel;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * 交易模块
 */
public class CrowdPresenter extends BasePresenter implements CrowdContract.ICrowdPresenter {

    private CrowdContract.ICrowdView crowdView;


    @Override
    public void attachView(IView view) {
        if (view instanceof CrowdContract.ICrowdView) {
            crowdView = (CrowdContract.ICrowdView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof CrowdContract.ICrowdView) {
            crowdView = null;
        }
    }

    @Override
    public void getRent(String token, Map<String, Object> map) {
        LoginRequest.getRent(token, map, new CallBack<CrowdModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (crowdView != null) {
                    crowdView.showLoading();
                }
            }

            @Override
            public void thread(CrowdModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (crowdView != null) {
                    try {
                        crowdView.getRentSuccess(new Gson().fromJson(responseBody.string(), CrowdModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    crowdView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (crowdView != null) {
                    crowdView.getRentFailed(retCode, description);
                    crowdView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });

    }

    @Override
    public void getCityCode(String token, Map<String, Object> map) {
        LoginRequest.getCityCode(token, map, new CallBack<CityCode>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (crowdView != null) {
                    //     dzAndZwView.showLoading();
                }
            }

            @Override
            public void thread(CityCode loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (crowdView != null) {
                    try {
                        crowdView.getCityCodeSuccess(new Gson().fromJson(responseBody.string(), CityCode.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    crowdView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (crowdView != null) {
                    crowdView.getCityCodeFailed(retCode, description);
                    crowdView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getMyRent(String token, Map<String, Object> map) {
        LoginRequest.getMyRent(token, map, new CallBack<MyCrowdModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (crowdView != null) {
                    crowdView.showLoading();
                }
            }

            @Override
            public void thread(MyCrowdModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (crowdView != null) {
                    try {
                        crowdView.getMyRentSuccess(new Gson().fromJson(responseBody.string(), MyCrowdModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    crowdView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (crowdView != null) {
                    crowdView.getMyRentFailed(retCode, description);
                    crowdView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

}
