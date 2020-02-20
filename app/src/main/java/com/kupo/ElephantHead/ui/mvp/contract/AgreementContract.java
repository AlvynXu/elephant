package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.main.model.AgreementModel;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

import okhttp3.RequestBody;

/**
 * @ClassName: LoginContract
 * @Description: 用户协议
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class AgreementContract {

    public interface IAgreementView extends IView {

        //获取注册协议成功
        void getRegisterInfosSuccess(AgreementModel agreementModel);

        //获取注册协议失败
        void getRegisterInfosFailed(int netCode, String msg);

    }


    public interface IAgreementPresenter extends IPresenter<IView> {

        void getRegisterInfos();

    }


}
