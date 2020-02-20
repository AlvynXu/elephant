package com.kupo.ElephantHead.ui.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseFragment;
import com.kupo.ElephantHead.ui.home.adapter.MessageItemAdapter;
import com.kupo.ElephantHead.ui.home.model.MessageModel;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.ui.mvp.contract.MessageContract;
import com.kupo.ElephantHead.ui.mvp.presenter.MessagePresenter;
import com.kupo.ElephantHead.ui.room.fragment.RoomItemFragment;
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
import genealogy.jczb.com.rvlibrary.BaseQuickAdapter;

/**
 * 消息列表页
 */
public class MessageItemFragment extends BaseFragment implements MessageContract.IMessageView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.empty)
    EmptyLayout empty;
    private OnFragmentInteractionListener mListener;
    private List<MessageModel.DataBean.RecordsBean> recordsBeans = new ArrayList<>();
    private int page = 1;
    private boolean status = false;
    private MessageContract.IMessagePresenter messagePresenter;
    private Map<String, Object> map;
    String loadMore;
    MessageItemAdapter messageItemAdapter;
    ViewHolder viewHolder;


    public MessageItemFragment() {

    }

    public MessageItemFragment(String ss) {
        status = ss.equals("已读") ? true : false;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.recycle_view;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void onInitPresenters() {
        messagePresenter = new MessagePresenter();
        messagePresenter.attachView(this);
        initListNet(page);
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initListNet(page);
                loadMore = "onLoadMore";
                refreshLayout.finishLoadMore(1000);
                //   refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据
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
        map.put("status", status);
        map.put("page", page);
        map.put("size", 15);
        messagePresenter.getMessageNet(CommonUtils.getUserInfo().getToken(), "getMessageList", map);
    }

    private void initListData(List<MessageModel.DataBean.RecordsBean> recordsBeans) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        messageItemAdapter = new MessageItemAdapter(recordsBeans, getActivity());
        messageItemAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        messageItemAdapter.isFirstOnly(false);
        messageItemAdapter.addFooterView(getFooterView());
        if (recordsBeans.size() < 1) {
            viewHolder.footEmpty.setVisibility(View.VISIBLE);
            viewHolder.footEmpty.setEmptyText("暂无数据,请上拉刷新");
            viewHolder.footEmpty.setImgWH(200, 200);
            viewHolder.footEmpty.setEmptyTextSize(28);
            viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.secondText));
            viewHolder.footEmpty.setTxtWH(500, 50);
        } else {
            viewHolder.footEmpty.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(messageItemAdapter);

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
     * 自主选择城市街道筛选条件
     *
     * @param singleModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMessageEventBus(SingleModel singleModel) {
        map = new HashMap<>();
        map.put("id", singleModel.getStatus());
        messagePresenter.setMessageIsReadNet(CommonUtils.getUserInfo().getToken(), "getMessageInfo", map);
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
    public void getMessageNetSuccess(MessageModel messageModel) {
        if (messageModel.getCode() == 100) {
            CommonUtils.showLabelAlert(getActivity(), "");
        } else if (messageModel.getCode() == 0) {
            if ("onLoadMore".equals(loadMore) && page > 1) {
                messageItemAdapter.addData((page - 1) * 15, messageModel.getData().getRecords());
            } else {
                initListData(messageModel.getData().getRecords());
            }
            if (page == messageModel.getData().getPages()) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        } else {
            ToastUtils.showShort(messageModel.getMessage());
        }
    }

    @Override
    public void getMessageNetFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);

    }

    @Override
    public void setMessageIsReadNetSuccess(MessageModel messageModel) {
        //  ToastUtils.showShort("");
        initListNet(page);
    }

    @Override
    public void setMessageIsReadNetFailed(int netCode, String msg) {
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
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
