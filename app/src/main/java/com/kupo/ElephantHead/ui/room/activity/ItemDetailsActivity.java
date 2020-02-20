package com.kupo.ElephantHead.ui.room.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.ui.mvp.contract.RoomItemDetailsContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.presenter.RoomItemDetailsPresenter;
import com.kupo.ElephantHead.ui.room.LeavingMessageAdapter;
import com.kupo.ElephantHead.ui.room.adapter.RoomDetailsAdapter;
import com.kupo.ElephantHead.ui.room.model.LeavingMessageModel;
import com.kupo.ElephantHead.ui.room.model.RoomIDetailsModel;
import com.kupo.ElephantHead.ui.room.model.SaveBoothModel;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.widget.EmptyLayout;
import com.kupo.ElephantHead.widget.NestRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 项目详情
 */
@Route(path = AppConfig.ACTIVITY_URL_ROOM_DETAILS)
public class ItemDetailsActivity extends BaseActivity implements RoomItemDetailsContract.IRoomItemDetailsView {


    @BindView(R.id.title_return_img)
    ImageView titleReturnImg;
    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_collect_iv)
    ImageView titleCollectIv;
    @BindView(R.id.title_share_iv)
    ImageView titleShareIv;
    @BindView(R.id.title_right_linear)
    LinearLayout titleRightLinear;
    @BindView(R.id.title_ll)
    LinearLayout titleLl;
    @BindView(R.id.room_details_title_tv)
    TextView roomDetailsTitleTv;
    @BindView(R.id.room_details_look_tv)
    TextView roomDetailsLookTv;
    @BindView(R.id.room_details_share_tv)
    TextView roomDetailsShareTv;
    @BindView(R.id.room_details_report_im)
    ImageView roomDetailsReportIm;
    @BindView(R.id.room_details_report_tv)
    TextView roomDetailsReportTv;
    @BindView(R.id.room_details_report_ll)
    LinearLayout roomDetailsReportLl;
    @BindView(R.id.room_details_phone_tv)
    TextView roomDetailsPhoneTv;
    @BindView(R.id.room_details_location_tv)
    TextView roomDetailsLocationTv;
    @BindView(R.id.details_recycler_view)
    RecyclerView detailsRecyclerView;
    @BindView(R.id.empty)
    EmptyLayout empty;
    @BindView(R.id.details_foot_recycler_view)
    RecyclerView detailsFootRecyclerView;
    @BindView(R.id.details_foot_refreshLayout)
    SmartRefreshLayout detailsFootRefreshLayout;
    @BindView(R.id.room_details_add_tv)
    TextView roomDetailsAddTv;
    @BindView(R.id.room_details_delete_tv)
    ImageView roomDetailsDeleteTv;
    @BindView(R.id.room_details_edit_tv)
    ImageView roomDetailsEditTv;
    @BindView(R.id.room_details_status_tv)
    TextView roomDetailsStatusTv;
    @BindView(R.id.details_btn_ll)
    LinearLayout detailsBtnLl;
    @BindView(R.id.details_message)
    TextView detailsMessage;
    @BindView(R.id.details_message_rl)
    RelativeLayout detailsMessageRl;
    @BindView(R.id.details_release_btn_ll)
    LinearLayout detailsReleaseBtnLl;
    @BindView(R.id.details_leaving_message_et)
    EditText detailsLeavingMessageEt;
    @BindView(R.id.details_leaving_message_tv)
    TextView detailsLeavingMessageTv;
    @BindView(R.id.details_leaving_message)
    LinearLayout detailsLeavingMessage;
    @BindView(R.id.nested_scroll_View)
    NestedScrollView nestedScrollView;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    private RoomItemDetailsContract.IRoomItemDetailsPresenter iRoomItemDetailsPresenter;
    int itemId;
    int status;
    boolean isFlag = false;
    private Map<String, Object> map;
    LeavingMessageAdapter leavingMessageAdapter = null;
    int page = 1;
    ViewHolder viewHolder;
    private String loadMore = "";
    List<LeavingMessageModel.DataBean.RecordsBean> allDataBeans = new ArrayList<>();
    RoomDetailsAdapter roomDetailsAdapter;


    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        iRoomItemDetailsPresenter = new RoomItemDetailsPresenter();
        iRoomItemDetailsPresenter.attachView(this);
        titleTitleTxt.setText("详情");
        itemId = getIntent().getIntExtra("detailsId", 1);
        status = getIntent().getIntExtra("status", 1);
        iRoomItemDetailsPresenter.getRoomItemDetails(CommonUtils.getUserInfo().getToken(), itemId);
        initLayout();
    }

    /**
     * 初始化界面
     */
    private void initLayout() {

        detailsFootRefreshLayout.autoLoadMore();//自动加载
        detailsFootRefreshLayout.setEnableRefresh(false);
        detailsFootRefreshLayout.setEnableAutoLoadMore(true);
        detailsFootRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initLeavingData(page);
                loadMore = "onLoadMore";
                refreshLayout.finishLoadMore(1000);
            }
        });
        if (status == 0) {
            titleCollectIv.setVisibility(View.GONE);
            titleShareIv.setVisibility(View.GONE);
            roomDetailsStatusTv.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
            layoutParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParam.setMargins(0, 0, 0, CommonUtils.dip2px(this, 40));//4个参数按顺序分别是左上右下
            roomDetailsStatusTv.setLayoutParams(layoutParam);
            roomDetailsStatusTv.setGravity(Gravity.CENTER);
            roomDetailsStatusTv.setWidth(CommonUtils.dip2px(this, 128));
            roomDetailsStatusTv.setHeight(CommonUtils.dip2px(this, 40));
            roomDetailsStatusTv.setText("审核中");
            roomDetailsDeleteTv.setVisibility(View.GONE);
            detailsReleaseBtnLl.setVisibility(View.GONE);
            detailsLeavingMessage.setVisibility(View.GONE);
            roomDetailsStatusTv.setEnabled(true);
        } else if (status == -1) {
            titleCollectIv.setVisibility(View.GONE);
            titleShareIv.setVisibility(View.GONE);
            roomDetailsEditTv.setVisibility(View.VISIBLE);
            roomDetailsDeleteTv.setVisibility(View.VISIBLE);
            roomDetailsStatusTv.setVisibility(View.VISIBLE);
            detailsReleaseBtnLl.setVisibility(View.GONE);
            detailsLeavingMessage.setVisibility(View.GONE);
            roomDetailsStatusTv.setText("审核失败");

        } else if (status == 3) {
            roomDetailsDeleteTv.setVisibility(View.GONE);
            roomDetailsEditTv.setVisibility(View.GONE);
            roomDetailsStatusTv.setVisibility(View.VISIBLE);
            roomDetailsAddTv.setVisibility(View.VISIBLE);
            detailsReleaseBtnLl.setVisibility(View.GONE);
            detailsLeavingMessage.setVisibility(View.GONE);
            roomDetailsStatusTv.setText("下架");
            titleCollectIv.setVisibility(View.GONE);
            titleShareIv.setVisibility(View.GONE);
        } else if (status == 2) {
            titleCollectIv.setVisibility(View.GONE);
            titleShareIv.setVisibility(View.GONE);
            roomDetailsEditTv.setVisibility(View.VISIBLE);
            roomDetailsDeleteTv.setVisibility(View.VISIBLE);
            roomDetailsStatusTv.setVisibility(View.VISIBLE);
            detailsReleaseBtnLl.setVisibility(View.GONE);
            detailsLeavingMessage.setVisibility(View.GONE);
            roomDetailsStatusTv.setText("发布");
        } else if (status == 8) {
            roomDetailsStatusTv.setVisibility(View.GONE);
            roomDetailsEditTv.setVisibility(View.GONE);
            roomDetailsDeleteTv.setVisibility(View.GONE);
            roomDetailsAddTv.setVisibility(View.GONE);
            roomDetailsReportLl.setVisibility(View.VISIBLE);
            detailsReleaseBtnLl.setVisibility(View.VISIBLE);
            detailsLeavingMessage.setVisibility(View.GONE);
        }else if(status == 9){
            roomDetailsStatusTv.setVisibility(View.GONE);
            roomDetailsEditTv.setVisibility(View.GONE);
            roomDetailsDeleteTv.setVisibility(View.GONE);
            roomDetailsAddTv.setVisibility(View.GONE);
            roomDetailsReportLl.setVisibility(View.GONE);
            detailsReleaseBtnLl.setVisibility(View.GONE);
            detailsLeavingMessage.setVisibility(View.GONE);
        }
        roomDetailsReportIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //举报
                ToastUtils.showShort("暂未开通");
            }
        });
        roomDetailsReportTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //举报
                ToastUtils.showShort("暂未开通");
            }
        });
    }

    @OnClick({R.id.room_details_status_tv, R.id.title_return_linear, R.id.title_collect_iv, R.id.title_share_iv, R.id.room_details_delete_tv, R.id.room_details_edit_tv, R.id.room_details_add_tv, R.id.details_btn_ll, R.id.details_leaving_message_tv, R.id.details_message_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.room_details_status_tv:
                if (status == 0) {
                    roomDetailsStatusTv.setEnabled(true);
                    ToastUtils.showShort("审核中不能操作");
                } else if (status == -1) {
                    ToastUtils.showShort("审核失败，请修改后再次发布");
                } else if (status == 3) {
                    roomDetailsEditTv.setVisibility(View.GONE);
                    roomDetailsStatusTv.setText("下架");
                    map = new HashMap<>();
                    map.put("id", itemId);
                    RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), new JSONObject(map).toString());
                    iRoomItemDetailsPresenter.removeItem(CommonUtils.getUserInfo().getToken(), requestBaseUpdateBody);
                } else if (status == 2) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_ROOM_OPERATION)
                            .withInt("detailsId", itemId)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                    finish();
                } else if (status == 8) {
                    roomDetailsStatusTv.setVisibility(View.GONE);
                    roomDetailsEditTv.setVisibility(View.GONE);
                    roomDetailsDeleteTv.setVisibility(View.GONE);
                    roomDetailsAddTv.setVisibility(View.GONE);
                    roomDetailsStatusTv.setEnabled(true);
                }
                break;
            case R.id.title_return_linear:
                if (getIntent().getStringExtra("intentType").equals("adapter")) {
                    finish();
                } else if (getIntent().getStringExtra("intentType").equals("project")) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MINE_PROJECT)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                    finish();
                } else if (getIntent().getStringExtra("intentType").equals("finish")) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MINE_COLLECT)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                    finish();
                }
                break;
            case R.id.title_collect_iv:
                //收藏
                if (isFlag) {
                    //收藏
                    map = new HashMap<>();
                    map.put("itemId", itemId);
                    RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), new JSONObject(map).toString());
                    iRoomItemDetailsPresenter.collectionItem(CommonUtils.getUserInfo().getToken(), "cancelCollection", requestBaseUpdateBody);

                } else {
                    //未收藏
                    map = new HashMap<>();
                    map.put("itemId", itemId);
                    RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), new JSONObject(map).toString());
                    iRoomItemDetailsPresenter.collectionItem(CommonUtils.getUserInfo().getToken(), "collection", requestBaseUpdateBody);
                }
                break;
            case R.id.title_share_iv:
                //分享
                iRoomItemDetailsPresenter.shareProject(CommonUtils.getUserInfo().getToken(), itemId);
                break;
            case R.id.room_details_delete_tv:
                //删除
                new AlertDialog.Builder(this)
                        .setTitle("温馨提示")
                        .setMessage("确定要删除吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                map = new HashMap<>();
                                map.put("id", itemId);
                                RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), new JSONObject(map).toString());
                                iRoomItemDetailsPresenter.removeProject(CommonUtils.getUserInfo().getToken(), requestBaseUpdateBody);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setCancelable(false)
                        .setOnKeyListener(new DialogInterface.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                                    return true;
                                } else {
                                    return false;
                                }

                            }
                        })
                        .create().show();

                break;
            case R.id.room_details_edit_tv:
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MINE_EDIT)
                        .withInt("itemId", itemId)
                        .withInt("status", status)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                finish();
                break;
            case R.id.room_details_add_tv:
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_ROOM_OPERATION)
                        .withInt("detailsId", itemId)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                finish();
                break;
            case R.id.details_btn_ll:
                detailsReleaseBtnLl.setVisibility(View.GONE);
                detailsLeavingMessage.setVisibility(View.VISIBLE);
                detailsLeavingMessageEt.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(detailsLeavingMessageEt, 0);
                break;
            case R.id.details_leaving_message_tv:
                if (detailsLeavingMessageEt.getText().toString().trim().length() < 1 || detailsLeavingMessageEt.getText().toString().trim().length() > 100) {
                    ToastUtils.showShort("留言不能为空并且不能超过100个字");
                    return;
                }
                map = new HashMap<>();
                map.put("itemId", itemId);
                map.put("message", detailsLeavingMessageEt.getText().toString().trim());
                RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), new JSONObject(map).toString());
                iRoomItemDetailsPresenter.saveLeaveMessage(CommonUtils.getUserInfo().getToken(), requestBaseUpdateBody);
                break;
            case R.id.details_message_rl:
                nestedScrollView.scrollTo(0, detailsRecyclerView.getBottom());
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_item_details;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    /**
     * 获取详情列表
     *
     * @param roomIDetailsModel
     */
    @Override
    public void getRoomItemDetailsSuccess(RoomIDetailsModel roomIDetailsModel) {
        if (roomIDetailsModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
        } else if (roomIDetailsModel.getCode() == 0) {
            if (roomIDetailsModel.getData().isCollect() == true) {
                //收藏
                isFlag = true;
                titleCollectIv.setImageDrawable(getResources().getDrawable(R.drawable.room_details_collect_select));
            } else {
                //未收藏
                isFlag = false;
                titleCollectIv.setImageDrawable(getResources().getDrawable(R.drawable.room_details_collect_default));
            }
            if (roomIDetailsModel.getData().getDetailList() != null && roomIDetailsModel.getData().getDetailList().size() > 0) {
                initDetailsList(roomIDetailsModel.getData().getDetailList());
                roomDetailsTitleTv.setText(roomIDetailsModel.getData().getDescription());
                roomDetailsLookTv.setText(roomIDetailsModel.getData().getViews() + "");
                roomDetailsShareTv.setText(roomIDetailsModel.getData().getShareCount() + "");
                roomDetailsLocationTv.setText("联系地址：" + roomIDetailsModel.getData().getAddressDetail());
                roomDetailsPhoneTv.setText("电话/微信：" + roomIDetailsModel.getData().getPhone());
            }
        } else {
            ToastUtils.showShort(roomIDetailsModel.getMessage());
        }
    }

    @Override
    public void getRoomItemDetailsFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void removeItemSuccess(SaveBoothModel zwItemModel) {
        if (zwItemModel.getCode() == 0) {
            ToastUtils.showShort("下架成功");
            ARouter.getInstance()
                    .build(AppConfig.ACTIVITY_URL_MINE_PROJECT)
                    //进入动画
                    .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    .navigation();
            finish();
        } else if (zwItemModel.getCode() == 100) {
            CommonUtils.showLabelAlert(this, "");
        } else {
            ToastUtils.showShort(zwItemModel.getMessage());
        }
    }

    @Override
    public void removeItemFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void reportItemSuccess(BaseResult baseResult) {

    }

    @Override
    public void reportItemFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void collectionItemSuccess(SaveBoothModel saveBoothModel) {
        if (saveBoothModel.getCode() == 0) {
            if (isFlag) {
                isFlag = false;
                titleCollectIv.setImageDrawable(getResources().getDrawable(R.drawable.room_details_collect_default));
                ToastUtils.showShort("取消收藏");
            } else {
                isFlag = true;
                titleCollectIv.setImageDrawable(getResources().getDrawable(R.drawable.room_details_collect_select));
                ToastUtils.showShort("收藏成功");
            }
        } else {
            ToastUtils.showShort(saveBoothModel.getMessage());
        }
    }

    @Override
    public void collectionItemFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void removeProjectSuccess(SaveBoothModel saveBoothModel) {
        if (saveBoothModel.getCode() == 0) {
            ToastUtils.showShort("删除成功");
            ARouter.getInstance()
                    .build(AppConfig.ACTIVITY_URL_MINE_PROJECT)
                    //进入动画
                    .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    .navigation();
            finish();
        } else {
            ToastUtils.showShort(saveBoothModel.getMessage());
        }
    }

    @Override
    public void removeProjectFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void shareProjectSuccess(HomeInfoModel homeInfoModel) {
        if (homeInfoModel.getCode() == 0) {
            Intent textIntent = new Intent(Intent.ACTION_SEND);
            textIntent.setType("text/plain");
            textIntent.putExtra(Intent.EXTRA_TEXT, homeInfoModel.getData().getSharePath());
            startActivity(Intent.createChooser(textIntent, "分享项目详情"));
        } else {
            ToastUtils.showShort(homeInfoModel.getMessage());
        }
    }

    @Override
    public void shareProjectFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void getLeaveMessagePageSuccess(LeavingMessageModel leavingMessageModel) {
        if (leavingMessageModel.getCode() == 0) {
            //获取留言列表
            if ("onLoadMore".equals(loadMore) && page > 1) {
                leavingMessageAdapter.addData((page - 1) * 15, leavingMessageModel.getData().getRecords());
            } else {
                initLeavingMessage(leavingMessageModel.getData().getRecords());
            }
            if (page == leavingMessageModel.getData().getPages()) {
                detailsFootRefreshLayout.finishLoadMoreWithNoMoreData();
            }
            detailsMessage.setText(leavingMessageModel.getData().getTotal() + "");
        } else {
            ToastUtils.showShort(leavingMessageModel.getMessage());
        }
    }

    @Override
    public void getLeaveMessagePageFailed(int netCode, String msg) {
        ToastUtils.showShort("获取留言失败");
    }

    @Override
    public void saveLeaveMessageSuccess(LeavingMessageModel leavingMessageModel) {
        if (leavingMessageModel.getCode() == 0) {
            ToastUtils.showShort("留言成功");
            LeavingMessageModel.DataBean.RecordsBean recordsBean = new LeavingMessageModel.DataBean.RecordsBean(1, itemId, detailsLeavingMessageEt.getText().toString().trim(), "*******" + CommonUtils.getUserInfo().getPhone().substring(7, 11), 123123, CommonUtils.getUserInfo().getId(), CommonUtils.getUserInfo().getLevel());
            leavingMessageAdapter.addData(0, recordsBean);
            leavingMessageAdapter.notifyDataSetChanged();
            detailsLeavingMessageEt.setText("");
            detailsReleaseBtnLl.setVisibility(View.VISIBLE);
            detailsLeavingMessage.setVisibility(View.GONE);
            detailsMessage.setText(allDataBeans.size() + "");
            viewHolder.footEmpty.setVisibility(View.GONE);
            nestedScrollView.scrollTo(0, detailsRecyclerView.getBottom());
        } else {
            ToastUtils.showShort(leavingMessageModel.getMessage());
        }
    }

    @Override
    public void saveLeaveMessageFailed(int netCode, String msg) {
        ToastUtils.showShort("留言失败");
    }

    /**
     * 初始化广告段落详情条列表
     */
    @SuppressLint("WrongConstant")
    private void initDetailsList(List<RoomIDetailsModel.DataBean.DetailListBean> detailListBeans) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        detailsRecyclerView.setLayoutManager(layoutManager);
        roomDetailsAdapter = new RoomDetailsAdapter(detailListBeans, this);
        roomDetailsAdapter.isFirstOnly(false);
        detailsRecyclerView.setAdapter(roomDetailsAdapter);
        detailsRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (status == 8) {
                    detailsReleaseBtnLl.setVisibility(View.VISIBLE);
                    detailsLeavingMessage.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                //      LogUtils.e("onTouchEvent");
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//                LogUtils.e("onRequestDisallowInterceptTouchEvent");
            }
        });
        if (status == 8) {
            tvMessage.setVisibility(View.VISIBLE);
            initLeavingData(page);
        } else {
            tvMessage.setVisibility(View.GONE);
        }

    }

    /**
     * 初始化留言列表
     */
    private void initLeavingData(int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("page", page);
        map.put("size", 15);
        iRoomItemDetailsPresenter.getLeaveMessagePage(CommonUtils.getUserInfo().getToken(), map);
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
     * 加载留言数据
     */
    @SuppressLint("WrongConstant")
    private void initLeavingMessage(List<LeavingMessageModel.DataBean.RecordsBean> dataBeans) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        detailsFootRecyclerView.setLayoutManager(layoutManager);
        leavingMessageAdapter = new LeavingMessageAdapter(dataBeans, this);
        leavingMessageAdapter.isFirstOnly(false);
        leavingMessageAdapter.addFooterView(getFooterView());
        if (dataBeans.size() < 1 && dataBeans.size() < 1) {
            viewHolder.footEmpty.setVisibility(View.VISIBLE);
            viewHolder.footEmpty.setEmptyText("暂无留言数据");
            viewHolder.footEmpty.setImgWH(250, 250);
            viewHolder.footEmpty.setEmptyTextSize(28);
            viewHolder.footEmpty.setEmptyTextColor(getResources().getColor(R.color.secondText));
            viewHolder.footEmpty.setTxtWH(500, 50);
        } else {
            viewHolder.footEmpty.setVisibility(View.GONE);
        }
        detailsFootRecyclerView.setAdapter(leavingMessageAdapter);
        detailsFootRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initLeavingData(page);
                loadMore = "onLoadMore";
                refreshLayout.finishLoadMore(1000);
            }
        });
        allDataBeans = dataBeans;
    }

}
