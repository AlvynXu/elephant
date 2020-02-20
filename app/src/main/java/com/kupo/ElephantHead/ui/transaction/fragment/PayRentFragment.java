package com.kupo.ElephantHead.ui.transaction.fragment;


import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseDiaLogFragment;
import com.kupo.ElephantHead.ui.home.fragment.BuySuccessFragment;
import com.kupo.ElephantHead.ui.mvp.contract.PayRentContract;
import com.kupo.ElephantHead.ui.mvp.presenter.PayRentPresenter;
import com.kupo.ElephantHead.ui.room.adapter.OperationItemAdapter;
import com.kupo.ElephantHead.ui.transaction.adapter.PayRentAdapter;
import com.kupo.ElephantHead.ui.transaction.model.RentStreetModel;
import com.kupo.ElephantHead.utils.CommonUtils;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * 交易模块出租街道
 */
public class PayRentFragment extends BaseDiaLogFragment implements PayRentContract.IPayRentView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.pay_rent_num_tv)
    TextView payRentNumTv;
    @BindView(R.id.pay_rent_delete_im)
    ImageView payRentDeleteIm;
    @BindView(R.id.pay_rent_price_tv)
    TextView payRentPriceTv;
    @BindView(R.id.pay_rent_sure_tv)
    TextView payRentSureTv;
    private Window window;
    private PayRentContract.IPayRentPresenter payRentPresenter;
    private int orderId, day;
    private List<String> list = new ArrayList<>();
    private BigDecimal price;

    @Override
    protected void init() {
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        if (bundle != null) {
            orderId = bundle.getInt("orderId");
            day = bundle.getInt("day");
        }
        payRentPresenter = new PayRentPresenter();
        payRentPresenter.attachView(this);
    }

    @Override
    protected void onInitPresenters() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", orderId);
        RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type,application/json; charset=utf-8"), new JSONObject(map).toString());
        payRentPresenter.getCanRentStreet(CommonUtils.getUserInfo().getToken(), requestBaseUpdateBody);
        payRentDeleteIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        payRentSureTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() > 0) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("orderId", orderId);
                    map.put("streetCodes", list);
                    RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type,application/json; charset=utf-8"), new JSONObject(map).toString());
                    payRentPresenter.postRentToOther(CommonUtils.getUserInfo().getToken(), requestBaseUpdateBody);
                } else {
                    ToastUtils.showShort("请勾选一条街道或者前去购买");
                }

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pay_rent_popup;
    }

    /**
     * 初始化出租街道列表
     *
     * @param dataBeans
     */
    private void initRentList(List<RentStreetModel.DataBean.UserStreetDTOListBean> dataBeans) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        PayRentAdapter payRentAdapter = new PayRentAdapter(dataBeans, mActivity);
        payRentAdapter.isFirstOnly(false);
        recyclerView.setAdapter(payRentAdapter);
        payRentAdapter.setOnItemRemoveListener(new PayRentAdapter.OnItemRemoveListener() {
            @Override
            public void onItemRemoveClick(int position, int flag) {
                if (flag == 1) {
                    //点击时显示选中图标
                    list.add(dataBeans.get(position).getStreetCode());
                } else {
                    if (list.size() > 0) {
                        Iterator<String> it = list.iterator();
                        while (it.hasNext()) {
                            String userObj = it.next();
                            if (userObj == dataBeans.get(position).getStreetCode()) {
                                it.remove();
                            }
                        }
                    }
                }
                if (list.size() > 0) {
                    BigDecimal h = new BigDecimal(list.size()).multiply(price).multiply(new BigDecimal(day));
//                    BigDecimal s = new BigDecimal("1.00").add(rentRate);
                    BigDecimal s = new BigDecimal("1.00");
                    DecimalFormat df = new DecimalFormat("0.00");
                    payRentPriceTv.setText("￥" + df.format(h.multiply(s)));
                } else {
                    payRentPriceTv.setText("￥0.00");
                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        // 下面这些设置必须在此方法(onStart())中才有效
        window = getDialog().getWindow();
        // 如果不设置这句代码, 那么弹框就会与四边都有一定的距离
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
        params.width = getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(params);

    }

    @Override
    public void getCanRentStreetSuccess(RentStreetModel rentStreetModel) {
        if (rentStreetModel.getCode() == 0) {
            String str = "区域内您有<font color='#F85F53' size='15px'>" + rentStreetModel.getData().getCount() + "</font>个电线竿";
            payRentNumTv.setTextSize(12);
            payRentNumTv.setText(Html.fromHtml(str));
            price = rentStreetModel.getData().getPrice();
     //       rentRate = rentStreetModel.getData().getRentRate();
            BigDecimal h = new BigDecimal(rentStreetModel.getData().getCount()).multiply(rentStreetModel.getData().getPrice()).multiply(new BigDecimal(day));
            //    BigDecimal s = new BigDecimal("1.00").add(rentStreetModel.getData().getRentRate());
            BigDecimal s = new BigDecimal("1.00");
            DecimalFormat df = new DecimalFormat("0.00");
            payRentPriceTv.setText("￥" + df.format(h.multiply(s)));
            for (int i = 0; i < rentStreetModel.getData().getUserStreetDTOList().size(); i++) {
                if (rentStreetModel.getData().getUserStreetDTOList().get(i).isHasBooth()) {
                    list.add(rentStreetModel.getData().getUserStreetDTOList().get(i).getStreetCode());
                }
            }
            initRentList(rentStreetModel.getData().getUserStreetDTOList());
        } else if (rentStreetModel.getCode() == 100) {
            CommonUtils.showLabelAlert(mActivity, "");
        } else {
            ToastUtils.showShort(rentStreetModel.getMessage());
        }
    }

    @Override
    public void getCanRentStreetFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void postRentToOtherSuccess(RentStreetModel rentStreetModel) {
        if (rentStreetModel.getCode() == 0) {
            BuySuccessFragment buySuccessFragment = new BuySuccessFragment();
            Bundle bundle = new Bundle();
            bundle.putString("desc", "出租成功");
            buySuccessFragment.setArguments(bundle);
            buySuccessFragment.show(getFragmentManager(), "buySuccessFragment");
            getDialog().dismiss();
            //         ToastUtils.showShort("租用成功");
        } else {
            ToastUtils.showShort(rentStreetModel.getMessage());
        }
    }

    @Override
    public void postRentToOtherFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }
}
