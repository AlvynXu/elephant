package com.kupo.ElephantHead.ui.home.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.greendao.UserInfo;
import com.kupo.ElephantHead.greendao.UserInfoOperateDao;
import com.kupo.ElephantHead.ui.home.model.CurrentUserModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.home.model.ProfitItemModel;
import com.kupo.ElephantHead.ui.home.model.WalletModel;
import com.kupo.ElephantHead.ui.mvp.contract.WalletContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;
import com.kupo.ElephantHead.ui.mvp.model.RefreshModel;
import com.kupo.ElephantHead.ui.mvp.presenter.WalletPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收支明细/1.0版本暂时不用
 */
@Route(path = AppConfig.ACTIVITY_URL_HOME_PROFIT)
public class WalletActivity extends BaseActivity implements WalletContract.IWalletView {
    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_return_img)
    ImageView titleReturnImg;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.wallet_balance_tv)
    TextView walletBalanceTv;
    private WalletContract.IWalletPresenter mWalletPresenter;


    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        mWalletPresenter = new WalletPresenter();
        mWalletPresenter.attachView(this);
        titleTitleTxt.setText("我的余额");
        titleRightTxt.setText("收支明细");
        titleRightImg.setVisibility(View.GONE);
        mWalletPresenter.getCurrentUserInfo(CommonUtils.getUserInfo().getToken());
    }

    @OnClick({R.id.title_return_linear, R.id.wallet_cash_tv, R.id.wallet_recharge_tv, R.id.title_right_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_linear:
                EventBus.getDefault().postSticky(new BaseResult("wallet刷新"));
                finish();
                break;
            case R.id.wallet_cash_tv:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_HOME_CASHADVANCE)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
            case R.id.wallet_recharge_tv:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_HOME_RECHARGE)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
            case R.id.title_right_txt:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MAIN_TEAM)
                            .withInt("type", 0)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet;
    }


    @Override
    public void postWalletSuccess(PayInfoModel payInfoModel) {

    }

    @Override
    public void postWalletFailed(int netCode, String msg) {

    }

    @Override
    public void postCashAdvanceSuccess(WalletModel payInfoModel) {

    }

    @Override
    public void postCashAdvanceFailed(int netCode, String msg) {

    }

    @Override
    public void getRechargeInfoSuccess(HomeInfoModel homeInfoModel) {

    }

    @Override
    public void getRechargeInfoFailed(int netCode, String msg) {

    }

    @Override
    public void getCurrentUserInfoNetSuccess(CurrentUserModel currentUserModel) {
        UserInfo userInfo = CommonUtils.getUserInfo();
        if (currentUserModel.getCode() == 0) {
            walletBalanceTv.setText(currentUserModel.getData().getBalance() + "");
            userInfo.setBalance(currentUserModel.getData().getBalance());
            UserInfoOperateDao.deleteDataBean();
            UserInfoOperateDao.insertUserInfo(userInfo);
        } else if (currentUserModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
            return;
        }
    }

    @Override
    public void getCurrentUserInfoNetFailed(int netCode, String msg) {

    }

    /**
     * 通知刷新
     *
     * @param refreshModel
     */
    @Subscribe(sticky = true)
    public void receiveProfitRefreshEventBus(RefreshModel refreshModel) {
        if ("profit刷新".equals(refreshModel.getIsRefresh())) {
            mWalletPresenter.getCurrentUserInfo(CommonUtils.getUserInfo().getToken());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
