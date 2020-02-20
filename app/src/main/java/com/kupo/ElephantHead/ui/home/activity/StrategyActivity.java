package com.kupo.ElephantHead.ui.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.base.basemodel.BaseResult;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 攻略
 */
@Route(path = AppConfig.ACTIVITY_URL_HOME_STRATEGY)
public class StrategyActivity extends BaseActivity {


    @BindView(R.id.iv_big)
    ImageView ivBig;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        titleTitleTxt.setText("赚钱攻略");
        titleRightImg.setVisibility(View.GONE);
        titleRightTxt.setVisibility(View.GONE);
        ivBig.setImageDrawable(getResources().getDrawable(R.drawable.strategy));
        titleReturnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new BaseResult("gl刷新"));
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_strategy;
    }


}
