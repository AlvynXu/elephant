package com.kupo.ElephantHead.ui.room.activity;


import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;

import java.io.File;

import butterknife.BindView;


/**
 * 图片处理
 */
@Route(path = AppConfig.ACTIVITY_URL_MINE_PICTURE)
public class PictureActivity extends BaseActivity {

    @BindView(R.id.preImg)
    SubsamplingScaleImageView preImg;
    String imgPath;
    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;


    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.GONE);
        titleTitleTxt.setText("图片预览");
        titleReturnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgPath = getIntent().getStringExtra("imgPath");
        if (!TextUtils.isEmpty(imgPath)) {
            Glide.with(this)
                    .load(imgPath)
                    .downloadOnly(new CustomTarget<File>() {
                        @Override
                        public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                            preImg.setImage(ImageSource.uri(Uri.fromFile(resource)));
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture;
    }


}
