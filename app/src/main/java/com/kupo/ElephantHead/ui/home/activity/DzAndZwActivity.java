package com.kupo.ElephantHead.ui.home.activity;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
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
import com.kupo.ElephantHead.ui.home.adapter.DzItemAdapter;
import com.kupo.ElephantHead.ui.home.adapter.ZwItemAdapter;
import com.kupo.ElephantHead.ui.home.fragment.DzCityFragment;
import com.kupo.ElephantHead.ui.home.fragment.ZwCityFragment;
import com.kupo.ElephantHead.ui.home.model.CityCode;
import com.kupo.ElephantHead.ui.home.model.CitySelectStatusModel;
import com.kupo.ElephantHead.ui.home.model.DzItemModel;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.ui.home.model.ZwItemModel;
import com.kupo.ElephantHead.ui.main.MainActivity;
import com.kupo.ElephantHead.ui.mvp.contract.DzAndZwContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.presenter.DzAndZwPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;
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

import static com.kupo.ElephantHead.ElephantHeadApplication.SafeApplication;


/**
 * 地主交易/抢展位
 */
@Route(path = AppConfig.ACTIVITY_URL_HOME_QDZ)
public class DzAndZwActivity extends BaseActivity implements DzAndZwContract.IDzAndZwView {

    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_linear)
    LinearLayout titleRightLinear;
    @BindView(R.id.buy_allNum)
    TextView buyAllNum;
    @BindView(R.id.buy_buyNum)
    TextView buyBuyNum;
    @BindView(R.id.buy_bar)
    TextView buyBar;
    @BindView(R.id.location_txt)
    TextView locationTxt;
    @BindView(R.id.location_ll)
    LinearLayout locationLl;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.icon_im)
    ImageView iconIm;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.dz_zw_buy_desc)
    TextView dzZwBuyDesc;
    private String title;
    private int page = 1;
    //页面类型(地主/展位)
    private int type;
    //默认城市
    String locality = "";
    //默认城市编码
    private String code = "1001270";
    //售卖状态
    private int buyStatus = 0;
    Map<String, Object> map = null;
    private DzAndZwContract.IDzAndZwPresenter mIDzAndZwPresenter = null;
    private int areaType = 3;
    ViewHolder viewHolder;
    private String province, city;
    int index = 0;
    int count = 0;
    String loadMore;
    DzItemAdapter qdzItemAdapter;
    ZwItemAdapter zwItemAdapter;
    private boolean locationChang = false;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        mIDzAndZwPresenter = new DzAndZwPresenter();
        mIDzAndZwPresenter.attachView(this);
        buyBar.setVisibility(View.GONE);
        province = CommonUtils.getMaskInfo().getProvince();
        city = CommonUtils.getMaskInfo().getCity();
        map = new HashMap<>();
        map.put("cityName", CommonUtils.getMaskInfo().getCity());
        locationTxt.setText(CommonUtils.getMaskInfo().getProvince() + " " + CommonUtils.getMaskInfo().getCity());
        mIDzAndZwPresenter.getCityCode(CommonUtils.getUserInfo().getToken(), map);
        initData();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_dz_and_zw;
    }

    /**
     * 初始化基础数据
     */
    private void initData() {
        title = getIntent().getStringExtra("title");
        titleTitleTxt.setText(title);
        titleRightImg.setVisibility(View.GONE);
        //根据type获取的值判断是地主页(0)还是展位页(1)，并获取相关页面的数据
        type = getIntent().getIntExtra("type", 0);
        String token = CommonUtils.getUserInfo().getToken();
        if (type == 0) {
            titleRightTxt.setText("我的街道");
            dzZwBuyDesc.setText("地主限量发行 (总量)");
            mIDzAndZwPresenter.getDzHeadInfo(token);
        } else {
            titleRightTxt.setText("我的展位");
            dzZwBuyDesc.setText("展位限量发行 (总量)");
            iconIm.setImageDrawable(getResources().getDrawable(R.drawable.zw_icon));
            mIDzAndZwPresenter.getZwHeadInfo(token);
        }
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (type == 0) {
                    //地主页面
                    loadMore = "onLoadMore";
                    initDzListInfo(buyStatus, page, code);
                } else {
                    //展位页面
                    loadMore = "onLoadMore";
                    initZwListInfo(buyStatus, page, code);
                }
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (page == 1) {
                    if (type == 0) {
                        //地主页面
                        loadMore = "onRefresh";
                        initDzListInfo(buyStatus, page, code);
                    } else {
                        //展位页面
                        loadMore = "onRefresh";
                        initZwListInfo(buyStatus, page, code);
                    }
                } else {
                    if (type == 0) {
                        //地主页面
                        loadMore = "onRefresh";
                        initDzListInfo(buyStatus, 1, code);
                    } else {
                        //展位页面
                        loadMore = "onRefresh";
                        initZwListInfo(buyStatus, 1, code);
                    }
                }
                refreshLayout.finishRefresh(1000);
            }
        });
    }

    /**
     * 自主选择城市街道筛选条件
     *
     * @param singleModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveCodeAndCityEventBus(SingleModel singleModel) {
        EventBus.getDefault().removeStickyEvent(singleModel);
        locality = singleModel.getCity();
        code = singleModel.getCode();
        locationTxt.setText(locality);
        locationChang = true;
        page = 1;
        if (type == 0) {
            areaType = 2;
            //地主页面
            initDzListInfo(buyStatus, page, code);
        } else {
            areaType = 1;
            //展位页面
            initZwListInfo(buyStatus, page, code);
        }
    }

    private int citiesPosition = 11;
    private int districtsPosition = 1;
    private int streetsPosition = 3;
    private int otherPosition = 3;

    /**
     * 记录地主选择城市位置
     *
     * @param dzCitySelectModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receivedzCityStatusEventBus(CitySelectStatusModel dzCitySelectModel) {
        EventBus.getDefault().removeStickyEvent(dzCitySelectModel);
        citiesPosition = dzCitySelectModel.getCitiesPosition();
        districtsPosition = dzCitySelectModel.getDistrictsPosition();
        streetsPosition = dzCitySelectModel.getStreetsPosition();
    }

    /**
     * 记录展位选择城市位置
     *
     * @param citySelectStatusModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receivezwCityStatusEventBus(CitySelectStatusModel citySelectStatusModel) {
        EventBus.getDefault().removeStickyEvent(citySelectStatusModel);
        citiesPosition = citySelectStatusModel.getCitiesPosition();
        districtsPosition = citySelectStatusModel.getDistrictsPosition();
        streetsPosition = citySelectStatusModel.getStreetsPosition();
        otherPosition = citySelectStatusModel.getOtherPosition();
    }


    @OnClick({R.id.buy_ll, R.id.location_ll, R.id.title_return_linear, R.id.title_right_linear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buy_ll:
                break;
            case R.id.location_ll:
                if (type == 0) {
                    //地主页面
                    DzCityFragment dzCityFragment = new DzCityFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("citiesPosition", citiesPosition);
                    bundle.putInt("districtsPosition", districtsPosition);
                    bundle.putInt("streetsPosition", streetsPosition);
                    if (citiesPosition == 11 && districtsPosition == 1 && streetsPosition == 3 && otherPosition == 3) {
                        bundle.putString("province", province);
                        bundle.putString("city", city);
                    }
                    dzCityFragment.setArguments(bundle);
                    dzCityFragment.show(getSupportFragmentManager(), "selectDzCity");
                } else {
                    //展位页面
                    ZwCityFragment zwCityFragment = new ZwCityFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("citiesPosition", citiesPosition);
                    bundle.putInt("districtsPosition", districtsPosition);
                    bundle.putInt("streetsPosition", streetsPosition);
                    bundle.putInt("otherPosition", otherPosition);
                    if (citiesPosition == 11 && districtsPosition == 1 && streetsPosition == 3 && otherPosition == 3) {
                        bundle.putString("province", province);
                        bundle.putString("city", city);
                    }
                    zwCityFragment.setArguments(bundle);
                    zwCityFragment.show(getSupportFragmentManager(), "selectZwCity");
                }
                break;
            case R.id.title_return_linear:
                if (CommonUtils.singleOnClick()) {
                    EventBus.getDefault().postSticky(new BaseResult("dzZw刷新"));
                    finish();
                }
                break;
            case R.id.title_right_linear:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MINE_DZAND_ZW)
                            .withInt("type", type)
                            .withString("intentType", "dzOrZw")
                            .withString("location", locationTxt.getText().toString())
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                    finish();
                }
                break;
        }
    }


    /**
     * 展位发起请求数据
     *
     * @param buyStatus 售卖状态(未售买：0；已售卖：1)
     * @param page      页数
     * @param code      城市编码
     */
    private void initZwListInfo(int buyStatus, int page, String code) {
        map = new HashMap<>();
        map.put("page", page);
        map.put("size", 15);
        map.put("type", buyStatus);
        map.put("areaCode", code);
        map.put("areaType", areaType);
        mIDzAndZwPresenter.getZwListNet(CommonUtils.getUserInfo().getToken(), map);
    }

    /**
     * 地主发起请求数据
     *
     * @param buyStatus 售卖状态(未售买：0；已售卖：1)
     * @param page      页数
     * @param code      城市编码
     */
    private void initDzListInfo(int buyStatus, int page, String code) {
        map.put("page", page);
        map.put("size", 15);
        map.put("type", buyStatus);
        map.put("areaCode", code);
        map.put("areaType", areaType);
        mIDzAndZwPresenter.getDzListNet(CommonUtils.getUserInfo().getToken(), map);
    }


    /**
     * 根据传入的isDzOrZw来判断展示地主或者展位的数据
     *
     * @param dzList
     * @param zwList
     * @param isDzOrZw
     */
    @SuppressLint("WrongConstant")
    private void initDzOrZwListRecycler(List<DzItemModel.DataBean.RecordsBean> dzList, List<ZwItemModel.DataBean.RecordsBean> zwList, String isDzOrZw) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        if ("dz".equals(isDzOrZw)) {
            qdzItemAdapter = new DzItemAdapter(dzList, this);
            qdzItemAdapter.isFirstOnly(false);
            qdzItemAdapter.addFooterView(getFooterView());
            if (dzList.size() < 1) {
                viewHolder.footEmpty.setVisibility(View.VISIBLE);
                viewHolder.footEmpty.setEmptyText("暂无数据,请上拉刷新");
                viewHolder.footEmpty.setImgWH(200, 200);
                viewHolder.footEmpty.setEmptyTextSize(30);
                viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.black));
                viewHolder.footEmpty.setTxtWH(500, 100);
            } else {
                viewHolder.footEmpty.setVisibility(View.GONE);
            }
            recyclerView.setAdapter(qdzItemAdapter);
        } else if ("zw".equals(isDzOrZw)) {
            zwItemAdapter = new ZwItemAdapter(zwList, this);
            zwItemAdapter.isFirstOnly(false);
            zwItemAdapter.addFooterView(getFooterView());
            if (zwList.size() < 1) {
                viewHolder.footEmpty.setVisibility(View.VISIBLE);
                viewHolder.footEmpty.setEmptyText("暂无数据");
                viewHolder.footEmpty.setImgWH(250, 250);
                viewHolder.footEmpty.setEmptyTextSize(28);
                viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.black));
                viewHolder.footEmpty.setTxtWH(500, 100);
            } else {
                viewHolder.footEmpty.setVisibility(View.GONE);
            }
            recyclerView.setAdapter(zwItemAdapter);
            zwItemAdapter.setOnZwItemListener(new ZwItemAdapter.OnZwItemListener() {
                @Override
                public void onZwItemClick(int position) {
                    mIDzAndZwPresenter.getChooseZwUnLock(CommonUtils.getUserInfo().getToken(), zwList.get(position).getId());
                }
            });
        }

    }

    private View getFooterView() {
        View view = getLayoutInflater().inflate(R.layout.item_pay_dz_zw_foot, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);
        view.setLayoutParams(layoutParams);
        viewHolder = new ViewHolder(view);
        viewHolder.toDzOrZw.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void getDzNetSuccess(DzItemModel dzItemModel) {
        if (dzItemModel.getCode() != 0) {
            ToastUtils.showShort(dzItemModel.getMessage());
        } else if (dzItemModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
        } else if (dzItemModel.getCode() == 0) {
            if ("onLoadMore".equals(loadMore) && page > 1 && !locationChang) {
                qdzItemAdapter.addData((page - 1) * 15, dzItemModel.getData().getRecords());
            } else if (locationChang && !"onRefresh".equals(loadMore)) {
                if (page == 1) {
                    qdzItemAdapter.getData().clear();
                    qdzItemAdapter.notifyDataSetChanged();
                }
                qdzItemAdapter.addData((page - 1) * 15, dzItemModel.getData().getRecords());
            } else {
                initDzOrZwListRecycler(dzItemModel.getData().getRecords(), null, "dz");
            }
            if (page == dzItemModel.getData().getPages()) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void getDzNetFailed(int netCode, String msg) {
        if (netCode == AppConfig.HTTP_TOKEN_ERROR) {
            CommonUtils.showLabelAlert(this, "");
            return;
        } else {
            ToastUtils.showShort(msg);
        }
    }

    @Override
    public void getZwNetSuccess(ZwItemModel zwItemModel) {
        if (zwItemModel.getCode() != 0) {
            ToastUtils.showShort(zwItemModel.getMessage());
        } else if (zwItemModel.getCode() == 0) {
            if ("onLoadMore".equals(loadMore) && page > 1 && !locationChang) {
                zwItemAdapter.addData((page - 1) * 15, zwItemModel.getData().getRecords());
            } else if (locationChang && !"onRefresh".equals(loadMore)) {
                if (page == 1) {
                    zwItemAdapter.getData().clear();
                    zwItemAdapter.notifyDataSetChanged();
                }
                zwItemAdapter.addData((page - 1) * 15, zwItemModel.getData().getRecords());
            } else {
                initDzOrZwListRecycler(null, zwItemModel.getData().getRecords(), "zw");
            }
            if (page == zwItemModel.getData().getPages()) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void getZwNetFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void getCityCodeSuccess(CityCode cityCode) {
        //  code:0:成功；1：失败
        if (cityCode.getCode() != 0) {
            ToastUtils.showShort(cityCode.getMessage());
        } else {
            code = cityCode.getData().getCode();
            if (type == 0) {
                //地主页面
                initDzListInfo(buyStatus, page, code);
            } else {
                //展位页面
                initZwListInfo(buyStatus, page, code);
            }
        }
    }


    @Override
    public void getCityCodeFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }


    @Override
    public void getZwHeadInfoSuccess(HomeInfoModel loginModel) {
        if (loginModel.getCode() == 0) {
            buyAllNum.setText(loginModel.getData().getTotalCount() + "");
            buyBuyNum.setText(loginModel.getData().getSoldCount() + "");
        }
    }

    @Override
    public void getZwHeadInfoFailed(int netCode, String msg) {

    }

    @Override
    public void getDzHeadInfoSuccess(HomeInfoModel loginModel) {
        if (loginModel.getCode() == 0) {
            buyAllNum.setText(loginModel.getData().getTotalCount() + "");
            buyBuyNum.setText(loginModel.getData().getSoldCount() + "");
        }
    }

    @Override
    public void getDzHeadInfoFailed(int netCode, String msg) {

    }

    @Override
    public void getChooseZwUnLockSuccess(BaseResult baseResult) {
        if (baseResult.getCode() == 0) {
            ARouter.getInstance()
                    .build(AppConfig.ACTIVITY_URL_MINE_DZAND_ZW)
                    .withInt("type", type)
                    .withString("intentType", "dzOrZw")
                    .withString("location", locationTxt.getText().toString())
                    //进入动画
                    .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    .navigation();
            finish();
            ToastUtils.showShort("解锁成功");
        } else {
            ToastUtils.showShort(baseResult.getMessage());
        }
    }

    @Override
    public void getChooseZwUnLockFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }


    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
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
}
