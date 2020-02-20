package com.kupo.ElephantHead.ui.home.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.ui.home.model.CurrentUserModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.home.model.WalletModel;
import com.kupo.ElephantHead.ui.mvp.contract.WalletContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.WxNofit;
import com.kupo.ElephantHead.ui.mvp.presenter.WalletPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 充值界面
 */
@Route(path = AppConfig.ACTIVITY_URL_HOME_RECHARGE)
public class RechargeActivity extends BaseActivity implements WalletContract.IWalletView {

    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.recharge_money)
    EditText rechargeMoney;
    @BindView(R.id.recharge_tv)
    TextView rechargeTv;
    @BindView(R.id.head_ll)
    LinearLayout headLl;
    @BindView(R.id.pay_success_desc_tv)
    TextView paySuccessDescTv;
    @BindView(R.id.buy_success_ok)
    TextView buySuccessOk;
    @BindView(R.id.bottom_ll)
    LinearLayout bottomLl;
    private WalletContract.IWalletPresenter mWalletPresenter;
    private IWXAPI iwxapi;
    private BigDecimal money;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        mWalletPresenter = new WalletPresenter();
        mWalletPresenter.attachView(this);
        titleTitleTxt.setText("充值");
        titleRightImg.setVisibility(View.GONE);
        titleRightTxt.setVisibility(View.GONE);
        rechargeMoney.addTextChangedListener(textWatcher);
        titleReturnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rechargeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(rechargeMoney.getText().toString().trim())) {
                    money = new BigDecimal(rechargeMoney.getText().toString().trim());
                    if (money.compareTo(new BigDecimal(0)) == 1) {
                        mWalletPresenter.postWalletData(CommonUtils.getUserInfo().getToken(), "buy", money);
                    } else {
                        ToastUtils.showShort("充值金额不能小于0");
                    }
                } else {
                    ToastUtils.showShort("充值金额不能为空");
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
    }

    private TextWatcher textWatcher = new TextWatcher() {

        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            temp = charSequence;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String editStr = editable.toString().trim();
            if (CommonUtils.isNumeric(editStr)) {
                editStart = rechargeMoney.getSelectionStart();
                editEnd = rechargeMoney.getSelectionEnd();
                if (temp.length() > 5) {//输入字数限制
                    editable.delete(editStart - 1, editEnd);//删除限制外的内容
                    int tempSelection = editStart;
                    rechargeMoney.setText(editable);//显示限制内的内容
                    rechargeMoney.setSelection(tempSelection);//光标焦点设置在行末
                }
            } else {
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot < 0) {
                    return;
                }
                if (editStr.length() - posDot - 1 > 2) {
                    editable.delete(posDot + 3, posDot + 4);
                } else {
                    money = new BigDecimal(rechargeMoney.getText().toString().trim());
                }
            }
        }
    };

    private void startWx(PayInfoModel.DataBean wxModel) {
        iwxapi = WXAPIFactory.createWXAPI(this, null);
        iwxapi.registerApp("wx7631f5e5be502d21");
        new Thread(new Runnable() {
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
                //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = wxModel.getAppid();
                request.partnerId = wxModel.getPartnerid();
                request.prepayId = wxModel.getPrepayid();
                request.packageValue = "Sign=WXPay";
                request.nonceStr = wxModel.getNoncestr();
                request.timeStamp = wxModel.getTimestamp();
                request.sign = wxModel.getSign();
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        }).start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recharge;
    }


    @Override
    public void postWalletSuccess(PayInfoModel payInfoModel) {
        if (payInfoModel.getCode() == 0) {
            startWx(payInfoModel.getData());
        } else if (payInfoModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
        } else {
            ToastUtils.showLong(payInfoModel.getMessage());
        }
    }

    /**
     * 订单Id
     *
     * @param baseResult
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveBuyWxEventBus(WxNofit baseResult) {
        EventBus.getDefault().removeStickyEvent(baseResult);
        if (baseResult.getCode() == 0) {
            headLl.setVisibility(View.GONE);
            bottomLl.setVisibility(View.VISIBLE);
            paySuccessDescTv.setText("恭喜您，成功充值了" + rechargeMoney.getText().toString().trim() + "元");
        } else if (baseResult.getCode() == -1) {
            ToastUtils.showShort("支付失败，请联系客服！");
        } else {
            ToastUtils.showShort("支付取消！");
        }
    }

    @Override
    public void postWalletFailed(int netCode, String msg) {
        ToastUtils.showLong("充值失败：" + msg);
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

    }

    @Override
    public void getCurrentUserInfoNetFailed(int netCode, String msg) {

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
