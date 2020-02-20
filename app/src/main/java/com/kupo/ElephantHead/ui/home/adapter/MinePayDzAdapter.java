package com.kupo.ElephantHead.ui.home.adapter;

import android.app.Activity;
import android.view.View;

import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.MinePayDzOrZwModel;

import java.util.ArrayList;
import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * Created by G400 on 2019/8/18.
 * 功能：用户已经购买的展位和地主
 * 作者：
 */
public class MinePayDzAdapter extends BaseMultiItemQuickAdapter<MinePayDzOrZwModel.DataBean.RecordsBean, BaseViewHolder> {

    private Activity mActivity;
    private List<MinePayDzOrZwModel.DataBean.RecordsBean> data = new ArrayList<>();

    public MinePayDzAdapter(List<MinePayDzOrZwModel.DataBean.RecordsBean> data, Activity mActivity) {
        super(data);
        addItemType(0, R.layout.item_pay_dz_zw);
        this.mActivity = mActivity;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MinePayDzOrZwModel.DataBean.RecordsBean recordsBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                baseViewHolder.getView(R.id.item_mine_pay_zw_ll).setVisibility(View.GONE);
                baseViewHolder.getView(R.id.item_mine_pay_dz_ll).setVisibility(View.VISIBLE);
                baseViewHolder.setText(R.id.item_mine_pay_dz_time_tv, recordsBean.getCity() + recordsBean.getDistrict() + recordsBean.getStreet());
                break;
        }
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void addDataList(List<MinePayDzOrZwModel.DataBean.RecordsBean> addMessageList) {
        //增加数据
        int position = data.size();
        data.addAll(position, addMessageList);
        notifyItemInserted(position);
    }

    public void refresh(List<MinePayDzOrZwModel.DataBean.RecordsBean> newList) {
        //刷新数据
        data.removeAll(data);
        data.addAll(newList);
        notifyDataSetChanged();
    }
}
