package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.DzLockModel;
import com.kupo.ElephantHead.ui.home.model.MinePayDzOrZwModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mvp.contract.MinePayDzOrZwContract;
import com.kupo.ElephantHead.ui.mvp.contract.PayOrderContract;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @ClassName: LoginPresenter
 * @Description: 处理获取订单的业务逻辑
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class MinePayDzOrZwPresenter extends BasePresenter implements MinePayDzOrZwContract.IMinePayDzOrZwPresenter {

    private MinePayDzOrZwContract.IMinePayDzOrZwView minePayDzOrZwView;


    @Override
    public void getMinePayDzOrZwNet(String token, String centerType, String footType, Map<String, Object> map) {
        LoginRequest.getMinePayDzOrZwData(token, centerType, footType, map, new CallBack<MinePayDzOrZwModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (minePayDzOrZwView != null) {
                    minePayDzOrZwView.showLoading();
                }
            }

            @Override
            public void thread(MinePayDzOrZwModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (minePayDzOrZwView != null) {
                    try {
                        minePayDzOrZwView.getMinePayDzOrZwNetSuccess(new Gson().fromJson(responseBody.string(), MinePayDzOrZwModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    minePayDzOrZwView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (minePayDzOrZwView != null) {
                    minePayDzOrZwView.getMinePayDzOrZwNetFailed(retCode, description);
                    minePayDzOrZwView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getChooseCount(String token) {
        LoginRequest.getChooseCount(token, new CallBack<DzLockModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (minePayDzOrZwView != null) {
                    minePayDzOrZwView.showLoading();
                }
            }

            @Override
            public void thread(DzLockModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (minePayDzOrZwView != null) {
                    try {
                        minePayDzOrZwView.getChooseCountSuccess(new Gson().fromJson(responseBody.string(), DzLockModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    minePayDzOrZwView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (minePayDzOrZwView != null) {
                    minePayDzOrZwView.getChooseCountFailed(retCode, description);
                    minePayDzOrZwView.hideLoading();
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
        if (view instanceof MinePayDzOrZwContract.IMinePayDzOrZwView) {
            minePayDzOrZwView = (MinePayDzOrZwContract.IMinePayDzOrZwView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof MinePayDzOrZwContract.IMinePayDzOrZwView) {
            minePayDzOrZwView = null;
        }
    }


}
