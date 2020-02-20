package com.kupo.ElephantHead.ui.room.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.holder.GlideLoader;
import com.kupo.ElephantHead.holder.OnSingleClickListener;
import com.kupo.ElephantHead.ui.room.model.RoomIDetailsModel;

import java.io.File;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;
import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * 展厅详情里面的图文视频详情
 */
public class RoomDetailsAdapter extends BaseMultiItemQuickAdapter<RoomIDetailsModel.DataBean.DetailListBean, BaseViewHolder> {

    private Activity mActivity;

    //type:1:文字;2:图片;3:视频
    public RoomDetailsAdapter(List<RoomIDetailsModel.DataBean.DetailListBean> data, Activity mActivity) {
        super(data);
        addItemType(1, R.layout.item_details_txt_rv);
        addItemType(2, R.layout.item_details_img_rv);
        addItemType(3, R.layout.item_details_video_rv);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RoomIDetailsModel.DataBean.DetailListBean detailListBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 1:
                baseViewHolder.setText(R.id.item_details_txt_tv, detailListBean.getDescription());
                break;
            case 2:
                SubsamplingScaleImageView sslImageView = baseViewHolder.getView(R.id.item_details_ssi_im);
                Glide.with(mActivity)
                        .load(detailListBean.getFilePath())
                        .placeholder(R.drawable.default_banner)
                        .format(DecodeFormat.PREFER_RGB_565)
                        .error(R.mipmap.icon_image_default)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .downloadOnly(new CustomTarget<File>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                            @Override
                            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                                sslImageView.setImage(ImageSource.uri(Uri.fromFile(resource)));
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                sslImageView.setImage(ImageSource.resource(R.drawable.default_banner));
                            }
                        });
//                new GlideLoader().loadPreImage(detailListBean.getFilePath());
                baseViewHolder.getView(R.id.item_details_ssi_im).setOnClickListener(new OnSingleClickListener() {
                    @Override
                    public void onSingleClickListener(View v) {
                        ARouter.getInstance()
                                .build(AppConfig.ACTIVITY_URL_MINE_PICTURE)
                                .withString("imgPath", detailListBean.getFilePath())
                                //进入动画
                                .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                .navigation();
                    }
                });
                break;
            case 3:
                JZVideoPlayerStandard jzVideoPlayerStandard = baseViewHolder.getView(R.id.item_details_jz);
                jzVideoPlayerStandard.setUp(detailListBean.getFilePath()
                        , JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
                //设置图片为全屏
                jzVideoPlayerStandard.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(mActivity).load(detailListBean.getFilePath()).frame(4000000).centerCrop()
                        .format(DecodeFormat.PREFER_RGB_565).into(jzVideoPlayerStandard.thumbImageView);
                break;
        }
    }
}
