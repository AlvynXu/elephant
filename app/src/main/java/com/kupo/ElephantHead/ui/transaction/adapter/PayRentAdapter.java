package com.kupo.ElephantHead.ui.transaction.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.room.adapter.OperationItemAdapter;
import com.kupo.ElephantHead.ui.transaction.model.MyCrowdModel;
import com.kupo.ElephantHead.ui.transaction.model.RentStreetModel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * 交易模块出租街道
 */
public class PayRentAdapter extends BaseMultiItemQuickAdapter<RentStreetModel.DataBean.UserStreetDTOListBean, BaseViewHolder> {
    private Activity mActivity;
    private boolean isFlag = false;

    private Map<Integer, Boolean> map = new HashMap<>();

    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnItemRemoveListener mOnItemRemoveListener;

    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnItemRemoveListener {
        void onItemRemoveClick(int position, int flag);

    }

    // 接口回调第二步: 初始化接口的引用
    public void setOnItemRemoveListener(OnItemRemoveListener l) {
        this.mOnItemRemoveListener = l;
    }


    public PayRentAdapter(List<RentStreetModel.DataBean.UserStreetDTOListBean> data, Activity mActivity) {
        super(data);
        //添加多布局
        addItemType(0, R.layout.item_pay_rent_rv);
        this.mActivity = mActivity;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, RentStreetModel.DataBean.UserStreetDTOListBean dataBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                baseViewHolder.setText(R.id.pay_rent_location_tv, dataBean.getCityName() + " " + dataBean.getDistrictName() + " " + dataBean.getStreetName());
                ImageView imageView = baseViewHolder.getView(R.id.pay_rent_select_tv);
                TextView tv = baseViewHolder.getView(R.id.pay_rent_location_tv);
                if (dataBean.isHasBooth()) {
                    imageView.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.user_read));
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                    tv.setTextColor(Color.parseColor("#BABABA"));
                }

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isFlag) {
                            imageView.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.user_read));
                            isFlag = false;
                            map.put(baseViewHolder.getAdapterPosition(), true);
                            if (mOnItemRemoveListener != null) {
                                mOnItemRemoveListener.onItemRemoveClick(baseViewHolder.getAdapterPosition(), 1);
                            }
                        } else {
                            imageView.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.user_read_default));
                            isFlag = true;
                            map.remove(baseViewHolder.getAdapterPosition());
                            if (mOnItemRemoveListener != null) {
                                mOnItemRemoveListener.onItemRemoveClick(baseViewHolder.getAdapterPosition(), 2);
                            }
                        }
                    }
                });
                break;
        }
    }
}
