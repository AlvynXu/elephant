package com.kupo.ElephantHead.ui.transaction.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;

import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * 我的求租/街道列表数据适配器
 */
public class MyRentAdapter extends BaseMultiItemQuickAdapter<ProvincesModel.DataBean, BaseViewHolder> {
    private Activity mActivity;
    //先声明一个int成员变量
    private String districtsCode;

    //再定义一个int类型的返回值方法
    public String getDistrictsCode() {
        return districtsCode;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setDistrictsCode(String districtsCode) {
        this.districtsCode = districtsCode;
    }

    public MyRentAdapter(List<ProvincesModel.DataBean> data, Activity mActivity) {
        super(data);
        addItemType(0, R.layout.item_my_rent_rv);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProvincesModel.DataBean dataBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                baseViewHolder.setText(R.id.item_my_rent_tv, dataBean.getName());
                ImageView iv = baseViewHolder.getView(R.id.item_my_rent_iv);
                if (dataBean.isSelected()) {
                    iv.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.user_read));
                } else {
                    iv.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.user_read_default));
                }
                break;
        }
    }
}
