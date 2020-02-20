package com.kupo.ElephantHead.ui.home.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.ui.home.adapter.MinePayDzAdapter;
import com.kupo.ElephantHead.ui.home.adapter.MinePayZwAdapter;
import com.kupo.ElephantHead.ui.home.model.DzLockModel;
import com.kupo.ElephantHead.ui.home.model.MinePayDzOrZwModel;
import com.kupo.ElephantHead.ui.mvp.contract.MinePayDzOrZwContract;
import com.kupo.ElephantHead.ui.mvp.presenter.MinePayDzOrZwPresenter;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.widget.EmptyLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 已经购买的展位和地主
 */
@Route(path = AppConfig.ACTIVITY_URL_MINE_DZAND_ZW)
public class MineDzAndZwActivity extends BaseActivity implements MinePayDzOrZwContract.IMinePayDzOrZwView {

    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.my_zw_all_num_tv)
    TextView myZwAllNumTv;
    private int type;
    Map<String, Object> map = null;
    private int page = 1;
    private MinePayDzOrZwContract.IMinePayDzOrZwPresenter minePayDzOrZwPresenter;
    MinePayDzAdapter minePayDzAdapter;
    MinePayZwAdapter minePayZwAdapter;
    String loadMore;
    private String intentType;
    HeaderViewHolder headerViewHolder;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        minePayDzOrZwPresenter = new MinePayDzOrZwPresenter();
        minePayDzOrZwPresenter.attachView(this);
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.GONE);
        type = getIntent().getIntExtra("type", 0);
        intentType = getIntent().getStringExtra("intentType");
        if (type == 0) {
            titleTitleTxt.setText("我的地主");
            initPayList(page, "street", "getCurrentUserStreet");
        } else {
            titleTitleTxt.setText("我的展位");
            initPayList(page, "booth", "getCurrentUserBooth");
        }
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                loadMore = "onLoadMore";
                if (type == 0) {
                    //地主页面
                    initPayList(page, "street", "getCurrentUserStreet");
                } else {
                    //展位页面
                    initPayList(page, "booth", "getCurrentUserBooth");
                }
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (page > 1) {
                    page--;
                    if (type == 0) {
                        //地主页面
                        initPayList(page, "street", "getCurrentUserStreet");
                    } else {
                        //展位页面
                        initPayList(page, "booth", "getCurrentUserBooth");
                    }
                } else {
                    if (type == 0) {
                        //地主页面
                        initPayList(1, "street", "getCurrentUserStreet");
                    } else {
                        //展位页面
                        initPayList(1, "booth", "getCurrentUserBooth");
                    }
                }
                refreshLayout.finishRefresh(1000);
            }
        });
    }

    private void initPayList(int page, String centerType, String footType) {
        map = new HashMap<>();
        map.put("page", page);
        map.put("size", 15);
        map.put("status", 1);
        minePayDzOrZwPresenter.getMinePayDzOrZwNet(CommonUtils.getUserInfo().getToken(), centerType, footType, map);
    }

    @OnClick({R.id.title_return_linear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_return_linear:
                if (type == 0) {
                    if (intentType.equals("dzOrZw")) {
                        ARouter.getInstance()
                                .build(AppConfig.ACTIVITY_URL_HOME_QDZ)
                                //进入动画
                                .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                .withInt("type", type)
                                .withString("title", type == 0 ? "地主交易" : "展位交易")
                                .withString("location", getIntent().getStringExtra("location"))
                                .navigation();
                        finish();
                    } else {
                        finish();
                    }
                } else if (type == 1) {
                    if (intentType.equals("dzOrZw")) {
                        ARouter.getInstance()
                                .build(AppConfig.ACTIVITY_URL_HOME_QDZ)
                                //进入动画
                                .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                .withInt("type", type)
                                .withString("title", type == 0 ? "地主交易" : "展位交易")
                                .withString("location", getIntent().getStringExtra("location"))
                                .navigation();
                        finish();
                    } else {
                        finish();
                    }
                }
                break;
        }
    }

    ViewHolder viewHolder = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_dz_and_zw;
    }

    private View getFooterView() {
        View view = getLayoutInflater().inflate(R.layout.item_pay_dz_zw_foot, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);
        view.setLayoutParams(layoutParams);
        viewHolder = new ViewHolder(view);
        if (type == 0) {
            viewHolder.toDzOrZw.setText("去抢地主");
        } else {
            viewHolder.toDzOrZw.setText("去抢展位");
        }
        viewHolder.toDzOrZw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 0) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_HOME_QDZ)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .withInt("type", type)
                            .withString("title", type == 0 ? "地主交易" : "展位交易")
                            .navigation();
                } else {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_HOME_QDZ)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .withInt("type", type)
                            .withString("title", type == 0 ? "地主交易" : "展位交易")
                            .navigation();
                }
                finish();
            }
        });
        return view;
    }

    private View getHeaderView() {
        minePayDzOrZwPresenter.getChooseCount(CommonUtils.getUserInfo().getToken());
        View view = getLayoutInflater().inflate(R.layout.activity_unlock_zw, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 20);
        view.setLayoutParams(layoutParams);
        headerViewHolder = new HeaderViewHolder(view);
        headerViewHolder.toUnLockTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_HOME_QDZ)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .withInt("type", 1)
                        .withString("title", "展位交易")
                        .navigation();
                finish();
            }
        });
        return view;
    }


    /**
     * 地主
     *
     * @param dzList
     */
    @SuppressLint("WrongConstant")
    private void initDzOrZwListRecycler(List<MinePayDzOrZwModel.DataBean.RecordsBean> dzList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        minePayDzAdapter = new MinePayDzAdapter(dzList, this);
        minePayDzAdapter.isFirstOnly(false);
        minePayDzAdapter.addHeaderView(getHeaderView());
        minePayDzAdapter.addFooterView(getFooterView());
        if (dzList.size() < 1) {
            viewHolder.footEmpty.setVisibility(View.VISIBLE);
            viewHolder.footEmpty.setEmptyText("暂无数据");
            viewHolder.footEmpty.setImgWH(250, 250);
            viewHolder.footEmpty.setEmptyTextSize(30);
            viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.black));
            viewHolder.footEmpty.setTxtWH(500, 100);
        } else {
            viewHolder.footEmpty.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(minePayDzAdapter);

    }

    /**
     * 展位
     *
     * @param zwList
     */
    @SuppressLint("WrongConstant")
    private void initZwListRecycler(List<MinePayDzOrZwModel.DataBean.RecordsBean> zwList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        minePayZwAdapter = new MinePayZwAdapter(zwList, this);
        minePayZwAdapter.isFirstOnly(false);
        minePayZwAdapter.addFooterView(getFooterView());
        if (zwList.size() < 1) {
            viewHolder.footEmpty.setVisibility(View.VISIBLE);
            viewHolder.footEmpty.setEmptyText("暂无数据");
            viewHolder.footEmpty.setImgWH(250, 250);
            viewHolder.footEmpty.setEmptyTextSize(30);
            viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.black));
            viewHolder.footEmpty.setTxtWH(500, 100);
        } else {
            viewHolder.footEmpty.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(minePayZwAdapter);

    }

    @Override
    public void getMinePayDzOrZwNetSuccess(MinePayDzOrZwModel minePayDzOrZwModel) {
        if (minePayDzOrZwModel.getCode() == 0) {
            if (type == 0) {
                String str = "当前有<font color='#F85F53'>" + minePayDzOrZwModel.getData().getTotal() + "</font>个地主";
                myZwAllNumTv.setTextSize(14);
                myZwAllNumTv.setText(Html.fromHtml(str));
                if ("onLoadMore".equals(loadMore) && page > 1) {
                    minePayDzAdapter.addData((page - 1) * 15, minePayDzOrZwModel.getData().getRecords());
                } else {
                    initDzOrZwListRecycler(minePayDzOrZwModel.getData().getRecords());
                }
            } else {
                String str = "当前有<font color='#F85F53'>" + minePayDzOrZwModel.getData().getTotal() + "</font>个展位";
                myZwAllNumTv.setTextSize(14);
                myZwAllNumTv.setText(Html.fromHtml(str));
                if ("onLoadMore".equals(loadMore) && page > 1) {
                    minePayZwAdapter.addData((page - 1) * 15, minePayDzOrZwModel.getData().getRecords());
                } else {
                    initZwListRecycler(minePayDzOrZwModel.getData().getRecords());
                }
            }
            if (page == minePayDzOrZwModel.getData().getPages()) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        } else if (minePayDzOrZwModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
        } else {
            ToastUtils.showShort(minePayDzOrZwModel.getMessage());
        }
    }

    @Override
    public void getMinePayDzOrZwNetFailed(int netCode, String msg) {
        if (netCode == AppConfig.HTTP_TOKEN_ERROR) {
            CommonUtils.showLabelAlert(this, "");
            return;
        } else {
            ToastUtils.showShort(msg);
        }
    }

    @Override
    public void getChooseCountSuccess(DzLockModel dzLockModel) {
        if (dzLockModel.getCode() == 0) {
            String str = "未解锁展位：<font color='#ffa647'>" + dzLockModel.getData().getCanUsed() + "</font>个";
            headerViewHolder.newNumTv.setTextSize(14);
            headerViewHolder.newNumTv.setText(Html.fromHtml(str));
            headerViewHolder.allNumTv.setText("总特权展位：" + dzLockModel.getData().getTotal() + "个");
            if (dzLockModel.getData().getCanUsed() == 0) {
                headerViewHolder.toUnLockTv.setBackground(getResources().getDrawable(R.drawable.bg_content_gray));
                headerViewHolder.toUnLockTv.setText("去解锁");
                headerViewHolder.toUnLockTv.setEnabled(false);
            }
        }
    }

    @Override
    public void getChooseCountFailed(int netCode, String msg) {

    }

    static
    class ViewHolder {
        @BindView(R.id.toDzOrZw)
        TextView toDzOrZw;
        @BindView(R.id.foot_empty)
        EmptyLayout footEmpty;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static
    class HeaderViewHolder {
        @BindView(R.id.all_num_tv)
        TextView allNumTv;
        @BindView(R.id.new_num_tv)
        TextView newNumTv;
        @BindView(R.id.to_unLock_tv)
        TextView toUnLockTv;

        HeaderViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
