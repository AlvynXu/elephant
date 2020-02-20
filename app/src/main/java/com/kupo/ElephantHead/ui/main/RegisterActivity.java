package com.kupo.ElephantHead.ui.main;


import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.greendao.UserInfo;
import com.kupo.ElephantHead.greendao.UserInfoOperateDao;
import com.kupo.ElephantHead.ui.mvp.contract.RegisterContract;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;
import com.kupo.ElephantHead.ui.mvp.presenter.RegisterPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 注册页面
 */
@Route(path = AppConfig.ACTIVITY_URL_MAIN_REGISTER)
public class RegisterActivity extends BaseActivity implements RegisterContract.IRegisterView {
    @BindView(R.id.user_register_phone)
    EditText userRegisterPhone;
    @BindView(R.id.user_register_sendCode)
    TextView userRegisterSendCode;
    @BindView(R.id.user_register_code)
    EditText userRegisterCode;
    @BindView(R.id.user_register_btn)
    TextView userRegisterBtn;
    @BindView(R.id.user_register_delete)
    ImageView userRegisterDelete;
    @BindView(R.id.user_iv)
    ImageView userIv;
    @BindView(R.id.user_isRead)
    ImageView userIsRead;
    @BindView(R.id.user_explain)
    TextView userExplain;
    @BindView(R.id.user_register_delete_default)
    ImageView userRegisterDeleteDefault;
    private Handler handler = new Handler();
    private int number = 60;
    private String userPhoneStr;
    private RegisterPresenter mRegisterPresenter = null;
    private String userRegisterCodeStr;
    private boolean isFlag = true;

