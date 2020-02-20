package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.DzItemModel;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.home.model.ZwItemModel;

import java.util.Map;

/**
 * @ClassName: LoginContract
 * @Description: 登录
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class CitySelectContract {

    public interface ICitySelectView extends IView {

        //获取省份/城市/区县/街道成功
        void getLocationSuccess(ProvincesModel dzItemModel, String type);

        //获取省份/城市/区县/街道失败
        void getLocationFailed(int netCode, String msg);


    }


    public interface ICitySelectPresenter extends IPresenter<IView> {

        /**
         * 获取省份/城市/区县/街道
         *
         * @param token
         */
        void getLocationNet(String token, String type, Map<String, Object> map);
    }


}
