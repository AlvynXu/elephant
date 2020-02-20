package com.kupo.ElephantHead.ui.mine.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.room.model.RoomIDetailsModel;

import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * 我的广告
 */
public class MyProjectAdapter extends BaseMultiItemQuickAdapter<HomeTopModel.DataBean.RecordsBean, BaseViewHolder> {
    private Activity mfActivity;

    public MyProjectAdapter(List<HomeTopModel.DataBean.RecordsBean> data, Activity mfActivity) {
        super(data);
        addItemType(0, R.layout.item_project_rv);
        this.mfActivity = mfActivity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final HomeTopModel.DataBean.RecordsBean detailListBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                String status = "";
                TextView tv = baseViewHolder.getView(R.id.project_status_tv);
                if (detailListBean.getStatus() == -1) {
                    tv.setTextColor(mfActivity.getResources().getColor(R.color.wsh));
                    status = "审核失败";
                } else if (detailListBean.getStatus() == 0) {
                    tv.setTextColor(mfActivity.getResources().getColor(R.color.sh));
                    status = "审核中";
                } else if (detailListBean.getStatus() == 3) {
                    tv.setTextColor(mfActivity.getResources().getColor(R.color.fb));
                    status = "发布中";
                } else if (detailListBean.getStatus() == 2) {
                    tv.setTextColor(mfActivity.getResources().getColor(R.color.kfb));
                    status = "未发布";
                }
                baseViewHolder.setText(R.id.project_name_tv, detailListBean.getDescription())
                        .setText(R.id.project_status_tv, status);
                baseViewHolder.getView(R.id.item_project_ll).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance()
                                .build(AppConfig.ACTIVITY_URL_ROOM_DETAILS)
                                .withInt("detailsId", detailListBean.getId())
                                .withInt("status", detailListBean.getStatus())
                                .withString("intentType", "project")
                                //进入动画
                                .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                .navigation();
                        mfActivity.finish();
                    }
                });
                break;
        }
    }
}
