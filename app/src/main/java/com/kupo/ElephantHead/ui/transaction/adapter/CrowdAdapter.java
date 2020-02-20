package com.kupo.ElephantHead.ui.transaction.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.LogUtils;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.room.adapter.OperationItemAdapter;
import com.kupo.ElephantHead.ui.transaction.fragment.PayRentFragment;
import com.kupo.ElephantHead.ui.transaction.model.CrowdModel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * 交易模块下面的列表
 */
public class CrowdAdapter extends BaseMultiItemQuickAdapter<CrowdModel.DataBean.RecordsBean, BaseViewHolder> {
    private Activity mActivity;

    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnItemListener mOnItemListener;

    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnItemListener {
        void onItemClick(int position);

    }

    // 接口回调第二步: 初始化接口的引用
    public void setOnItemListener(OnItemListener l) {
        this.mOnItemListener = l;
    }

    public CrowdAdapter(List<CrowdModel.DataBean.RecordsBean> data, Activity activity) {
        super(data);
        //添加多布局
        addItemType(0, R.layout.item_crowd_rv);
        this.mActivity = mActivity;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, CrowdModel.DataBean.RecordsBean recordsBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                ProgressBar progressBar = baseViewHolder.getView(R.id.progress_bar_h);
                if (recordsBean.getSoldCount() == 0) {
                    progressBar.setProgress(0);
                } else {
                    progressBar.setProgress(((new BigDecimal(recordsBean.getSoldCount()).multiply(new BigDecimal(100.000))
                            .divide(new BigDecimal(recordsBean.getTotalCount()), 0, BigDecimal.ROUND_UP)).intValue()));
                }
                DecimalFormat df = new DecimalFormat("0.00");
                baseViewHolder.setText(R.id.item_crowd_day_tv, recordsBean.getDays() + "")
                        .setText(R.id.item_crowd_price_tv, "￥" + df.format(recordsBean.getPrice()))
                        .setText(R.id.item_crowd_bar_tv, recordsBean.getSoldCount() + "/" + recordsBean.getTotalCount());
                baseViewHolder.getView(R.id.item_crowd_rent_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemListener != null) {
                            mOnItemListener.onItemClick(baseViewHolder.getAdapterPosition());
                        }
                    }
                });
                break;
        }
    }
}
