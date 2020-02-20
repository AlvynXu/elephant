package com.kupo.ElephantHead.ui.transaction;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseFragment;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.greendao.MaskInfo;
import com.kupo.ElephantHead.greendao.MaskInfoOperateDao;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.ui.mvp.contract.CrowdContract;
import com.kupo.ElephantHead.ui.mvp.model.RefreshModel;
import com.kupo.ElephantHead.ui.mvp.presenter.CrowdPresenter;
import com.kupo.ElephantHead.ui.room.fragment.RoomItemFragment;
import com.kupo.ElephantHead.ui.transaction.adapter.CrowdAdapter;
import com.kupo.ElephantHead.ui.transaction.adapter.HeadCrowdAdapter;
import com.kupo.ElephantHead.ui.transaction.fragment.PayRentFragment;
import com.kupo.ElephantHead.ui.transaction.fragment.RentCityFragment;
import com.kupo.ElephantHead.ui.transaction.model.CrowdModel;
import com.kupo.ElephantHead.ui.transaction.model.CrowdPayRefreshModel;
import com.kupo.ElephantHead.ui.transaction.model.CrowdRefreshModel;
import com.kupo.ElephantHead.ui.transaction.model.MyCrowdModel;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.utils.StatusBarUtil;
import com.kupo.ElephantHead.widget.EmptyLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 交易模块
 */
