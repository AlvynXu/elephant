package com.kupo.ElephantHead.ui.transaction.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.base.BaseDiaLogFragment;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.main.MainActivity;
import com.kupo.ElephantHead.ui.mvp.model.WxNofit;
import com.kupo.ElephantHead.ui.transaction.model.CrowdPayRefreshModel;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 我的求租/支付方式
 */
@Route(path = AppConfig.ACTIVITY_URL_TRANSACTION_RENT_TYPE)
public class PayRentTypeActivity extends Activity {
    @BindView(R.id.pay_rent_type_price_tv)
    TextView payRentTypePriceTv;
    @BindView(R.id.pay_rent_type_isSelect_tv)
    ImageView payRentTypeIsSelectTv;
    @BindView(R.id.pay_rent_type_sure_pay)
    TextView payRentTypeSurePay;
    @BindView(R.id.pay_rent_type_ll)
    LinearLayout payRentTypeLl;
    @BindView(R.id.head_my_rent_ll)
    LinearLayout headMyRentLl;
    @BindView(R.id.pay_rent_type_desc_tv)
    TextView payRentTypeDescTv;
    @BindView(R.id.pay_rent_type_success_ok)
    TextView payRentTypeSuccessOk;
    @BindView(R.id.bottom_my_rent_ll)
    LinearLayout bottomMyRentLl;
    private boolean isFlag = true;
    PayInfoModel.DataBean wxModel = new PayInfoModel.DataBean();
    private IWXAPI iwxapi;
    private int payType = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pay_rent_type_popup);
        ButterKnife.bind(this);
        wxModel = (PayInfoModel.DataBean) getIntent().getSerializableExtra("wxModel");
        payRentTypePriceTv.setText(getIntent().getStringExtra("price"));
        payRentTypeSurePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payType == 0) {
                    startWx(wxModel);
                } else {
                    ToastUtils.showShort("请勾选支付方式");
                }
            }
        });
        payRentTypeLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFlag) {
                    //点击时显示选中图标
                    payRentTypeIsSelectTv.setImageDrawable(getResources().getDrawable(R.drawable.popup_item_default));
                    isFlag = false;
                    payType = 1;
                } else {
                    payRentTypeIsSelectTv.setImageDrawable(getResources().getDrawable(R.drawable.popup_item_select));
                    //再次点击时显示默认图标
                    isFlag = true;
                    payType = 0;
                }
            }
        });
        payRentTypeSuccessOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new CrowdPayRefreshModel("qz刷新"));
                finish();
            }
        });
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
            payRentTypeIsSelectTv.setImageDrawable(getResources().getDrawable(R.drawable.popup_item_default));
        } else {
            payRentTypeIsSelectTv.setImageDrawable(getResources().getDrawable(R.drawable.popup_item_select));
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
            headMyRentLl.setVisibility(View.GONE);
            bottomMyRentLl.setVisibility(View.VISIBLE);
            payRentTypeDescTv.setText("您成功发布了一条求租信息！");
        } else if (baseResult.getCode() == -1) {
            finish();
            ToastUtils.showShort("支付失败，请联系客服");
        } else {
            finish();
            ToastUtils.showShort("支付取消");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    //实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();//如果不想点击外部关闭弹窗去掉此行代码即可
        return true;
    }

}
