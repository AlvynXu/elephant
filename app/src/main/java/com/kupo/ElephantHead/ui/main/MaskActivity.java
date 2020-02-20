package com.kupo.ElephantHead.ui.main;


import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.greendao.MaskInfo;
import com.kupo.ElephantHead.greendao.MaskInfoOperateDao;

import butterknife.BindView;

/**
 * 蒙版
 */
@Route(path = AppConfig.ACTIVITY_URL_HOME_MASK)
public class MaskActivity extends BaseActivity {

    @BindView(R.id.mask_bg_iv)
    ImageView maskBgIv;
    private int index = 0;

    @Override
    protected void init(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    protected void onInitPresenters() {
        MaskInfo maskInfo = new MaskInfo();
        maskInfo.setIsShow(1);
        maskInfo.setDesc("出现蒙版");
        MaskInfoOperateDao.insertMaskInfo(maskInfo);
        maskBgIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MAIN_MAIN)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                    finish();
                } else {
                    index++;
                    maskBgIv.setImageDrawable(getResources().getDrawable(R.drawable.lead_2));
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mask;
    }


}
