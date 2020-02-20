package com.kupo.ElephantHead.ui.mvp.presenter;

import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.CurrentUserModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.home.model.WalletModel;
import com.kupo.ElephantHead.ui.mvp.contract.WalletContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.utils.JsonUtil;

import java.math.BigDecimal;

import okhttp3.ResponseBody;

/**
 * 提现详情
 */
public class WalletPresenter extends BasePresenter implements WalletContract.IWalletPresenter {

    private WalletContract.IWalletView walletView;


    @Override
    public void postCashAdvanceData(String token, String footType, int amount) {
        LoginRequest.postCashAdvance(token, footType, amount, new CallBack<WalletModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (walletView != null) {
                    walletView.showLoading();
                }
            }

            @Override
            public void thread(WalletModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (walletView != null) {
                    try {
                        walletView.postCashAdvanceSuccess(new Gson().fromJson(responseBody.string(), WalletModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    walletView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (walletView != null) {
                    walletView.postCashAdvanceFailed(retCode, description);
                    walletView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });


    }

    @Override
    public void postWalletData(String token, String footType, BigDecimal amount) {
        LoginRequest.postTransfer(token, footType, amount, new CallBack<PayInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (walletView != null) {
                    walletView.showLoading();
                }
            }

            @Override
            public void thread(PayInfoModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (walletView != null) {
                    try {
                        walletView.postWalletSuccess(new Gson().fromJson(responseBody.string(), PayInfoModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    walletView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (walletView != null) {
                    walletView.postWalletFailed(retCode, description);
                    walletView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getRechargeInfo(String token) {
        LoginRequest.getRechargeInfo(token, new CallBack<HomeInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (walletView != null) {
                    walletView.showLoading();
                }
            }

            @Override
            public void thread(HomeInfoModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (walletView != null) {
                    try {
                        walletView.getRechargeInfoSuccess(new Gson().fromJson(responseBody.string(), HomeInfoModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    walletView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (walletView != null) {
                    walletView.getRechargeInfoFailed(retCode, description);
                    walletView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getCurrentUserInfo(String body) {
        LoginRequest.getCurrentUserInfo(body, new CallBack<CurrentUserModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (walletView != null) {
                    //    homeView.showLoading();
                }
            }

            @Override
            public void thread(CurrentUserModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (walletView != null) {
                    try {
                        walletView.getCurrentUserInfoNetSuccess(new Gson().fromJson(responseBody.string(), CurrentUserModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    walletView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (walletView != null) {
                    walletView.getCurrentUserInfoNetFailed(retCode, description);
                    walletView.hideLoading();
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
        if (view instanceof WalletContract.IWalletView) {
            walletView = (WalletContract.IWalletView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof WalletContract.IWalletView) {
            walletView = null;
        }
    }
}
