package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.room.model.LeavingMessageModel;
import com.kupo.ElephantHead.ui.room.model.RoomIDetailsModel;
import com.kupo.ElephantHead.ui.room.model.SaveBoothModel;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * 获取展厅详情
 */
public class RoomItemDetailsContract {

    public interface IRoomItemDetailsView extends IView {
        //获取展厅详情成功
        void getRoomItemDetailsSuccess(RoomIDetailsModel roomIDetailsModel);

        //获取展厅详情失败
        void getRoomItemDetailsFailed(int netCode, String msg);

        //商品下架展位成功
        void removeItemSuccess(SaveBoothModel zwItemModel);

        //商品下架展位失败
        void removeItemFailed(int netCode, String msg);

        //举报广告成功
        void reportItemSuccess(BaseResult baseResult);

        //举报广告失败
        void reportItemFailed(int netCode, String msg);

        //商品收藏成功
        void collectionItemSuccess(SaveBoothModel saveBoothModel);

        //商品收藏失败
        void collectionItemFailed(int netCode, String msg);

        //删除项目成功
        void removeProjectSuccess(SaveBoothModel saveBoothModel);

        //删除项目失败
        void removeProjectFailed(int netCode, String msg);

        //分享项目成功
        void shareProjectSuccess(HomeInfoModel homeInfoModel);

        //分享项目失败
        void shareProjectFailed(int netCode, String msg);

        //获取商品留言成功
        void getLeaveMessagePageSuccess(LeavingMessageModel leavingMessageModel);

        //获取商品留言失败
        void getLeaveMessagePageFailed(int netCode, String msg);

        //保存商品留言成功
        void saveLeaveMessageSuccess(LeavingMessageModel leavingMessageModel);

        //保存商品留言失败
        void saveLeaveMessageFailed(int netCode, String msg);

    }

    public interface IRoomItemDetailsPresenter extends IPresenter<IView> {

        void getRoomItemDetails(String token, int itemId);

        void removeItem(String token, RequestBody body);

        void reportItem(String token, int itemId);

        void collectionItem(String token, String footType, RequestBody body);

        void removeProject(String token, RequestBody body);

        void shareProject(String token, long itemId);

        void getLeaveMessagePage(String token, Map<String, Object> map);

        void saveLeaveMessage(String token, RequestBody body);

    }
}
