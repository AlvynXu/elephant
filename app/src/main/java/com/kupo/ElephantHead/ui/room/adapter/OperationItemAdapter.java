package com.kupo.ElephantHead.ui.room.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.MinePayDzOrZwModel;
import com.kupo.ElephantHead.ui.home.model.ZwItemModel;
import com.kupo.ElephantHead.ui.mine.adapter.EditItemAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * @ClassName: OperationItemAdapter
 * @Description: 展位管理
 * @Author:
 * @Version: 1.0
 */
public class OperationItemAdapter extends BaseMultiItemQuickAdapter<MinePayDzOrZwModel.DataBean.RecordsBean, BaseViewHolder> {
    private Activity mActivity;
    boolean isFlag = false;
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

    public OperationItemAdapter(List data, Activity mActivity) {
        super(data);
        //添加多布局
        addItemType(0, R.layout.item_operation_rv);
        this.mActivity = mActivity;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, MinePayDzOrZwModel.DataBean.RecordsBean recordsBeans) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                baseViewHolder.setText(R.id.item_operation_location, recordsBeans.getCity() + "-" + recordsBeans.getDistrict() + "-" + recordsBeans.getStreet());
                baseViewHolder.setText(R.id.item_operation_num, "编号：" + recordsBeans.getCode());
                baseViewHolder.setText(R.id.item_operation_time, "剩余：" + recordsBeans.getLeftDate() + "天");
                CheckBox im = baseViewHolder.getView(R.id.item_operation_choose);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (im.isChecked()) {
                            map.put(baseViewHolder.getAdapterPosition(), true);
                            if (mOnItemRemoveListener != null) {
                                mOnItemRemoveListener.onItemRemoveClick(baseViewHolder.getAdapterPosition(), 1);
                            }
                        } else {
                            map.remove(baseViewHolder.getAdapterPosition());
                            if (mOnItemRemoveListener != null) {
                                mOnItemRemoveListener.onItemRemoveClick(baseViewHolder.getAdapterPosition(), 2);
                            }
                        }
                    }
                });
                if (map != null && map.containsKey(baseViewHolder.getAdapterPosition())) {
                    im.setChecked(true);
                } else {
                    im.setChecked(false);
                }
                break;
        }
    }
}

