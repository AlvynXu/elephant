package com.kupo.ElephantHead.ui.main;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.greendao.UserInfo;
import com.kupo.ElephantHead.greendao.UserInfoOperateDao;
import com.kupo.ElephantHead.ui.mvp.contract.LoginContract;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;
import com.kupo.ElephantHead.ui.mvp.presenter.LoginPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.utils.JsonUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


/**
 * @ClassName: LoginActivity
 * @Description: 登录页面
 * @Author:
 * @CreateDate: 2019/8/14 10:05
 * @Version: 1.0
 */
@Route(path = AppConfig.ACTIVITY_URL_MAIN_LOGIN)
public class LoginActivity extends BaseActivity implements LoginContract.ILoginView {
    @BindView(R.id.user_phone)
    EditText userPhone;
    @BindView(R.id.user_pwd)
    EditText userPwd;
    @BindView(R.id.user_login_btn)
    TextView userLoginBtn;

    private LoginPresenter mLoginPresenter = null;


    @Override
    protected void init(Bundle savedInstanceState) {
    }

    @OnClick({R.id.user_toCode, R.id.user_login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_toCode:
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MAIN_REGISTER)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                break;
            case R.id.user_login_btn:
                String userPhoneStr = userPhone.getText().toString().trim();
                String userCodeStr = userPwd.getText().toString().trim();
                if (!CommonUtils.judPhone(userPhoneStr)) {
                    return;
                }
                if (userCodeStr.isEmpty()) {
                    ToastUtils.showShort("密码不能为空");
                    return;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("phone", userPhoneStr);
                map.put("password", userCodeStr);
                RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type,application/json; charset=utf-8"), new JSONObject(map).toString());
                mLoginPresenter.getLoginNet(requestBaseUpdateBody);
                break;
        }

    }

    @Override
    protected void onInitPresenters() {
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.attachView(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void getLoginNetSuccess(ResponseBody responseBody) {
        LoginModel loginModel = new LoginModel();
        try {
            loginModel = JsonUtil.JsonLoginModel(responseBody.string());
            //  code:0:成功；1：失败
            if (loginModel.getCode() != 0) {
                ToastUtils.showShort(loginModel.getMessage());
            } else {
                UserInfo userInfo = AppConfig.getSqlUserBean(loginModel.getData().getUser());
                userInfo.setToken(loginModel.getData().getToken());
                UserInfoOperateDao.insertUserInfo(userInfo);
//                LogUtils.e(userInfo);
                //当前用户没有填写邀请码;根据PromoterId==0判断
                if (loginModel.getData().getUser().getPromoterId() == 0) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MAIN_CHANNEL)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                    finish();
                } else {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MAIN_MAIN)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                    finish();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void getLoginNetFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void getLoginNetInfoFailed(BaseResult loginModel) {
        ToastUtils.showShort(loginModel.getMessage());
    }

}



