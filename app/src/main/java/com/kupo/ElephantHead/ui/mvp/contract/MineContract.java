package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;

/**
 * 获取我的展位和地主数量
 */
public class MineContract {

    public interface IMineView extends IView {
        //获取我的展位和地主数量成功
        void getMyBaseInfoSuccess(HomeInfoModel homeInfoModel);

        //获取我的展位和地主数量失败
        void getMyBaseInfoFailed(int netCode, String msg);


    }

    public interface IMinePresenter extends IPresenter<IView> {

        void getMyBaseInfo(String token);

    }
}
