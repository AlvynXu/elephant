package com.kupo.ElephantHead.ui.main;


import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;

import androidx.appcompat.app.AlertDialog;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.AppManager;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.greendao.MaskInfo;
import com.kupo.ElephantHead.greendao.MaskInfoOperateDao;
import com.kupo.ElephantHead.ui.home.HomeFragment;
import com.kupo.ElephantHead.ui.main.adapter.MainAdapter;
import com.kupo.ElephantHead.ui.mine.MineFragment;
import com.kupo.ElephantHead.ui.mvp.model.RefreshModel;
import com.kupo.ElephantHead.ui.room.ShowRoomFragment;
import com.kupo.ElephantHead.ui.room.fragment.RoomItemFragment;
import com.kupo.ElephantHead.ui.transaction.CrowdFragment;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.widget.BaseLineInstructions;
import com.kupo.ElephantHead.widget.CanGradualView;
import com.kupo.ElephantHead.widget.CustomViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kupo.ElephantHead.ElephantHeadApplication.SafeApplication;

/**
 * 主页
 */
@Route(path = AppConfig.ACTIVITY_URL_MAIN_MAIN)
public class MainActivity extends BaseActivity implements HomeFragment.OnFragmentInteractionListener, MineFragment.OnFragmentInteractionListener, ShowRoomFragment.OnFragmentInteractionListener, RoomItemFragment.OnFragmentInteractionListener, CrowdFragment.OnFragmentInteractionListener {
    @BindView(R.id.custom_viewPager)
    CustomViewPager customViewPager;
    @BindView(R.id.baseIndicator)
    BaseLineInstructions baseIndicator;
    @BindView(R.id.home_page)
    CanGradualView homePage;
    @BindView(R.id.project_page)
    CanGradualView projectPage;
    @BindView(R.id.crowd_page)
    CanGradualView crowdPage;
    @BindView(R.id.mine_page)
    CanGradualView minePage;
    private boolean isExit;
    //百度定位
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private String province, city;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        customViewPager.setAdapter(mainAdapter);
        customViewPager.setCurrentItem(0);
        baseIndicator.setViewPager(customViewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    /**
     * 点击退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit) {
                AppManager.getAppManager().AppExit(SafeApplication.getContext());
            } else {
                ToastUtils.showShort("再按一次退出");
                isExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * 进行fragment之间的交互
     *
     * @param uri
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            province = location.getProvince();    //获取省份
            city = location.getCity();    //获取城市
            MaskInfo mf = CommonUtils.getMaskInfo();
            if (TextUtils.isEmpty(mf.getProvince())) {
                MaskInfoOperateDao.deleteMaskBean();
                MaskInfo maskInfo = new MaskInfo();
                maskInfo.setIsShow(2);
                maskInfo.setDesc("出现蒙版");
                if (TextUtils.isEmpty(province) && TextUtils.isEmpty(maskInfo.getProvince())) {
                    province = "浙江省";
                }
                if (TextUtils.isEmpty(city) && TextUtils.isEmpty(maskInfo.getCity())) {
                    city = "杭州市";
                }
                maskInfo.setProvince(province);
                maskInfo.setCity(city);
                MaskInfoOperateDao.insertMaskInfo(maskInfo);
            }
        }
    }

    private void initLocation() {
        if (CommonUtils.isLocServiceEnable(this)) {
            LocationClientOption option = new LocationClientOption();
            mLocationClient = new LocationClient(getApplicationContext());
            mLocationClient.registerLocationListener(myListener);
            option.setIsNeedAddress(true);
            option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);//设置低功耗定位定位模式
            option.setOpenGps(true); // 打开gps
            option.setAddrType("all");
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(0);
            mLocationClient.setLocOption(option);
            mLocationClient.start();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("温馨提示")
                    .setMessage("当前app是基于位置获取数据的，请去设置开通定位权限")
                    .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CommonUtils.openGpsSettings(MainActivity.this);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            AppManager.getAppManager().AppExit(SafeApplication.getContext());
                        }
                    })
                    .setCancelable(false)
                    .setOnKeyListener(new DialogInterface.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                                return true;
                            } else {
                                return false;
                            }

                        }
                    })
                    .create().show();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myListener != null && mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(myListener);
            mLocationClient.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initLocation();
    }


}
