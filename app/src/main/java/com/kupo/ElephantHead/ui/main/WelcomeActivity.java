package com.kupo.ElephantHead.ui.main;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.greendao.MaskInfo;
import com.kupo.ElephantHead.greendao.MaskInfoOperateDao;
import com.kupo.ElephantHead.greendao.UserInfo;
import com.kupo.ElephantHead.greendao.UserInfoOperateDao;
import com.kupo.ElephantHead.holder.GlideLoader;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.utils.StatusBarUtil;
import com.kupo.ElephantHead.widget.FullImage;


import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * 欢迎页
 */
@Route(path = AppConfig.ACTIVITY_URL_HOME_WELCOME)
public class WelcomeActivity extends Activity {

    @BindView(R.id.iv_entry)
    FullImage mIVEntry;

    private static final int ANIM_TIME = 1000;

    private static final float SCALE_END = 1.15F;

    private static final int[] Imgs = {
            R.drawable.welcome_head};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 如果是第一次启动app，则直接进入登录页
          //  │ UserInfo{balance=0.07, createTime=1578284463000, deleted=false, id=7, isVip=true, level=3, password='e10adc3949ba59abbe56e057f20f883e', phone='13691212520', profit=0.07, promoterId=1, regCode='666666', teamProfit=0.0, updateTime=1578284463000, token='eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjMzMDgyODY4MTE5LCJ1c2VybmFtZSI6IjEzNjkxMjEyNTIwIn0.-41ovKuqG-hr_tZ6yBqxQSlQApndMAjChqi4KXhrkNE', levelName='地主', chooseBoothCount=20}
//        UserInfo userInfo = CommonUtils.getUserInfo();
//        userInfo.setPhone("13691212520");
//        userInfo.setPromoterId(1);
//        userInfo.setRegCode("666666");
//        userInfo.setId(7);
//        userInfo.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjMzMDgyODY4MTE5LCJ1c2VybmFtZSI6IjEzNjkxMjEyNTIwIn0.-41ovKuqG-hr_tZ6yBqxQSlQApndMAjChqi4KXhrkNE");
//        UserInfoOperateDao.insertUserInfo(userInfo);
//       0
   //     LogUtils.e(CommonUtils.getUserInfo());
        if (MaskInfoOperateDao.queryMaskAll().size() < 1 || CommonUtils.getMaskInfo() == null) {
            ARouter.getInstance()
                    .build(AppConfig.ACTIVITY_URL_MAIN_GUIDE)
                    //进入动画
                    .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    .navigation();
            finish();
            return;
        } else {
            if (CommonUtils.getUserInfo().getPromoterId() == 0 && !TextUtils.isEmpty(CommonUtils.getUserInfo().getPhone())) {
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MAIN_CHANNEL)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                finish();
            } else if (CommonUtils.getUserInfo().getPromoterId() == -1 || TextUtils.isEmpty(CommonUtils.getUserInfo().getPhone())) {
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MAIN_REGISTER)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                finish();
            } else {
                setContentView(R.layout.activity_welcome);
                //下面图1
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    StatusBarUtil.setStatusBarMode(this, true, R.color.black);
                } else {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
                ButterKnife.bind(this);
                startMainActivity();
            }
        }
    }

    /**
     * 进入
     */
    private void startMainActivity() {
        Random random = new Random(SystemClock.elapsedRealtime());//SystemClock.elapsedRealtime() 从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
        //    mIVEntry.setImageResource(Imgs[random.nextInt(Imgs.length)]);
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        ARouter.getInstance()
                                .build(AppConfig.ACTIVITY_URL_MAIN_MAIN)
                                .navigation();
                        finish();
                    }
                });

    }


    //开始动画，并进入首页
    private void startAnim() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIVEntry, "scaleX", 0f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIVEntry, "scaleY", 0f, SCALE_END);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MAIN_MAIN)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                finish();
            }
        });
    }

    /**
     * 屏蔽物理返回按钮
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
