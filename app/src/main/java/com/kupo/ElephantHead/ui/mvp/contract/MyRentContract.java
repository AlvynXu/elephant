package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * 我的求租
 */
public class MyRentContract {

    public interface IMyRentView extends IView {

        //根据城市code获取区县成功
        void getLocationSuccess(ProvincesModel dzItemModel);

        //根据城市code区县失败
        void getLocationFailed(int netCode, String msg);

        //求组成功
        void postSeekRentSuccess(PayInfoModel payInfoModel);

        //求组失败
        void postSeekRentFailed(int netCode, String msg);


    }

    public interface IMyRentPresenter extends IPresenter<IView> {
        /**
         * 获取区域
         *
         * @param token
         * @param map
         */
        void getLocationNet(String token, String type, Map<String, Object> map);

        void postSeekRentNt(String token, RequestBody body);

    }
}
