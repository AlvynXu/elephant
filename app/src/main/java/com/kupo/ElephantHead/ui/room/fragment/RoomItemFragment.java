package com.kupo.ElephantHead.ui.room.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseFragment;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.greendao.MaskInfo;
import com.kupo.ElephantHead.greendao.MaskInfoOperateDao;
import com.kupo.ElephantHead.ui.home.adapter.HomeTopAdapter;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.ui.mvp.contract.RoomItemContract;
import com.kupo.ElephantHead.ui.mvp.presenter.RoomItemPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.widget.EmptyLayout;
import com.ms.banner.Banner;
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
import genealogy.jczb.com.rvlibrary.BaseQuickAdapter;

/**
 * 展厅列表
 */
public class RoomItemFragment extends BaseFragment implements RoomItemContract.IRoomItemView {

    @BindView(R.id.room_banner)
    Banner roomBanner;
    @BindView(R.id.empty)
    EmptyLayout empty;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private OnFragmentInteractionListener mListener;
    private RoomItemContract.IRoomItemPresenter roomItemPresenter;
    Map<String, Object> map = null;
    int page = 1;
    int categoryId;
    private String city = "", areaCode;
    ViewHolder viewHolder;
    String loadMore;
    HomeTopAdapter homeTopAdapter;
    int areaType = 1;

    public RoomItemFragment() {
    }

    public RoomItemFragment(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_room_item;
    }

    @Override
    protected void init() {
        roomItemPresenter = new RoomItemPresenter();
        roomItemPresenter.attachView(this);
        areaType = 3;
    }

    @Override
    protected void onInitPresenters() {
        if (!("出现蒙版").equals(CommonUtils.getMaskInfo().getDesc())) {
            if (CommonUtils.getMaskInfo().getIsShow() == 1) {
                //街道
                areaType = 1;
            } else {
                //市
                areaType = 3;
            }
            areaCode = CommonUtils.getMaskInfo().getDesc();
            initNet(page, areaCode, categoryId, areaType);
        } else {
            map = new HashMap<>();
            map.put("cityName", CommonUtils.getMaskInfo().getCity());
            roomItemPresenter.getCityCode(CommonUtils.getUserInfo().getToken(), map);
        }
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initNet(page, areaCode, categoryId, areaType);
                loadMore = "onLoadMore";
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initNet(1, areaCode, categoryId, areaType);
                refreshLayout.finishRefresh(1000);
            }
        });
    }

    /**
     * 初始化项目头条列表
     */
    @SuppressLint("WrongConstant")
    private void initTopList(List<HomeTopModel.DataBean.RecordsBean> recordsBeans) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        homeTopAdapter = new HomeTopAdapter(recordsBeans, getActivity());
        homeTopAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        homeTopAdapter.isFirstOnly(false);
        homeTopAdapter.addFooterView(getFooterView());
        if (recordsBeans.size() < 1) {
            viewHolder.footEmpty.setVisibility(View.VISIBLE);
            viewHolder.footEmpty.setEmptyText("暂无数据");
            viewHolder.footEmpty.setImgWH(250, 250);
            viewHolder.footEmpty.setEmptyTextSize(28);
            viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.secondText));
            viewHolder.footEmpty.setTxtWH(500, 50);
        } else {
            viewHolder.footEmpty.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(homeTopAdapter);

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

    @Override
    public void getRoomItemListSuccess(HomeTopModel homeTopModel) {
        if (homeTopModel.getCode() == 0) {
            refreshLayout.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
            if ("onLoadMore".equals(loadMore) && page > 1) {
                homeTopAdapter.addData((page - 1) * 15, homeTopModel.getData().getRecords());
            } else {
                initTopList(homeTopModel.getData().getRecords());
            }
            if (page == homeTopModel.getData().getPages()) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        } else if (homeTopModel.getCode() == 100) {
            CommonUtils.showLabelAlert(getActivity(), "");
        } else {
            ToastUtils.showShort(homeTopModel.getMessage());
        }
    }

    @Override
    public void getRoomItemListFailed(int netCode, String msg) {

    }


    @Override
    public void getCityCodeSuccess(CityCode cityCode) {
        if (cityCode.getCode() == 0) {
            areaCode = cityCode.getData().getCode();
            initNet(page, areaCode, categoryId, areaType);
        }

    }

    private void initNet(int page, String areaCode, int categoryId, int areaType) {
        map = new HashMap<>();
        map.put("page", page);
        map.put("size", 15);
        map.put("areaCode", areaCode);
        map.put("areaType", areaType);
        map.put("categoryId", categoryId);
        roomItemPresenter.getRoomItemList(CommonUtils.getUserInfo().getToken(), map);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveRefreshEventBus(BaseResult baseResult) {
        EventBus.getDefault().removeStickyEvent(baseResult);
        areaCode = CommonUtils.getMaskInfo().getDesc();
        areaType = 1;
        initNet(1, areaCode, categoryId, areaType);
    }

    @Override
    public void getCityCodeFailed(int netCode, String msg) {

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
