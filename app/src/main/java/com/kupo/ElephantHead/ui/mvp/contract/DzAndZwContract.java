package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.DzItemModel;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.home.model.ZwItemModel;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;

import java.util.Map;

/**
 * @ClassName: LoginContract
 * @Description: 登录
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class DzAndZwContract {

    public interface IDzAndZwView extends IView {
        //请求地主列表成功
        void getDzNetSuccess(DzItemModel dzItemModel);

        //请求地主列表失败
        void getDzNetFailed(int netCode, String msg);

        //请求展位列表成功
        void getZwNetSuccess(ZwItemModel dzItemModel);

        //请求展位列表失败
        void getZwNetFailed(int netCode, String msg);

        //请求城市编码成功
        void getCityCodeSuccess(CityCode cityCode);

        //请求城市编码失败
        void getCityCodeFailed(int netCode, String msg);

        //请求展位头部数据成功
        void getZwHeadInfoSuccess(HomeInfoModel loginModel);

        //请求展位头部数据失败
        void getZwHeadInfoFailed(int netCode, String msg);

        //请求地主头部数据成功
        void getDzHeadInfoSuccess(HomeInfoModel loginModel);

        //请求地主头部数据失败
        void getDzHeadInfoFailed(int netCode, String msg);

        //选择展位免费解锁成功
        void getChooseZwUnLockSuccess(BaseResult baseResult);

        //选择展位免费解锁失败
        void getChooseZwUnLockFailed(int netCode, String msg);


    }

    public interface IDzAndZwPresenter extends IPresenter<IView> {
        /**
         * 请求地主列表
         *
         * @param token
         */
        void getDzListNet(String token, Map<String, Object> map);

        /**
         * 请求城市编码列表
         *
         * @param token
         */
        void getCityCode(String token, Map<String, Object> map);

        /**
         * 请求展位列表
         *
         * @param token
         */
        void getZwListNet(String token, Map<String, Object> map);

        /**
         * 展位头部数据
         *
         * @param body
         */
        void getZwHeadInfo(String body);

        /**
         * 地主头部数据
         *
         * @param body
         */
        void getDzHeadInfo(String body);


        /**
         * 选择展位免费解锁
         *
         * @param token
         */
        void getChooseZwUnLock(String token, int boothId);

    }


}