public class CrowdFragment extends BaseFragment implements CrowdContract.ICrowdView {
    @BindView(R.id.head_recycler_view)
    RecyclerView headRecyclerView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.crowd_location_tv)
    TextView crowdLocationTv;
    @BindView(R.id.crowd_location_im)
    ImageView crowdLocationIm;
    @BindView(R.id.crowd_my_rent)
    TextView crowdMyRent;
    @BindView(R.id.crowd_location_ll)
    LinearLayout crowdLocationLl;
    private OnFragmentInteractionListener mListener;
    private CrowdContract.ICrowdPresenter crowdPresenter;
    Map<String, Object> map = null;
    int page = 1;
    String areaCode;
    private CrowdAdapter crowdAdapter;
    String loadMore;
    ViewHolder viewHolder;
    List<MyCrowdModel.DataBean> headDataBeans;
    private int citiesPosition = 1, provincePosition = 11;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_crowd;
    }

    @Override
    protected void init() {
        crowdPresenter = new CrowdPresenter();
        crowdPresenter.attachView(this);
        crowdLocationTv.setText(CommonUtils.getMaskInfo().getCity());

    }

    @Override
    protected void onInitPresenters() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setStatusBarMode(mActivity, true, R.color.white);
        }
        MaskInfo maskInfo = CommonUtils.getMaskInfo();
        maskInfo.setDesc("出现蒙版");
        MaskInfoOperateDao.deleteMaskBean();
        MaskInfoOperateDao.insertMaskInfo(maskInfo);
        citiesPosition = 1;
        provincePosition = 11;
        crowdLocationTv.setText(CommonUtils.getMaskInfo().getCity());
        if (!("出现蒙版").equals(CommonUtils.getMaskInfo().getDesc())) {
            areaCode = CommonUtils.getMaskInfo().getDesc();
            initRentListInfo(page, areaCode);
            initMyRentListInfo(areaCode);
        } else {
            map = new HashMap<>();
            map.put("cityName", CommonUtils.getMaskInfo().getCity());
            crowdPresenter.getCityCode(CommonUtils.getUserInfo().getToken(), map);
            crowdLocationTv.setText(CommonUtils.getMaskInfo().getCity());
        }
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initRentListInfo(page, areaCode);
                loadMore = "onLoadMore";
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (page == 1) {
                    initRentListInfo(page, areaCode);
                }
                refreshLayout.finishRefresh(1000);
            }
        });
    }

    @OnClick({R.id.crowd_my_rent, R.id.crowd_location_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.crowd_my_rent:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_TRANSACTION_MY_RENT)
                            .withString("city", crowdLocationTv.getText().toString().trim())
                            .withString("cityCode", areaCode)
                            .withInt("citiesPosition", citiesPosition)
                            .withInt("provincePosition", provincePosition)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
            case R.id.crowd_location_ll:
                RentCityFragment rentCityFragment = new RentCityFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("citiesPosition", citiesPosition);
                bundle.putInt("provincePosition", provincePosition);
                if (citiesPosition == 1 && provincePosition == 11) {
                    bundle.putString("city", CommonUtils.getMaskInfo().getCity());
                    bundle.putString("province", CommonUtils.getMaskInfo().getProvince());
                }
                rentCityFragment.setArguments(bundle);
                rentCityFragment.show(getChildFragmentManager(), "rentCityFragment");
                break;
        }
    }


    private View getFooterView() {
        View view = getLayoutInflater().inflate(R.layout.item_pay_dz_zw_foot, null);
        LinearLayout.LayoutParams layoutParams;
        if (page == 1) {
            layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(0, 400, 0, 0);
        } else {
            layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 0);
        }
        view.setLayoutParams(layoutParams);
        viewHolder = new ViewHolder(view);
        viewHolder.toDzOrZw.setVisibility(View.GONE);
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.foot_empty)
        EmptyLayout footEmpty;
        @BindView(R.id.toDzOrZw)
        TextView toDzOrZw;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 选择城市
     *
     * @param refreshModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveRentCityEventBus(RefreshModel refreshModel) {
        areaCode = refreshModel.getCityCode();
        crowdLocationTv.setText(refreshModel.getCityName());
        headDataBeans.clear();
        //头部/我的求组
        initMyRentListInfo(areaCode);
        //下面列表/租赁列表
        initRentListInfo(page, areaCode);
    }

    /**
     * 选择城市记录
     *
     * @param baseResult
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveCityStatusEventBus(CrowdRefreshModel baseResult) {
        citiesPosition = baseResult.getCitiesPosition();
        provincePosition = baseResult.getCount();
    }

    /**
     * 初始化租赁列表
     *
     * @param page
     * @param code
     */
    private void initRentListInfo(int page, String code) {
        map = new HashMap<>();
        map.put("page", page);
        map.put("size", 15);
        map.put("areaCode", code);
        crowdPresenter.getRent(CommonUtils.getUserInfo().getToken(), map);
    }

    /**
     * 初始化我的租赁列表
     *
     * @param code
     */
    private void initMyRentListInfo(String code) {
        map = new HashMap<>();
        map.put("areaCode", code);
        crowdPresenter.getMyRent(CommonUtils.getUserInfo().getToken(), map);
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
     * 初始化下面列表
     *
     * @param recordsBeans
     */
    private void initCrowdList(List<CrowdModel.DataBean.RecordsBean> recordsBeans) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        crowdAdapter = new CrowdAdapter(recordsBeans, mActivity);
        crowdAdapter.isFirstOnly(false);
        crowdAdapter.addFooterView(getFooterView());
        if (recordsBeans.size() < 1 && headDataBeans.size() < 1) {
            viewHolder.footEmpty.setVisibility(View.VISIBLE);
            viewHolder.footEmpty.setEmptyText("暂无数据");
            viewHolder.footEmpty.setImgWH(250, 250);
            viewHolder.footEmpty.setEmptyTextSize(28);
            viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.secondText));
            viewHolder.footEmpty.setTxtWH(500, 50);
        } else {
            viewHolder.footEmpty.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(crowdAdapter);
        crowdAdapter.setOnItemListener(new CrowdAdapter.OnItemListener() {
            @Override
            public void onItemClick(int position) {
              //  LogUtils.e(recordsBeans.get(position).getTotalCount());
                PayRentFragment payRentFragment = new PayRentFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("orderId", recordsBeans.get(position).getId());
                bundle.putInt("day", recordsBeans.get(position).getDays());
                payRentFragment.setArguments(bundle);
                payRentFragment.show(getChildFragmentManager(), "payRentFragment");
            }
        });
    }

    /**
     * 初始化head面列表
     *
     * @param dataBeans
     */
    private void initCrowdHeadList(List<MyCrowdModel.DataBean> dataBeans) {
        headDataBeans = new ArrayList<>();
        headDataBeans = dataBeans;
        headRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        HeadCrowdAdapter headCrowdAdapter = new HeadCrowdAdapter(dataBeans, mActivity);
        headCrowdAdapter.isFirstOnly(false);
        headRecyclerView.setAdapter(headCrowdAdapter);
    }

    @Override
    public void getRentSuccess(CrowdModel crowdModel) {
        if (crowdModel.getCode() == 0) {
            if ("onLoadMore".equals(loadMore) && page > 1) {
                crowdAdapter.addData((page - 1) * 15, crowdModel.getData().getRecords());
            } else {
                initCrowdList(crowdModel.getData().getRecords());
            }
            if (page == crowdModel.getData().getPages()) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        } else if (crowdModel.getCode() == 100) {
            CommonUtils.showLabelAlert(mActivity, "");
        } else {
            ToastUtils.showShort(crowdModel.getMessage());
        }

    }

    @Override
    public void getRentFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void getCityCodeSuccess(CityCode cityCode) {
        if (cityCode.getCode() == 0) {
            areaCode = cityCode.getData().getCode();
            //头部/我的求组
            initMyRentListInfo(areaCode);
            //下面列表/租赁列表
            initRentListInfo(page, areaCode);
        }
    }

    @Override
    public void getCityCodeFailed(int netCode, String msg) {

    }

    @Override
    public void getMyRentSuccess(MyCrowdModel myCrowdModel) {
        if (myCrowdModel.getCode() == 0) {
            initCrowdHeadList(myCrowdModel.getData());
        } else if (myCrowdModel.getCode() == 100) {
            CommonUtils.showLabelAlert(mActivity, "");
        } else {
            ToastUtils.showShort(myCrowdModel.getMessage());
        }
    }

    @Override
    public void getMyRentFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
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

    /**
     * 求租支付完成刷新
     *
     * @param baseResult
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveQzEventBus(CrowdPayRefreshModel baseResult) {
        EventBus.getDefault().removeStickyEvent(baseResult);
        if (!TextUtils.isEmpty(areaCode)) {
            initMyRentListInfo(areaCode);
        }
    }
}
