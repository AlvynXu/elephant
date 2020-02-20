package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;

import java.util.Map;

/**
 * 获取我的收藏数量
 */
public class CollectContract {

    public interface ICollectView extends IView {

        //获取我的收藏的列表成功
        void getCollectListSuccess(HomeTopModel payInfoModel);

        //获取我的收藏的列表失败
        void getCollectListFailed(int netCode, String msg);


    }

    public interface ICollectPresenter extends IPresenter<IView> {

        void getCollectListInfo(String token, Map<String, Object> map);

    }
}
