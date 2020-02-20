package com.kupo.ElephantHead.ui.mvp.presenter;

import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.mvp.contract.MineContract;
import com.kupo.ElephantHead.ui.mvp.contract.RoomContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;

import okhttp3.ResponseBody;

/**
 * 获取我的展位和地主数量
 */
public class MinePresenter extends BasePresenter implements MineContract.IMinePresenter {

    private MineContract.IMineView mineView;

    @Override
    public void getMyBaseInfo(String token) {
        LoginRequest.getMyBaseInfo(token, new CallBack<HomeInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (mineView != null) {
                    mineView.showLoading();
                }
            }

            @Override
            public void thread(HomeInfoModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (mineView != null) {
                    try {
                        mineView.getMyBaseInfoSuccess(new Gson().fromJson(responseBody.string(), HomeInfoModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mineView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (mineView != null) {
                    mineView.getMyBaseInfoFailed(retCode, description);
                    mineView.hideLoading();
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
        if (view instanceof MineContract.IMineView) {
            mineView = (MineContract.IMineView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof MineContract.IMineView) {
            mineView = null;
        }
    }
}
