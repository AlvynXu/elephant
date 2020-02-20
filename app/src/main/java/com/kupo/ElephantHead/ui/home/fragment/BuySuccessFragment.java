package com.kupo.ElephantHead.ui.home.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseDiaLogFragment;

import butterknife.BindView;

/**
 * 成功页面提示框
 */
public class BuySuccessFragment extends BaseDiaLogFragment {

    @BindView(R.id.buy_success_ok)
    TextView buySuccessOk;
    @BindView(R.id.pay_success_desc_tv)
    TextView paySuccessDescTv;
    private String desc;


    @Override
    protected void onInitPresenters() {
        paySuccessDescTv.setText(desc);
        buySuccessOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_success;
    }

    @Override
    protected void init() {
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        if (bundle != null) {
            desc = bundle.getString("desc");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog.getWindow();
        if (dialog != null) {
            if (window != null) {
                // 如果不设置这句代码, 那么弹框就会与四边都有一定的距离
                window.setBackgroundDrawableResource(android.R.color.white);
                WindowManager.LayoutParams params = window.getAttributes();
                params.gravity = Gravity.BOTTOM;
                // 如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
                params.width = getResources().getDisplayMetrics().widthPixels;
                params.height=getResources().getDisplayMetrics().heightPixels;
                window.setAttributes(params);
            }
        }
    }
}
