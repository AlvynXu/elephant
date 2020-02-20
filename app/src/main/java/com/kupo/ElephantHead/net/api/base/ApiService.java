package com.kupo.ElephantHead.net.api.base;

import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.net.api.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by G400 on 2019/8/3.
 * 功能：封装Retrofit请求
 * 作者：
 */
public class ApiService {

    private static ApiService apiService = null;
    private static Retrofit retrofit;
    private final int TIMEOUT = 30;

    /**
     * 多线程单例安全保护
     */
    private static synchronized void apiFactoryInit() {
        if (apiService == null) {
            apiService = new ApiService();
        }
    }

    /**
     * 供外部调用
     *
     * @return
     */
    public static ApiService getInstance() {
        if (apiService == null) {
            apiFactoryInit();
        }
        return apiService;
    }

    private ApiService() {
        //创建http过滤器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)  //连接超时设置
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)  //写入缓存超时10s
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)  //读取缓存超时10s
                .retryOnConnectionFailure(true)  //失败重连
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.API_ADDRESS)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    private <T> T create(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public static API create() {
        return retrofit.create(API.class);
    }


}
