package com.kupo.ElephantHead.net.request;


import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.net.api.API;
import com.kupo.ElephantHead.net.api.base.ApiService;
import com.kupo.ElephantHead.net.request.base.BaseRequest;
import com.kupo.ElephantHead.net.request.base.CallBack;
import com.kupo.ElephantHead.ui.home.model.AppShareModel;
import com.kupo.ElephantHead.ui.home.model.AppUpdateModel;
import com.kupo.ElephantHead.ui.home.model.BannerModel;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.CurrentUserModel;
import com.kupo.ElephantHead.ui.home.model.DzItemModel;
import com.kupo.ElephantHead.ui.home.model.DzLockModel;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.home.model.MessageModel;
import com.kupo.ElephantHead.ui.home.model.MinePayDzOrZwModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.ui.home.model.TeamListModel;
import com.kupo.ElephantHead.ui.home.model.WalletModel;
import com.kupo.ElephantHead.ui.home.model.ZwItemModel;
import com.kupo.ElephantHead.ui.main.model.AgreementModel;
import com.kupo.ElephantHead.ui.mvp.model.BuySuccessModel;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;
import com.kupo.ElephantHead.ui.room.model.LeavingMessageModel;
import com.kupo.ElephantHead.ui.room.model.ReleaseModel;
import com.kupo.ElephantHead.ui.room.model.RoomIDetailsModel;
import com.kupo.ElephantHead.ui.room.model.SaveBoothModel;
import com.kupo.ElephantHead.ui.transaction.model.CrowdModel;
import com.kupo.ElephantHead.ui.transaction.model.MyCrowdModel;
import com.kupo.ElephantHead.ui.transaction.model.RentStreetModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.PartMap;


/**
 * Created by G400 on 2019/8/4.
 * 功能：对应LoginPresenter的请求集合
 * 作者：
 */
public class LoginRequest extends BaseRequest {

