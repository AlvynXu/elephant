package com.kupo.ElephantHead.ui.mvp.presenter;


import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.TeamListModel;
import com.kupo.ElephantHead.ui.mvp.contract.ProfitAndTeamContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @ClassName: ProfitAndTeamPresenter
 * @Description: 处理团队模块的业务逻辑
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class ProfitAndTeamPresenter extends BasePresenter implements ProfitAndTeamContract.ITeamPresenter {

    private ProfitAndTeamContract.ITeamView teamView;


    @Override
    public void getHomeUserInfoNet(String body) {
        LoginRequest.getHomeUserInfoNet(body, new CallBack<HomeInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (teamView != null) {
                 //   teamView.showLoading();
                }
            }

            @Override
            public void thread(HomeInfoModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (teamView != null) {
                    try {
                        teamView.getHomeUserInfoNetSuccess(new Gson().fromJson(responseBody.string(), HomeInfoModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    teamView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (teamView != null) {
                    teamView.getHomeUserInfoNetFailed(retCode, description);
                    teamView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getUserTeamInfoNet(String token, String teamType) {
        LoginRequest.getUserTeamInfoNet(token, teamType, new CallBack<HomeInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (teamView != null) {
                  //  teamView.showLoading();
                }
            }

            @Override
            public void thread(HomeInfoModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (teamView != null) {
                    try {
                        teamView.getUserTeamInfoNetSuccess(new Gson().fromJson(responseBody.string(), HomeInfoModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    teamView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (teamView != null) {
                    teamView.getUserTeamInfoNetFailed(retCode, description);
                    teamView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getUserTeamListNet(String token, String teamType, Map<String, Object> map) {
        LoginRequest.getUserTeamListNet(token, teamType, map, new CallBack<TeamListModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (teamView != null) {
                    teamView.showLoading();
                }
            }

            @Override
            public void thread(TeamListModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (teamView != null) {
                    try {
                        teamView.getUserTeamListNetSuccess(new Gson().fromJson(responseBody.string(), TeamListModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    teamView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (teamView != null) {
                    teamView.getUserTeamListNetFailed(retCode, description);
                    teamView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getUserProfitListNet(String token, String teamType, Map<String, Object> map) {
        LoginRequest.getUserProfitListNet(token, teamType, map, new CallBack<TeamListModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (teamView != null) {
                    teamView.showLoading();
                }
            }

            @Override
            public void thread(TeamListModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (teamView != null) {
                    try {
                        teamView.getUserProfitListNetSuccess(new Gson().fromJson(responseBody.string(), TeamListModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    teamView.hideLoading();
                }
            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (teamView != null) {
                    teamView.getUserProfitListNetFailed(retCode, description);
                    teamView.hideLoading();
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
        if (view instanceof ProfitAndTeamContract.ITeamView) {
            teamView = (ProfitAndTeamContract.ITeamView) view;
        }

    }

    /**
     * 解除绑定
     *
     * @param view
     */
    @Override
    public void detachView(IView view) {
        if (view instanceof ProfitAndTeamContract.ITeamView) {
            teamView = null;
        }
    }

}
