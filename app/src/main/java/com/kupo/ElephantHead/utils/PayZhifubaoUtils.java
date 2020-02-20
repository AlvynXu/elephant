package com.kupo.ElephantHead.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;

import java.util.Map;

/**
 * 支付宝支付
 */
public class PayZhifubaoUtils {
    Activity mActivity;
    Handler mHandler;
    private static PayZhifubaoUtils instance;

    private PayZhifubaoUtils(Activity mActivity, Handler mHandler) {
        this.mActivity = mActivity;
        this.mHandler = mHandler;
    }

    public static PayZhifubaoUtils getInstance(Activity mActivity, Handler mHandler) {
        if (instance == null) {
            synchronized (PayZhifubaoUtils.class) {
                if (instance == null) {
                    instance = new PayZhifubaoUtils(mActivity, mHandler);
                }
            }
        }
        return instance;
    }

    /**
     * 支付宝支付业务 ,服务器调好后这些重要数据都不可以放在客户端
     */
    public void payZhibaoMoney(String order_string) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX); //
        // 沙箱环境测试,生产环境是需要注释掉，沙箱指南中居然没有提到
        final String orderInfo = order_string;
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
