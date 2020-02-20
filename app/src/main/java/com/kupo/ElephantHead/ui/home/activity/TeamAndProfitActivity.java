package com.kupo.ElephantHead.ui.home.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.greendao.UserInfo;
import com.kupo.ElephantHead.greendao.UserInfoOperateDao;
import com.kupo.ElephantHead.ui.home.adapter.ProfitItemAdapter;
import com.kupo.ElephantHead.ui.home.adapter.TeamItemAdapter;
import com.kupo.ElephantHead.ui.home.model.TeamListModel;
import com.kupo.ElephantHead.ui.mvp.contract.ProfitAndTeamContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.model.RefreshModel;
import com.kupo.ElephantHead.ui.mvp.presenter.ProfitAndTeamPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.widget.EmptyLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import genealogy.jczb.com.rvlibrary.BaseQuickAdapter;

/**
 * 收益/团队
 */
@Route(path = AppConfig.ACTIVITY_URL_MAIN_TEAM)
public class TeamAndProfitActivity extends BaseActivity implements ProfitAndTeamContract.ITeamView {

    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_return_img)
    ImageView titleReturnImg;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.profit_team_tv)
    TextView profitTeamTv;
    @BindView(R.id.profit_team_im)
    ImageView profitTeamIm;
    @BindView(R.id.profit_team_desc_tv)
    TextView profitTeamDescTv;
    @BindView(R.id.profit_team_num_tv)
    TextView profitTeamNumTv;
    @BindView(R.id.empty)
    EmptyLayout empty;
    @BindView(R.id.profit_head_ll)
    LinearLayout profitHeadLl;
    private int type;
    private ProfitAndTeamContract.ITeamPresenter teamPresenter;
    private Map<String, Object> map;
    private int page = 1;
    ViewHolder viewHolder;
    String loadMore;
    TeamItemAdapter teamItemAdapter;
    ProfitItemAdapter profitItemAdapter;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        teamPresenter = new ProfitAndTeamPresenter();
        teamPresenter.attachView(this);
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.GONE);
        refreshLayout.setEnableAutoLoadMore(false);
        type = getIntent().getIntExtra("type", 0);
        if (type == 0) {
            //收益
            titleTitleTxt.setText("收支明细");
            profitTeamDescTv.setText("收支明细");
            profitTeamTv.setVisibility(View.GONE);
            profitTeamIm.setVisibility(View.GONE);
            profitHeadLl.setVisibility(View.GONE);
            initProfit(page);
            //   teamPresenter.getHomeUserInfoNet(CommonUtils.getUserInfo().getToken());
        } else {
            //团队
            titleTitleTxt.setText("我的团队");
            profitTeamTv.setVisibility(View.GONE);
            profitTeamDescTv.setText("直推/总人数");
            //获取团队头部信息
            teamPresenter.getUserTeamInfoNet(CommonUtils.getUserInfo().getToken(), "getTeam");
            initTeam(page);
        }
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (type == 0) {
                    //收益
                    teamPresenter.getHomeUserInfoNet(CommonUtils.getUserInfo().getToken());
                    initProfit(page);
                    loadMore = "onLoadMore";
                } else {
                    //团队
                    teamPresenter.getUserTeamInfoNet(CommonUtils.getUserInfo().getToken(), "getTeam");
                    initTeam(page);
                    loadMore = "onLoadMore";
                }
                refreshLayout.finishLoadMore(1000);
                //    refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (page > 1) {
                    page--;
                    if (type == 0) {
                        //收益
                        initProfit(page);
                        teamPresenter.getHomeUserInfoNet(CommonUtils.getUserInfo().getToken());
                    } else {
                        //团队
                        teamPresenter.getUserTeamInfoNet(CommonUtils.getUserInfo().getToken(), "getTeam");
                        initTeam(page);
                    }
                } else {
                    if (type == 0) {
                        //收益
                        initProfit(1);
                        teamPresenter.getHomeUserInfoNet(CommonUtils.getUserInfo().getToken());
                    } else {
                        //团队
                        teamPresenter.getUserTeamInfoNet(CommonUtils.getUserInfo().getToken(), "getTeam");
                        initTeam(1);
                    }
                }

                refreshLayout.finishRefresh(1000);
            }
        });
    }

    private void initTeam(int page) {
        //获取团队列表
        map = new HashMap<>();
        map.put("page", page);
        map.put("size", 15);
        teamPresenter.getUserTeamListNet(CommonUtils.getUserInfo().getToken(), "getTeamPage", map);
    }

    private void initProfit(int page) {
        //获取收益列表
        map = new HashMap<>();
        map.put("page", page);
        map.put("size", 15);
        teamPresenter.getUserProfitListNet(CommonUtils.getUserInfo().getToken(), "getPayLog", map);
    }

    private void initListData(List<TeamListModel.DataBean.RecordsBean> teamListModels, int type) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (type == 0) {
            teamItemAdapter = new TeamItemAdapter(teamListModels, this);
            teamItemAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            teamItemAdapter.isFirstOnly(false);
            teamItemAdapter.addFooterView(getFooterView());
            if (teamListModels.size() < 1) {
                viewHolder.footEmpty.setVisibility(View.VISIBLE);
                viewHolder.footEmpty.setEmptyText("暂无数据");
                viewHolder.footEmpty.setImgWH(250, 250);
                viewHolder.footEmpty.setEmptyTextSize(28);
                viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.black));
                viewHolder.footEmpty.setTxtWH(500, 100);
            } else {
                viewHolder.footEmpty.setVisibility(View.GONE);
            }
            recyclerView.setAdapter(teamItemAdapter);
        } else {
            profitItemAdapter = new ProfitItemAdapter(teamListModels, this);
            profitItemAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            profitItemAdapter.isFirstOnly(false);
            profitItemAdapter.addFooterView(getFooterView());
            if (teamListModels.size() < 1) {
                viewHolder.footEmpty.setVisibility(View.VISIBLE);
                viewHolder.footEmpty.setEmptyText("暂无数据");
                viewHolder.footEmpty.setImgWH(250, 250);
                viewHolder.footEmpty.setEmptyTextSize(28);
                viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.black));
                viewHolder.footEmpty.setTxtWH(500, 100);
            } else {
                viewHolder.footEmpty.setVisibility(View.GONE);
            }
            recyclerView.setAdapter(profitItemAdapter);
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


    @OnClick({R.id.title_return_linear, R.id.profit_team_tv, R.id.profit_team_im})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_linear:
                finish();
                EventBus.getDefault().postSticky(new RefreshModel("profit刷新"));
                break;
            case R.id.profit_team_im:
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_HOME_SHARE)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                break;
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_team;
    }


    @Override
    public void getUserTeamInfoNetSuccess(HomeInfoModel homeInfoModel) {
        //code:0:成功；1：失败
        if (homeInfoModel.getCode() != 0) {
            ToastUtils.showShort(homeInfoModel.getMessage());
        } else if (homeInfoModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
        } else {
            profitTeamNumTv.setText(homeInfoModel.getData().getDirectCount() + "/" + homeInfoModel.getData().getTotalCount());
        }
    }

    @Override
    public void getUserTeamInfoNetFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void getUserTeamListNetSuccess(TeamListModel teamListModel) {
        //code:0:成功；1：失败
        if (teamListModel.getCode() == 200) {
            CommonUtils.showLabelAlert(this, "");
        } else if (teamListModel.getCode() == 0) {
            if ("onLoadMore".equals(loadMore) && page > 1) {
                teamItemAdapter.addData((page - 1) * 15, teamListModel.getData().getRecords());
            } else {
                initListData(teamListModel.getData().getRecords(), 0);
            }
            if (page == teamListModel.getData().getPages()) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        } else {
            ToastUtils.showShort(teamListModel.getMessage());
        }
    }

    @Override
    public void getUserTeamListNetFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void getUserProfitListNetSuccess(TeamListModel teamListModel) {
        //code:0:成功；1：失败
        if (teamListModel.getCode() == 200) {
            CommonUtils.showLabelAlert(this, "");
        } else if (teamListModel.getCode() == 0) {
            if ("onLoadMore".equals(loadMore) && page > 1) {
                profitItemAdapter.addData((page - 1) * 15, teamListModel.getData().getRecords());
            } else {
                initListData(teamListModel.getData().getRecords(), 1);
            }
            if (page == teamListModel.getData().getPages()) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        } else {
            ToastUtils.showShort(teamListModel.getMessage());
        }
    }

    @Override
    public void getUserProfitListNetFailed(int netCode, String msg) {
        if (netCode == AppConfig.HTTP_TOKEN_ERROR) {
            CommonUtils.showLabelAlert(this, "");
            return;
        } else {
            ToastUtils.showShort(msg);
        }
    }

    @Override
    public void getHomeUserInfoNetSuccess(HomeInfoModel loginModel) {
        profitTeamNumTv.setText(loginModel.getData().getProfit() + "");
    }

    @Override
    public void getHomeUserInfoNetFailed(int netCode, String msg) {
        if (netCode == AppConfig.HTTP_TOKEN_ERROR) {
            CommonUtils.showLabelAlert(this, "");
            return;
        } else {
            ToastUtils.showShort(msg);
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
}