    @Override
    protected void init(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onInitPresenters() {
        //    StatusBarUtil.setTranslucentStatus(this);
        mRegisterPresenter = new RegisterPresenter();
        mRegisterPresenter.attachView(this);
        userRegisterPhone.addTextChangedListener(textWatcher);
        userRegisterCode.addTextChangedListener(btnTextWatcher);

    }

    /**
     * 验证码按钮变化
     */
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            userRegisterSendCode.setEnabled(false);
            userRegisterSendCode.setTextColor(getResources().getColor(R.color.secondText));
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                userRegisterDelete.setVisibility(View.VISIBLE);
                userRegisterDeleteDefault.setVisibility(View.GONE);
            } else {
                userRegisterDelete.setVisibility(View.GONE);
                userRegisterDeleteDefault.setVisibility(View.VISIBLE);
            }
            if (s.length() == 11 && "获取验证码".equals(userRegisterSendCode.getText())) {
                userIv.setImageDrawable(getResources().getDrawable(R.drawable.login_phone));
                userRegisterSendCode.setEnabled(true);
                userRegisterSendCode.setTextColor(getResources().getColor(R.color.yellow));
            } else {
                userIv.setImageDrawable(getResources().getDrawable(R.drawable.login_phone_default));
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                userRegisterSendCode.setEnabled(false);
                userRegisterSendCode.setTextColor(getResources().getColor(R.color.secondText));
            } else if (s.length() == 11 && "获取验证码".equals(userRegisterSendCode.getText())) {
                userRegisterSendCode.setEnabled(true);
                userRegisterSendCode.setTextColor(getResources().getColor(R.color.yellow));
            }
        }
    };

    /**
     * 确定按钮变化
     */
    TextWatcher btnTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            userRegisterBtn.setEnabled(false);
            userRegisterBtn.setBackground(getResources().getDrawable(R.drawable.rectangle_gray));
            userRegisterBtn.setTextColor(getResources().getColor(R.color.white));
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            userPhoneStr = userRegisterPhone.getText().toString().trim();
            if (s.length() == 4 && userPhoneStr.length() == 11) {
                userRegisterBtn.setEnabled(true);
                userRegisterBtn.setBackground(getResources().getDrawable(R.drawable.rectangle_yellow));
                userRegisterBtn.setTextColor(getResources().getColor(R.color.black));
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
            userPhoneStr = userRegisterPhone.getText().toString().trim();
            if (s.length() == 0) {
                userRegisterBtn.setEnabled(false);
                userRegisterBtn.setBackground(getResources().getDrawable(R.drawable.rectangle_gray));
                userRegisterBtn.setTextColor(getResources().getColor(R.color.white));
            } else if (s.length() == 4 && userPhoneStr.length() == 11) {
                userRegisterBtn.setEnabled(true);
                userRegisterBtn.setBackground(getResources().getDrawable(R.drawable.rectangle_yellow));
                userRegisterBtn.setTextColor(getResources().getColor(R.color.black));
            }
        }
    };

    @OnClick({R.id.user_register_sendCode, R.id.user_register_btn, R.id.user_register_delete, R.id.user_explain, R.id.user_isRead})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_register_sendCode:
                userPhoneStr = userRegisterPhone.getText().toString().trim();
                if (!CommonUtils.judPhone(userPhoneStr)) {
                    return;
                }
                handler.postDelayed(runnable, 1000);
                ToastUtils.showShort("获取验证码");
                mRegisterPresenter.getRegisterNet(userPhoneStr);
                break;
            case R.id.user_register_btn:
                if (!isFlag) {
                    ToastUtils.showShort("请阅读相关协议");
                    return;
                }
                userPhoneStr = userRegisterPhone.getText().toString().trim();
                userRegisterCodeStr = userRegisterCode.getText().toString().trim();
                if (!CommonUtils.judCode(userRegisterCodeStr)) {
                    return;
                }
                if (!CommonUtils.judPhone(userPhoneStr)) {
                    return;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("phone", userPhoneStr);
                map.put("password", userRegisterCodeStr);
                RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type,application/json; charset=utf-8"), new JSONObject(map).toString());
                mRegisterPresenter.validRegisterCodeNet(requestBaseUpdateBody);
                break;
            case R.id.user_register_delete:
                userRegisterPhone.setText("");
                break;
            case R.id.user_isRead:
                //默认图标
                if (!isFlag) {
                    //点击时显示选中图标
                    userIsRead.setImageDrawable(getResources().getDrawable(R.drawable.user_read));
                    isFlag = true;
                } else {
                    //再次点击时显示默认图标
                    userIsRead.setImageDrawable(getResources().getDrawable(R.drawable.user_read_default));
                    isFlag = false;
                }
                break;
            case R.id.user_explain:
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MAIN_AGREEMENT)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                break;
        }

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (number > 1) {
                number--;
                userRegisterSendCode.setText("再次发送 （" + number + "）");
                userRegisterSendCode.setEnabled(false);
                userRegisterSendCode.setTextColor(getResources().getColor(R.color.code_send));
                handler.postDelayed(this, 1000);
            } else {
                userRegisterSendCode.setEnabled(true);
                userRegisterSendCode.setText("获取验证码");
                number = 60;
                userRegisterSendCode.setTextColor(getResources().getColor(R.color.yellow));
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_regisiter;
    }

    @Override
    public void getRegisterNetSuccess(BaseResult baseResult) {
        //code:0:成功；1：失败
        if (baseResult.getCode() != 0) {
            ToastUtils.showShort(baseResult.getMessage());
        } else {
            ToastUtils.showShort("验证码发送成功");
        }
    }

    @Override
    public void getRegisterNetFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void validRegisterCodeNetSuccess(LoginModel loginModel) {
        //code:0:成功；1：失败
        if (loginModel.getCode() == 0) {
            UserInfo userInfo = CommonUtils.resetUserInfo(loginModel.getData().getUser());
            userInfo.setToken(loginModel.getData().getToken());
            UserInfoOperateDao.deleteDataBean();
            UserInfoOperateDao.insertUserInfo(userInfo);
            if (CommonUtils.getUserInfo().getPromoterId() != 0 && !CommonUtils.getUserInfo().getToken().isEmpty()) {
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MAIN_MAIN)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                finish();
            } else {
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MAIN_CHANNEL)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                finish();
            }
        } else {
            ToastUtils.showShort(loginModel.getMessage());
        }
    }


    @Override
    public void validRegisterCodeNetFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

}
