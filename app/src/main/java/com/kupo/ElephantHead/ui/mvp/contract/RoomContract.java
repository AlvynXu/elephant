package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.WalletModel;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;

import java.util.Map;

/**
 * 展厅
 */
public class RoomContract {

    public interface IRoomView extends IView {
        //获取展厅类目列表成功
        void getCategoryListSuccess(CategoryModel categoryModel);

        //获取展厅类目列表失败
        void getCategoryListFailed(int netCode, String msg);


    }

    public interface IRoomPresenter extends IPresenter<IView> {

        void getCategoryList(String token);

    }
}
