package com.kupo.ElephantHead.holder;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kupo.ElephantHead.ElephantHeadApplication;
import com.kupo.ElephantHead.R;
import com.lcw.library.imagepicker.utils.ImageLoader;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * 实现自定义图片加载
 */
public class GlideLoader implements ImageLoader {

    private RequestOptions mOptions = new RequestOptions()
            .format(DecodeFormat.PREFER_RGB_565)
            .placeholder(R.mipmap.icon_image_default)
            .error(R.mipmap.icon_image_default)
            .disallowHardwareConfig()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

    private RequestOptions mPreOptions = new RequestOptions()
            .error(R.mipmap.icon_image_default);

    @Override
    public void loadImage(ImageView imageView, String imagePath) {
        //小图加载
        Glide.with(imageView.getContext()).load(imagePath).apply(mOptions).into(imageView);
    }

    @Override
    public void loadPreImage(ImageView imageView, String imagePath) {
        //大图加载
        Glide.with(imageView.getContext()).load(imagePath).apply(mPreOptions).into(imageView);
    }

    @Override
    public void clearMemoryCache() {
        //清理缓存
        Glide.get(ElephantHeadApplication.getContext()).clearMemory();
    }


}
