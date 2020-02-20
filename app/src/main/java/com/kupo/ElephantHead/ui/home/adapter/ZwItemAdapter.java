package com.kupo.ElephantHead.ui.home.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.ZwItemModel;
import com.kupo.ElephantHead.ui.room.adapter.OperationItemAdapter;

import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * @ClassName: DzItemAdapter
 * @Description: 展位
 * @Author:
 * @Version: 1.0
 */
public class ZwItemAdapter extends BaseMultiItemQuickAdapter<ZwItemModel.DataBean.RecordsBean, BaseViewHolder> {
    private Activity mActivity;

    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnZwItemListener mOnZwItemListener;

    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnZwItemListener {
        void onZwItemClick(int position);

    }

    // 接口回调第二步: 初始化接口的引用
    public void setOnZwItemListener(OnZwItemListener l) {
        this.mOnZwItemListener = l;
    }


    public ZwItemAdapter(List data, Activity mActivity) {
        super(data);
        //添加多布局
        addItemType(0, R.layout.item_dzzw_rv);
        this.mActivity = mActivity;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, ZwItemModel.DataBean.RecordsBean recordsBeans) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                TextView tv = baseViewHolder.getView(R.id.qdz_item_buy);
                if (recordsBeans.isSaved()) {
                    tv.setText("免费解锁");
                    tv.setTextSize(14);
                    tv.setBackground(mActivity.getResources().getDrawable(R.drawable.bg_border_green));
                    baseViewHolder.setText(R.id.qdz_item_location, "地主展位：" + recordsBeans.getBoothCode()).setText(R.id.qdz_item_price, "￥" + recordsBeans.getPrice() + "/年");
                    baseViewHolder.getView(R.id.qdz_item_buy).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mOnZwItemListener != null) {
                                mOnZwItemListener.onZwItemClick(baseViewHolder.getAdapterPosition());
                            }
                        }
                    });
                } else {
                    tv.setTextSize(14);
                    tv.setText("立即抢购");
                    tv.setBackground(mActivity.getResources().getDrawable(R.drawable.bg_border_yellow));
                    baseViewHolder.setText(R.id.qdz_item_location, "普通展位：" + recordsBeans.getBoothCode()).setText(R.id.qdz_item_price, "￥" + recordsBeans.getPrice() + "/年");
                    baseViewHolder.getView(R.id.qdz_item_buy).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ARouter.getInstance()
                                    .build(AppConfig.ACTIVITY_URL_HOME_PAYTYPE)
                                    //进入动画
                                    .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                    .withString("type", "buyBooth")
                                    .withDouble("price", recordsBeans.getPrice())
                                    .withInt("orderId", recordsBeans.getId())
                                    .withString("num", recordsBeans.getBoothCode())
                                    .withString("location", recordsBeans.getBoothName())
                                    .navigation();
                        }
                    });
                }
                break;
        }
    }
}

