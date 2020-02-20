package com.kupo.ElephantHead.ui.mvp.presenter;

import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.mvp.contract.MyProjectContract;
import com.kupo.ElephantHead.ui.mvp.contract.RoomContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;
import com.kupo.ElephantHead.ui.room.model.RoomIDetailsModel;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * 我的广告
 */
public class MyProjectPresenter extends BasePresenter implements MyProjectContract.IMyProjectPresenter {

    private MyProjectContract.IMyProjectView myProjectView;


    @Override
    public void getMyProjectList(String token, Map<String, Object> map) {
        LoginRequest.getMyItemPageList(token, map, new CallBack<HomeTopModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (myProjectView != null) {
                    myProjectView.showLoading();
                }
            }

            @Override
            public void thread(HomeTopModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (myProjectView != null) {
                    try {
                        myProjectView.getMyProjectListSuccess(new Gson().fromJson(responseBody.string(), HomeTopModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    myProjectView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (myProjectView != null) {
                    myProjectView.getMyProjectListFailed(retCode, description);
                    myProjectView.hideLoading();
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
        if (view instanceof MyProjectContract.IMyProjectView) {
            myProjectView = (MyProjectContract.IMyProjectView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof MyProjectContract.IMyProjectView) {
            myProjectView = null;
        }
    }
}
