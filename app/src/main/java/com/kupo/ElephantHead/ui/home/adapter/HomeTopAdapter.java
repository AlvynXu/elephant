package com.kupo.ElephantHead.ui.home.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.holder.GlideLoader;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.utils.CommonUtils;

import java.util.List;

import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * @ClassName: HomeTopAdapter
 * @Description: java类作用描述
 */
public class HomeTopAdapter extends BaseMultiItemQuickAdapter<HomeTopModel.DataBean.RecordsBean, BaseViewHolder> {
    private Activity mActivity;

    public HomeTopAdapter(List data, Activity mActivity) {
        super(data);
        //添加多布局
        addItemType(0, R.layout.item_top_rv);
        this.mActivity = mActivity;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeTopModel.DataBean.RecordsBean recordsBean) {
        //处理多布局使用
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                baseViewHolder.setText(R.id.item_top_tv, recordsBean.getDescription())
                        .setText(R.id.item_top_look_num, recordsBean.getViews() + "次浏览");
                new GlideLoader().loadImage(baseViewHolder.getView(R.id.item_top_im), recordsBean.getBannerPath());
                baseViewHolder.getView(R.id.item_top_ll).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (CommonUtils.singleOnClick()) {
                            ARouter.getInstance()
                                    .build(AppConfig.ACTIVITY_URL_ROOM_DETAILS)
                                    .withInt("detailsId", recordsBean.getId())
                                    .withInt("status", 8)
                                    .withString("intentType", "adapter")
                                    //进入动画
                                    .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                    .navigation();
                        }
                    }
                });

                break;
        }
    }
}
