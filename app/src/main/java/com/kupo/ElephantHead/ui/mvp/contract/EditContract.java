package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;
import com.kupo.ElephantHead.ui.room.model.ReleaseModel;
import com.kupo.ElephantHead.ui.room.model.RoomIDetailsModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 保存商品信息
 */
public class EditContract {

    public interface IEditView extends IView {
        //保存商品信息成功
        void postEditInfoSuccess(ReleaseModel loginModel);

        //保存商品信息失败
        void postEditInfoFailed(int netCode, String msg);

        //保存商品详情信息成功
        void postEditDetailsInfoSuccess(ReleaseModel loginModel);

        //保存商品详情信息失败
        void postEditDetailsInfoFailed(int netCode, String msg);

        //获取展厅类目列表成功
        void getCategoryListSuccess(CategoryModel categoryModel);

        //获取展厅类目列表失败
        void getCategoryListFailed(int netCode, String msg);

        //获取展厅详情成功//二次编辑使用
        void getRoomItemDetailsSuccess(RoomIDetailsModel roomIDetailsModel);

        //获取展厅详情失败//二次编辑使用
        void getRoomItemDetailsFailed(int netCode, String msg);

    }

    public interface IEditPresenter extends IPresenter<IView> {

        void postEditInfo(String token, Map<String, Object> map, MultipartBody.Part part);

        void postEditDetailsInfo(String token, Map<String, Object> map, MultipartBody.Part part);

        void getCategoryList(String token);

        void getRoomItemDetails(String token, int itemId);
    }
}
