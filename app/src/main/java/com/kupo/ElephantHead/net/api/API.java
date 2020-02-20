package com.kupo.ElephantHead.net.api;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by G400 on 2019/8/3.
 * 功能：API接口类
 * 作者：
 */
public interface API {

    /**
     * 登录
     *
     * @param body
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/users/login")
    Observable<ResponseBody> getLoginNet(@Body RequestBody body);


    /**
     * 获取验证码
     *
     * @param phone
     * @return
     */
    @GET("api/users/phoneLogin/getLoginRandomCode")
    Observable<ResponseBody> getRegisterCodeNet(@Query("phone") String phone);

    /**
     * 验证验证码并登录
     *
     * @param body
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/users/phoneLogin")
    Observable<ResponseBody> validRegisterCodeNet(@Body RequestBody body);


    /**
     * 设置密码
     *
     * @param
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/users/setPassword")
    Observable<ResponseBody> setUserPwdNet(@Query("phone") String phone, @Query("password") String password, @Query("userId") String userId);

    /**
     * 录入邀请码
     *
     * @param body
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/users/phoneLogin/setPromoter")
    Observable<ResponseBody> setUserPromoterNet(@Body RequestBody body);


    /**
     * 获取首页展位信息
     *
     * @param
     * @return
     */
    @GET("api/booth/getBootCountMessage")
    Observable<ResponseBody> getHomeZwInfoNet(@Header("token") String token);

    /**
     * 获取首页地主信息
     *
     * @param
     * @return
     */
    @GET("api/street/getCountMessage")
    Observable<ResponseBody> getHomeDzInfoNet(@Header("token") String token);


    /**
     * 获取首页用户信息
     *
     * @param
     * @return
     */
    @GET("api/users/getMessage")
    Observable<ResponseBody> getHomeUserInfoNet(@Header("token") String token);

    /**
     * 获取首页头条
     *
     * @param
     * @return
     */
    @GET("api/headlines/pageList")
    Observable<ResponseBody> getHomeTopListNet(@Header("token") String token, @QueryMap Map<String, Object> map);

    /**
     * 获取首页banner
     *
     * @param
     * @return
     */
    @GET("api/mallBanner/getBanner")
    Observable<ResponseBody> getHomeBannerListNet(@Header("token") String token, @Query("areaCode") String areaCode);

    /**
     * 获取消息模块列表/以及将消息置为已读
     *
     * @param
     * @return
     */
    @GET("api/message/{type}")
    Observable<ResponseBody> getMessageListNet(@Header("token") String token, @Path("type") String type, @QueryMap Map<String, Object> map);


    /**
     * 获取我的团队页面头部信息
     *
     * @param
     * @return
     */
    @GET("api/users/{type}")
    Observable<ResponseBody> getUserTeamInfoNet(@Header("token") String token, @Path("type") String teamType);

    /**
     * 获取我的团队页面分页列表信息
     *
     * @param
     * @return
     */
    @GET("api/users/{type}")
    Observable<ResponseBody> getUserTeamListNet(@Header("token") String token, @Path("type") String teamType, @QueryMap Map<String, Object> map);

    /**
     * 获取地主交易列表数据信息
     *
     * @param
     * @return
     */
    @GET("api/street/getStreetPage")
    Observable<ResponseBody> getQdzInfoListNet(@Header("token") String token, @QueryMap Map<String, Object> map);

    /**
     * 获取根据城市获取编码数据信息
     *
     * @param
     * @return
     */
    @GET("api/street/getCityCode")
    Observable<ResponseBody> getCityCode(@Header("token") String token, @QueryMap Map<String, Object> map);

    /**
     * 获取地主列表条目订单数据信息
     *
     * @param
     * @return
     */
    @GET("api/{payType}/{type}")
    Observable<ResponseBody> getQdzItemOrderInfoNet(@Header("token") String token, @Path("type") String type, @Path("payType") String payType, @QueryMap Map<String, Object> map);

    /**
     * 获取展位交易列表数据信息
     *
     * @param
     * @return
     */
    @GET("api/booth/getBoothPage")
    Observable<ResponseBody> getZwInfoListNet(@Header("token") String token, @QueryMap Map<String, Object> map);

