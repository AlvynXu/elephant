package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.BuySuccessModel;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @ClassName: LoginContract
 * @Description: 登录
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class PayOrderContract {

    public interface IQdzItemOrderInfoView extends IView {

        //请求列表成功
        void getQdzItemOrderInfoNetSuccess(PayInfoModel payInfoModel);

        //请求列表失败
        void getQdzItemOrderInfoNetFailed(int netCode, String msg);

        //请求支付成功后数据成功
        void getBuySuccessNetSuccess(BuySuccessModel buySuccessModel);

        //请求支付成功后数据失败
        void getBuySuccessNetFailed(int netCode, String msg);

        //取消支付订单成功
        void getCancelPaySuccess(PayInfoModel payInfoModel);

        //取消支付订单失败
        void getCancelPayFailed(int netCode, String msg);

    }


    public interface IQdzItemOrderInfoPresenter extends IPresenter<IView> {


        /**
         * 请求单个订单列表
         *
         * @param token
         */
        void getQdzItemOrderInfoNet(String token, String type, String payType, Map<String, Object> map);

        /**
         * 请求单个订单列表
         *
         * @param token
         */
        void getPaySuccessInfo(String token, String centerType, String footerType, Map<String, Object> map);


        /**
         * 取消订单列表
         *
         * @param token
         */
        void getCancelPayInfo(String token, Map<String, Object> map);

    }


}
