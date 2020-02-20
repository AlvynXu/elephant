package com.kupo.ElephantHead.ui.room;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.ElephantHeadApplication;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseFragment;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.greendao.MaskInfo;
import com.kupo.ElephantHead.greendao.MaskInfoOperateDao;
import com.kupo.ElephantHead.ui.home.fragment.ZwCityFragment;
import com.kupo.ElephantHead.ui.home.model.CitySelectStatusModel;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.ui.mvp.contract.RoomContract;
import com.kupo.ElephantHead.ui.mvp.presenter.RoomPresenter;
import com.kupo.ElephantHead.ui.room.adapter.RoomPagerAdapter;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.utils.StatusBarUtil;
import com.kupo.ElephantHead.widget.CustomIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 展厅模块
 */
public class ShowRoomFragment extends BaseFragment implements RoomContract.IRoomView {
    @BindView(R.id.room_location_name_tv)
    TextView roomLocationTv;
    @BindView(R.id.room_location_im)
    ImageView roomLocationIm;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private OnFragmentInteractionListener mListener;
    private RoomContract.IRoomPresenter roomPresenter;
    private int citiesPosition = 11;
    private int districtsPosition = 1;
    private int streetsPosition = 3;
    private int otherPosition = 3;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void init() {
        roomPresenter = new RoomPresenter();
        roomPresenter.attachView(this);
        roomLocationTv.setText(CommonUtils.getMaskInfo().getProvince() + " " + CommonUtils.getMaskInfo().getCity());
    }


    @Override
    protected void onInitPresenters() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setStatusBarMode(mActivity, true, R.color.white);
        }
        citiesPosition = 11;
        districtsPosition = 1;
        streetsPosition = 3;
        otherPosition = 3;
        roomPresenter.getCategoryList(CommonUtils.getUserInfo().getToken());
        roomLocationTv.setText(CommonUtils.getMaskInfo().getProvince() + " " + CommonUtils.getMaskInfo().getCity());
    }

    @OnClick({R.id.room_location_ll, R.id.go_release})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.room_location_ll:
                ZwCityFragment zwCityFragment = new ZwCityFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("citiesPosition", citiesPosition);
                bundle.putInt("districtsPosition", districtsPosition);
                bundle.putInt("streetsPosition", streetsPosition);
                bundle.putInt("otherPosition", otherPosition);
                if (citiesPosition == 11 && districtsPosition == 1 && streetsPosition == 3 && otherPosition == 3) {
                    bundle.putString("province", CommonUtils.getMaskInfo().getProvince());
                    bundle.putString("city", CommonUtils.getMaskInfo().getCity());
                }
                zwCityFragment.setArguments(bundle);
                zwCityFragment.show(getChildFragmentManager(), "selectRoomCity");
                break;
            case R.id.go_release:
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MINE_ADD)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                break;
        }
    }

    /**
     * 记录城市选择记录
     *
     * @param citySelectStatusModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveRoomCityEventBus(CitySelectStatusModel citySelectStatusModel) {
        EventBus.getDefault().removeStickyEvent(citySelectStatusModel);
        citiesPosition = citySelectStatusModel.getCitiesPosition();
        districtsPosition = citySelectStatusModel.getDistrictsPosition();
        streetsPosition = citySelectStatusModel.getStreetsPosition();
        otherPosition = citySelectStatusModel.getOtherPosition();
    }

    /**
     * 通知刷新
     *
     * @param baseResult
     */
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void receiveLkRefreshEventBus(BaseResult baseResult) {
        if ("look刷新".equals(baseResult.getMessage())) {
            MaskInfo maskInfo = CommonUtils.getMaskInfo();
            maskInfo.setDesc("出现蒙版");
            MaskInfoOperateDao.deleteMaskBean();
            MaskInfoOperateDao.insertMaskInfo(maskInfo);
            roomPresenter.getCategoryList(CommonUtils.getUserInfo().getToken());
            roomLocationTv.setText(CommonUtils.getMaskInfo().getProvince() + " " + CommonUtils.getMaskInfo().getCity());
        }
    }


    /**
     * 自主选择城市街道筛选条件
     *
     * @param cityInfo
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveCodeAndCityEventBus(SingleModel cityInfo) {
        EventBus.getDefault().removeStickyEvent(cityInfo);
        roomLocationTv.setText(cityInfo.getCity());
        MaskInfo maskInfo = CommonUtils.getMaskInfo();
        MaskInfoOperateDao.deleteMaskBean();
        maskInfo.setDesc(cityInfo.getCode());
        MaskInfoOperateDao.insertMaskInfo(maskInfo);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * 初始化加载tab页面
     */
    private void initTabPage(List<CategoryModel.DataBean> caDataBeanList) {
        tabLayout.setIndicatorHeight(4);
        RoomPagerAdapter roomPagerAdapter = new RoomPagerAdapter(getChildFragmentManager(), caDataBeanList);
        viewPager.setAdapter(roomPagerAdapter);
        tabLayout.setViewPager(viewPager);
        tabLayout.setCurrentTab(tabLayout.getCurrentTab());
    }


    @Override
    public void getCategoryListSuccess(CategoryModel categoryModel) {
        initTabPage(categoryModel.getData());
    }


    @Override
    public void getCategoryListFailed(int netCode, String msg) {
        if (netCode == AppConfig.HTTP_TOKEN_ERROR) {
            CommonUtils.showLabelAlert(getActivity(), "");
            return;
        } else {
            ToastUtils.showShort(msg);
        }
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}
