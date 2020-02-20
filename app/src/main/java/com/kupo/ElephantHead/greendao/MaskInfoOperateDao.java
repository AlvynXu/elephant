package com.kupo.ElephantHead.greendao;

import com.kupo.ElephantHead.ElephantHeadApplication;

import java.util.List;

/**
 * 蒙版基础信息操作（增删改查）
 */
public class MaskInfoOperateDao {
    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param dataBean
     */
    public static void insertMaskInfo(MaskInfo dataBean) {
        ElephantHeadApplication.getDaoInstant().getMaskInfoDao().insertOrReplace(dataBean);
    }

    /**
     * 删除全部数据
     *
     * @param
     */
    public static void deleteMaskBean() {
        ElephantHeadApplication.getDaoInstant().getMaskInfoDao().deleteAll();
    }

    /**
     * 更新数据
     */
    public static void updateMask(MaskInfo dataBean) {
        ElephantHeadApplication.getDaoInstant().getMaskInfoDao().update(dataBean);
    }


    /**
     * 查询所有数据
     *
     * @return
     */
    public static List<MaskInfo> queryMaskAll() {

        return ElephantHeadApplication.getDaoInstant().getMaskInfoDao().loadAll();
    }

}
