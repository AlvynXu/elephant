package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.AppShareModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;

import java.util.Map;

/**
 * @ClassName: AppShareContract
 * @Description: App分享
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class AppShareContract {

    public interface IAppShareInfoView extends IView {

        //请求App分享数据成功
        void getAppShareNetSuccess(AppShareModel appShareModel);

        //请求App分享数据失败
        void getAppShareNetFailed(int netCode, String msg);

    }


    public interface IAppSharePresenter extends IPresenter<IView> {


        /**
         * 请求App分享数据
         *
         * @param token
         */
        void getAppShareInfoNet(String token);

    }


}
