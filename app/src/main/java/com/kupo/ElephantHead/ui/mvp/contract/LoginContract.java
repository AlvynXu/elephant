package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @ClassName: LoginContract
 * @Description: 登录
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class LoginContract {

    public interface ILoginView extends IView {
        //请求登录成功
        void getLoginNetSuccess(ResponseBody responseBody);
        //请求登录失败
        void getLoginNetFailed(int netCode, String msg);
        //请求登录成功
        void getLoginNetInfoFailed(BaseResult loginModel);

    }


    public interface ILoginPresenter extends IPresenter<IView> {
        /**
         * 登录
         *
         * @param body
         */
        void getLoginNet(RequestBody body);

    }


}
