package com.kupo.ElephantHead.ui.transaction.fragment;


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
import com.kupo.ElephantHead.ui.home.model.CitySelectStatusModel;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.ui.mvp.contract.CitySelectContract;
import com.kupo.ElephantHead.ui.mvp.contract.RentCityContract;
import com.kupo.ElephantHead.ui.mvp.model.RefreshModel;
import com.kupo.ElephantHead.ui.mvp.presenter.CitySelectPresenter;
import com.kupo.ElephantHead.ui.mvp.presenter.RentCityPresenter;
import com.kupo.ElephantHead.ui.transaction.model.CrowdRefreshModel;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.widget.WheelView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 交易模块选择城市
 */
public class RentCityFragment extends BaseDiaLogFragment implements CitySelectContract.ICitySelectView {

    private CitySelectContract.ICitySelectPresenter rentCityPresenter;
    @BindView(R.id.rent_city_wheel)
    WheelView cityWheel;
    @BindView(R.id.rent_province_wheel)
    WheelView provinceWheel;
    private int citiesPosition = 3, provincePosition = 3;
    private String cityName, provinceName, cityCode;

    @Override
    protected void init() {
        rentCityPresenter = new CitySelectPresenter();
        rentCityPresenter.attachView(this);
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        if (bundle != null) {
            citiesPosition = bundle.getInt("citiesPosition");
            provincePosition = bundle.getInt("provincePosition");
            cityName = bundle.getString("city");
            provinceName = bundle.getString("province");
        }
    }

    @Override
    protected void onInitPresenters() {
        Map<String, Object> map = new HashMap<>();
        rentCityPresenter.getLocationNet(CommonUtils.getUserInfo().getToken(), "provinces", map);
        provinceWheel.setWheelViewSelectedListener(new WheelView.IWheelViewSelectedListener() {
            @Override
            public void wheelViewSelectedChanged(WheelView myWheelView, List<ProvincesModel.DataBean> data, int position) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", data.get(position).getCode());
                rentCityPresenter.getLocationNet(CommonUtils.getUserInfo().getToken(), "cities", map);
                provincePosition = position;
            }
        });
        cityWheel.setWheelViewSelectedListener(new WheelView.IWheelViewSelectedListener() {
            @Override
            public void wheelViewSelectedChanged(WheelView myWheelView, List<ProvincesModel.DataBean> data, int position) {
                cityName = data.get(position).getName();
                cityCode = data.get(position).getCode();
                citiesPosition = position;
            }
        });
    }


    @OnClick({R.id.rent_city_close, R.id.rent_city__sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rent_city_close:
                dismiss();
                break;
            case R.id.rent_city__sure:
                EventBus.getDefault().post(new RefreshModel(cityName, cityCode));
                EventBus.getDefault().post(new CrowdRefreshModel(provincePosition, citiesPosition));
                dismiss();
                break;
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rent_city_popup;
    }


    @Override
    public void onStart() {
        super.onStart();
        // 下面这些设置必须在此方法(onStart())中才有效
        Window window = getDialog().getWindow();
        // 如果不设置这句代码, 那么弹框就会与四边都有一定的距离
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
        params.width = getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(params);

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
                            provincePosition = i;
                            break;
                        }
                    }
                }
                provinceWheel.setDataWithSelectedItemIndex(provincesModel.getData(), provincePosition, "provinces");// 省级
            } else if ("cities".equals(type)) {
                if (cityName != null) {
                    for (int i = 0; i < provincesModel.getData().size(); i++) {
                        if (cityName.equals(provincesModel.getData().get(i).getName())) {
                            citiesPosition = i;
                            break;
                        }
                    }
                }
                if (provincesModel.getData().size() == 1) {
                    cityWheel.setDataWithSelectedItemIndex(provincesModel.getData(), 0, "cities");// 市级
                } else {
                    cityWheel.setDataWithSelectedItemIndex(provincesModel.getData(), citiesPosition, "cities");// 市级
                }
            }
        }
    }

    @Override
    public void getLocationFailed(int netCode, String msg) {

    }
}
