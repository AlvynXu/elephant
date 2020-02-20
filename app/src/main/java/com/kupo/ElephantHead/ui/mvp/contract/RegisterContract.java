package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.main.model.AgreementModel;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

import okhttp3.RequestBody;

/**
 * @ClassName: LoginContract
 * @Description: 注册
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class RegisterContract {

    public interface IRegisterView extends IView {
        //获取验证码成功
        void getRegisterNetSuccess(BaseResult baseResult);

        //获取验证码失败
        void getRegisterNetFailed(int netCode, String msg);

        //验证验证码并登录成功
        void validRegisterCodeNetSuccess(LoginModel loginModel);

        //验证验证码并登录失败
        void validRegisterCodeNetFailed(int netCode, String msg);


    }


    public interface IRegisterPresenter extends IPresenter<IView> {
        /**
         * 获取验证码
         *
         * @param body
         */
        void getRegisterNet(String body);

        void validRegisterCodeNet(RequestBody body);


    }


}
