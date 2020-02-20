package com.kupo.ElephantHead.ui.mine.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.ui.home.model.HomeTopModel;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.mine.adapter.MyCollectAdapter;
import com.kupo.ElephantHead.ui.mine.adapter.MyProjectAdapter;
import com.kupo.ElephantHead.ui.mvp.contract.CollectContract;
import com.kupo.ElephantHead.ui.mvp.contract.MyProjectContract;
import com.kupo.ElephantHead.ui.mvp.presenter.CollectPresenter;
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

/**
 * 我的收藏
 */
@Route(path = AppConfig.ACTIVITY_URL_MINE_COLLECT)
public class MyCollectActivity extends BaseActivity implements CollectContract.ICollectView {


    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.empty)
    EmptyLayout empty;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private CollectContract.ICollectPresenter collectPresenter;
    private Map<String, Object> map;
    private int page = 1;
    MyCollectAdapter adapter;
    ViewHolder viewHolder;
    String loadMore;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        collectPresenter = new CollectPresenter();
        collectPresenter.attachView(this);
        titleTitleTxt.setText("我的收藏");
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.GONE);
        initListNet(page);
        titleReturnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initListNet(page);
                loadMore = "onLoadMore";
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (page > 1) {
                    page--;
                    initListNet(page);
                } else {
                    initListNet(1);
                }
                refreshLayout.finishRefresh(1000);
            }
        });

    }

    private void initListNet(int page) {
        map = new HashMap<>();
        map.put("page", page);
        map.put("size", 15);
        collectPresenter.getCollectListInfo(CommonUtils.getUserInfo().getToken(), map);
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

    private void initData(List<HomeTopModel.DataBean.RecordsBean> recordsBeans) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyCollectAdapter(recordsBeans, this);
        adapter.isFirstOnly(false);
        adapter.addFooterView(getFooterView());
        if (recordsBeans.size() < 1 && recordsBeans != null) {
            viewHolder.footEmpty.setVisibility(View.VISIBLE);
            if (page > 1) {
                viewHolder.footEmpty.setEmptyText("暂无数据请下拉刷新");
            } else {
                viewHolder.footEmpty.setEmptyText("暂无数据");
            }
            viewHolder.footEmpty.setImgWH(250, 250);
            viewHolder.footEmpty.setEmptyTextSize(30);
            viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.black));
            viewHolder.footEmpty.setTxtWH(500, 100);
        } else {
            viewHolder.footEmpty.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collect;
    }

    @Override
    public void getCollectListSuccess(HomeTopModel roomIDetailsModel) {
        if (roomIDetailsModel.getCode() == 0) {
            if ("onLoadMore".equals(loadMore) && page > 1) {
                adapter.addData((page - 1) * 15, roomIDetailsModel.getData().getRecords());
            } else {
                initData(roomIDetailsModel.getData().getRecords());
            }
        } else if (roomIDetailsModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
        } else {
            ToastUtils.showShort(roomIDetailsModel.getMessage());
        }
    }

    @Override
    public void getCollectListFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
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
