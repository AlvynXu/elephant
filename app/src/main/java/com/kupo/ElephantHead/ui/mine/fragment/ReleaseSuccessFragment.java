package com.kupo.ElephantHead.ui.mine.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.AppManager;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseDiaLogFragment;
import com.kupo.ElephantHead.ui.room.activity.ItemDetailsActivity;

import butterknife.BindView;


/**
 * @ClassName: ReleaseSuccessFragment
 * @Description: 发布成功
 * @Author:
 * @CreateDate: 2019/8/14 10:05
 * @Version: 1.0
 */
public class ReleaseSuccessFragment extends BaseDiaLogFragment {

    @BindView(R.id.toLook)
    TextView toLook;
    @BindView(R.id.toRelease)
    TextView toRelease;
    private int detailsId;


    @Override
    protected void onInitPresenters() {
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
        toLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_ROOM_DETAILS)
                        .withInt("detailsId", detailsId)
                        .withInt("status", 8)
                        .withString("intentType", "project")
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                getActivity().finish();
                dismiss();
            }
        });
        toRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(ItemDetailsActivity.class);
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MINE_PROJECT)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                getActivity().finish();
                dismiss();
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_release_success;
    }

    @Override
    protected void init() {
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        if (bundle != null) {
            detailsId = bundle.getInt("detailsId");
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
                params.height = getResources().getDisplayMetrics().heightPixels;
                window.setAttributes(params);
            }
        }

    }
}



