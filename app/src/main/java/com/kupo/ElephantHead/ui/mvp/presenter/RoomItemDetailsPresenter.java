package com.kupo.ElephantHead.ui.mvp.presenter;

import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.mvp.contract.RoomItemContract;
import com.kupo.ElephantHead.ui.mvp.contract.RoomItemDetailsContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.room.model.LeavingMessageModel;
import com.kupo.ElephantHead.ui.room.model.RoomIDetailsModel;
import com.kupo.ElephantHead.ui.room.model.SaveBoothModel;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 展厅详情数据
 */
public class RoomItemDetailsPresenter extends BasePresenter implements RoomItemDetailsContract.IRoomItemDetailsPresenter {

    private RoomItemDetailsContract.IRoomItemDetailsView roomItemDetailsView;


    @Override
    public void getRoomItemDetails(String token, int itemId) {
        LoginRequest.getRoomDetail(token, itemId, new CallBack<RoomIDetailsModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.showLoading();
                }
            }

            @Override
            public void thread(RoomIDetailsModel roomIDetailsModel) {
                super.thread(roomIDetailsModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (roomItemDetailsView != null) {
                    try {
                        roomItemDetailsView.getRoomItemDetailsSuccess(new Gson().fromJson(responseBody.string(), RoomIDetailsModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    roomItemDetailsView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.getRoomItemDetailsFailed(retCode, description);
                    roomItemDetailsView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });

    }

    @Override
    public void removeItem(String token, RequestBody body) {
        LoginRequest.removeItem(token, body, new CallBack<SaveBoothModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.showLoading();
                }
            }

            @Override
            public void thread(SaveBoothModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (roomItemDetailsView != null) {
                    try {
                        roomItemDetailsView.removeItemSuccess(new Gson().fromJson(responseBody.string(), SaveBoothModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    roomItemDetailsView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.removeItemFailed(retCode, description);
                    roomItemDetailsView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void reportItem(String token, int itemId) {
        LoginRequest.getReportItem(token, itemId, new CallBack<BaseResult>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.showLoading();
                }
            }

            @Override
            public void thread(BaseResult loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (roomItemDetailsView != null) {
                    try {
                        roomItemDetailsView.reportItemSuccess(new Gson().fromJson(responseBody.string(), BaseResult.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    roomItemDetailsView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.reportItemFailed(retCode, description);
                    roomItemDetailsView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void collectionItem(String token, String footType, RequestBody body) {
        LoginRequest.collectionItem(token, footType, body, new CallBack<SaveBoothModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.showLoading();
                }
            }

            @Override
            public void thread(SaveBoothModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (roomItemDetailsView != null) {
                    try {
                        roomItemDetailsView.collectionItemSuccess(new Gson().fromJson(responseBody.string(), SaveBoothModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    roomItemDetailsView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.collectionItemFailed(retCode, description);
                    roomItemDetailsView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void removeProject(String token, RequestBody body) {
        LoginRequest.removeProject(token, body, new CallBack<SaveBoothModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.showLoading();
                }
            }

            @Override
            public void thread(SaveBoothModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (roomItemDetailsView != null) {
                    try {
                        roomItemDetailsView.removeProjectSuccess(new Gson().fromJson(responseBody.string(), SaveBoothModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    roomItemDetailsView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.removeProjectFailed(retCode, description);
                    roomItemDetailsView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void shareProject(String token, long itemId) {
        LoginRequest.getShareUrl(token, itemId, new CallBack<HomeInfoModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.showLoading();
                }
            }

            @Override
            public void thread(HomeInfoModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (roomItemDetailsView != null) {
                    try {
                        roomItemDetailsView.shareProjectSuccess(new Gson().fromJson(responseBody.string(), HomeInfoModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    roomItemDetailsView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.shareProjectFailed(retCode, description);
                    roomItemDetailsView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getLeaveMessagePage(String token, Map<String, Object> map) {
        LoginRequest.getLeaveMessagePage(token, map, new CallBack<LeavingMessageModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.showLoading();
                }
            }

            @Override
            public void thread(LeavingMessageModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (roomItemDetailsView != null) {
                    try {
                        roomItemDetailsView.getLeaveMessagePageSuccess(new Gson().fromJson(responseBody.string(), LeavingMessageModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    roomItemDetailsView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.getLeaveMessagePageFailed(retCode, description);
                    roomItemDetailsView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void saveLeaveMessage(String token, RequestBody body) {
        LoginRequest.saveLeaveMessage(token, body, new CallBack<LeavingMessageModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.showLoading();
                }
            }

            @Override
            public void thread(LeavingMessageModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (roomItemDetailsView != null) {
                    try {
                        roomItemDetailsView.saveLeaveMessageSuccess(new Gson().fromJson(responseBody.string(), LeavingMessageModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    roomItemDetailsView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (roomItemDetailsView != null) {
                    roomItemDetailsView.saveLeaveMessageFailed(retCode, description);
                    roomItemDetailsView.hideLoading();
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
        if (view instanceof RoomItemDetailsContract.IRoomItemDetailsView) {
            roomItemDetailsView = (RoomItemDetailsContract.IRoomItemDetailsView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof RoomItemDetailsContract.IRoomItemDetailsView) {
            roomItemDetailsView = null;
        }
    }
}
