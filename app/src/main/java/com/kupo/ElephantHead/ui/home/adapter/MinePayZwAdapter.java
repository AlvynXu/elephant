package com.kupo.ElephantHead.ui.home.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.MinePayDzOrZwModel;
import com.kupo.ElephantHead.utils.CommonUtils;

import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * Created by G400 on 2019/8/18.
 * 功能：用户已经购买的展位和地主
 * 作者：
 */
public class MinePayZwAdapter extends BaseMultiItemQuickAdapter<MinePayDzOrZwModel.DataBean.RecordsBean, BaseViewHolder> {

    private Activity mActivity;

    public MinePayZwAdapter(List<MinePayDzOrZwModel.DataBean.RecordsBean> data, Activity mActivity) {
        super(data);
        addItemType(0, R.layout.item_pay_dz_zw);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MinePayDzOrZwModel.DataBean.RecordsBean recordsBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                String status = "闲置";
                TextView tv = baseViewHolder.getView(R.id.item_mine_pay_zw_status_tv);
                LinearLayout noLl = baseViewHolder.getView(R.id.item_mine_pay_zw_no_ll);
                ImageView titleMore = baseViewHolder.getView(R.id.item_mine_pay_title_more);
                LinearLayout titleLl = baseViewHolder.getView(R.id.item_mine_pay_zw_title_ll);
                TextView footLine = baseViewHolder.getView(R.id.item_mine_foot_line);
                if (recordsBean.getUseStatus() == 1) {
                    status = "闲置";
                    tv.setBackground(mActivity.getResources().getDrawable(R.drawable.bg_round_half_red));
                    noLl.setVisibility(View.GONE);
                    titleLl.setVisibility(View.GONE);
                    footLine.setVisibility(View.GONE);
                } else if (recordsBean.getUseStatus() == 2) {
                    status = "待租";
                } else if (recordsBean.getUseStatus() == 6) {
                    status = "广告";
                    tv.setBackground(mActivity.getResources().getDrawable(R.drawable.bg_round_half_green));
                    titleLl.setVisibility(View.VISIBLE);
                    noLl.setVisibility(View.GONE);
                    baseViewHolder.setText(R.id.item_mine_pay_zw_title_tv, recordsBean.getDescription());
                    titleLl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (CommonUtils.singleOnClick()) {
                                if (recordsBean.getItemId() != 0) {
                                    ARouter.getInstance()
                                            .build(AppConfig.ACTIVITY_URL_ROOM_DETAILS)
                                            .withInt("detailsId", recordsBean.getItemId())
                                            .withInt("status", 9)
                                            .withString("intentType", "adapter")
                                            //进入动画
                                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                            .navigation();
                                }
                            }
                        }
                    });
                } else if (recordsBean.getUseStatus() == 5) {
                    status = "租用";
                    tv.setBackground(mActivity.getResources().getDrawable(R.drawable.bg_round_half_orange));
                    noLl.setVisibility(View.VISIBLE);
                    baseViewHolder.setText(R.id.item_mine_pay_title_tv, TextUtils.isEmpty(recordsBean.getDescription()) ? "未发布广告" : recordsBean.getDescription());
                    if (recordsBean.getRentHours() > 0) {
                        baseViewHolder.setText(R.id.item_mine_pay_zw_no_time_tv, "出租剩余时间：" + recordsBean.getRentHours() / 24 + "天" + recordsBean.getRentHours() % 24 + "小时");
                    } else {
                        baseViewHolder.setText(R.id.item_mine_pay_zw_no_time_tv, "出租剩余时间：" + recordsBean.getRentHours());
                    }
                    if (TextUtils.isEmpty(recordsBean.getDescription())) {
                        titleMore.setVisibility(View.GONE);
                    } else {
                        titleMore.setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.item_mine_pay_more_ll).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (CommonUtils.singleOnClick()) {
                                    if (recordsBean.getItemId() != 0) {
                                        ARouter.getInstance()
                                                .build(AppConfig.ACTIVITY_URL_ROOM_DETAILS)
                                                .withInt("detailsId", recordsBean.getItemId())
                                                .withInt("status", 9)
                                                .withString("intentType", "adapter")
                                                //进入动画
                                                .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                                .navigation();
                                    }
                                }
                            }
                        });
                    }
                } else if (recordsBean.getUseStatus() == 4) {
                    status = "已租";
                    tv.setBackground(mActivity.getResources().getDrawable(R.drawable.bg_round_half_gray));
                    noLl.setVisibility(View.VISIBLE);
                    titleLl.setVisibility(View.GONE);
                    baseViewHolder.setText(R.id.item_mine_pay_title_tv, TextUtils.isEmpty(recordsBean.getDescription()) ? "未发布广告" : recordsBean.getDescription());
                    if (recordsBean.getRentHours() > 0) {
                        baseViewHolder.setText(R.id.item_mine_pay_zw_no_time_tv, "出租剩余时间：" + recordsBean.getRentHours() / 24 + "天" + recordsBean.getRentHours() % 24 + "小时");
                    } else {
                        baseViewHolder.setText(R.id.item_mine_pay_zw_no_time_tv, "出租剩余时间：" + recordsBean.getRentHours());
                    }
                    if (TextUtils.isEmpty(recordsBean.getDescription())) {
                        titleMore.setVisibility(View.GONE);
                    } else {
                        titleMore.setVisibility(View.VISIBLE);
                        baseViewHolder.getView(R.id.item_mine_pay_more_ll).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (CommonUtils.singleOnClick()) {
                                    if (recordsBean.getItemId() != 0) {
                                        ARouter.getInstance()
                                                .build(AppConfig.ACTIVITY_URL_ROOM_DETAILS)
                                                .withInt("detailsId", recordsBean.getItemId())
                                                .withInt("status", 9)
                                                .withString("intentType", "adapter")
                                                //进入动画
                                                .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                                .navigation();
                                    }
                                }
                            }
                        });
                    }
                }
                baseViewHolder.setText(R.id.item_mine_pay_zw_status_tv, status);
                baseViewHolder.getView(R.id.item_mine_pay_dz_ll).setVisibility(View.GONE);
                baseViewHolder.getView(R.id.item_mine_pay_zw_ll).setVisibility(View.VISIBLE);
                baseViewHolder.setText(R.id.item_mine_pay_zw_location_tv, recordsBean.getCity() + recordsBean.getDistrict() + recordsBean.getStreet());
                baseViewHolder.setText(R.id.item_mine_pay_zw_time_tv, "剩余：" + recordsBean.getDays());
                baseViewHolder.setText(R.id.item_mine_pay_zw_num_tv, "编号：" + recordsBean.getCode());
                break;
        }
    }
}
