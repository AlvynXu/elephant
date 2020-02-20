package com.kupo.ElephantHead.ui.mvp.presenter;

import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.mvp.contract.RoomContract;
import com.kupo.ElephantHead.ui.mvp.contract.RoomItemContract;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * 展厅列表数据
 */
public class RoomItemPresenter extends BasePresenter implements RoomItemContract.IRoomItemPresenter {

    private RoomItemContract.IRoomItemView roomItemView;


    @Override
    public void getRoomItemList(String token, Map<String, Object> map) {
        LoginRequest.getRoomItemPageList(token, map, new CallBack<HomeTopModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (roomItemView != null) {
                    roomItemView.showLoading();
                }
            }

            @Override
            public void thread(HomeTopModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (roomItemView != null) {
                    try {
                        roomItemView.getRoomItemListSuccess(new Gson().fromJson(responseBody.string(), HomeTopModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    roomItemView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (roomItemView != null) {
                    roomItemView.getRoomItemListFailed(retCode, description);
                    roomItemView.hideLoading();
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
                if (roomItemView != null) {
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
                if (roomItemView != null) {
                    try {
                        roomItemView.getCityCodeSuccess(new Gson().fromJson(responseBody.string(), CityCode.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    roomItemView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (roomItemView != null) {
                    roomItemView.getCityCodeFailed(retCode, description);
                    roomItemView.hideLoading();
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
        if (view instanceof RoomItemContract.IRoomItemView) {
            roomItemView = (RoomItemContract.IRoomItemView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof RoomItemContract.IRoomItemView) {
            roomItemView = null;
        }
    }
}
