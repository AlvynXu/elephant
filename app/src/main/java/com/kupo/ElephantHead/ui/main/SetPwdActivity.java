package com.kupo.ElephantHead.ui.main;


import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.greendao.UserInfo;
import com.kupo.ElephantHead.greendao.UserInfoOperateDao;
import com.kupo.ElephantHead.ui.mvp.contract.SetUserPwdContract;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;
import com.kupo.ElephantHead.ui.mvp.presenter.SetPwdPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置密码页
 */
@Route(path = AppConfig.ACTIVITY_URL_MAIN_SETPWD)
public class SetPwdActivity extends BaseActivity implements SetUserPwdContract.ISetUserPwdView {
    @BindView(R.id.user_pwd)
    EditText userPwd;
    @BindView(R.id.user_setPwd_btn)
    TextView userSetPwdBtn;
    @BindView(R.id.pw_show_im)
    ImageView pwShowIm;
    private SetPwdPresenter mSetPwdPresenter = null;
    private String userPwdStr = null;
    boolean isFlag = true;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        mSetPwdPresenter = new SetPwdPresenter();
        mSetPwdPresenter.attachView(this);
        userSetPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPwdStr = userPwd.getText().toString().trim();
                if (CommonUtils.verifyPassword(userPwdStr) == 1) {
                    //处理密码的操作需要
                    ToastUtils.showShort("密码不能为空，必须在6-20位");
                    return;
                } else if (CommonUtils.verifyPassword(userPwdStr) == 2) {
                    ToastUtils.showShort("密码必须是数字，字母");
                    return;
                }
                mSetPwdPresenter.getSetUserPwdNet(CommonUtils.getUserInfo().getPhone(), userPwdStr, String.valueOf(CommonUtils.getUserInfo().getId()));
            }
        });
        pwShowIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFlag) {
                    userPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isFlag = true;
                } else {
                    userPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isFlag = false;
                }
            }
        });
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    pwShowIm.setVisibility(View.VISIBLE);
                } else {
                    pwShowIm.setVisibility(View.GONE);
                }

            }
        };
        userPwd.addTextChangedListener(watcher);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_pwd;
    }


    @Override
    public void getSetUserPwdNetSuccess(LoginModel baseResult) {
        //code:0:成功；1：失败
        if (baseResult.getCode() != 0) {
            ToastUtils.showShort(baseResult.getMessage());
        } else {
            UserInfo userInfo = CommonUtils.getUserInfo();
            userInfo.setPassword(baseResult.getData().getUser().getPassword());
            userInfo.setToken(baseResult.getData().getToken());
            UserInfoOperateDao.deleteDataBean();
            UserInfoOperateDao.insertUserInfo(userInfo);
            ARouter.getInstance()
                    .build(AppConfig.ACTIVITY_URL_MAIN_CHANNEL)
                    //进入动画
                    .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    .navigation();
            finish();
        }

    }


    @Override
    public void getSetUserPwdFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

}
