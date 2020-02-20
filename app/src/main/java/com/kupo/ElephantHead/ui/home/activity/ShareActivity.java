package com.kupo.ElephantHead.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.ui.home.model.AppShareModel;
import com.kupo.ElephantHead.ui.mvp.contract.AppShareContract;
import com.kupo.ElephantHead.ui.mvp.presenter.AppSharePresenter;
import com.kupo.ElephantHead.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分享
 */
@Route(path = AppConfig.ACTIVITY_URL_HOME_SHARE)
public class ShareActivity extends BaseActivity implements AppShareContract.IAppShareInfoView {

    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.share_save_im)
    ImageView shareSaveIm;
    @BindView(R.id.share_share_im)
    ImageView shareShareIm;
    @BindView(R.id.share_content_ll)
    RelativeLayout shareContentLl;
    @BindView(R.id.share_code_tv)
    TextView shareCodeTv;
    @BindView(R.id.code_im)
    ImageView codeIm;
    @BindView(R.id.share_bootom_ll)
    LinearLayout shareBootomLl;
    private AppShareContract.IAppSharePresenter appSharePresenter = null;
    private String extUrl;
    private String downLoadUrl;

    @Override
    protected void init(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onInitPresenters() {
        appSharePresenter = new AppSharePresenter();
        appSharePresenter.attachView(this);
        titleTitleTxt.setText("分享赚钱");
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.GONE);
        appSharePresenter.getAppShareInfoNet(CommonUtils.getUserInfo().getToken());
        codeIm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CommonUtils.toWeChatScan();
                return false;
            }
        });
    }

    @OnClick({R.id.share_save_im, R.id.share_share_im, R.id.title_return_linear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_save_im:
                try {
                    shareBootomLl.setVisibility(View.GONE);
                    viewSaveToImage(this, convertViewToBitmap(shareContentLl));
                    ToastUtils.showShort("图片已经保存到相册");
                    shareBootomLl.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    shareBootomLl.setVisibility(View.VISIBLE);
                    ToastUtils.showShort("保存失败");
                }
                break;
            case R.id.share_share_im:
                if (CommonUtils.singleOnClick()) {
                    Intent textIntent = new Intent(Intent.ACTION_SEND);
                    textIntent.setType("text/plain");
                    textIntent.putExtra(Intent.EXTRA_TEXT, extUrl);
                    startActivity(Intent.createChooser(textIntent, "分享"));
                }
                break;
            case R.id.title_return_linear:
                EventBus.getDefault().post(new BaseResult("fx刷新"));
                finish();
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    public void getAppShareNetSuccess(AppShareModel appShareModel) {
        if (appShareModel.getCode() == 0) {
            byte[] decodedString = Base64.decode(appShareModel.getData().getImageBase64(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            codeIm.setImageBitmap(decodedByte);
            shareCodeTv.setText(appShareModel.getData().getPromoterId());
            extUrl = appShareModel.getData().getUrl();
            downLoadUrl = appShareModel.getData().getAndroid();
        } else if (appShareModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
        } else {
            ToastUtils.showShort(appShareModel.getMessage());
        }
    }


    @Override
    public void getAppShareNetFailed(int netCode, String msg) {

    }


    private Bitmap convertViewToBitmap(View tempView) {
        /**
         * 创建一个bitmap放于画布之上进行绘制 （简直如有神助）
         */
        Bitmap bmp = Bitmap.createBitmap(tempView.getWidth(),
                tempView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        tempView.draw(canvas);
        return bmp;
    }

    private String imagePath = "";

    private void viewSaveToImage(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "DCIM");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
            imagePath = file.getAbsolutePath();
            file.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));

    }

}
