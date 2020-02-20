package com.kupo.ElephantHead.ui.home.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.MessageModel;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.ui.home.model.TeamListModel;
import com.kupo.ElephantHead.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * Created by G400 on 2019/8/18.
 * 功能：团队模块列表adapter
 * 作者：
 */
public class MessageItemAdapter extends BaseMultiItemQuickAdapter<MessageModel.DataBean.RecordsBean, BaseViewHolder> {

    private Activity mActivity;

    public MessageItemAdapter(List<MessageModel.DataBean.RecordsBean> data, Activity mActivity) {
        super(data);
        addItemType(0, R.layout.item_message_rv);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MessageModel.DataBean.RecordsBean recordsBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                try {
                    baseViewHolder.setText(R.id.message_item_type, recordsBean.getSender())
                            .setText(R.id.message_item_time,CommonUtils.longToString(recordsBean.getCreateTime(),"yyyy-MM-dd HH:mm").toString())
                            .setText(R.id.message_item_content, recordsBean.getContent());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                baseViewHolder.getView(R.id.message_item_ll).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(new SingleModel(recordsBean.getId()));
                    }
                });
                break;
        }
    }
}
