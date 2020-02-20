package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.MessageModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;

import java.util.Map;

/**
 * @ClassName: MessageContract
 * @Description: 获取消息
 * @Author:
 * @CreateDate: 2019/8/16 13:41
 * @Version: 1.0
 */
public class MessageContract {

    public interface IMessageView extends IView {

        //请求消息列表成功
        void getMessageNetSuccess(MessageModel messageModel);

        //请求消息列表失败
        void getMessageNetFailed(int netCode, String msg);

        //将消息置为已读成功
        void setMessageIsReadNetSuccess(MessageModel messageModel);

        //将消息置为已读失败
        void setMessageIsReadNetFailed(int netCode, String msg);

    }


    public interface IMessagePresenter extends IPresenter<IView> {

        /**
         * 请求消息列表
         *
         * @param token
         */
        void getMessageNet(String token, String type, Map<String, Object> map);

        /**
         * 将消息置为已读
         *
         * @param token
         */
        void setMessageIsReadNet(String token, String type, Map<String, Object> map);

    }


}
