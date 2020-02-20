package com.kupo.ElephantHead.greendao;

import com.kupo.ElephantHead.ElephantHeadApplication;

import java.util.List;

/**
 * 用户基础信息操作（增删改查）
 */
public class UserInfoOperateDao {
    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param dataBean
     */
    public static void insertUserInfo(UserInfo dataBean) {
        ElephantHeadApplication.getDaoInstant().getUserInfoDao().insertOrReplace(dataBean);
    }

    /**
     * 删除全部数据
     *
     * @param
     */
    public static void deleteDataBean() {
        ElephantHeadApplication.getDaoInstant().getUserInfoDao().deleteAll();
    }

    /**
     * 更新数据
     */
    public static void updateDataBean(UserInfo dataBean) {
        ElephantHeadApplication.getDaoInstant().getUserInfoDao().update(dataBean);
    }


    /**
     * 查询所有数据
     *
     * @return
     */
    public static List<UserInfo> queryAll() {
        return ElephantHeadApplication.getDaoInstant().getUserInfoDao().loadAll();
    }

}
