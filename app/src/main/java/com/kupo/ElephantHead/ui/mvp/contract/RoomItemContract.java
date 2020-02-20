package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;

import java.util.Map;

/**
 * 展厅
 */
public class RoomItemContract {

    public interface IRoomItemView extends IView {
        //获取展厅列表成功
        void getRoomItemListSuccess(HomeTopModel homeTopModel);

        //获取展厅列表失败
        void getRoomItemListFailed(int netCode, String msg);

        //请求城市编码成功
        void getCityCodeSuccess(CityCode cityCode);

        //请求城市编码失败
        void getCityCodeFailed(int netCode, String msg);
    }

    public interface IRoomItemPresenter extends IPresenter<IView> {

        void getRoomItemList(String token, Map<String, Object> map);

        /**
         * 请求城市编码列表
         *
         * @param token
         */
        void getCityCode(String token, Map<String, Object> map);
    }
}
