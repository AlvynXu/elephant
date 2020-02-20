package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

import okhttp3.RequestBody;

/**
 * @ClassName: LoginContract
 * @Description: 设置用户密码
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class SetUserPwdContract {

    public interface ISetUserPwdView extends IView {
        //设置用户密码成功
        void getSetUserPwdNetSuccess(LoginModel baseResult);

   
        //设置用户密码失败
        void getSetUserPwdFailed(int netCode, String msg);

    }


    public interface ISetUserPwdPresenter extends IPresenter<IView> {
        /**
         * 设置用户密码
         *
         * @param
         */
        void getSetUserPwdNet(String phone, String pwd, String userId);


    }


}
