package com.kupo.ElephantHead.ui.transaction.model;

/**
 * 求租交易成功后刷新
 */
public class CrowdPayRefreshModel {

    private String paySuccess;

    public CrowdPayRefreshModel(String paySuccess) {
        this.paySuccess = paySuccess;
    }

    public String getPaySuccess() {
        return paySuccess;
    }

    public void setPaySuccess(String paySuccess) {
        this.paySuccess = paySuccess;
    }
}
