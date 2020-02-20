package com.kupo.ElephantHead.ui.home.adapter;

import android.app.Activity;

import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.ProfitItemModel;

import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * Created by G400 on 2019/8/18.
 * 功能：我的钱包模块列表adapter
 * 作者：
 */
public class WalletItemAdapter extends BaseMultiItemQuickAdapter<ProfitItemModel, BaseViewHolder> {

    private Activity mActivity;

    public WalletItemAdapter(List<ProfitItemModel> data, Activity mActivity) {
        super(data);
        addItemType(0, R.layout.item_profit_rv);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProfitItemModel profitItemModel) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                if (baseViewHolder.getAdapterPosition() % 2 == 1) {
                    baseViewHolder.getView(R.id.item_profit_ll).setBackgroundColor(mActivity.getResources().getColor(R.color.f7));
                }
                baseViewHolder.setText(R.id.item_profit_time, profitItemModel.getTime())
                        .setText(R.id.item_profit_content, profitItemModel.getContent())
                        .setText(R.id.item_profit_price, profitItemModel.getPrice());
                break;
        }
    }
}
