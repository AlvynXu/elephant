package com.kupo.ElephantHead.ui.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 客服
 */
@Route(path = AppConfig.ACTIVITY_URL_MINE_KEFU)
public class KeFuActivity extends BaseActivity {


    @BindView(R.id.title_return_img)
    ImageView titleReturnImg;
    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.title_right_linear)
    LinearLayout titleRightLinear;
    @BindView(R.id.common_tv)
    TextView commonTv;
    @BindView(R.id.common_iv)
    ImageView commonIv;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        titleTitleTxt.setText("客服");
        titleRightImg.setVisibility(View.GONE);
        titleRightTxt.setVisibility(View.GONE);
        commonIv.setImageDrawable(getResources().getDrawable(R.drawable.weixin));
        titleReturnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commonIv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CommonUtils.toWeChatScan();
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ke_fu;
    }


}
