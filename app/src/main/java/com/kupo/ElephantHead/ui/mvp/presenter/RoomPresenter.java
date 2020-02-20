package com.kupo.ElephantHead.ui.mvp.presenter;

import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.WalletModel;
import com.kupo.ElephantHead.ui.mvp.contract.RoomContract;
import com.kupo.ElephantHead.ui.mvp.contract.WalletContract;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;
import com.kupo.ElephantHead.utils.JsonUtil;

import okhttp3.ResponseBody;

/**
 * 提现详情
 */
public class RoomPresenter extends BasePresenter implements RoomContract.IRoomPresenter {

    private RoomContract.IRoomView roomView;


    @Override
    public void getCategoryList(String token) {
        LoginRequest.getCategoryList(token, new CallBack<CategoryModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (roomView != null) {
                    roomView.showLoading();
                }
            }

            @Override
            public void thread(CategoryModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (roomView != null) {
                    try {
                        roomView.getCategoryListSuccess(new Gson().fromJson(responseBody.string(), CategoryModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    roomView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (roomView != null) {
                    roomView.getCategoryListFailed(retCode, description);
                    roomView.hideLoading();
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
        if (view instanceof RoomContract.IRoomView) {
            roomView = (RoomContract.IRoomView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof RoomContract.IRoomView) {
            roomView = null;
        }
    }
}
