package com.kupo.ElephantHead.ui.home.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseDiaLogFragment;
import com.kupo.ElephantHead.utils.CommonUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;


/**
 * @ClassName: AppUpdateFragment
 * @Description: 版本更新窗口
 * @Author:
 * @CreateDate: 2019/8/14 10:05
 * @Version: 1.0
 */
public class AppUpdateFragment extends BaseDiaLogFragment {

    @BindView(R.id.app_update_version_tv)
    TextView appUpdateVersionTv;
    @BindView(R.id.app_update_desc_tv)
    TextView appUpdateDescTv;
    @BindView(R.id.app_update_ok_tv)
    TextView appUpdateOkTv;
    String downloadPth = null;
    boolean forceUpdate;
    private static final String saveFileName = "daxiang.apk";
    @BindView(R.id.app_update_title_tv)
    TextView appUpdateTitleTv;

    private Handler installHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_app_update;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onInitPresenters() {
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        if (bundle != null) {
            //     appUpdateVersionTv.setText("更新版本：" + bundle.getString("appVersion"));
            appUpdateVersionTv.setText("有新版本了");
            appUpdateDescTv.setText(bundle.getString("description"));
            downloadPth = bundle.getString("downloadPth");
            forceUpdate = bundle.getBoolean("forceUpdate");
        }
        if (forceUpdate) {
            getDialog().setCancelable(false);
            getDialog().setCanceledOnTouchOutside(false);
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                    return false;
                }
            });
        } else {
        }
        appUpdateOkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (downloadPth.endsWith(".apk")) {
//                    downFile(downloadPth);
//                } else {
                CommonUtils.openBrowser(mActivity, downloadPth);
//                    ARouter.getInstance()
//                            .build(AppConfig.ACTIVITY_URL_MAIN_WEB_VIEW)
//                            .withString("downLoadUrl", downloadPth)
//                            .withString("title", "下载地址")
//                            //进入动画
//                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
//                            .navigation();
//                }

            }
        });
    }

    private int mCurrentSize = 0;
    private int mUpdateTotalSize = 0;
    private FileOutputStream outputStream;
    private InputStream inputStream;

    private void downFile(final String strUrl) {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    HttpURLConnection urlConnection = null;
                    inputStream = null;
                    outputStream = null;
                    URL url = new URL(strUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestProperty("User-Agent",
                            "PacificHttpClient");
                    if (mCurrentSize > 0) {
                        urlConnection.setRequestProperty("RANGE", "bytes="
                                + mCurrentSize + "-");
                    }
                    mUpdateTotalSize = urlConnection.getContentLength();
                    appUpdateOkTv.setText(mUpdateTotalSize);
                    inputStream = urlConnection.getInputStream();

                    if (inputStream != null) {
                        File file = new File(Environment.getExternalStorageDirectory(), saveFileName);
                        outputStream = new FileOutputStream(file);
                        int count = 0;
                        int in = -1;
                        byte buf[] = new byte[1024];
                        while ((in = inputStream.read(buf)) != -1) {
                            outputStream.write(buf, 0, in);
                            count += in;
                            if (mUpdateTotalSize > 0) {
                                appUpdateOkTv.setText(count + "%");
                            }
                        }
                    }

                    outputStream.flush();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    // 下载完成通知安装
                    updateDown();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void updateDown() {
        installHandler.post(new Runnable() {
            @Override
            public void run() {
                appUpdateOkTv.setText("下载完成并安装");
                installApk();
            }
        });

    }

    private void installApk() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), saveFileName)),
                "application/vnd.android.package-archive");
        mActivity.startActivity(intent);

    }


}



