package com.kupo.ElephantHead.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseFragment;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.greendao.MaskInfo;
import com.kupo.ElephantHead.greendao.MaskInfoOperateDao;
import com.kupo.ElephantHead.greendao.UserInfo;
import com.kupo.ElephantHead.greendao.UserInfoOperateDao;
import com.kupo.ElephantHead.holder.CustomViewHolder;
import com.kupo.ElephantHead.ui.home.adapter.HomeTopAdapter;
import com.kupo.ElephantHead.ui.home.fragment.AppUpdateFragment;
import com.kupo.ElephantHead.ui.home.model.AppUpdateModel;
import com.kupo.ElephantHead.ui.home.model.BannerModel;
import com.kupo.ElephantHead.ui.home.model.CurrentUserModel;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.mvp.contract.HomeContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.RefreshModel;
import com.kupo.ElephantHead.ui.mvp.presenter.HomePresenter;
import com.kupo.ElephantHead.utils.AppUtils;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.utils.StatusBarUtil;
import com.kupo.ElephantHead.widget.EmptyLayout;
import com.ms.banner.Banner;
import com.ms.banner.BannerConfig;
import com.ms.banner.listener.OnBannerClickListener;
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
 * 主页模块
 */
public class HomeFragment extends BaseFragment implements HomeContract.IHomeView {
    @BindView(R.id.home_dz_ll)
    LinearLayout homeDzLl;
    @BindView(R.id.home_zw_ll)
    LinearLayout homeZwLl;
    @BindView(R.id.profit)
    LinearLayout profitLl;
    @BindView(R.id.team)
    LinearLayout team;
    @BindView(R.id.home_message)
    RelativeLayout homeMessage;
    @BindView(R.id.tv_price)
    TextView tvProfit;
    @BindView(R.id.tv_num)
    TextView tvTeam;
    @BindView(R.id.home_message_num)
    TextView homeMessageNum;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.home_message_new)
    TextView homeMessageNew;
    @BindView(R.id.home_zw_all_num)
    TextView homeZwAllNum;
    @BindView(R.id.home_dz_all_num)
    TextView homeDzAllNum;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.lLayoutIndicator)
    LinearLayout lLayoutIndicator;
    @BindView(R.id.zcw_bg_im)
    ImageView zcwBgIm;
    @BindView(R.id.home_zw_im_gif)
    ImageView homeZwImGif;
    @BindView(R.id.home_dz_im_gif)
    ImageView homeDzImGif;
    private OnFragmentInteractionListener mListener;
    private HomeContract.IHomePresenter mHomePresenter = null;
    private double zcwMoney = 10.0;
    List<BannerModel.DataBean> bannerList = new ArrayList<>();
    int page = 1;
    ViewHolder viewHolder;
    //    List<HomeTopModel.DataBean.RecordsBean> recordsBeans = new ArrayList<>();
    String loadMore;
    HomeTopAdapter homeTopAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
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
    protected void init() {
        mHomePresenter = new HomePresenter();
        mHomePresenter.attachView(this);
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initTopList(page);
                loadMore = "onLoadMore";
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (page > 1) {
                    page--;
                    initTopList(page);
                } else {
                    initNet();
                }
                refreshLayout.finishRefresh(1000);
            }
        });
        mHomePresenter.getAppUpdateInfo(CommonUtils.getUserInfo().getToken(), 0);
        Glide.with(this).load(R.drawable.to_make).into(zcwBgIm);
        Glide.with(this).load(R.drawable.home_zw).into(homeZwImGif);
        Glide.with(this).load(R.drawable.home_dz).into(homeDzImGif);
    }

    @Override
    protected void onInitPresenters() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setStatusBarMode(mActivity, true, R.color.white);
        }
