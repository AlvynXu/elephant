package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.DzLockModel;
import com.kupo.ElephantHead.ui.home.model.MinePayDzOrZwModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;

import java.util.Map;

/**
 * @ClassName: LoginContract
 * @Description: 登录
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class MinePayDzOrZwContract {

    public interface IMinePayDzOrZwView extends IView {

        //请求获取当前登录用已经购买的展位或者地主成功
        void getMinePayDzOrZwNetSuccess(MinePayDzOrZwModel minePayDzOrZwModel);

        //请求获取当前登录用已经购买的展位或者地主失败
        void getMinePayDzOrZwNetFailed(int netCode, String msg);

        //获取带解锁展位数量成功
        void getChooseCountSuccess(DzLockModel dzLockModel);

        //获取带解锁展位数量失败
        void getChooseCountFailed(int netCode, String msg);

    }


    public interface IMinePayDzOrZwPresenter extends IPresenter<IView> {


        /**
         * 请求获取当前登录用已经购买的展位或者地主
         *
         * @param token
         */
        void getMinePayDzOrZwNet(String token, String centerType, String footType, Map<String, Object> map);


        /**
         * 获取带解锁展位数量
         *
         * @param token
         */
        void getChooseCount(String token);

    }


}
