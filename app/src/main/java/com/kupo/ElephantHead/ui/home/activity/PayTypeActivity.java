package com.kupo.ElephantHead.ui.home.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

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
import com.kupo.ElephantHead.ui.home.fragment.BuySuccessFragment;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mvp.contract.PayOrderContract;
import com.kupo.ElephantHead.ui.mvp.model.BuySuccessModel;
import com.kupo.ElephantHead.ui.mvp.model.WxNofit;
import com.kupo.ElephantHead.ui.mvp.presenter.PayOrderPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.utils.PayResult;
import com.kupo.ElephantHead.utils.PayZhifubaoUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @ClassName: PayTypeActivity
 * @Description: 支付页面
 * @Author:
 * @CreateDate: 2019/8/14 10:05
 * @Version: 1.0
 */
@Route(path = AppConfig.ACTIVITY_URL_HOME_PAYTYPE)
public class PayTypeActivity extends Activity implements PayOrderContract.IQdzItemOrderInfoView {


    @BindView(R.id.home_temp_title)
    TextView homeTempTitle;
    @BindView(R.id.user_isRead)
    ImageView userIsRead;
    @BindView(R.id.user_explain)
    TextView userExplain;
    @BindView(R.id.user_isRead_linear)
    LinearLayout userIsReadLinear;
    @BindView(R.id.sure_pay)
    TextView surePay;
    @BindView(R.id.linear)
    RelativeLayout linear;
    PayZhifubaoUtils payZhifubaoUtils = null;
    @BindView(R.id.pay_close)
    LinearLayout payClose;
    @BindView(R.id.pay_price)
    TextView payPrice;
    @BindView(R.id.popup_pay_wx_isFlag)
    ImageView popupPayWxIsFlag;
    @BindView(R.id.popup_item_zfb_isFlag)
    ImageView popupItemZfbIsFlag;
    @BindView(R.id.pay_bottom_ll)
    LinearLayout payBottomLl;
    @BindView(R.id.pay_success)
    LinearLayout paySuccess;
    @BindView(R.id.popup_pay_wxicon)
    ImageView popupPayWxicon;
    @BindView(R.id.popup_pay_wx_name)
    TextView popupPayWxName;
    @BindView(R.id.popup_pay_wx_ll)
    LinearLayout popupPayWxLl;
    @BindView(R.id.popup_item_zfb_icon)
    ImageView popupItemZfbIcon;
    @BindView(R.id.popup_item_zfb_name)
    TextView popupItemZfbName;
    @BindView(R.id.popup_item_zfb_ll)
    LinearLayout popupItemZfbLl;
    @BindView(R.id.pay_success_desc_tv)
    TextView paySuccessDescTv;
    @BindView(R.id.pay_success_name_tv)
    TextView paySuccessNameTv;
    @BindView(R.id.pay_success_location_tv)
    TextView paySuccessLocationTv;
    @BindView(R.id.pay_success_time_tv)
    TextView paySuccessTimeTv;
    @BindView(R.id.foot_line)
    TextView footLine;
    @BindView(R.id.buy_success_ok)
    TextView buySuccessOk;
    @BindView(R.id.pay_location_type)
    TextView payLocationType;
    @BindView(R.id.pay_location_name)
    TextView payLocationName;
    @BindView(R.id.pay_location_street_name)
    TextView payLocationStreetName;
    private PayOrderPresenter payOrderPresenter = null;
    private String orderInfo = null;
    private int payType = 0;
    private boolean isFlag = true;
    String type = null;
    private IWXAPI iwxapi;
    int orderId;
    Map<String, Object> map = null;
    PayInfoModel.DataBean wxModel = new PayInfoModel.DataBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_type);
        ButterKnife.bind(this);
        payOrderPresenter = new PayOrderPresenter();
        payOrderPresenter.attachView(this);
        initData();
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {//支付成功，请求接口确定成功
                        //进行成功的操作（一般请求自己的服务器确定支付成功）
//                        ARouter.getInstance()
//                                .build(AppConfig.ACTIVITY_URL_MAIN_BUY_SUCCESS)
//                                //进入动画
//                                .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
//                                .navigation();
                        finish();
                    } else if (TextUtils.equals(resultStatus, "6001")) {//支付取消
                        //进行取消操作
                        ToastUtils.showShort("支付被取消");
                    } else { //支付失败
                        //进行失败操作
                        ToastUtils.showShort("支付失败");
                    }
                    break;
            }
        }
    };

    @OnClick({R.id.pay_close, R.id.sure_pay, R.id.popup_pay_wx_ll, R.id.popup_item_zfb_ll, R.id.user_isRead_linear, R.id.user_explain, R.id.buy_success_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pay_close:
                finish();
                break;
            case R.id.user_isRead_linear:
                //默认图标
                if (!isFlag) {
                    //点击时显示选中图标
                    userIsRead.setImageDrawable(getResources().getDrawable(R.drawable.popup_read_select));
                    isFlag = true;
                } else {
                    //再次点击时显示默认图标
                    userIsRead.setImageDrawable(getResources().getDrawable(R.drawable.popup_read_default));
                    isFlag = false;
                }
                break;
            case R.id.sure_pay:
//                if (!isFlag) {
//                    ToastUtils.showShort("请阅读相关协议");
//                    return;
//                }
                map = new HashMap<>();
                if (orderId > 0) {
                    if ("bugStreet".equals(type)) {
                        map.put("streetId", orderId);
                    } else if ("buyBooth".equals(type)) {
                        map.put("boothId", orderId);
                    }
                }
                if (payType == 0) {
                    payOrderPresenter.getQdzItemOrderInfoNet(CommonUtils.getUserInfo().getToken(), type, payType == 0 ? "wxPay" : "aliPay", map);
                    return;
                } else if (payType == 1) {
                    ToastUtils.showShort("目前不支持支付宝支付");
                    //   payOrderPresenter.getQdzItemOrderInfoNet(CommonUtils.getUserInfo().getToken(), type, payType == 0 ? "wxPay" : "aliPay", map);
                } else {
                    ToastUtils.showShort("请选择支付方式");
                }
                break;
            case R.id.popup_pay_wx_ll:
                if (isFlag) {
                    //点击时显示选中图标
                    popupPayWxIsFlag.setImageDrawable(getResources().getDrawable(R.drawable.popup_item_default));
                    isFlag = false;
                    payType = 8;
                } else {
                    payType = 0;
                    popupPayWxIsFlag.setImageDrawable(getResources().getDrawable(R.drawable.popup_item_select));
                    //再次点击时显示默认图标
                    isFlag = true;
                }
                break;
            case R.id.popup_item_zfb_ll:
                payType = 1;
                resetImage(0);
                break;
            case R.id.user_explain:
                ToastUtils.showShort("点击了用户协议");
                break;
            case R.id.buy_success_ok:
                finish();
                break;
        }
    }

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

    public void resetImage(int index) {
        if (index == 0) {
            popupPayWxIsFlag.setImageDrawable(getResources().getDrawable(R.drawable.popup_item_default));
            //    popupItemZfbIsFlag.setImageDrawable(getResources().getDrawable(R.drawable.popup_item_select));
        } else {
            popupPayWxIsFlag.setImageDrawable(getResources().getDrawable(R.drawable.popup_item_select));
            //   popupItemZfbIsFlag.setImageDrawable(getResources().getDrawable(R.drawable.popup_item_default));
        }
    }

    private void initData() {
        Double price = getIntent().getDoubleExtra("price", 0);
        orderId = getIntent().getIntExtra("orderId", 0);
        type = getIntent().getStringExtra("type");
        payPrice.setText(price + "");
        if ("bugStreet".equals(type)) {
            map = new HashMap<>();
            map.put("id", orderId);
            map.put("type", 2);
            payOrderPresenter.getPaySuccessInfo(CommonUtils.getUserInfo().getToken(), "street", "getDetail", map);
        } else if ("buyBooth".equals(type)) {
            map = new HashMap<>();
            map.put("id", orderId);
            map.put("type", 1);
            payOrderPresenter.getPaySuccessInfo(CommonUtils.getUserInfo().getToken(), "street", "getDetail", map);
        } else if ("buyVip".equals(type)) {
            payLocationType.setText("正在购买会员");
            payLocationName.setVisibility(View.GONE);
            payLocationStreetName.setText("电线竿会员");
        }
    }


    @Override
    public void getQdzItemOrderInfoNetSuccess(PayInfoModel payInfoModel) {
        if (payInfoModel.getCode() != 0) {
            ToastUtils.showShort(payInfoModel.getMessage());
        } else {
            if (payType == 1) {
                if ("bugStreet".equals(type)) {
                    orderInfo = payInfoModel.getData().getData();
                } else if ("buyBooth".equals(type)) {
                    orderInfo = payInfoModel.getData().getOrderInfo();
                } else {
                    orderInfo = payInfoModel.getData().getOrderInfo();
                }
                try {
                    if (payZhifubaoUtils == null) {
                        payZhifubaoUtils = PayZhifubaoUtils.getInstance(PayTypeActivity.this, mHandler);
                    }
                    payZhifubaoUtils.payZhibaoMoney(orderInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort("支付失败");
                }
            } else {
                wxModel = payInfoModel.getData();
                startWx(wxModel);
            }

        }
    }


    /**
     * 订单Id
     *
     * @param baseResult
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveWxEventBus(WxNofit baseResult) {
        EventBus.getDefault().removeStickyEvent(baseResult);
        if (baseResult.getCode() == 0) {
            if ("bugStreet".equals(type)) {
                paySuccessDescTv.setText("恭喜您，成功购买了一个地主");
                payClose.setVisibility(View.GONE);
                linear.setBackgroundColor(getResources().getColor(R.color.white));
                payBottomLl.setVisibility(View.GONE);
                paySuccess.setVisibility(View.VISIBLE);
            } else if ("buyBooth".equals(type)) {
                paySuccessDescTv.setText("恭喜您，成功购买了一个展位");
                payClose.setVisibility(View.GONE);
                linear.setBackgroundColor(getResources().getColor(R.color.white));
                payBottomLl.setVisibility(View.GONE);
                paySuccess.setVisibility(View.VISIBLE);
            } else if ("buyVip".equals(type)) {
                UserInfo userInfo = CommonUtils.getUserInfo();
                userInfo.setIsVip(true);
                UserInfoOperateDao.deleteDataBean();
                UserInfoOperateDao.insertUserInfo(userInfo);
                paySuccessDescTv.setText("恭喜您，荣升为电线竿会员");
                paySuccessTimeTv.setVisibility(View.GONE);
                footLine.setVisibility(View.GONE);
                payBottomLl.setVisibility(View.GONE);
                paySuccess.setVisibility(View.VISIBLE);
            }
        } else if (baseResult.getCode() == -1) {
            int index = 0;
            if ("bugStreet".equals(type)) {
                index = 2;
            } else if ("buyBooth".equals(type)) {
                index = 1;
            } else if ("buyVip".equals(type)) {
                index = 0;
            }
            map = new HashMap<>();
            map.put("id", orderId);
            map.put("type", index);
            payOrderPresenter.getCancelPayInfo(CommonUtils.getUserInfo().getToken(), map);
            //    ToastUtils.showShort("支付失败，请联系客服！");
        } else {
            int index = 0;
            if ("bugStreet".equals(type)) {
                index = 2;
            } else if ("buyBooth".equals(type)) {
                index = 1;
            } else if ("buyVip".equals(type)) {
                index = 0;
            }
            map = new HashMap<>();
            map.put("id", orderId);
            map.put("type", index);
            payOrderPresenter.getCancelPayInfo(CommonUtils.getUserInfo().getToken(), map);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void getQdzItemOrderInfoNetFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void getBuySuccessNetSuccess(BuySuccessModel buySuccessModel) {
        if ("bugStreet".equals(type)) {
            payLocationType.setText("正在购买地主");
            payLocationName.setText(buySuccessModel.getData().getProvince() + " " + buySuccessModel.getData().getCity() + " " + buySuccessModel.getData().getDistrict());
            payLocationStreetName.setText(buySuccessModel.getData().getStreet());
        } else if ("buyBooth".equals(type)) {
            payLocationType.setText("正在购买展位");
            payLocationName.setText(buySuccessModel.getData().getProvince() + " " + buySuccessModel.getData().getCity() + " " + buySuccessModel.getData().getDistrict() + " " + buySuccessModel.getData().getStreet());
            payLocationStreetName.setText("展位编码：" + buySuccessModel.getData().getCode());
        } else {
            payLocationType.setText("正在购买会员");
        }
    }

    @Override
    public void getBuySuccessNetFailed(int netCode, String msg) {

    }

    @Override
    public void getCancelPaySuccess(PayInfoModel payInfoModel) {
        if (payInfoModel.getCode() == 0) {
            ToastUtils.showShort("支付订单取消！");
        }

    }

    @Override
    public void getCancelPayFailed(int netCode, String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    //实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();//如果不想点击外部关闭弹窗去掉此行代码即可
        return true;
    }
}



