package com.kupo.ElephantHead.ui.home.activity;


import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 代理授权(占财位)
 */
@Route(path = AppConfig.ACTIVITY_URL_HOME_ZCW)
public class ZcwActivity extends BaseActivity {

    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.update_agent)
    TextView updateAgent;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.zcw_money_tv)
    TextView zcwMoneyTv;
    @BindView(R.id.update_agent_gray)
    TextView updateAgentGray;
    @BindView(R.id.zcw_before_money_tv)
    TextView zcwBeforeMoneyTv;
    private double money = 10.0;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @OnClick({R.id.title_return_linear, R.id.update_agent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_linear:
                EventBus.getDefault().postSticky(new BaseResult("zcw刷新"));
                finish();
                break;
            case R.id.update_agent:
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_HOME_PAYTYPE)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .withString("type", "buyVip")
                        .withDouble("price", money)
                        .withInt("orderId", CommonUtils.getUserInfo().getId())
                        .navigation();
                break;
        }
    }

    @Override
    protected void onInitPresenters() {
        money = getIntent().getDoubleExtra("price", 10.0);
        zcwMoneyTv.setText("￥" + money);
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.GONE);
        titleTitleTxt.setText("升级会员");
        zcwBeforeMoneyTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        if (CommonUtils.getUserInfo().getIsVip()) {
            updateAgentGray.setVisibility(View.VISIBLE);
            updateAgent.setVisibility(View.GONE);
            updateAgent.setEnabled(false);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zcw;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (CommonUtils.getUserInfo().getIsVip()) {
            updateAgentGray.setVisibility(View.VISIBLE);
            updateAgent.setVisibility(View.GONE);
            updateAgent.setEnabled(false);
        }
    }
}