    /**
     * 获取省份/城市/区县/街道
     *
     * @param
     * @return
     */
    @GET("api/areas/{type}")
    Observable<ResponseBody> getLocationNet(@Header("token") String token, @Path("type") String type, @QueryMap Map<String, Object> map);


    /**
     * 意见反馈
     *
     * @param body
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/feedback/saveFeedBack")
    Observable<ResponseBody> saveFeedBack(@Header("token") String token, @Body RequestBody body);


    /**
     * 获取支付成功后的信息buySuccessActivity
     *
     * @param
     * @return
     */
    @GET("api/{centerType}/{footerType}")
    Observable<ResponseBody> getPaySuccessInfo(@Header("token") String token, @Path("centerType") String centerType, @Path("footerType") String footerType, @QueryMap Map<String, Object> map);


    /**
     * 获取当前登录用户信息
     *
     * @param
     * @return
     */
    @GET("api/users/getCurrentUserInfo")
    Observable<ResponseBody> getCurrentUserInfo(@Header("token") String token);


    /**
     * 检测app更新
     * type:1:ios;0：Android
     *
     * @param
     * @return
     */
    @GET("api/appVersion/getAppVersion")
    Observable<ResponseBody> getAppUpdateInfo(@Header("token") String token, @Query("type") int type);


    /**
     * app分享信息
     *
     * @param
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/users/getAppShare")
    Observable<ResponseBody> getAppShare(@Header("token") String token);


    /**
     * 获取当前登录用已经购买的展位或者地主
     *
     * @param
     * @return
     */
    @GET("api/{centerType}/{footType}")
    Observable<ResponseBody> getMinePayDzOrZwData(@Header("token") String token, @Path("centerType") String centerType, @Path("footType") String footType, @QueryMap Map<String, Object> map);

    /**
     * 提现transfer/充值buy
     *
     * @param
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/wxPay/{footType}")
    Observable<ResponseBody> postTransfer(@Header("token") String token, @Path("footType") String footType, @Query("amount") BigDecimal amount);


    /**
     * 提现transfer
     *
     * @param
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/wxPay/{footType}")
    Observable<ResponseBody> postTransferInt(@Header("token") String token, @Path("footType") String footType, @Query("amount") int amount);

    /**
     * 获取提现设置最大值最小值及费率
     *
     * @param
     * @return
     */
    @GET("api/balancePay/getRecharge")
    Observable<ResponseBody> getRechargeInfo(@Header("token") String token);


    /**
     * 获取展厅页面的类目
     *
     * @param
     * @return
     */
    @GET("api/mallItemCategory/getList")
    Observable<ResponseBody> getCategoryList(@Header("token") String token);

    /**
     * 获取展厅列表
     *
     * @param
     * @return
     */
    @GET("api/mallItemInfo/pageList")
    Observable<ResponseBody> getRoomItemPageList(@Header("token") String token, @QueryMap Map<String, Object> map);

    /**
     * 获取展厅详情
     *
     * @param
     * @return
     */
    @GET("api/mallItemInfo/getDetail")
    Observable<ResponseBody> getRoomDetail(@Header("token") String token, @Query("itemId") int itemId);

    /**
     * 获取我的展位和地主数量
     *
     * @param
     * @return
     */
    @GET("api/users/getMyBaseInfo")
    Observable<ResponseBody> getMyBaseInfo(@Header("token") String token);

    /**
     * 获取我的广告
     *
     * @param
     * @return
     */
    @GET("api/mallItemInfo/getMyItemPageList")
    Observable<ResponseBody> getMyItemPageList(@Header("token") String token, @QueryMap Map<String, Object> map);

    /**
     * 保存商品信息
     *
     * @param token
     * @param part
     * @return
     */
    @Multipart
    @POST("api/mallItemInfo/saveInfo")
    Observable<ResponseBody> saveShopInfo(@Header("token") String token, @QueryMap Map<String, Object> map, @Part MultipartBody.Part part);


    /**
     * 保存商品详情信息
     *
     * @param token
     * @param part
     * @return
     */
    @Multipart
    @POST("api/mallItemInfo/saveInfoDetail")
    Observable<ResponseBody> saveInfoDetail(@Header("token") String token, @QueryMap Map<String, Object> map, @Part MultipartBody.Part part);


