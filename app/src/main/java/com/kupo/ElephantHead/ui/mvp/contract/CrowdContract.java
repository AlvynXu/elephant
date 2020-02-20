package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.transaction.model.CrowdModel;
import com.kupo.ElephantHead.ui.transaction.model.MyCrowdModel;

import java.util.Map;

/**
 * 交易模块
 */
public class CrowdContract {

    public interface ICrowdView extends IView {
        //获取租赁信息成功
        void getRentSuccess(CrowdModel crowdModel);

        //获取租赁信息失败
        void getRentFailed(int netCode, String msg);

        //请求城市编码成功
        void getCityCodeSuccess(CityCode cityCode);

        //请求城市编码失败
        void getCityCodeFailed(int netCode, String msg);

        //获取我的租赁信息成功
        void getMyRentSuccess(MyCrowdModel myCrowdModel);

        //获取我的租赁信息失败
        void getMyRentFailed(int netCode, String msg);


    }

    public interface ICrowdPresenter extends IPresenter<IView> {

        void getRent(String token, Map<String, Object> map);

        void getCityCode(String token, Map<String, Object> map);

        void getMyRent(String token, Map<String, Object> map);

    }
}
