package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.DzItemModel;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.home.model.ZwItemModel;
import com.kupo.ElephantHead.ui.mvp.contract.CitySelectContract;
import com.kupo.ElephantHead.ui.mvp.contract.DzAndZwContract;
import com.kupo.ElephantHead.utils.JsonUtil;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @ClassName: CitySelectPresenter
 * @Description: 获取省份的业务逻辑
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class CitySelectPresenter extends BasePresenter implements CitySelectContract.ICitySelectPresenter {

    private CitySelectContract.ICitySelectView citySelectView;

    @Override
    public void getLocationNet(String token, String type, Map<String, Object> map) {
        LoginRequest.getLocationNet(token, type, map, new CallBack<ProvincesModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (citySelectView != null) {
                 //   citySelectView.showLoading();
                }
            }

            @Override
            public void thread(ProvincesModel zwItemModel) {
                super.thread(zwItemModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (citySelectView != null) {
                    try {

                        citySelectView.getLocationSuccess(new Gson().fromJson(responseBody.string(), ProvincesModel.class), type);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    citySelectView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (citySelectView != null) {
                    citySelectView.getLocationFailed(retCode, description);
                    citySelectView.hideLoading();
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
        if (view instanceof CitySelectContract.ICitySelectView) {
            citySelectView = (CitySelectContract.ICitySelectView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof CitySelectContract.ICitySelectView) {
            citySelectView = null;
        }
    }


}