    /**
     * 获取可以使用的展位
     *
     * @param
     * @return
     */
    @GET("api/itemBoot/getCanUseBooth")
    Observable<ResponseBody> getCanUseBooth(@Header("token") String token, @QueryMap Map<String, Object> map);


    /**
     * 商品挂上展位
     *
     * @param
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/itemBoot/saveItemBooth")
    Observable<ResponseBody> saveItemBooth(@Header("token") String token, @Body RequestBody body);

    /**
     * 商品下架展位
     *
     * @param
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/itemBoot/removeItem")
    Observable<ResponseBody> removeItem(@Header("token") String token, @Body RequestBody body);

    /**
     * 选择展位免费解锁
     *
     * @param
     * @return
     */
    @GET("api/booth/choose")
    Observable<ResponseBody> getChooseZwUnLock(@Header("token") String token, @Query("boothId") int boothId);

    /**
     * 获取带解锁展位数量
     *
     * @param
     * @return
     */
    @GET("api/users/getChooseCount")
    Observable<ResponseBody> getChooseCount(@Header("token") String token);

    /**
     * 举报单个广告
     *
     * @param
     * @return
     */
    @GET("api/mallItemInfo/report")
    Observable<ResponseBody> getReportItem(@Header("token") String token, @Query("itemId") int itemId);


    /**
     * 取消支付订单
     *
     * @param
     * @return
     */
    @GET("api/payLog/cancelPay")
    Observable<ResponseBody> getCancelPay(@Header("token") String token, @QueryMap Map<String, Object> map);


    /**
     * 收藏广告/取消广告
     *
     * @param
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/itemCollection/{footType}")
    Observable<ResponseBody> collection(@Header("token") String token, @Path("footType") String footType, @Body RequestBody body);


    /**
     * 获取收藏列表
     *
     * @param
     * @return
     */
    @GET("api/mallItemInfo/getCollectionPageList")
    Observable<ResponseBody> getCollectionPageList(@Header("token") String token, @QueryMap Map<String, Object> map);


    /**
     * 获取租赁信息
     *
     * @param
     * @return
     */
    @GET("api/market/getRent")
    Observable<ResponseBody> getRent(@Header("token") String token, @QueryMap Map<String, Object> map);

    /**
     * 获取我的租赁信息
     *
     * @param
     * @return
     */
    @GET("api/market/getMyRent")
    Observable<ResponseBody> getMyRent(@Header("token") String token, @QueryMap Map<String, Object> map);

    /**
     * 获取可以出租的街道
     *
     * @param
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/market/getCanRentStreet")
    Observable<ResponseBody> getCanRentStreet(@Header("token") String token, @Body RequestBody body);


    /**
     * 租给ta
     *
     * @param
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/market/rentToOther")
    Observable<ResponseBody> postRentToOther(@Header("token") String token, @Body RequestBody body);


    /**
     * 获取全部城市
     *
     * @param
     * @return
     */
    @GET("api/areas/getCities")
    Observable<ResponseBody> getCities(@Header("token") String token);


    /**
     * 获取注册协议
     *
     * @param
     * @return
     */
    @GET("api/users/phoneLogin/getRegisterInfos")
    Observable<ResponseBody> getRegisterInfos();


    /**
     * 删除项目
     *
     * @param
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/mallItemInfo/remove")
    Observable<ResponseBody> removeProject(@Header("token") String token, @Body RequestBody body);

    /**
     * 求组
     *
     * @param
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/wxPay/seekRent")
    Observable<ResponseBody> postSeekRent(@Header("token") String token, @Body RequestBody body);


    /**
     * 获取项目详情的分享链接
     *
     * @param
     * @return
     */
    @GET("api/mallItemInfo/getShareUrl")
    Observable<ResponseBody> getShareUrl(@Header("token") String token, @Query("itemId") long itemId);

    /**
     * 获取商品留言
     *
     * @param
     * @return
     */
    @GET("api/mallItemInfo/getLeaveMessagePage")
    Observable<ResponseBody> getLeaveMessagePage(@Header("token") String token, @QueryMap Map<String, Object> map);


    /**
     * 保存商品留言
     *
     * @param
     * @return
     */
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("api/mallItemInfo/saveLeaveMessage")
    Observable<ResponseBody> saveLeaveMessage(@Header("token") String token, @Body RequestBody body);

}
