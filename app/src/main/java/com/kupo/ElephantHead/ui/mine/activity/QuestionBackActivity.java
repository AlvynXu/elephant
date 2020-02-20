package com.kupo.ElephantHead.ui.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.ui.mvp.contract.QuestionBackContract;
import com.kupo.ElephantHead.ui.mvp.presenter.QuestionBackPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 问题反馈
 */
@Route(path = AppConfig.ACTIVITY_URL_MINE_QUESTION)
public class QuestionBackActivity extends BaseActivity implements QuestionBackContract.IQuestionBackView {


    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.question_et)
    EditText questionEt;
    @BindView(R.id.question_tv)
    TextView questionTv;
    private QuestionBackContract.IQuestionBackPresenter questionBackPresenter;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        questionBackPresenter = new QuestionBackPresenter();
        questionBackPresenter.attachView(this);
        titleTitleTxt.setText("问题反馈");
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.GONE);
        questionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qt = questionEt.getText().toString().trim();
                if (qt != null && qt != "" && qt.length() >= 10 && qt.length() <= 200) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("content", qt);
                    RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type,application/json; charset=utf-8"), new JSONObject(map).toString());
                    questionBackPresenter.saveFeedBack(CommonUtils.getUserInfo().getToken(), requestBaseUpdateBody);
                } else {
                    ToastUtils.showShort("反馈内容不能为空并且不能小于10大于200个字符");

                }
            }
        });
        titleReturnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_question_back;
    }

    @Override
    public void getQuestionBackNetSuccess(BaseResult baseResult) {
        //  code:0:成功；1：失败
        if (baseResult.getCode() != 0) {
            ToastUtils.showShort(baseResult.getMessage());
        } else {
            ToastUtils.showLong("意见反馈成功");
            finish();
        }
    }

    @Override
    public void getQuestionBackNetFailed(int netCode, String msg) {
        ToastUtils.showShort("意见反馈失败，原因：" + msg);
    }
}
