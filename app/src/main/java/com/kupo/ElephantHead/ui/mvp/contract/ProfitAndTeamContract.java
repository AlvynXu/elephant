package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.home.model.TeamListModel;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;

import java.util.Map;

/**
 * @ClassName: 获取我的团队
 * @Description: 登录
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class ProfitAndTeamContract {

    public interface ITeamView extends IView {

        //请求团队模块头部信息《直推/总人数》成功
        void getUserTeamInfoNetSuccess(HomeInfoModel homeInfoModel);

        //请求团队模块头部信息《直推/总人数》失败
        void getUserTeamInfoNetFailed(int netCode, String msg);

        //请求团队模块列表信息《直推/总人数》成功
        void getUserTeamListNetSuccess(TeamListModel teamListModel);

        //请求团队模块列表信息《直推/总人数》失败
        void getUserTeamListNetFailed(int netCode, String msg);

        //请求收益模块列表信息《直推/总人数》成功
        void getUserProfitListNetSuccess(TeamListModel teamListModel);

        //请求收益模块列表信息《直推/总人数》失败
        void getUserProfitListNetFailed(int netCode, String msg);


        //请求首页用户信息成功
        void getHomeUserInfoNetSuccess(HomeInfoModel loginModel);

        //请求首页用户信息失败
        void getHomeUserInfoNetFailed(int netCode, String msg);

    }


    public interface ITeamPresenter extends IPresenter<IView> {
        /**
         * 首页用户信息
         *
         * @param body
         */
        void getHomeUserInfoNet(String body);

        /**
         * 请求团队模块头部信息《直推/总人数》
         *
         * @param token
         */
        void getUserTeamInfoNet(String token, String type);


        /**
         * 请求团队模块列表信息《直推/总人数》
         *
         * @param token
         */
        void getUserTeamListNet(String token, String type, Map<String, Object> map);

        /**
         * 请求收益模块列表信息《直推/总人数》
         *
         * @param token
         */
        void getUserProfitListNet(String token, String type, Map<String, Object> map);

    }


}
