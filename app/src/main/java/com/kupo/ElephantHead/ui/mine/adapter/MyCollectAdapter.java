package com.kupo.ElephantHead.ui.mine.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;

import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * 我的广告
 */
public class MyCollectAdapter extends BaseMultiItemQuickAdapter<HomeTopModel.DataBean.RecordsBean, BaseViewHolder> {
    private Activity mfActivity;

    public MyCollectAdapter(List<HomeTopModel.DataBean.RecordsBean> data, Activity mfActivity) {
        super(data);
        addItemType(0, R.layout.item_project_rv);
        this.mfActivity = mfActivity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final HomeTopModel.DataBean.RecordsBean detailListBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                TextView tv = baseViewHolder.getView(R.id.project_status_tv);
                tv.setVisibility(View.GONE);
                baseViewHolder.setText(R.id.project_name_tv, detailListBean.getDescription());
                baseViewHolder.getView(R.id.item_project_ll).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance()
                                .build(AppConfig.ACTIVITY_URL_ROOM_DETAILS)
                                .withInt("detailsId", detailListBean.getId())
                                .withInt("status", 8)
                                .withString("intentType", "finish")
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
