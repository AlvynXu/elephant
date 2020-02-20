package com.kupo.ElephantHead.ui.room.activity;


import android.annotation.SuppressLint;
import android.graphics.Color;
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
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.ui.home.model.MinePayDzOrZwModel;
import com.kupo.ElephantHead.ui.mvp.contract.OperationContract;
import com.kupo.ElephantHead.ui.mvp.presenter.OperationPresenter;
import com.kupo.ElephantHead.ui.room.adapter.OperationItemAdapter;
import com.kupo.ElephantHead.ui.room.model.SaveBoothModel;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.widget.EmptyLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 展位管理
 */
@Route(path = AppConfig.ACTIVITY_URL_ROOM_OPERATION)
public class OperationActivity extends BaseActivity implements OperationContract.IOperationView {
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
    @BindView(R.id.operation_num_tv)
    TextView operationNumTv;
    private OperationContract.IOperationPresenter operationPresenter;
    private Map<String, Object> map;
    int page = 1;
    int count = 0;
    private List<Integer> list = new ArrayList<>();
    boolean isFlag = false;
    ViewHolder viewHolder;
    String loadMore;
    List<MinePayDzOrZwModel.DataBean.RecordsBean> operationList = new ArrayList<>();
    private OperationItemAdapter operationItemAdapter;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        operationPresenter = new OperationPresenter();
        operationPresenter.attachView(this);
        titleTitleTxt.setText("展位管理");
        titleRightTxt.setVisibility(View.VISIBLE);
        titleRightTxt.setTextColor(Color.parseColor("#ffcc00"));
        titleRightTxt.setText("购买展位");
        int itemId = getIntent().getIntExtra("detailsId", 1);
        titleRightImg.setVisibility(View.GONE);
        titleReturnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MINE_PROJECT)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                finish();
            }
        });
        titleRightTxt.setOnClickListener(new View.OnClickListener() {
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
        operationNumTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() > 0) {
                    map = new HashMap<>();
                    map.put("itemId", itemId);
                    map.put("boothIds", list);
                    RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), new JSONObject(map).toString());
                    operationPresenter.saveItemBooth(CommonUtils.getUserInfo().getToken(), requestBaseUpdateBody);
                } else {
                    ToastUtils.showShort("请选择一个展位");
                }
            }
        });
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initNet(page);
                loadMore = "onLoadMore";
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (page > 1) {
                    page--;
                    initNet(page);
                } else {
                    initNet(1);
                }
                list.clear();
                operationNumTv.setText("请选择展位");
                refreshLayout.finishRefresh(1000);
            }
        });
        initNet(page);
    }

    private void initNet(int page) {
        map = new HashMap<>();
        map.put("page", page);
        map.put("size", 15);
        operationPresenter.getCanUseBooth(CommonUtils.getUserInfo().getToken(), map);
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
     * 展位管理列表
     *
     * @param operationList
     */
    @SuppressLint("WrongConstant")
    private void initZwListRecycler(List<MinePayDzOrZwModel.DataBean.RecordsBean> operationList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        operationItemAdapter = new OperationItemAdapter(operationList, this);
        operationItemAdapter.isFirstOnly(false);
        operationItemAdapter.addFooterView(getFooterView());
        if (operationList.size() < 1 && operationList != null) {
            viewHolder.footEmpty.setEmptyText("暂无数据");
            viewHolder.footEmpty.setImgWH(250, 250);
            viewHolder.footEmpty.setEmptyTextSize(28);
            viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.black));
            viewHolder.footEmpty.setTxtWH(500, 100);
        } else {
            viewHolder.footEmpty.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(operationItemAdapter);
        operationItemAdapter.setOnItemRemoveListener(new OperationItemAdapter.OnItemRemoveListener() {
            @Override
            public void onItemRemoveClick(int position, int flag) {
                //+1
                if (flag == 1) {
                    //点击时显示选中图标
                    isFlag = true;
                    list.add(operationList.get(position).getId());
                } else {
                    if (list.size() > 0) {
                        Iterator<Integer> it = list.iterator();
                        while (it.hasNext()) {
                            Integer userObj = it.next();
                            if (userObj == operationList.get(position).getId()) {
                                it.remove();
                            }
                        }
                    }
                }
                count = list.size();
                if (count < 1) {
                    operationNumTv.setBackground(getResources().getDrawable(R.drawable.bg_content_white_border_fe));
                    operationNumTv.setTextColor(Color.parseColor("#ff4d4d"));
                    operationNumTv.setText("*请选择展位*");
                } else {
                    operationNumTv.setBackground(getResources().getDrawable(R.drawable.rectangle_green));
                    operationNumTv.setTextColor(Color.parseColor("#ffffff"));
                    operationNumTv.setText("确认选择(" + count + ")");
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_operation;
    }

    @Override
    public void getCanUseBoothSuccess(MinePayDzOrZwModel zwItemModel) {
        if (zwItemModel.getCode() == 0) {
            if ("onLoadMore".equals(loadMore) && page > 1) {
                operationItemAdapter.addData((page - 1) * 15, zwItemModel.getData().getRecords());
            } else {
                initZwListRecycler(zwItemModel.getData().getRecords());
            }
            if (page == zwItemModel.getData().getPages()) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void getCanUseBoothFailed(int netCode, String msg) {

    }

    @Override
    public void saveItemBoothSuccess(SaveBoothModel saveBoothModel) {
        if (saveBoothModel.getCode() == 0) {
            ToastUtils.showShort("上架成功");
            ARouter.getInstance()
                    .build(AppConfig.ACTIVITY_URL_MINE_PROJECT)
                    //进入动画
                    .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    .navigation();
            finish();
        } else if (saveBoothModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
        } else {
            ToastUtils.showShort(saveBoothModel.getMessage());
        }

    }

    @Override
    public void saveItemBoothFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }


}
