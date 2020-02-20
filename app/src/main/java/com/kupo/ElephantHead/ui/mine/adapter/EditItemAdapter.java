package com.kupo.ElephantHead.ui.mine.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.holder.GlideLoader;
import com.kupo.ElephantHead.ui.mine.fragment.TextInputFragment;
import com.kupo.ElephantHead.ui.mvp.model.EditItemModel;
import com.kupo.ElephantHead.widget.FullImage;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;
import genealogy.jczb.com.rvlibrary.BaseMultiItemQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;

/**
 * 编辑文章的的段落adapter
 */
public class EditItemAdapter extends BaseMultiItemQuickAdapter<EditItemModel, BaseViewHolder> {
    Activity mActivity;

    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnItemRemoveListener mOnItemRemoveListener;
    public OnItemTextInputListener mOnItemTextInputListener;

    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnItemRemoveListener {
        void onItemRemoveClick(int position);
    }

    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnItemTextInputListener {
        void onItemTextInputClick(int position);

    }

    // 接口回调第二步: 初始化接口的引用
    public void setOnItemTextInputListener(OnItemTextInputListener l) {
        this.mOnItemTextInputListener = l;
    }

    // 接口回调第二步: 初始化接口的引用
    public void setOnItemRemoveListener(OnItemRemoveListener l) {
        this.mOnItemRemoveListener = l;
    }

    public EditItemAdapter(List<EditItemModel> data, Activity mActivity) {
        super(data);
        //添加多布局1文字；2图片；3是视频
        addItemType(1, R.layout.item_edit_txt_rv);
        addItemType(2, R.layout.item_edit_img_rv);
        addItemType(3, R.layout.item_edit_video_rv);
        this.mActivity = mActivity;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, EditItemModel editItemModel) {
        switch (baseViewHolder.getItemViewType()) {
            case 1:
                baseViewHolder.setText(R.id.item_edit_et, editItemModel.getContent());
                baseViewHolder.getView(R.id.item_edit_et).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemTextInputListener != null) {
                            mOnItemTextInputListener.onItemTextInputClick(baseViewHolder.getAdapterPosition() - 1);
                        }
                    }
                });
                break;
            case 2:
                new GlideLoader().loadPreImage(baseViewHolder.getView(R.id.item_edit_im), editItemModel.getPicUrl());
                baseViewHolder.getView(R.id.item_edit_im).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance()
                                .build(AppConfig.ACTIVITY_URL_MINE_PICTURE)
                                .withString("imgPath", editItemModel.getPicUrl())
                                //进入动画
                                .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                .navigation();
                    }
                });
                break;
            case 3:
                JZVideoPlayerStandard jzVideoPlayerStandard = baseViewHolder.getView(R.id.item_edit_video);
                jzVideoPlayerStandard.setUp(editItemModel.getVideoUrl()
                        , JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
                jzVideoPlayerStandard.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(mActivity).load(editItemModel.getVideoUrl()).frame(4000000).centerCrop()
                        .format(DecodeFormat.PREFER_RGB_565).into(jzVideoPlayerStandard.thumbImageView);
                break;
        }
        baseViewHolder.getView(R.id.item_edit_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mActivity)
                        .setTitle("温馨提示")
                        .setMessage("确定要删除当前段落吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (mOnItemRemoveListener != null) {
                                    mOnItemRemoveListener.onItemRemoveClick(baseViewHolder.getAdapterPosition() - 1);
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }
        });
    }

}