//        if (MaskInfoOperateDao.queryMaskAll().size() < 1) {
//            ARouter.getInstance()
//                    .build(AppConfig.ACTIVITY_URL_HOME_MASK)
//                    .withDouble("price", zcwMoney)
//                    //进入动画
//                    .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
//                    .navigation();
//        }
        initNet();
        MaskInfo maskInfo = CommonUtils.getMaskInfo();
        MaskInfoOperateDao.deleteMaskBean();
        maskInfo.setDesc("出现蒙版");
        maskInfo.setIsShow(1);
        MaskInfoOperateDao.insertMaskInfo(maskInfo);
    }

    /**
     * 初始化项目头条列表
     */
    @SuppressLint("WrongConstant")
    private void initTopList(List<HomeTopModel.DataBean.RecordsBean> recordsBeans) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        homeTopAdapter = new HomeTopAdapter(recordsBeans, getActivity());
        homeTopAdapter.isFirstOnly(false);
        homeTopAdapter.addFooterView(getFooterView());
        if (recordsBeans.size() < 1 && recordsBeans != null) {
            viewHolder.footEmpty.setVisibility(View.VISIBLE);
            viewHolder.footEmpty.setEmptyText("暂无头条数据");
            viewHolder.footEmpty.setImgWH(250, 250);
            viewHolder.footEmpty.setEmptyTextSize(28);
            viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.black));
            viewHolder.footEmpty.setTxtWH(500, 100);
        } else {
            viewHolder.footEmpty.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(homeTopAdapter);
    }

    private void initTopList(int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("size", 15);
        map.put("areaType", 4);
        mHomePresenter.getHomeTopList(CommonUtils.getUserInfo().getToken(), map);
    }


    private void initNet() {
        String token = CommonUtils.getUserInfo().getToken();
        mHomePresenter.getHomeBannerListNet(token, "-1");
        mHomePresenter.getHomeUserInfoNet(token);
        mHomePresenter.getHomeDzInfoNet(CommonUtils.getUserInfo().getToken());
        mHomePresenter.getCurrentUserInfo(token);
        mHomePresenter.getHomeInfoNet(token);
        Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("size", 15);
        map.put("areaType", 4);
        mHomePresenter.getHomeTopList(token, map);

    }

    private void initBanner(List<BannerModel.DataBean> bannerList) {
        //开始banner以及页面的点击事件
        banner.setAutoPlay(true)
                .setIndicatorRes(R.drawable.ic_banner_on, R.drawable.ic_banner_off)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setDelayTime(5000)
                .setPages(bannerList, new CustomViewHolder())
                .setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void onBannerClick(List datas, int position) {
                        if (bannerList.get(position).getType() == 4) {
                            if (CommonUtils.singleOnClick()) {
                                ARouter.getInstance()
                                        .build(AppConfig.ACTIVITY_URL_MAIN_WEB_VIEW)
                                        .withString("downLoadUrl", bannerList.get(position).getItemId())
                                        .withString("title", "攻略详情")
                                        //进入动画
                                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                        .navigation();
                            }

                        }
                    }
                })
                .start();

    }

    @OnClick({R.id.home_dz_im_gif, R.id.home_zw_im_gif, R.id.profit, R.id.team, R.id.home_message, R.id.zcw_bg_im, R.id.home_toTop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_dz_im_gif:
                //type:0代表地主页面跳转;1:代表展位页面跳转
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_HOME_QDZ)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .withInt("type", 0)
                            .withString("title", "地主交易")
                            .navigation();
                }
                break;
            case R.id.home_zw_im_gif:
                //type:0代表地主页面跳转;1:代表展位页面跳转
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_HOME_QDZ)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .withInt("type", 1)
                            .withString("title", "展位交易")
                            .navigation();
                }
                break;
            case R.id.profit:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_HOME_PROFIT)
                            .withInt("type", 0)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
            case R.id.team:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MAIN_TEAM)
                            .withInt("type", 1)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
            case R.id.home_message:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MAIN_MESSAGE)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
            case R.id.zcw_bg_im:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MINE_ADD)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
            case R.id.home_toTop:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MINE_KEFU)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
        }
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

    @Override
    public void getHomeNetSuccess(HomeInfoModel loginModel) {
        //code:0:成功；1：失败
        if (loginModel.getCode() != 0) {
            ToastUtils.showShort(loginModel.getMessage());
        } else if (loginModel.getCode() == 0) {
            homeZwAllNum.setText(loginModel.getData().getTotalCount() + "");
        }
    }

    @Override
    public void getHomeNetFailed(int netCode, String msg) {
        if (netCode == AppConfig.HTTP_TOKEN_ERROR) {
            CommonUtils.showLabelAlert(getActivity(), "");
            return;
        } else {
            ToastUtils.showShort(msg);
        }
    }

    @Override
    public void getHomeDzNetSuccess(HomeInfoModel loginModel) {
        //code:0:成功；1：失败
        if (loginModel.getCode() != 0) {
            ToastUtils.showShort(loginModel.getMessage());
        } else if (loginModel.getCode() == 0) {
            homeDzAllNum.setText(loginModel.getData().getTotalCount() + "");
        } else if (loginModel.getCode() == 100) {
            CommonUtils.showLabelAlert(mActivity, "");
        }
    }

    @Override
    public void getHomeDzNetFailed(int netCode, String msg) {
        if (netCode == AppConfig.HTTP_TOKEN_ERROR) {
            CommonUtils.showLabelAlert(getActivity(), "");
            return;
        } else {
            ToastUtils.showShort(msg);
        }
    }

    @Override
    public void getHomeUserInfoNetSuccess(HomeInfoModel loginModel) {
        //code:0:成功；1：失败
        if (loginModel.getCode() != 0) {
            ToastUtils.showShort(loginModel.getMessage());
        } else if (loginModel.getCode() == 100) {
            CommonUtils.showLabelAlert(mActivity, "");
            return;
        } else {
            if (loginModel.getData().getMessage() > 0) {
                homeMessageNew.setVisibility(View.VISIBLE);
            } else {
                homeMessageNew.setVisibility(View.GONE);
            }
            tvTeam.setText(loginModel.getData().getTeam() + "");
            homeMessageNum.setText(loginModel.getData().getMessage() + "");
            zcwMoney = loginModel.getData().getVipPrice();
        }

    }

    @Override
    public void getHomeUserInfoNetFailed(int netCode, String msg) {
        if (netCode == AppConfig.HTTP_TOKEN_ERROR) {
            CommonUtils.showLabelAlert(getActivity(), "");
            return;
        } else {
            ToastUtils.showShort(msg);
        }

    }

    @Override
    public void getCurrentUserInfoNetSuccess(CurrentUserModel currentUserModel) {
        UserInfo userInfo = CommonUtils.getUserInfo();
        if (currentUserModel.getCode() == 0) {
            tvProfit.setText(currentUserModel.getData().getBalance() + "");
            userInfo.setIsVip(currentUserModel.getData().isIsVip());
            userInfo.setLevel(currentUserModel.getData().getLevel());
            userInfo.setBalance(currentUserModel.getData().getBalance());
            userInfo.setRegCode(currentUserModel.getData().getRegCode());
            userInfo.setLevelName(currentUserModel.getData().getLevelName());
            userInfo.setChooseBoothCount(currentUserModel.getData().getChooseBoothCount());
            UserInfoOperateDao.deleteDataBean();
            UserInfoOperateDao.insertUserInfo(userInfo);
        } else if (currentUserModel.getCode() == 100) {
            CommonUtils.showLabelAlert(mActivity, "");
            return;
        }

    }

    @Override
    public void getCurrentUserInfoNetFailed(int netCode, String msg) {
        if (netCode == AppConfig.HTTP_TOKEN_ERROR) {
            CommonUtils.showLabelAlert(getActivity(), "");
            return;
        }
    }

    @Override
    public void getAppUpdateInfoNetSuccess(AppUpdateModel appUpdateModel) {
        if (appUpdateModel.getCode() == 0) {
            if (AppUtils.getVerStrCode(getActivity()) < Double.valueOf((appUpdateModel.getData().getAppVersion()))) {
                AppUpdateFragment appUpdateFragment = new AppUpdateFragment();
                Bundle bundle = new Bundle();
                bundle.putString("appVersion", appUpdateModel.getData().getAppVersion());
                bundle.putString("description", appUpdateModel.getData().getDescription());
                bundle.putString("downloadPth", appUpdateModel.getData().getDownloadPth());
                bundle.putBoolean("forceUpdate", appUpdateModel.getData().isForceUpdate());
                appUpdateFragment.setArguments(bundle);//数据传递到fragment中
                appUpdateFragment.show(getChildFragmentManager(), "appUpdate");
            }
        }

    }

    @Override
    public void getAppUpdateInfoNetFailed(int netCode, String msg) {
        if (netCode == AppConfig.HTTP_TOKEN_ERROR) {
            CommonUtils.showLabelAlert(getActivity(), "");
            return;
        } else {
            ToastUtils.showShort(msg);
        }
    }

    @Override
    public void getHomeTopListNetSuccess(HomeTopModel homeTopModel) {
        if (homeTopModel.getCode() == 0) {
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
            return;
        } else {
            ToastUtils.showShort(homeTopModel.getMessage());
        }
    }


    @Override
    public void getHomeTopListNetFailed(int netCode, String msg) {
        if (netCode == AppConfig.HTTP_TOKEN_ERROR) {
            CommonUtils.showLabelAlert(getActivity(), "");
            return;
        } else {
            ToastUtils.showShort(msg);
        }
    }

    @Override
    public void getHomeBannerListNetSuccess(BannerModel bannerModel) {
        if (bannerModel.getCode() == 0) {
            bannerList = bannerModel.getData();
            initBanner(bannerList);
        } else if (bannerModel.getCode() == 100) {
            CommonUtils.showLabelAlert(getActivity(), "");
        } else {
            ToastUtils.showShort(bannerModel.getMessage());
        }
    }

    @Override
    public void getHomeBannerListNetFailed(int netCode, String msg) {
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

    /**
     * 通知刷新
     *
     * @param baseResult
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveTopRefreshEventBus(BaseResult baseResult) {
        if ("top刷新".equals(baseResult.getMessage())) {
            initNet();
        }

    }

    /**
     * 通知刷新
     *
     * @param baseResult
     */
    @Subscribe(sticky = true)
    public void receiveWalletRefreshEventBus(BaseResult baseResult) {
        if ("wallet刷新".equals(baseResult.getMessage())) {
            initNet();
        }

    }

    /**
     * 通知刷新
     *
     * @param baseResult
     */
    @Subscribe(sticky = true)
    public void receiveZcwRefreshEventBus(BaseResult baseResult) {
        if ("zcw刷新".equals(baseResult.getMessage())) {
            initNet();
        }

    }

    /**
     * 通知刷新
     *
     * @param baseResult
     */
    @Subscribe(sticky = true)
    public void receiveDzZwRefreshEventBus(BaseResult baseResult) {
        if ("dzZw刷新".equals(baseResult.getMessage())) {
            initNet();
        }

    }

    /**
     * 通知刷新
     *
     * @param baseResult
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveGlRefreshEventBus(BaseResult baseResult) {
        if ("zcw刷新".equals(baseResult.getMessage())) {
            initNet();
        }

    }

    /**
     * 通知刷新
     *
     * @param baseResult
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveFxRefreshEventBus(BaseResult baseResult) {
        if ("fx刷新".equals(baseResult.getMessage())) {
            initNet();
        }
    }

    /**
     * 通知刷新
     *
     * @param baseResult
     */
    @Subscribe(sticky = true)
    public void receivexxRefreshEventBus(RefreshModel baseResult) {
        if ("消息刷新".equals(baseResult.getIsRefresh())) {
            EventBus.getDefault().removeStickyEvent(baseResult);
            mHomePresenter.getHomeUserInfoNet(CommonUtils.getUserInfo().getToken());
        }
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
