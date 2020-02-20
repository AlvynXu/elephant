package com.kupo.ElephantHead.ui.transaction.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.utils.CommonUtils;

import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * 我的求租/头部区域数据适配器
 */
public class MyRentHeadAdapter extends BaseMultiItemQuickAdapter<ProvincesModel.DataBean, BaseViewHolder> {
    private Activity mActivity;
    //先声明一个int成员变量
    private int thisPosition;

    //再定义一个int类型的返回值方法
    public int getThisPosition() {
        return thisPosition;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }

    public MyRentHeadAdapter(List<ProvincesModel.DataBean> data, Activity mActivity) {
        super(data);
        addItemType(0, R.layout.item_my_rent_head_rv);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProvincesModel.DataBean dataBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                TextView tv = baseViewHolder.getView(R.id.item_my_rent_head_rv_tv);
                if (baseViewHolder.getAdapterPosition() == getThisPosition()) {
                    tv.setTextColor(Color.parseColor("#363636"));
                    tv.setBackground(mActivity.getResources().getDrawable(R.drawable.bg_content_white_border_yellow));
                    tv.setTextSize(16);
                } else {
                    tv.setTextColor(Color.parseColor("#999999"));
                    tv.setBackground(mActivity.getResources().getDrawable(R.drawable.bg_content_white_border_fe));
                    tv.setTextSize(14);
                }
                baseViewHolder.setText(R.id.item_my_rent_head_rv_tv, dataBean.getName());
                break;
        }
    }
}
