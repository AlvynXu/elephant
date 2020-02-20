package com.kupo.ElephantHead;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.kupo.ElephantHead.greendao.UserInfo;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

/**
 * Created by G400 on 2019/8/3.
 * 功能：项目配置信息
 * 作者：
 */
public class AppConfig {


    //TODO 项目名
    public static final String PROJECT_NAME = "com.kupo.ElephantHead";

    public static final int PROJECT_SIGN = 168821730;

    /**
     * 是否是测试模式
     */
    public static boolean isDebug = false;

    /**
     * 正式服务器地址
     */
    static String API_ADDRESS_RELEASE = "http://wlgx.hiyanjiao.com/";

    /**
     * 测试服务器地址
     */
    static String API_ADDRESS_DEBUG = "http://wlgx.test.hiyanjiao.com/";
    // static String API_ADDRESS_DEBUG = "http://192.168.5.4:9000/";

    /**
     * 服务器域名
     */
    public static String API_ADDRESS = isDebug ? API_ADDRESS_DEBUG : API_ADDRESS_RELEASE;

    /**
     * http请求错误码,用于OkHttp处理使用
     **/
    public static final int HTTP_ERROR = 1;

    /**
     * http请求错误码,用于Token失效处理使用
     **/
    public static final int HTTP_TOKEN_ERROR = 401;

    /**
     * http请求成功码,用于OkHttp处理使用
     **/
    public static final int HTTP_SUCCESS = 0;


    /**
     * ARouter路由注解的路径标识，在这里方便同意管理
     */
    public static final String ACTIVITY_URL_MAIN_MAIN = "/app/MainActivity";
    public static final String ACTIVITY_URL_MAIN_GUIDE = "/app/GuideActivity";
    public static final String ACTIVITY_URL_MAIN_LOGIN = "/app/LoginActivity";
    public static final String ACTIVITY_URL_MAIN_REGISTER = "/app/RegisterActivity";
    public static final String ACTIVITY_URL_MAIN_SETPWD = "/app/SetPwdActivity";
    public static final String ACTIVITY_URL_MAIN_CHANNEL = "/app/ChannelActivity";
    public static final String ACTIVITY_URL_MAIN_TEAM = "/app/TeamAndProfitActivity";
    public static final String ACTIVITY_URL_MAIN_MESSAGE = "/app/MessageActivity";
    public static final String ACTIVITY_URL_MAIN_AGREEMENT = "/app/AgreementActivity";
    public static final String ACTIVITY_URL_HOME_ZCW = "/app/ZcwActivity";
    public static final String ACTIVITY_URL_HOME_MASK = "/app/MaskActivity";
    public static final String ACTIVITY_URL_HOME_QDZ = "/app/DzAndZwActivity";
    public static final String ACTIVITY_URL_HOME_PAYTYPE = "/app/PayTypeActivity";
    public static final String ACTIVITY_URL_HOME_SHARE = "/app/ShareActivity";
    public static final String ACTIVITY_URL_HOME_RECHARGE = "/app/RechargeActivity";
    public static final String ACTIVITY_URL_HOME_CASHADVANCE = "/app/CashAdvanceActivity";
    public static final String ACTIVITY_URL_HOME_STRATEGY = "/app/StrategyActivity";
    public static final String ACTIVITY_URL_MAIN_WEB_VIEW = "/app/WebViewActivity";
    public static final String ACTIVITY_URL_HOME_PROFIT = "/app/WalletActivity";
    public static final String ACTIVITY_URL_HOME_WELCOME = "/app/WelcomeActivity";
    public static final String ACTIVITY_URL_MINE_QUESTION = "/app/QuestionBackActivity";
    public static final String ACTIVITY_URL_MINE_DZAND_ZW = "/app/MineDzAndZwActivity";
    public static final String ACTIVITY_URL_MINE_KEFU = "/app/KeFuActivity";
    public static final String ACTIVITY_URL_MINE_COLLECT = "/app/MyCollectActivity";
    public static final String ACTIVITY_URL_MINE_ADD = "/app/AddActivity";
    public static final String ACTIVITY_URL_MINE_EDIT = "/app/EditActivity";
    public static final String ACTIVITY_URL_MINE_PROJECT = "/app/MyProjectActivity";
    public static final String ACTIVITY_URL_MINE_PICTURE = "/app/PictureActivity";
    public static final String ACTIVITY_URL_ROOM_DETAILS = "/app/ItemDetailsActivity";
    public static final String ACTIVITY_URL_ROOM_OPERATION = "/app/OperationActivity";
    public static final String ACTIVITY_URL_TRANSACTION_MY_RENT = "/app/MyRentActivity";
    public static final String ACTIVITY_URL_TRANSACTION_RENT_TYPE = "/app/PayRentTypeActivity";


    /**
     * 返回个人实体UserInfo
     *
     * @param userBean
     * @return
     */
    public static UserInfo getSqlUserBean(LoginModel.DataBean.UserBean userBean) {
        UserInfo sqlUserBean = new UserInfo();
        sqlUserBean.setBalance(userBean.getBalance());
        sqlUserBean.setId(userBean.getId());
        sqlUserBean.setCreateTime(userBean.getCreateTime());
        sqlUserBean.setDeleted(userBean.isDeleted());
        sqlUserBean.setIsVip(userBean.isIsVip());
        sqlUserBean.setLevel(userBean.getLevel());
        sqlUserBean.setPassword(userBean.getPassword());
        sqlUserBean.setPhone(userBean.getPhone());
        sqlUserBean.setProfit(userBean.getProfit());
        sqlUserBean.setPromoterId(userBean.getPromoterId());
        sqlUserBean.setRegCode(userBean.getRegCode());
        sqlUserBean.setTeamProfit(userBean.getTeamProfit());
        sqlUserBean.setUpdateTime(userBean.getUpdateTime());
        return sqlUserBean;
    }

    /**
     * 返回个人实体UserInfo
     *
     * @param userBean
     * @return
     */
    public static LoginModel.DataBean.UserBean getUserBean(UserInfo userBean) {
        LoginModel.DataBean.UserBean sqlUserBean = new LoginModel.DataBean.UserBean();
        sqlUserBean.setBalance(userBean.getBalance());
        sqlUserBean.setId(userBean.getId());
        sqlUserBean.setCreateTime(userBean.getCreateTime());
        sqlUserBean.setDeleted(userBean.getDeleted());
        sqlUserBean.setIsVip(userBean.getIsVip());
        sqlUserBean.setLevel(userBean.getLevel());
        sqlUserBean.setPassword(userBean.getPassword());
        sqlUserBean.setPhone(userBean.getPhone());
        sqlUserBean.setProfit(userBean.getProfit());
        sqlUserBean.setPromoterId(userBean.getPromoterId());
        sqlUserBean.setRegCode(userBean.getRegCode());
        sqlUserBean.setTeamProfit(userBean.getTeamProfit());
        sqlUserBean.setUpdateTime(userBean.getUpdateTime());
        return sqlUserBean;
    }
}