    /**
     * 登录
     *
     * @param body
     * @param callBack
     */
    public static void getLoginNet(RequestBody body, CallBack<LoginModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getLoginNet(body), callBack);
    }


    /**
     * 获取验证码
     *
     * @param body
     * @param callBack
     */
    public static void getRegisterCodeNet(String body, CallBack<BaseResult> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getRegisterCodeNet(body), callBack);
    }


    /**
     * 验证验证码并登录
     *
     * @param body
     * @param callBack
     */
    public static void validRegisterCodeNet(RequestBody body, CallBack<LoginModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.validRegisterCodeNet(body), callBack);
    }

    /**
     * 设置用户密码
     *
     * @param
     * @param callBack
     */
    public static void setUserPwdNet(String phone, String pwd, String userId, CallBack<LoginModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.setUserPwdNet(phone, pwd, userId), callBack);
    }

    /**
     * 录入邀请码
     *
     * @param body
     * @param callBack
     */
    public static void setUserPromoterNet(RequestBody body, CallBack<BaseResult> callBack) {
        API api = ApiService.getInstance().create();
        build(api.setUserPromoterNet(body), callBack);
    }

    /**
     * 获取首页地主
     *
     * @param token
     * @param callBack
     */
    public static void getHomeDzInfoNet(String token, CallBack<HomeInfoModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getHomeDzInfoNet(token), callBack);
    }

    /**
     * 获取首页展位
     *
     * @param token
     * @param callBack
     */
    public static void getHomeZwInfoNet(String token, CallBack<HomeInfoModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getHomeZwInfoNet(token), callBack);
    }

    /**
     * 获取首页用户信息
     *
     * @param token
     * @param callBack
     */
    public static void getHomeUserInfoNet(String token, CallBack<HomeInfoModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getHomeUserInfoNet(token), callBack);
    }

    /**
     * 获取首页banner信息
     *
     * @param token
     * @param areaCode
     * @param callBack
     */
    public static void getHomeBannerListNet(String token, String areaCode, CallBack<BannerModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getHomeBannerListNet(token, areaCode), callBack);
    }

    /**
     * 获取首页下面项目头条列表
     *
     * @param token
     * @param callBack
     */
    public static void getHomeTopListNet(String token, Map<String, Object> map, CallBack<HomeTopModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getHomeTopListNet(token, map), callBack);
    }

    /**
     * 获取信息列表
     *
     * @param token
     * @param callBack
     */
    public static void getMessageListNet(String token, String type, Map<String, Object> map, CallBack<MessageModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getMessageListNet(token, type, map), callBack);
    }

    /**
     * 将消息置为已读
     *
     * @param token
     * @param callBack
     */
    public static void setMessageIsReadNet(String token, String type, Map<String, Object> map, CallBack<MessageModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getMessageListNet(token, type, map), callBack);
    }

    /**
     * 获取我的团队页面头部信息
     *
     * @param teamType
     * @param token
     * @param callBack
     */
    public static void getUserTeamInfoNet(String token, String teamType, CallBack<HomeInfoModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getUserTeamInfoNet(token, teamType), callBack);
    }


    /**
     * 获取我的团队页面分页列表信息
     *
     * @param token
     * @param callBack
     */
    public static void getUserTeamListNet(String token, String teamType, Map<String, Object> map, CallBack<TeamListModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getUserTeamListNet(token, teamType, map), callBack);
    }

    /**
     * 获取我的收益页面分页列表信息
     *
     * @param token
     * @param callBack
     */
    public static void getUserProfitListNet(String token, String teamType, Map<String, Object> map, CallBack<TeamListModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getUserTeamListNet(token, teamType, map), callBack);
    }


    /**
     * 获取地主列表信息
     *
     * @param token
     * @param callBack
     */
    public static void getDzListNet(String token, Map<String, Object> map, CallBack<DzItemModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getQdzInfoListNet(token, map), callBack);
    }

    /**
     * 获取城市获取编码信息
     *
     * @param token
     * @param callBack
     */
    public static void getCityCode(String token, Map<String, Object> map, CallBack<CityCode> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getCityCode(token, map), callBack);
    }

    /**
     * 获取展位列表信息
     *
     * @param token
     * @param callBack
     */
    public static void getZwListNet(String token, Map<String, Object> map, CallBack<ZwItemModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getZwInfoListNet(token, map), callBack);
    }

    /**
     * 获取省份/城市/区县/街道
     *
     * @param token
     * @param callBack
     */
    public static void getLocationNet(String token, String type, Map<String, Object> map, CallBack<ProvincesModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getLocationNet(token, type, map), callBack);
    }

    /**
     * 获取订单信息
     *
     * @param token
     * @param callBack
     */
    public static void getQdzItemOrderInfoNet(String token, String type, String payType, Map<String, Object> map, CallBack<PayInfoModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getQdzItemOrderInfoNet(token, type, payType, map), callBack);
    }

    /**
     * 意见反馈
     *
     * @param token
     * @param callBack
     */
    public static void saveFeedBack(String token, RequestBody body, CallBack<BaseResult> callBack) {
        API api = ApiService.getInstance().create();
        build(api.saveFeedBack(token, body), callBack);
    }

    /**
     * 获取支付成功后信息
     *
     * @param token
     * @param callBack
     */
    public static void getPaySuccessInfo(String token, String centerType, String footerType, Map<String, Object> map, CallBack<BuySuccessModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getPaySuccessInfo(token, centerType, footerType, map), callBack);
    }

    /**
     * 获取当前登录用户信息
     *
     * @param token
     * @param callBack
     */
    public static void getCurrentUserInfo(String token, CallBack<CurrentUserModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getCurrentUserInfo(token), callBack);
    }

    /**
     * 检测app更新
     *
     * @param token
     * @param callBack
     */
    public static void getAppUpdateInfo(String token, int type, CallBack<AppUpdateModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getAppUpdateInfo(token, type), callBack);
    }

    /**
     * app分享信息
     *
     * @param token
     * @param callBack
     */
    public static void getAppShare(String token, CallBack<AppShareModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getAppShare(token), callBack);
    }

    /**
     * 获取当前登录用已经购买的展位或者地主
     *
     * @param token
     * @param callBack
     */
    public static void getMinePayDzOrZwData(String token, String centerType, String footType, Map<String, Object> map, CallBack<MinePayDzOrZwModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getMinePayDzOrZwData(token, centerType, footType, map), callBack);
    }

    /**
     * 微信提现
     *
     * @param token
     * @param amount
     * @param footType
     * @param callBack
     */
    public static void postTransfer(String token, String footType, BigDecimal amount, CallBack<PayInfoModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.postTransfer(token, footType, amount), callBack);
    }

    /**
     * 微信充值
     *
     * @param token
     * @param amount
     * @param footType
     * @param callBack
     */
    public static void postCashAdvance(String token, String footType, int amount, CallBack<WalletModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.postTransferInt(token, footType, amount), callBack);
    }

    /**
     * 获取提现设置最大值最小值及费率
     *
     * @param token
     * @param callBack
     */
    public static void getRechargeInfo(String token, CallBack<HomeInfoModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getRechargeInfo(token), callBack);
    }


    /**
     * 获取展厅页面的类目
     *
     * @param token
     * @param callBack
     */
    public static void getCategoryList(String token, CallBack<CategoryModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getCategoryList(token), callBack);
    }

    /**
     * 获取展厅列表
     *
     * @param token
     * @param callBack
     */
    public static void getRoomItemPageList(String token, Map<String, Object> map, CallBack<HomeTopModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getRoomItemPageList(token, map), callBack);
    }

    /**
     * 获取展厅详情
     *
     * @param token
     * @param itemId
     * @param callBack
     */
    public static void getRoomDetail(String token, int itemId, CallBack<RoomIDetailsModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getRoomDetail(token, itemId), callBack);
    }

    /**
     * 获取我的展位和地主数量
     *
     * @param token
     * @param callBack
     */
    public static void getMyBaseInfo(String token, CallBack<HomeInfoModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getMyBaseInfo(token), callBack);
    }

    /**
     * 获取我的广告
     *
     * @param token
     * @param callBack
     */
    public static void getMyItemPageList(String token, Map<String, Object> map, CallBack<HomeTopModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getMyItemPageList(token, map), callBack);
    }


    /**
     * 保存商品
     *
     * @param token
     * @param part
     * @param callBack
     */
    public static void saveShopInfo(String token, Map<String, Object> map, MultipartBody.Part part, CallBack<ReleaseModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.saveShopInfo(token, map, part), callBack);
    }

    /**
     * 保存商品详情
     *
     * @param token
     * @param part
     * @param callBack
     */
    public static void saveInfoDetail(String token, Map<String, Object> map, MultipartBody.Part part, CallBack<ReleaseModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.saveInfoDetail(token, map, part), callBack);
    }


    /**
     * 获取可以使用的展位
     *
     * @param token
     * @param callBack
     */
    public static void getCanUseBooth(String token, Map<String, Object> map, CallBack<MinePayDzOrZwModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getCanUseBooth(token, map), callBack);
    }

    /**
     * 商品挂上展位
     *
     * @param token
     * @param callBack
     */
    public static void saveItemBooth(String token, RequestBody body, CallBack<SaveBoothModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.saveItemBooth(token, body), callBack);
    }

    /**
     * 商品下架展位
     *
     * @param token
     * @param callBack
     */
    public static void removeItem(String token, RequestBody body, CallBack<SaveBoothModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.removeItem(token, body), callBack);
    }

    /**
     * 选择展位免费解锁
     *
     * @param token
     * @param callBack
     */
    public static void getChooseZwUnLock(String token, int boothId, CallBack<BaseResult> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getChooseZwUnLock(token, boothId), callBack);
    }

    /**
     * 获取带解锁展位数量
     *
     * @param token
     * @param callBack
     */
    public static void getChooseCount(String token, CallBack<DzLockModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getChooseCount(token), callBack);
    }

    /**
     * 举报单个广告
     *
     * @param token
     * @param callBack
     */
    public static void getReportItem(String token, int itemId, CallBack<BaseResult> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getReportItem(token, itemId), callBack);
    }

    /**
     * 取消支付订单
     *
     * @param token
     * @param callBack
     */
    public static void getCancelPay(String token, Map<String, Object> map, CallBack<PayInfoModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getCancelPay(token, map), callBack);
    }

    /**
     * 收藏广告
     *
     * @param token
     * @param callBack
     */
    public static void collectionItem(String token, String footType, RequestBody body, CallBack<SaveBoothModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.collection(token, footType, body), callBack);
    }

    /**
     * 获取收藏列表
     *
     * @param token
     * @param callBack
     */
    public static void getCollectionPageList(String token, Map<String, Object> map, CallBack<HomeTopModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getCollectionPageList(token, map), callBack);
    }

    /**
     * 获取租赁信息
     *
     * @param token
     * @param callBack
     */
    public static void getRent(String token, Map<String, Object> map, CallBack<CrowdModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getRent(token, map), callBack);
    }

    /**
     * 获取我的租赁信息
     *
     * @param token
     * @param callBack
     */
    public static void getMyRent(String token, Map<String, Object> map, CallBack<MyCrowdModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getMyRent(token, map), callBack);
    }


    /**
     * 获取可以出租的街道
     *
     * @param token
     * @param callBack
     */
    public static void getCanRentStreet(String token, RequestBody body, CallBack<RentStreetModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getCanRentStreet(token, body), callBack);
    }

    /**
     * 租给ta
     *
     * @param token
     * @param callBack
     */
    public static void postRentToOther(String token, RequestBody body, CallBack<RentStreetModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.postRentToOther(token, body), callBack);
    }

    /**
     * 获取全部城市
     *
     * @param token
     * @param callBack
     */
    public static void getCities(String token, CallBack<ProvincesModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getCities(token), callBack);
    }

    /**
     * 获取注册协议
     *
     * @param callBack
     */
    public static void getRegisterInfos(CallBack<AgreementModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getRegisterInfos(), callBack);
    }

    /**
     * 删除项目
     *
     * @param token
     * @param callBack
     */
    public static void removeProject(String token, RequestBody body, CallBack<SaveBoothModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.removeProject(token, body), callBack);
    }

    /**
     * 求组
     *
     * @param token
     * @param callBack
     */
    public static void postSeekRent(String token, RequestBody body, CallBack<PayInfoModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.postSeekRent(token, body), callBack);
    }

    /**
     * 获取项目详情的分享链接
     *
     * @param token
     * @param callBack
     */
    public static void getShareUrl(String token, long itemId, CallBack<HomeInfoModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getShareUrl(token, itemId), callBack);
    }

    /**
     * 获取商品留言
     *
     * @param token
     * @param callBack
     */
    public static void getLeaveMessagePage(String token, Map<String, Object> map, CallBack<LeavingMessageModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.getLeaveMessagePage(token, map), callBack);
    }

    /**
     * 保存商品留言
     *
     * @param token
     * @param callBack
     */
    public static void saveLeaveMessage(String token, RequestBody body, CallBack<LeavingMessageModel> callBack) {
        API api = ApiService.getInstance().create();
        build(api.saveLeaveMessage(token, body), callBack);
    }
}
