package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.DzItemModel;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.home.model.ZwItemModel;
import com.kupo.ElephantHead.ui.mvp.contract.DzAndZwContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.QueryMap;

/**
 * @ClassName: LoginPresenter
 * @Description: 处理登录的业务逻辑
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class DzAndZwPresenter extends BasePresenter implements DzAndZwContract.IDzAndZwPresenter {

    private DzAndZwContract.IDzAndZwView dzAndZwView;

    /**
     * 请求登陆接口
     */
    @Override
    public void getDzListNet(String token, Map<String, Object> map) {
        LoginRequest.getDzListNet(token, map, new CallBack<DzItemModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (dzAndZwView != null) {
                    dzAndZwView.showLoading();
                }
            }

            @Override
            public void thread(DzItemModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (dzAndZwView != null) {
                    try {
                        dzAndZwView.getDzNetSuccess(new Gson().fromJson(responseBody.string(), DzItemModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dzAndZwView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (dzAndZwView != null) {
                    dzAndZwView.getDzNetFailed(retCode, description);
                    dzAndZwView.hideLoading();
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
                if (dzAndZwView != null) {
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
                if (dzAndZwView != null) {
                    try {
                        dzAndZwView.getCityCodeSuccess(new Gson().fromJson(responseBody.string(), CityCode.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dzAndZwView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (dzAndZwView != null) {
                    dzAndZwView.getCityCodeFailed(retCode, description);
                    dzAndZwView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getZwListNet(String token, Map<String, Object> map) {
        LoginRequest.getZwListNet(token, map, new CallBack<ZwItemModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (dzAndZwView != null) {
                    dzAndZwView.showLoading();
                }
            }

            @Override
            public void thread(ZwItemModel zwItemModel) {
                super.thread(zwItemModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (dzAndZwView != null) {
                    try {
                        dzAndZwView.getZwNetSuccess(new Gson().fromJson(responseBody.string(), ZwItemModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dzAndZwView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (dzAndZwView != null) {
                    dzAndZwView.getZwNetFailed(retCode, description);
                    dzAndZwView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    /**
     * 获取展位头部数据
     *
     * @param body
     */
    @Override
    public void getZwHeadInfo(String body) {
        LoginRequest.getHomeZwInfoNet(body, new CallBack<HomeInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (dzAndZwView != null) {
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
                if (dzAndZwView != null) {
                    try {
                        dzAndZwView.getZwHeadInfoSuccess(new Gson().fromJson(responseBody.string(), HomeInfoModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dzAndZwView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (dzAndZwView != null) {
                    dzAndZwView.getZwHeadInfoFailed(retCode, description);
                    dzAndZwView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    /**
     * 获取地主数据
     *
     * @param body
     */
    @Override
    public void getDzHeadInfo(String body) {
        LoginRequest.getHomeDzInfoNet(body, new CallBack<HomeInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (dzAndZwView != null) {
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
                if (dzAndZwView != null) {
                    try {
                        dzAndZwView.getDzHeadInfoSuccess(new Gson().fromJson(responseBody.string(), HomeInfoModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dzAndZwView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (dzAndZwView != null) {
                    dzAndZwView.getDzHeadInfoFailed(retCode, description);
                    dzAndZwView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getChooseZwUnLock(String token, int boothId) {
        LoginRequest.getChooseZwUnLock(token, boothId, new CallBack<BaseResult>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (dzAndZwView != null) {
                    //   homeView.showLoading();
                }
            }

            @Override
            public void thread(BaseResult loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (dzAndZwView != null) {
                    try {
                        dzAndZwView.getChooseZwUnLockSuccess(new Gson().fromJson(responseBody.string(), BaseResult.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dzAndZwView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (dzAndZwView != null) {
                    dzAndZwView.getChooseZwUnLockFailed(retCode, description);
                    dzAndZwView.hideLoading();
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
        if (view instanceof DzAndZwContract.IDzAndZwView) {
            dzAndZwView = (DzAndZwContract.IDzAndZwView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof DzAndZwContract.IDzAndZwView) {
            dzAndZwView = null;
        }
    }


}
