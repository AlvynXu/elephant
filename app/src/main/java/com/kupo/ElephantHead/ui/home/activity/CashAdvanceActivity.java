package com.kupo.ElephantHead.ui.home.activity;


import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
import com.kupo.ElephantHead.greendao.UserInfo;
import com.kupo.ElephantHead.greendao.UserInfoOperateDao;
import com.kupo.ElephantHead.ui.home.model.CurrentUserModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.home.model.WalletModel;
import com.kupo.ElephantHead.ui.mvp.contract.WalletContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.presenter.WalletPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 提现界面
 */
@Route(path = AppConfig.ACTIVITY_URL_HOME_CASHADVANCE)
public class CashAdvanceActivity extends BaseActivity implements WalletContract.IWalletView {


    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.cash_advance_tv)
    TextView cashAdvanceTv;
    @BindView(R.id.cash_advance_money)
    EditText cashAdvanceMoney;
    @BindView(R.id.cash_advance_desc_tv)
    TextView cashAdvanceDescTv;
    @BindView(R.id.cash_advance_fee_tv)
    TextView cashAdvanceFeeTv;
    @BindView(R.id.cash_advance_info_tv)
    TextView cashAdvanceInfoTv;
    @BindView(R.id.cash_advance_money_tv)
    TextView cashAdvanceMoneyTv;
    @BindView(R.id.head_ll)
    LinearLayout headLl;
    @BindView(R.id.pay_success_desc_tv)
    TextView paySuccessDescTv;
    @BindView(R.id.buy_success_ok)
    TextView buySuccessOk;
    @BindView(R.id.bottom_ll)
    LinearLayout bottomLl;
    @BindView(R.id.cash_advance_all_tv)
    TextView cashAdvanceAllTv;
    private WalletContract.IWalletPresenter mWalletPresenter;
    private String minPrice, maxPrice;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        mWalletPresenter = new WalletPresenter();
        mWalletPresenter.attachView(this);
        titleTitleTxt.setText("提现");
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.GONE);
        cashAdvanceMoneyTv.setText("可提现金额：￥" + CommonUtils.getUserInfo().getBalance() + "");
        cashAdvanceAllTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //底部横线
        mWalletPresenter.getRechargeInfo(CommonUtils.getUserInfo().getToken());
        titleReturnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cashAdvanceTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(cashAdvanceMoney.getText().toString().trim())) {
                    int money = Integer.parseInt(cashAdvanceMoney.getText().toString().trim());
                    if (money < 5000) {
                        mWalletPresenter.postCashAdvanceData(CommonUtils.getUserInfo().getToken(), "transfer", money);
                    } else {
                        ToastUtils.showShort("提现金额" + minPrice + "到" + maxPrice + "之间");
                    }
                } else {
                    ToastUtils.showShort("提现金额不能为空");
                }

            }
        });
        buySuccessOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MAIN_TEAM)
                        .withInt("type", 0)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                finish();
            }
        });
        cashAdvanceAllTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cashAdvanceMoney.setText(CommonUtils.getUserInfo().getBalance() + "");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cash_advance;
    }

    @Override
    public void postWalletSuccess(PayInfoModel loginModel) {
    }

    @Override
    public void postWalletFailed(int netCode, String msg) {

    }

    @Override
    public void postCashAdvanceSuccess(WalletModel walletModel) {
        if (walletModel.getCode() == 0) {
            headLl.setVisibility(View.GONE);
            bottomLl.setVisibility(View.VISIBLE);
            paySuccessDescTv.setText("恭喜您，成功提现了" + cashAdvanceMoney.getText().toString().trim() + "元");
        } else if (walletModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
        } else {
            ToastUtils.showLong(walletModel.getMessage());
        }
    }

    @Override
    public void postCashAdvanceFailed(int netCode, String msg) {
        ToastUtils.showLong("提现失败");
    }

    @Override
    public void getRechargeInfoSuccess(HomeInfoModel homeInfoModel) {
        if (homeInfoModel.getCode() == 0) {
            minPrice = CommonUtils.formatToNumber(homeInfoModel.getData().getMin()) + "";
            maxPrice = CommonUtils.formatToNumber(homeInfoModel.getData().getMax());
            cashAdvanceDescTv.setText("每笔提现须大于等于" + CommonUtils.formatToNumber(homeInfoModel.getData().getMin()) + "元");
            cashAdvanceInfoTv.setText("单笔提现封顶" + CommonUtils.formatToNumber(homeInfoModel.getData().getMax()) + "元");
            cashAdvanceFeeTv.setText("提现手续费：" + CommonUtils.formatToNumber((homeInfoModel.getData().getRate().multiply(new BigDecimal(100)))) + "%");
            UserInfo userInfo = CommonUtils.getUserInfo();
            userInfo.setBalance(homeInfoModel.getData().getBalance().doubleValue());
            UserInfoOperateDao.deleteDataBean();
            UserInfoOperateDao.insertUserInfo(userInfo);
        } else if (homeInfoModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
        }
    }


    @Override
    public void getRechargeInfoFailed(int netCode, String msg) {

    }

    @Override
    public void getCurrentUserInfoNetSuccess(CurrentUserModel currentUserModel) {

    }

    @Override
    public void getCurrentUserInfoNetFailed(int netCode, String msg) {

    }

}
