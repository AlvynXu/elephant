package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.AppUpdateModel;
import com.kupo.ElephantHead.ui.home.model.BannerModel;
import com.kupo.ElephantHead.ui.home.model.CurrentUserModel;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

import java.util.Map;

/**
 * @ClassName: LoginContract
 * @Description: 登录
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class HomeContract {

    public interface IHomeView extends IView {
        //请求首页展位成功
        void getHomeNetSuccess(HomeInfoModel loginModel);

        //请求首页展位失败
        void getHomeNetFailed(int netCode, String msg);

        //请求首页地主成功
        void getHomeDzNetSuccess(HomeInfoModel loginModel);

        //请求首页地主失败
        void getHomeDzNetFailed(int netCode, String msg);

        //请求首页用户信息成功
        void getHomeUserInfoNetSuccess(HomeInfoModel loginModel);

        //请求首页用户信息失败
        void getHomeUserInfoNetFailed(int netCode, String msg);

        //请求当前登录用户信息成功
        void getCurrentUserInfoNetSuccess(CurrentUserModel currentUserModel);

        //请求当前登录用户信息失败
        void getCurrentUserInfoNetFailed(int netCode, String msg);

        //检测app更新成功
        void getAppUpdateInfoNetSuccess(AppUpdateModel appUpdateModel);

        //检测app更新失败
        void getAppUpdateInfoNetFailed(int netCode, String msg);

        //请求首页下面头条列表成功
        void getHomeTopListNetSuccess(HomeTopModel homeTopModel);

        //请求首页下面头条列表失败
        void getHomeTopListNetFailed(int netCode, String msg);

        //请求首页Banner列表成功
        void getHomeBannerListNetSuccess(BannerModel bannerModel);

        //请求首页Banner列表失败
        void getHomeBannerListNetFailed(int netCode, String msg);
    }


    public interface IHomePresenter extends IPresenter<IView> {
        /**
         * 首页展位
         *
         * @param body
         */
        void getHomeInfoNet(String body);

        /**
         * 首页地主
         *
         * @param body
         */
        void getHomeDzInfoNet(String body);

        /**
         * 首页用户信息
         *
         * @param body
         */
        void getHomeUserInfoNet(String body);

        /**
         * 当前登录用户信息
         *
         * @param body
         */
        void getCurrentUserInfo(String body);

        /**
         * 检测app更新
         *
         * @param body
         */
        void getAppUpdateInfo(String body, int type);

        /**
         * 请求首页下面头条列表
         *
         * @param token
         */
        void getHomeTopList(String token, Map<String, Object> map);

        /**
         * 请求首页Banner列表
         *
         * @param token
         */
        void getHomeBannerListNet(String token, String areaCode);

    }


}
