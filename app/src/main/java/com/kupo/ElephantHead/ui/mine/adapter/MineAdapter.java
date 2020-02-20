package com.kupo.ElephantHead.ui.mine.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.utils.AppUtils;
import com.kupo.ElephantHead.utils.CommonUtils;

import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * Created by Administrator on 2017/11/16/016.
 * 使用于settingActivity和AccountActivity
 *
 * @auther
 */

public class MineAdapter extends BaseMultiItemQuickAdapter<SingleModel, BaseViewHolder> {
    private Activity mfActivity;

    public MineAdapter(List<SingleModel> data, Activity mfActivity) {
        super(data);
        addItemType(0, R.layout.item_mine_rv);
        this.mfActivity = mfActivity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final SingleModel singleModel) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                ImageView iv = baseViewHolder.getView(R.id.item_mine_iv);
                iv.setImageDrawable(mfActivity.getResources().getDrawable(singleModel.getIcon()));
                baseViewHolder.setText(R.id.item_mine_tv, singleModel.getTitle());
                baseViewHolder.getView(R.id.item_rl).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (singleModel.getTitle()) {
                            case "邀请好友":
                                if (CommonUtils.singleOnClick()) {
                                    ARouter.getInstance()
                                            .build(AppConfig.ACTIVITY_URL_HOME_SHARE)
                                            //进入动画
                                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                            .navigation();
                                }
                                break;
                            case "客服中心":
                                if (CommonUtils.singleOnClick()) {
                                    ARouter.getInstance()
                                            .build(AppConfig.ACTIVITY_URL_MINE_KEFU)
                                            //进入动画
                                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                            .navigation();
                                }
                                break;
//                            case "我的余额":
//                                ARouter.getInstance()
//                                        .build(AppConfig.ACTIVITY_URL_HOME_PROFIT)
//                                        //进入动画
//                                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
//                                        .navigation();
//                                break;
                            case "意见反馈":
                                if (CommonUtils.singleOnClick()) {
                                    ARouter.getInstance()
                                            .build(AppConfig.ACTIVITY_URL_MINE_QUESTION)
                                            //进入动画
                                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                            .navigation();
                                }
                                break;
                            case "版本更新":
                                AppUtils.getVerCode(mfActivity);
                                AppUtils.getAppVersionName(mfActivity);
                                ToastUtils.showShort("当前已经是最新版本，版本号：V" + AppUtils.getAppVersionName(mfActivity));
                                break;
                            case "我的收藏":
                                if (CommonUtils.singleOnClick()) {
                                    ARouter.getInstance()
                                            .build(AppConfig.ACTIVITY_URL_MINE_COLLECT)
                                            //进入动画
                                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                            .navigation();
                                }
                                break;
                        }
                    }
                });
                break;
        }

    }
}
