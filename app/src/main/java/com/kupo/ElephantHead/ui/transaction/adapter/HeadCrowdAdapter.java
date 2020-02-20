package com.kupo.ElephantHead.ui.transaction.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.LogUtils;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.transaction.model.MyCrowdModel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * 交易模块头部的列表
 */
public class HeadCrowdAdapter extends BaseMultiItemQuickAdapter<MyCrowdModel.DataBean, BaseViewHolder> {
    private Activity mActivity;

    public HeadCrowdAdapter(List<MyCrowdModel.DataBean> data, Activity activity) {
        super(data);
        //添加多布局
        addItemType(0, R.layout.item_head_crowd_rv);
        this.mActivity = mActivity;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyCrowdModel.DataBean dataBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                ProgressBar progressBar = baseViewHolder.getView(R.id.progress_bar_h);
                if (dataBean.getSoldCount() == 0) {
                    progressBar.setProgress(0);
                } else {
                    progressBar.setProgress(((new BigDecimal(dataBean.getSoldCount()).multiply(new BigDecimal(100.000))
                            .divide(new BigDecimal(dataBean.getTotalCount()), 0, BigDecimal.ROUND_UP)).intValue()));
                }
                DecimalFormat df = new DecimalFormat("0.00");
                baseViewHolder.setText(R.id.item_crowd_head_day_tv, dataBean.getDays() + "")
                        .setText(R.id.item_crowd_head_price_tv, "￥" + df.format(dataBean.getPrice()))
                        .setText(R.id.item_crowd_head_bar_tv, dataBean.getSoldCount() + "/" + dataBean.getTotalCount());
                break;
        }
    }
}
