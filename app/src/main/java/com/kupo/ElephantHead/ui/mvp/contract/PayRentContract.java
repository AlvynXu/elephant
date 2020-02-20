package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.transaction.model.RentStreetModel;

import okhttp3.RequestBody;

/**
 * 获取可以出租的街道
 */
public class PayRentContract {

    public interface IPayRentView extends IView {

        //获取可以出租的街道成功
        void getCanRentStreetSuccess(RentStreetModel rentStreetModel);

        //获取可以出租的街道失败
        void getCanRentStreetFailed(int netCode, String msg);

        //租给ta成功
        void postRentToOtherSuccess(RentStreetModel rentStreetModel);

        //租给ta失败
        void postRentToOtherFailed(int netCode, String msg);


    }

    public interface IPayRentPresenter extends IPresenter<IView> {

        void getCanRentStreet(String token, RequestBody body);

        void postRentToOther(String token, RequestBody body);

    }
}
