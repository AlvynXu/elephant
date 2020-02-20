package com.kupo.ElephantHead.base;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;


import com.kupo.ElephantHead.AppManager;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.utils.StatusBarUtil;
import com.kupo.ElephantHead.widget.CustomProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by G400 on 2019/8/3.
 * 功能：Activity基类
 * 作者：
 */
public abstract class BaseActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    protected CustomProgressDialog pd;
    protected boolean progressShow;
    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题
        AppManager.getAppManager().addActivity(this);  //添加Activity到堆栈
        init(savedInstanceState);
        setContentView(getLayoutId());
        initStateBar();
        onInitPresenters();

    }

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void onInitPresenters();

    protected abstract int getLayoutId();

    /**
     * 使用注解
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    /**
     * 初始化沉浸式
     */
    private void initStateBar() {
        this.setStateBar(true, R.color.white);
    }

    /**
     * 设置状态栏
     *
     * @param isTextColorDeep
     * @param colorAccent
     */
    private void setStateBar(boolean isTextColorDeep, int colorAccent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //设置状态栏颜色和状态栏文字,图标颜色
            StatusBarUtil.setStatusBarMode(this, isTextColorDeep, colorAccent);
        }
    }

    /**
     * finish Activity 提供给xml中"返回"Button的Android:Onclick属性调用
     *
     * @param view
     */
    public void finish(View view) {
        finish();
    }


    public void showLoading() {
        showLoading("");
    }

    /**
     * 提供显示文字的加载框
     *
     * @param s
     */
    public void showLoading(String s) {
        if (!progressShow) {
            progressShow = true;
            if (pd == null) {
                pd = new CustomProgressDialog(this, s);
                pd.setCanceledOnTouchOutside(false);
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        progressShow = false;
                    }
                });
            }
            pd.show();
        }
    }

    /**
     * 取消进度条显示
     */
    public void hideLoading() {
        if (pd != null) {
            pd.dismiss();
        }
        progressShow = false;
    }


    @Override
    protected void onPause() {
        super.onPause();
        //处理操作
    }

    @Override
    protected void onResume() {
        super.onResume();
        //处理操作
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束Activity&从堆栈中移除
        AppManager.getAppManager().removeActivityFromStack(this);
        hideLoading();
    }


    /**
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("当前应用缺少必要权限。请点击\\\"设置\\\"-\\\"权限\\\"-打开所需权限");

        // 拒绝, 退出应用
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
