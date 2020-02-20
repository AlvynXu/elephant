package com.kupo.ElephantHead.holder;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

import static com.blankj.utilcode.util.ConvertUtils.dp2px;

/**
 * @ClassName: GlideRoundTransform
 * @Description: Glide设置圆角图片
 * @Author: ljl
 * @CreateDate: 2019/8/20 17:00
 * @Version: 1.0
 */
public class GlideRoundTransform extends BitmapTransformation {
    private static final String ID = "com.xiaohe.www.lib.tools.glide.GlideRoundTransform";

    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private int radius;

    public GlideRoundTransform(int radius) {

        this.radius = dp2px(radius);

    }

    @Override

    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {

        int width = toTransform.getWidth();

        int height = toTransform.getHeight();

        Bitmap bitmap = pool.get(width, height, Bitmap.Config.ARGB_8888);

        bitmap.setHasAlpha(true);

        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();

        paint.setAntiAlias(true);

        paint.setShader(new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));

        canvas.drawRoundRect(new RectF(0, 0, width, height), radius, radius, paint);

        return bitmap;

    }

    @Override

    public boolean equals(Object obj) {

        if (obj instanceof GlideRoundTransform) {

            GlideRoundTransform other = (GlideRoundTransform) obj;

            return radius == other.radius;

        }

        return false;

    }

    @Override

    public int hashCode() {

        return Util.hashCode(ID.hashCode(), Util.hashCode(radius));

    }

    @Override

    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        messageDigest.update(ID_BYTES);

        byte[] radiusData = ByteBuffer.allocate(4).putInt(radius).array();

        messageDigest.update(radiusData);

    }


    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
