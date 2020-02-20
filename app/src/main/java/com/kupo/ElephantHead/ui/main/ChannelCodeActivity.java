package com.kupo.ElephantHead.ui.main;


import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.kupo.ElephantHead.ui.mvp.contract.ChannelCodeContract;
import com.kupo.ElephantHead.ui.mvp.presenter.ChannelCodePresenter;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.utils.StatusBarUtil;
import com.kupo.ElephantHead.widget.VerificationCodeInput;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 邀请码
 */
@Route(path = AppConfig.ACTIVITY_URL_MAIN_CHANNEL)
public class ChannelCodeActivity extends BaseActivity implements ChannelCodeContract.IChannelCodeView {

    @BindView(R.id.user_channel_btn)
    TextView userChannelBtn;
    @BindView(R.id.channel_code)
    VerificationCodeInput channelCode;
    private ChannelCodePresenter mChannelCodePresenter = null;
    String channelCodeStr;

    @Override
    protected void init(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    @Override
    protected void onInitPresenters() {
//        StatusBarUtil.setTranslucentStatus(this);
        mChannelCodePresenter = new ChannelCodePresenter();
        mChannelCodePresenter.attachView(this);
//        channelCode.setOnCompleteListener(new VerificationCodeInput.Listener() {
//            @Override
//            public void onComplete(String content) {
//                LogUtils.e("完成输入：" + content);
//                channelCodeStr = content;
//                userChannelBtn.setEnabled(true);
//                userChannelBtn.setTextColor(getResources().getColor(R.color.black));
//                userChannelBtn.setBackground(getResources().getDrawable(R.drawable.rectangle_yellow));
//            }
//        });
        channelCode.setOnInputEndCallBack(new VerificationCodeInput.inputEndListener() {
            @Override
            public void input(String text) {
                channelCodeStr = text;
                if (text.length() == 6) {
                    userChannelBtn.setEnabled(true);
                    userChannelBtn.setTextColor(getResources().getColor(R.color.black));
                    userChannelBtn.setBackground(getResources().getDrawable(R.drawable.rectangle_yellow));
                }
            }

            @Override
            public void afterTextChanged(String text) {
                channelCodeStr = text;
                if (text.length() != 6) {
                    userChannelBtn.setEnabled(false);
                    userChannelBtn.setTextColor(getResources().getColor(R.color.white));
                    userChannelBtn.setBackground(getResources().getDrawable(R.drawable.rectangle_gray));
                }
            }
        });
        userChannelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommonUtils.judChannelCode(channelCodeStr)) {
                    return;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("userId", CommonUtils.getUserInfo().getId());
                map.put("promoterId", channelCodeStr);
                RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type,application/json; charset=utf-8"), new JSONObject(map).toString());
                mChannelCodePresenter.getChannelCodeNet(requestBaseUpdateBody);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_channel_code;
    }


    @Override
    public void getChannelCodeNetSuccess(BaseResult baseResult) {
        //code:0:成功；1：失败
        if (baseResult.getCode() != 0) {
            ToastUtils.showShort(baseResult.getMessage());
        } else {
            UserInfo userInfo = CommonUtils.getUserInfo();
            userInfo.setPromoterId(1);
            UserInfoOperateDao.deleteDataBean();
            UserInfoOperateDao.insertUserInfo(userInfo);
            ARouter.getInstance()
                    .build(AppConfig.ACTIVITY_URL_MAIN_MAIN)
                    //进入动画
                    .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    .navigation();
            finish();
        }

    }

    @Override
    public void getChannelCodeNetFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

}
