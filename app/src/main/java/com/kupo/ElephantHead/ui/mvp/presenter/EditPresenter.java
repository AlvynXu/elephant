package com.kupo.ElephantHead.ui.mvp.presenter;

import com.google.gson.Gson;
import com.kupo.ElephantHead.base.basepresenter.BasePresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.net.request.LoginRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.mvp.contract.EditContract;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;
import com.kupo.ElephantHead.ui.room.model.ReleaseModel;
import com.kupo.ElephantHead.ui.room.model.RoomIDetailsModel;
import com.kupo.ElephantHead.utils.JsonUtil;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 保存商品信息
 */
public class EditPresenter extends BasePresenter implements EditContract.IEditPresenter {

    private EditContract.IEditView editView;


    @Override
    public void postEditInfo(String token, Map<String, Object> map, MultipartBody.Part part) {
        LoginRequest.saveShopInfo(token, map, part, new CallBack<ReleaseModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (editView != null) {
                    editView.showLoading();
                }
            }

            @Override
            public void thread(ReleaseModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (editView != null) {
                    try {
                        editView.postEditInfoSuccess(new Gson().fromJson(responseBody.string(), ReleaseModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    editView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (editView != null) {
                    editView.postEditInfoFailed(retCode, description);
                    editView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });

    }

    @Override
    public void postEditDetailsInfo(String token, Map<String, Object> map, MultipartBody.Part part) {
        LoginRequest.saveInfoDetail(token, map, part, new CallBack<ReleaseModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (editView != null) {
                 //   editView.showLoading();
                }
            }

            @Override
            public void thread(ReleaseModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (editView != null) {
                    try {
                        editView.postEditDetailsInfoSuccess(new Gson().fromJson(responseBody.string(), ReleaseModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
              //      editView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (editView != null) {
                    editView.postEditDetailsInfoFailed(retCode, description);
                  //  editView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });
    }

    @Override
    public void getCategoryList(String token) {
        LoginRequest.getCategoryList(token, new CallBack<CategoryModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (editView != null) {
                    editView.showLoading();
                }
            }

            @Override
            public void thread(CategoryModel loginModel) {
                super.thread(loginModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (editView != null) {
                    try {
                        editView.getCategoryListSuccess(new Gson().fromJson(responseBody.string(), CategoryModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    editView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (editView != null) {
                    editView.getCategoryListFailed(retCode, description);
                    editView.hideLoading();
                }
            }

            @Override
            public void end() {
                super.end();
            }
        });

    }

    @Override
    public void getRoomItemDetails(String token, int itemId) {
        LoginRequest.getRoomDetail(token, itemId, new CallBack<RoomIDetailsModel>() {
            @Override
            public void prepare(String url) {
                super.prepare(url);
                if (editView != null) {
                    editView.showLoading();
                }
            }

            @Override
            public void thread(RoomIDetailsModel roomIDetailsModel) {
                super.thread(roomIDetailsModel);
            }

            @Override
            public void success(ResponseBody responseBody) {
                super.success(responseBody);
                if (editView != null) {
                    try {
                        editView.getRoomItemDetailsSuccess(new Gson().fromJson(responseBody.string(), RoomIDetailsModel.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    editView.hideLoading();
                }

            }

            @Override
            public void failure(int retCode, String description) {
                super.failure(retCode, description);
                if (editView != null) {
                    editView.getRoomItemDetailsFailed(retCode, description);
                    editView.hideLoading();
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
        if (view instanceof EditContract.IEditView) {
            editView = (EditContract.IEditView) view;
        }
    }

    @Override
    public void detachView(IView view) {
        if (view instanceof EditContract.IEditView) {
            editView = null;
        }
    }
}
