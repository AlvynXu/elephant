package com.kupo.ElephantHead.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.ui.main.model.AgreementModel;
import com.kupo.ElephantHead.ui.mvp.contract.AgreementContract;
import com.kupo.ElephantHead.ui.mvp.presenter.AgreementPresenter;
import com.kupo.ElephantHead.ui.mvp.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用户协议
 */
@Route(path = AppConfig.ACTIVITY_URL_MAIN_AGREEMENT)
public class AgreementActivity extends BaseActivity implements AgreementContract.IAgreementView {
    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.text_agreement)
    TextView textAgreement;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    private AgreementContract.IAgreementPresenter agreementPresenter = null;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        titleTitleTxt.setText("用户协议");
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.GONE);
        agreementPresenter = new AgreementPresenter();
        agreementPresenter.attachView(this);
        agreementPresenter.getRegisterInfos();
        titleReturnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_agreement;
    }


    @Override
    public void getRegisterInfosSuccess(AgreementModel agreementModel) {
        textAgreement.setText(agreementModel.getData().getText());
    }

    @Override
    public void getRegisterInfosFailed(int netCode, String msg) {

    }
}
