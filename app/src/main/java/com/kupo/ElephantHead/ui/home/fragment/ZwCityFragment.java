package com.kupo.ElephantHead.ui.home.fragment;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseDiaLogFragment;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.greendao.MaskInfo;
import com.kupo.ElephantHead.greendao.MaskInfoOperateDao;
import com.kupo.ElephantHead.ui.home.model.CitySelectStatusModel;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.ui.mvp.contract.CitySelectContract;
import com.kupo.ElephantHead.ui.mvp.presenter.CitySelectPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.widget.WheelView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: LoginActivity
 * @Description: 城市选择
 * @Author:
 * @CreateDate: 2019/8/14 10:05
 * @Version: 1.0
 */
public class ZwCityFragment extends BaseDiaLogFragment implements CitySelectContract.ICitySelectView {

    @BindView(R.id.province_wheel)
    WheelView provinceWheel;
    @BindView(R.id.city_wheel)
    WheelView cityWheel;
    @BindView(R.id.area_wheel)
    WheelView areaWheel;
    @BindView(R.id.street_wheel)
    WheelView streetWheel;
    private CitySelectPresenter mCitySelectPresenter = null;
    private String cityStr = "北京市";
    private String codeStr = "1001270";
    private Window window;
    private int citiesPosition = 3;
    private int districtsPosition = 3;
    private int streetsPosition = 3;
    private int otherPosition = 3;
    String province, city, area;
    private String provinceName, cityName;

    @Override
    protected void onInitPresenters() {
        mCitySelectPresenter = new CitySelectPresenter();
        mCitySelectPresenter.attachView(this);
        Map<String, Object> map = new HashMap<>();
        mCitySelectPresenter.getLocationNet(CommonUtils.getUserInfo().getToken(), "provinces", map);
        provinceWheel.setWheelViewSelectedListener(new WheelView.IWheelViewSelectedListener() {
            @Override
            public void wheelViewSelectedChanged(WheelView myWheelView, List<ProvincesModel.DataBean> data, int position) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", data.get(position).getCode());
                mCitySelectPresenter.getLocationNet(CommonUtils.getUserInfo().getToken(), "cities", map);
                province = data.get(position).getName();
                citiesPosition = position;
            }
        });
        cityWheel.setWheelViewSelectedListener(new WheelView.IWheelViewSelectedListener() {
            @Override
            public void wheelViewSelectedChanged(WheelView myWheelView, List<ProvincesModel.DataBean> data, int position) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", data.get(position).getCode());
                mCitySelectPresenter.getLocationNet(CommonUtils.getUserInfo().getToken(), "districts", map);
                city = data.get(position).getName();
                districtsPosition = position;
            }
        });
        areaWheel.setWheelViewSelectedListener(new WheelView.IWheelViewSelectedListener() {
            @Override
            public void wheelViewSelectedChanged(WheelView myWheelView, List<ProvincesModel.DataBean> data, int position) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", data.get(position).getCode());
                mCitySelectPresenter.getLocationNet(CommonUtils.getUserInfo().getToken(), "streets", map);
                area = data.get(position).getName();
                streetsPosition = position;
            }
        });
        streetWheel.setWheelViewSelectedListener(new WheelView.IWheelViewSelectedListener() {
            @Override
            public void wheelViewSelectedChanged(WheelView myWheelView, List<ProvincesModel.DataBean> data, int position) {
                cityStr = data.get(position).getName();
                codeStr = data.get(position).getCode();
                otherPosition = position;
            }
        });
    }

    @OnClick({R.id.popup_city_close, R.id.popup_city__sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.popup_city_close:
                dismiss();
                break;
            case R.id.popup_city__sure:
                EventBus.getDefault().postSticky(new CitySelectStatusModel(citiesPosition, districtsPosition, streetsPosition, otherPosition));
                EventBus.getDefault().postSticky(new SingleModel(province + " " + city + " " + area + " " + cityStr, codeStr));
                EventBus.getDefault().postSticky(new BaseResult("刷新"));
                MaskInfo maskInfo = CommonUtils.getMaskInfo();
                MaskInfoOperateDao.deleteMaskBean();
                maskInfo.setIsShow(1);
                MaskInfoOperateDao.insertMaskInfo(maskInfo);
                dismiss();
                break;
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_city_select_zw;
    }

    @Override
    protected void init() {
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        if (bundle != null) {
            citiesPosition = bundle.getInt("citiesPosition");
            districtsPosition = bundle.getInt("districtsPosition");
            streetsPosition = bundle.getInt("streetsPosition");
            otherPosition = bundle.getInt("otherPosition");
            provinceName = bundle.getString("province");
            cityName = bundle.getString("city");
        }
    }

    @Override
    public void getLocationSuccess(ProvincesModel provincesModel, String type) {
        if (provincesModel.getCode() == 100) {
            CommonUtils.showLabelAlert(getActivity(), "");
        } else if (provincesModel.getCode() == 200) {
            if ("provinces".equals(type)) {
                if (provinceName != null) {
                    for (int i = 0; i < provincesModel.getData().size(); i++) {
                        if (provinceName.equals(provincesModel.getData().get(i).getName())) {
                            citiesPosition = i;
                            break;
                        }
                    }
                }
                provinceWheel.setDataWithSelectedItemIndex(provincesModel.getData(), citiesPosition, "provinces");// 省级
            } else if ("cities".equals(type)) {
                if (cityName != null) {
                    for (int i = 0; i < provincesModel.getData().size(); i++) {
                        if (cityName.equals(provincesModel.getData().get(i).getName())) {
                            districtsPosition = i;
                            break;
                        }
                    }
                }
                if (provincesModel.getData().size() == 1) {
                    cityWheel.setDataWithSelectedItemIndex(provincesModel.getData(), 0, "cities");// 市级
                } else {
                    cityWheel.setDataWithSelectedItemIndex(provincesModel.getData(), districtsPosition, "cities");// 市级
                }
            } else if ("districts".equals(type)) {
                if (provincesModel.getData().size() == 1) {
                    areaWheel.setDataWithSelectedItemIndex(provincesModel.getData(), 0, "districts");// 区级
                } else {
                    areaWheel.setDataWithSelectedItemIndex(provincesModel.getData(), streetsPosition, "districts");// 区级
                }

            } else if ("streets".equals(type)) {
                if (provincesModel.getData().size() == 1) {
                    streetWheel.setDataWithSelectedItemIndex(provincesModel.getData(), 0, "streets");// 街道
                } else {
                    streetWheel.setDataWithSelectedItemIndex(provincesModel.getData(), otherPosition, "streets");// 街道
                }

            }
        }

    }


    @Override
    public void getLocationFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void onStart() {
        super.onStart();
        // 下面这些设置必须在此方法(onStart())中才有效
        window = getDialog().getWindow();
        // 如果不设置这句代码, 那么弹框就会与四边都有一定的距离
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
        params.width = getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(params);

    }

}



