package com.kupo.ElephantHead.ui.home.adapter;

import android.app.Activity;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.DzItemModel;

import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * @ClassName: DzItemAdapter
 * @Description: 抢地主的条目
 * @Author:
 * @Version: 1.0
 */
public class DzItemAdapter extends BaseMultiItemQuickAdapter<DzItemModel.DataBean.RecordsBean, BaseViewHolder> {
    private Activity mActivity;

    public DzItemAdapter(List data, Activity mActivity) {
        super(data);
        //添加多布局
        addItemType(0, R.layout.item_dzzw_rv);
        this.mActivity = mActivity;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, DzItemModel.DataBean.RecordsBean recordsBeans) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                baseViewHolder.setText(R.id.qdz_item_location, recordsBeans.getName()).setText(R.id.qdz_item_price, "￥" + recordsBeans.getPrice() + "/永久");
                baseViewHolder.getView(R.id.qdz_item_buy).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ARouter.getInstance()
                                .build(AppConfig.ACTIVITY_URL_HOME_PAYTYPE)
                                //进入动画
                                .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                .withString("type", "bugStreet")
                                .withDouble("price", recordsBeans.getPrice())
                                .withInt("orderId", recordsBeans.getId())
                                .withString("num", recordsBeans.getCodeX())
                                .withString("location", recordsBeans.getName())
                                .navigation();
                    }
                });
                break;
        }
    }
}

