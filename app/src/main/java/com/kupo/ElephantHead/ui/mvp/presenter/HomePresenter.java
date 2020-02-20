package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.AppUpdateModel;
import com.kupo.ElephantHead.ui.home.model.BannerModel;
import com.kupo.ElephantHead.ui.home.model.CurrentUserModel;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.mvp.contract.HomeContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @ClassName: LoginPresenter
 * @Description: 处理登录的业务逻辑
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class HomePresenter extends BasePresenter implements HomeContract.IHomePresenter {

    private HomeContract.IHomeView homeView;

    /**
     * 请求登陆接口
     */
    @Override
    public void getHomeInfoNet(String body) {
        LoginRequest.getHomeZwInfoNet(body, new CallBack<HomeInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (homeView != null) {
                    //  homeView.showLoading();
                }
            }

            @Override
            public void thread(HomeInfoModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (homeView != null) {
                    try {
                        homeView.getHomeNetSuccess(new Gson().fromJson(responseBody.string(), HomeInfoModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    homeView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (homeView != null) {
                    homeView.getHomeNetFailed(retCode, description);
                    homeView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });

    }

    @Override
    public void getHomeDzInfoNet(String body) {
        LoginRequest.getHomeDzInfoNet(body, new CallBack<HomeInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (homeView != null) {
                    //   homeView.showLoading();
                }
            }

            @Override
            public void thread(HomeInfoModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (homeView != null) {
                    try {
                        homeView.getHomeDzNetSuccess(new Gson().fromJson(responseBody.string(), HomeInfoModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    homeView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (homeView != null) {
                    homeView.getHomeDzNetFailed(retCode, description);
                    homeView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getHomeUserInfoNet(String body) {
        LoginRequest.getHomeUserInfoNet(body, new CallBack<HomeInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (homeView != null) {
                    //     homeView.showLoading();
                }
            }

            @Override
            public void thread(HomeInfoModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (homeView != null) {
                    try {
                        homeView.getHomeUserInfoNetSuccess(new Gson().fromJson(responseBody.string(), HomeInfoModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    homeView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (homeView != null) {
                    homeView.getHomeUserInfoNetFailed(retCode, description);
                    homeView.hideLoading();
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
                if (homeView != null) {
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
                if (homeView != null) {
                    try {
                        homeView.getCurrentUserInfoNetSuccess(new Gson().fromJson(responseBody.string(), CurrentUserModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    homeView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (homeView != null) {
                    homeView.getCurrentUserInfoNetFailed(retCode, description);
                    homeView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getAppUpdateInfo(String body, int type) {
        LoginRequest.getAppUpdateInfo(body, type, new CallBack<AppUpdateModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (homeView != null) {
                    //    homeView.showLoading();
                }
            }

            @Override
            public void thread(AppUpdateModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (homeView != null) {
                    try {
                        homeView.getAppUpdateInfoNetSuccess(new Gson().fromJson(responseBody.string(), AppUpdateModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    homeView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (homeView != null) {
                    homeView.getAppUpdateInfoNetFailed(retCode, description);
                    homeView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    /**
     * 获取首页下面项目头条列表
     *
     * @param token
     * @param map
     */
    @Override
    public void getHomeTopList(String token, Map<String, Object> map) {
        LoginRequest.getHomeTopListNet(token, map, new CallBack<HomeTopModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
            }

            @Override
            public void thread(HomeTopModel provincesModel) {
                super.thread(provincesModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (homeView != null) {
                    try {
                        homeView.getHomeTopListNetSuccess(new Gson().fromJson(responseBody.string(), HomeTopModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    homeView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (homeView != null) {
                    homeView.getHomeTopListNetFailed(retCode, description);
                    homeView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });

    }

    /**
     * 获取首页banner列表
     *
     * @param token
     * @param areaCode
     */
    @Override
    public void getHomeBannerListNet(String token, String areaCode) {
        LoginRequest.getHomeBannerListNet(token, areaCode, new CallBack<BannerModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
            }

            @Override
            public void thread(BannerModel bannerModel) {
                super.thread(bannerModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (homeView != null) {
                    try {
                        homeView.getHomeBannerListNetSuccess(new Gson().fromJson(responseBody.string(), BannerModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    homeView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (homeView != null) {
                    homeView.getHomeBannerListNetFailed(retCode, description);
                    homeView.hideLoading();
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
        if (view instanceof HomeContract.IHomeView) {
            homeView = (HomeContract.IHomeView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof HomeContract.IHomeView) {
            homeView = null;
        }
    }


}
