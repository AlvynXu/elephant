package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;
import com.kupo.ElephantHead.ui.room.model.RoomIDetailsModel;

import java.util.Map;

/**
 * 我的广告
 */
public class MyProjectContract {

    public interface IMyProjectView extends IView {
        //获取我的广告列表成功
        void getMyProjectListSuccess(HomeTopModel homeInfoModel);

        //获取我的广告列表失败
        void getMyProjectListFailed(int netCode, String msg);


    }

    public interface IMyProjectPresenter extends IPresenter<IView> {

        void getMyProjectList(String token, Map<String, Object> map);

    }
}
