package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;

/**
 * 获取全部城市
 */
public class RentCityContract {

    public interface IRentCityView extends IView {
        //获取全部城市成功
        void getCitiesSuccess(ProvincesModel provincesModel);

        //获取全部城市失败
        void getCitiesFailed(int netCode, String msg);


    }

    public interface IRentCityPresenter extends IPresenter<IView> {

        void getCities(String token);

    }
}
