package com.kupo.ElephantHead.ui.mvp.presenter;

import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.mvp.contract.MineContract;
import com.kupo.ElephantHead.ui.mvp.contract.RentCityContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;

import okhttp3.ResponseBody;

/**
 * 获取全部城市
 */
public class RentCityPresenter extends BasePresenter implements RentCityContract.IRentCityPresenter {

    private RentCityContract.IRentCityView rentCityView;

    @Override
    public void getCities(String token) {
        LoginRequest.getCities(token, new CallBack<ProvincesModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (rentCityView != null) {
                    rentCityView.showLoading();
                }
            }

            @Override
            public void thread(ProvincesModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (rentCityView != null) {
                    try {
                        rentCityView.getCitiesSuccess(new Gson().fromJson(responseBody.string(), ProvincesModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    rentCityView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (rentCityView != null) {
                    rentCityView.getCitiesFailed(retCode, description);
                    rentCityView.hideLoading();
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
        if (view instanceof RentCityContract.IRentCityView) {
            rentCityView = (RentCityContract.IRentCityView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof RentCityContract.IRentCityView) {
            rentCityView = null;
        }
    }
}
