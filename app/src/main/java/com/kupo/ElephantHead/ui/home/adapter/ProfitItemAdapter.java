package com.kupo.ElephantHead.ui.home.adapter;

import android.app.Activity;
import android.view.View;

import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.TeamListModel;

import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * Created by G400 on 2019/8/18.
 * 功能：收益模块列表adapter
 * 作者：
 */
public class ProfitItemAdapter extends BaseMultiItemQuickAdapter<TeamListModel.DataBean.RecordsBean, BaseViewHolder> {

    private Activity mActivity;

    public ProfitItemAdapter(List<TeamListModel.DataBean.RecordsBean> data, Activity mActivity) {
        super(data);
        addItemType(0, R.layout.item_team_rv);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TeamListModel.DataBean.RecordsBean recordsBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                String amount = recordsBean.getAmount() + "";
                baseViewHolder.getView(R.id.team_item_desc).setVisibility(View.GONE);
                baseViewHolder.setText(R.id.team_item_userName, recordsBean.getTypeString())
                        .setText(R.id.team_item_time, recordsBean.getTime())
                        .setText(R.id.team_item_num, amount.contains("-") ? amount : "+" + amount);
                break;
        }
    }
}
