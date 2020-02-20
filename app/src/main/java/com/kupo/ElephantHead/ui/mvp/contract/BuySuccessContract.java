package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.BuySuccessModel;

import java.util.Map;

/**
 * @ClassName: BuySuccessContract
 * @Description: 支付成功提示界面
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class BuySuccessContract {

    public interface IBuySuccessView extends IView {

        //请求列表成功
        void getBuySuccessNetSuccess(BuySuccessModel buySuccessModel);

        //请求列表失败
        void getBuySuccessNetFailed(int netCode, String msg);

    }


    public interface IBuySuccessPresenter extends IPresenter<IView> {


        /**
         * 请求单个订单列表
         *
         * @param token
         */
        void getPaySuccessInfo(String token, String centerType, String footerType, Map<String, Object> map);

    }


}
