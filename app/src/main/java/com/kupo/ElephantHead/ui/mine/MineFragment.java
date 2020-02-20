package com.kupo.ElephantHead.ui.mine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseFragment;
import com.kupo.ElephantHead.greendao.MaskInfo;
import com.kupo.ElephantHead.greendao.MaskInfoOperateDao;
import com.kupo.ElephantHead.greendao.UserInfo;
import com.kupo.ElephantHead.greendao.UserInfoOperateDao;
import com.kupo.ElephantHead.ui.home.activity.HistoryOrderActivity;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.ui.mine.adapter.MineAdapter;
import com.kupo.ElephantHead.ui.mvp.contract.MineContract;
import com.kupo.ElephantHead.ui.mvp.model.HomeInfoModel;
import com.kupo.ElephantHead.ui.mvp.presenter.MinePresenter;
import com.kupo.ElephantHead.utils.CommonUtils;
import com.kupo.ElephantHead.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人模块
 */
public class MineFragment extends BaseFragment implements MineContract.IMineView {

    @BindView(R.id.mine_title_ll)
    RelativeLayout mineTitleLl;
    @BindView(R.id.mine_level_iv)
    ImageView mineLevelIv;
    @BindView(R.id.mine_name_tv)
    TextView mineNameTv;
    @BindView(R.id.mine_channel_tv)
    TextView mineChannelTv;
    @BindView(R.id.mine_zw_num)
    TextView mineZwNum;
    @BindView(R.id.mine_dz_num)
    TextView mineDzNum;
    @BindView(R.id.card_head)
    CardView cardHead;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.mine_user_level_tv)
    TextView mineUserLevelTv;
    private OnFragmentInteractionListener mListener;
    private List<SingleModel> singleModels;
    private MineContract.IMinePresenter minePresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init() {
        minePresenter = new MinePresenter();
        minePresenter.attachView(this);
        initUserInfo();
        initData();
    }

    private void initUserInfo() {
        List<Integer> mips = new ArrayList<>();
        mips.add(R.drawable.level_0);
        mips.add(R.drawable.level_1);
        mips.add(R.drawable.level_2);
        mips.add(R.drawable.level_3);
        for (int i = 0; i < mips.size(); i++) {
            if (CommonUtils.getUserInfo().getLevel() == i) {
                mineLevelIv.setImageDrawable(getResources().getDrawable(mips.get(i)));
            }
        }
        mineNameTv.setText("用户：" + CommonUtils.getUserInfo().getPhone());
        mineChannelTv.setText("邀请码：" + CommonUtils.getUserInfo().getRegCode());
        mineUserLevelTv.setText("等级：" + CommonUtils.getUserInfo().getLevelName());
//        mineLevelIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mActivity.startActivity(new Intent(getActivity(), HistoryOrderActivity.class));
//            }
//        });
    }

    @OnClick({R.id.mine_out, R.id.mine_invite, R.id.mine_project_ll, R.id.mine_dz_ll, R.id.mine_zw_ll, R.id.mine_project_vip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_project_vip:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_HOME_ZCW)
                            .withDouble("price", 10.0)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
            case R.id.mine_dz_ll:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MINE_DZAND_ZW)
                            .withInt("type", 0)
                            .withString("intentType", "mine")
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
            case R.id.mine_zw_ll:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MINE_DZAND_ZW)
                            .withInt("type", 1)
                            .withString("intentType", "mine")
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
            case R.id.mine_project_ll:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_MINE_PROJECT)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
            case R.id.mine_invite:
                if (CommonUtils.singleOnClick()) {
                    ARouter.getInstance()
                            .build(AppConfig.ACTIVITY_URL_HOME_SHARE)
                            //进入动画
                            .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            .navigation();
                }
                break;
            case R.id.mine_out:
                new AlertDialog.Builder(mActivity)
                        .setTitle("温馨提示")
                        .setMessage("确定要退出吗？退出之后本地数据会被清除呦")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UserInfoOperateDao.deleteDataBean();
                                UserInfo userInfo = CommonUtils.getUserInfo();
                                //代表推出
                                userInfo.setPromoterId(-1);
                                UserInfoOperateDao.insertUserInfo(userInfo);
                                ARouter.getInstance()
                                        .build(AppConfig.ACTIVITY_URL_MAIN_REGISTER)
                                        //进入动画
                                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                        .navigation();
                                mActivity.finish();
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
        }
    }

    private void initData() {
        singleModels = new ArrayList<>();
        singleModels.add(new SingleModel("客服中心", R.drawable.mine_kefu, true));
        singleModels.add(new SingleModel("我的收藏", R.drawable.mine_collect, true));
        singleModels.add(new SingleModel("意见反馈", R.drawable.mine_question, true));
        singleModels.add(new SingleModel("版本更新", R.drawable.mine_version, true));
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 4));
        MineAdapter adapter = new MineAdapter(singleModels, mActivity);
        adapter.isFirstOnly(false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onInitPresenters() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setStatusBarMode(mActivity, true, R.color.f6);
        }
        minePresenter.getMyBaseInfo(CommonUtils.getUserInfo().getToken());
        //为了展厅模块刷新使用
        MaskInfo maskInfo = CommonUtils.getMaskInfo();
        MaskInfoOperateDao.deleteMaskBean();
        maskInfo.setDesc("出现蒙版");
        MaskInfoOperateDao.insertMaskInfo(maskInfo);

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
    public void getMyBaseInfoSuccess(HomeInfoModel homeInfoModel) {
        if (homeInfoModel.getCode() == 0) {
            mineZwNum.setText(homeInfoModel.getData().getBoothCount() + "");
            mineDzNum.setText(homeInfoModel.getData().getStreetCount() + "");
        }
    }

    @Override
    public void getMyBaseInfoFailed(int netCode, String msg) {
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


}
