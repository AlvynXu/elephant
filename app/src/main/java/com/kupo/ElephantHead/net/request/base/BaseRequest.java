package com.kupo.ElephantHead.net.request.base;


import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.ElephantHeadApplication;
import com.kupo.ElephantHead.utils.CheckNetwork;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by G400 on 2019/8/3.
 * 功能：封装Rxjava线程管理和订阅
 * 作者：
 */
public abstract class BaseRequest {


    /**
     * @param <T>
     * @param observable
     * @param callBack
     */
    protected static <T> void build(Observable<ResponseBody> observable, final CallBack<T> callBack) {
        callBack.prepare("");

        observable.subscribeOn(Schedulers.io())  //指定发射事件的进程
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //指定消费事件的进程
                .subscribe(new Observer<ResponseBody>() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        this.disposable = d;

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //TODO 测试
                        callBack.success(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        disposable.dispose(); //取消订阅，防止内存泄漏
                        e.printStackTrace();
                        if (!CheckNetwork.isNetworkConnected(ElephantHeadApplication.getContext()) && !CheckNetwork.isWifiConnected(ElephantHeadApplication.getContext())) {
                            callBack.failure(AppConfig.HTTP_ERROR, "当前网络没有连接，请求失败");
                        } else if (e.getMessage().contains("401")) {
                            callBack.failure(AppConfig.HTTP_TOKEN_ERROR, "Token失效请重新登录,请求失败");
                        } else {
                            callBack.failure(AppConfig.HTTP_ERROR, "服务器内部错误,请求失败");
                        }
                        callBack.end();

                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();  //完成，取消订阅，防止内存泄漏
                        callBack.end();

                    }
                });

    }


}
