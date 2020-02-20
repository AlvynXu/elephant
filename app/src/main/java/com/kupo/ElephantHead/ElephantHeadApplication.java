package com.kupo.ElephantHead;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kupo.ElephantHead.greendao.DaoMaster;
import com.kupo.ElephantHead.greendao.DaoSession;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * 全局extends TinkerApplication
 */
public class ElephantHeadApplication extends Application {
    public static ElephantHeadApplication SafeApplication;
    private static Context mContext;
    private static DaoSession daoSession;

    //static 代码段可以防止内存泄露(刷新使用)
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, android.R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }


//    public ElephantHeadApplication() {
//        super(ShareConstants.TINKER_ENABLE_ALL, "com.kupo.ElephantHead.SampleApplicationLike", "com.tencent.tinker.loader.TinkerLoader", false);
//    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //配置数据库
        this.setupDatabase();
        //是否进行ARouter调试
        if (AppConfig.isDebug) {
            //下面两句话必须放到init前面,否则配置无效
            ARouter.openLog();  //打印ARouter日志
            ARouter.openDebug();  //开启ARouter的调试模式(如果在InstantRun模式下运行,必须开启调试模式,线上版本需要关闭，否则有安全风险),
        }
        //官方建议在Application里面进行初始化(使用该注解路径至少两级)
        ARouter.init(this);
        handleSSLHandshake();
        //内存检测
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库safe.db
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "eh.db", null);
//        Database db = helper.getEncryptedWritableDb("abc4");
//        daoSession = new DaoMaster(db).newSession();
        //创建数据库safe.db
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "eh.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }


    /**
     * 忽略https的证书校验
     * 避免Glide加载https图片报错：
     * javax.net.ssl.SSLHandshakeException: java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.
     */
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("TLS");
            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }


}
