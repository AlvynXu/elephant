package com.kupo.ElephantHead.utils;


import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.home.model.WalletModel;
import com.kupo.ElephantHead.ui.mvp.model.LoginModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G400 on 2019/8/3.
 * 功能：JSON工具类
 * 作者：
 */
public class JsonUtil {

    public static LoginModel JsonLoginModel(String jsonData) throws JSONException {
        LoginModel loginModel = new LoginModel();
        JSONObject jsonObject = new JSONObject(jsonData);
        loginModel.setCode(jsonObject.getInt("code"));
        loginModel.setFailCode(jsonObject.getInt("failCode"));
        loginModel.setMessage(jsonObject.getString("message"));
        if (!jsonObject.isNull("data")) {
            JSONObject dataObject = jsonObject.getJSONObject("data");
            LoginModel.DataBean dataBean = new LoginModel.DataBean();
            dataBean.setFlag(dataObject.getBoolean("flag"));
            dataBean.setToken(dataObject.getString("token"));
            JSONObject userObject = dataObject.getJSONObject("user");
            LoginModel.DataBean.UserBean userBean = new LoginModel.DataBean.UserBean();
            userBean.setId(userObject.getInt("id"));
            userBean.setBalance(userObject.getDouble("balance"));
            userBean.setCreateTime(userObject.getLong("createTime"));
            userBean.setDeleted(userObject.getBoolean("deleted"));
            userBean.setIsVip(userObject.getBoolean("isVip"));
            userBean.setLevel(userObject.getInt("level"));
            userBean.setPassword(userObject.getString("password"));
            userBean.setPhone(userObject.getString("phone"));
            userBean.setProfit(userObject.getDouble("profit"));
            userBean.setPromoterId(userObject.getInt("promoterId"));
            userBean.setChooseBoothCount(userObject.getInt("chooseBoothCount"));
            userBean.setRegCode(userObject.getString("regCode"));
            userBean.setTeamProfit(userObject.getDouble("teamProfit"));
            userBean.setUpdateTime(userObject.getLong("updateTime"));
            dataBean.setUser(userBean);
            loginModel.setData(dataBean);
        }
        return loginModel;
    }

    public static WalletModel JsonWalletModel(String jsonData) throws JSONException {
        WalletModel walletModel = new WalletModel();
        JSONObject jsonObject = new JSONObject(jsonData);
        walletModel.setCode(jsonObject.getInt("code"));
        walletModel.setFailCode(jsonObject.getInt("failCode"));
        walletModel.setMessage(jsonObject.getString("message"));
        if (!jsonObject.isNull("data")) {
            JSONObject dataObject = jsonObject.getJSONObject("data");
            WalletModel.DataBean dataBean = new WalletModel.DataBean();
            dataBean.setId(dataObject.getInt("id"));
            dataBean.setBalance(dataObject.getDouble("balance"));
            dataBean.setCreateTime(dataObject.getLong("createTime"));
            dataBean.setDeleted(dataObject.getBoolean("deleted"));
            dataBean.setIsVip(dataObject.getBoolean("isVip"));
            dataBean.setLevel(dataObject.getInt("level"));
            dataBean.setPassword(dataObject.getString("password"));
            dataBean.setPhone(dataObject.getString("phone"));
            dataBean.setProfit(dataObject.getDouble("profit"));
            dataBean.setPromoterId(dataObject.getInt("promoterId"));
            dataBean.setChooseBoothCount(dataObject.getInt("chooseBoothCount"));
            dataBean.setRegCode(dataObject.getString("regCode"));
            dataBean.setTeamProfit(dataObject.getDouble("teamProfit"));
            dataBean.setUpdateTime(dataObject.getLong("updateTime"));
            walletModel.setData(dataBean);
        }
        return walletModel;
    }


}
