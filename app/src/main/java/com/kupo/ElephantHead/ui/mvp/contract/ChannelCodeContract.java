package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

import okhttp3.RequestBody;

/**
 * @ClassName: LoginContract
 * @Description: 登录
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class ChannelCodeContract {

    public interface IChannelCodeView extends IView {
        //请求登录成功
        void getChannelCodeNetSuccess(BaseResult baseResult);

        //请求登录失败
        void getChannelCodeNetFailed(int netCode, String msg);

    }

    public interface IChannelCodePresenter extends IPresenter<IView> {
        /**
         * 登录
         *
         * @param body
         */
        void getChannelCodeNet(RequestBody body);


    }


}
