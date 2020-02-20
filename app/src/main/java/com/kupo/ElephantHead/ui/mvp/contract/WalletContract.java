package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.CurrentUserModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.home.model.WalletModel;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 微信提现
 */
public class WalletContract {

    public interface IWalletView extends IView {
        //充值成功
        void postWalletSuccess(PayInfoModel payInfoModel);

        //充值失败
        void postWalletFailed(int netCode, String msg);

        //提现成功
        void postCashAdvanceSuccess(WalletModel payInfoModel);

        //提现失败
        void postCashAdvanceFailed(int netCode, String msg);

        //获取提现设置最大值最小值及费率成功
        void getRechargeInfoSuccess(HomeInfoModel homeInfoModel);

        //获取提现设置最大值最小值及费率失败
        void getRechargeInfoFailed(int netCode, String msg);

        //请求当前登录用户信息成功
        void getCurrentUserInfoNetSuccess(CurrentUserModel currentUserModel);

        //请求当前登录用户信息失败
        void getCurrentUserInfoNetFailed(int netCode, String msg);

    }

    public interface IWalletPresenter extends IPresenter<IView> {

        void postWalletData(String token, String footType, BigDecimal amount);

        void postCashAdvanceData(String token, String footType, int amount);

        void getRechargeInfo(String token);

        /**
         * 当前登录用户信息
         *
         * @param body
         */
        void getCurrentUserInfo(String body);

    }
}
