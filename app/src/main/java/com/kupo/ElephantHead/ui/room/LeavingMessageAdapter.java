package com.kupo.ElephantHead.ui.room;

import android.app.Activity;
import android.widget.ImageView;

import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.room.model.LeavingMessageModel;
import com.kupo.ElephantHead.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * 详情留言适配器
 */
public class LeavingMessageAdapter extends BaseMultiItemQuickAdapter<LeavingMessageModel.DataBean.RecordsBean, BaseViewHolder> {
    private Activity mActivity;

    public LeavingMessageAdapter(List<LeavingMessageModel.DataBean.RecordsBean> data, Activity mActivity) {
        super(data);
        addItemType(0, R.layout.item_leaving_message_rv);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, LeavingMessageModel.DataBean.RecordsBean dataBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                baseViewHolder.setText(R.id.item_leaving_user_tv, dataBean.getPhone()).setText(R.id.item_leaving_content_tv, dataBean.getMessage());
                ImageView imageView = baseViewHolder.getView(R.id.item_leaving_level_iv);
                List<Integer> mips = new ArrayList<>();
                mips.add(R.drawable.level_0);
                mips.add(R.drawable.level_1);
                mips.add(R.drawable.level_2);
                mips.add(R.drawable.level_3);
                for (int i = 0; i < mips.size(); i++) {
                    if (dataBean.getUserLevel() == i) {
                        imageView.setImageDrawable(mActivity.getResources().getDrawable(mips.get(i)));
                    }
                }
                break;
        }
    }
}
