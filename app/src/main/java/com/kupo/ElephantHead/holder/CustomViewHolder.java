package com.kupo.ElephantHead.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.ui.home.model.BannerModel;
import com.ms.banner.holder.BannerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by G400 on 2019/8/6.
 * 功能：自定义首页banner加载页面
 */
public class CustomViewHolder implements BannerViewHolder<BannerModel.DataBean> {

    @BindView(R.id.iv_banner)
    AppCompatImageView ivBanner;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onBind(Context context, int position, BannerModel.DataBean data) {
        new GlideLoader().loadImage(ivBanner, data.getImageUrl());
    }
}


