package com.kupo.ElephantHead.ui.mvp.contract;

import com.kupo.ElephantHead.base.basepresenter.IPresenter;
import com.kupo.ElephantHead.base.baseview.IView;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.home.model.MinePayDzOrZwModel;
import com.kupo.ElephantHead.ui.home.model.ZwItemModel;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;
import com.kupo.ElephantHead.ui.room.model.SaveBoothModel;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * 获取可以使用的展位
 */
public class OperationContract {

    public interface IOperationView extends IView {
        //获取可以使用的展位列表成功
        void getCanUseBoothSuccess(MinePayDzOrZwModel zwItemModel);

        //获取可以使用的展位列表失败
        void getCanUseBoothFailed(int netCode, String msg);

        //商品挂上展位成功
        void saveItemBoothSuccess(SaveBoothModel zwItemModel);

        //商品挂上展位失败
        void saveItemBoothFailed(int netCode, String msg);


    }

    public interface IOperationPresenter extends IPresenter<IView> {

        void getCanUseBooth(String token, Map<String, Object> map);

        void saveItemBooth(String token, RequestBody body);

    }
}
