package com.kupo.ElephantHead.ui.mvp.presenter;

import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.home.model.MinePayDzOrZwModel;
import com.kupo.ElephantHead.ui.home.model.ZwItemModel;
import com.kupo.ElephantHead.ui.mvp.contract.OperationContract;
import com.kupo.ElephantHead.ui.mvp.contract.RoomContract;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;
import com.kupo.ElephantHead.ui.room.model.SaveBoothModel;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 获取可以使用的展位
 */
public class OperationPresenter extends BasePresenter implements OperationContract.IOperationPresenter {

    private OperationContract.IOperationView operationView;


    @Override
    public void getCanUseBooth(String token, Map<String, Object> map) {
        LoginRequest.getCanUseBooth(token, map, new CallBack<MinePayDzOrZwModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (operationView != null) {
                    operationView.showLoading();
                }
            }

            @Override
            public void thread(MinePayDzOrZwModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (operationView != null) {
                    try {
                        operationView.getCanUseBoothSuccess(new Gson().fromJson(responseBody.string(), MinePayDzOrZwModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    operationView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (operationView != null) {
                    operationView.getCanUseBoothFailed(retCode, description);
                    operationView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });

    }

    @Override
    public void saveItemBooth(String token, RequestBody body) {
        LoginRequest.saveItemBooth(token, body, new CallBack<SaveBoothModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (operationView != null) {
                    operationView.showLoading();
                }
            }

            @Override
            public void thread(SaveBoothModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (operationView != null) {
                    try {
                        operationView.saveItemBoothSuccess(new Gson().fromJson(responseBody.string(), SaveBoothModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    operationView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (operationView != null) {
                    operationView.saveItemBoothFailed(retCode, description);
                    operationView.hideLoading();
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
        if (view instanceof OperationContract.IOperationView) {
            operationView = (OperationContract.IOperationView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof OperationContract.IOperationView) {
            operationView = null;
        }
    }
}
