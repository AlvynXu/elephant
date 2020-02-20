package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @ClassName: LoginContract
 * @Description: 登录
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class QuestionBackContract {

    public interface IQuestionBackView extends IView {

        //意见反馈成功
        void getQuestionBackNetSuccess(BaseResult baseResult);

        //意见反馈失败
        void getQuestionBackNetFailed(int netCode, String msg);

    }


    public interface IQuestionBackPresenter extends IPresenter<IView> {


        /**
         * 意见反馈
         *
         * @param token
         */
        void saveFeedBack(String token, RequestBody body);

    }


}
