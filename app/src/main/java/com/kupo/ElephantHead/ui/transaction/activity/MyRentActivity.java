package com.kupo.ElephantHead.ui.transaction.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.ui.home.model.PayInfoModel;
import com.kupo.ElephantHead.ui.home.model.ProvincesModel;
import com.kupo.ElephantHead.ui.home.model.SingleModel;
import com.kupo.ElephantHead.ui.mvp.contract.MyRentContract;
import com.kupo.ElephantHead.ui.mvp.model.RefreshModel;
import com.kupo.ElephantHead.ui.mvp.presenter.MyRentPresenter;
import com.kupo.ElephantHead.ui.transaction.adapter.MyRentAdapter;
import com.kupo.ElephantHead.ui.transaction.adapter.MyRentHeadAdapter;
import com.kupo.ElephantHead.ui.transaction.fragment.RentCityFragment;
import com.kupo.ElephantHead.ui.transaction.model.CrowdPayRefreshModel;
import com.kupo.ElephantHead.ui.transaction.model.CrowdRefreshModel;
import com.kupo.ElephantHead.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import genealogy.jczb.com.rvlibrary.BaseQuickAdapter;
import genealogy.jczb.com.rvlibrary.BaseViewHolder;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 我的求租
 */
@Route(path = AppConfig.ACTIVITY_URL_TRANSACTION_MY_RENT)
public class MyRentActivity extends BaseActivity implements MyRentContract.IMyRentView {

    @BindView(R.id.my_rent_return_img)
    ImageView myRentReturnImg;
    @BindView(R.id.my_rent_location_tv)
    TextView myRentLocationTv;
    @BindView(R.id.my_rent_location_ll)
    LinearLayout myRentLocationLl;
    @BindView(R.id.my_rent_all_select_im)
    ImageView myRentAllSelectIm;
    @BindView(R.id.my_rent_all_select_ll)
    LinearLayout myRentAllSelectLl;
    @BindView(R.id.my_rent_head_child_rv)
    RecyclerView myRentHeadChildRv;
    @BindView(R.id.my_rent_rv)
    RecyclerView myRentRv;
    String city, cityCode;
    @BindView(R.id.pay_rent_price_et)
    EditText payRentPriceEt;
    @BindView(R.id.pay_rent_day_et)
    EditText payRentDayEt;
    @BindView(R.id.pay_rent_num_tv)
    TextView payRentNumTv;
    @BindView(R.id.pay_rent_price_tv)
    TextView payRentPriceTv;
    @BindView(R.id.pay_rent_sure_tv)
    TextView payRentSureTv;
    private MyRentContract.IMyRentPresenter myRentPresenter;
    private boolean isFlag = false;
    List<ProvincesModel.DataBean> districts = new ArrayList<>();
    MyRentAdapter myRentAdapter = null;
    List<String> list = new ArrayList<>();
    private BigDecimal price, day;
    Map<String, List<ProvincesModel.DataBean>> map = new HashMap<>();
    int count = 0;
    boolean isAllSelect = false;
    boolean isRunStatus = false;
    int resultCount = 0;
    String firstCode = "";
    private int citiesPosition = 1, provincePosition = 11;
    private MyRentHeadAdapter myRentHeadAdapter;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    /**
     * 选择城市记录
     *
     * @param baseResult
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveCityStatusEventBus(CrowdRefreshModel baseResult) {
        citiesPosition = baseResult.getCitiesPosition();
        provincePosition = baseResult.getCount();

    }

    @Override
    protected void onInitPresenters() {
        myRentPresenter = new MyRentPresenter();
        myRentPresenter.attachView(this);
        payRentPriceEt.addTextChangedListener(textWatcher);
        payRentPriceEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && payRentPriceEt.getText().toString().trim().length() < 1) {
                    // 失去焦点
                    payRentPriceEt.setText("1.0");
                }
            }
        });
        payRentDayEt.addTextChangedListener(dayTextWatcher);
        payRentDayEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && payRentDayEt.getText().toString().trim().length() < 1) {
                    // 失去焦点
                    payRentDayEt.setText("10");
                }
            }
        });
        myRentReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payRentPriceEt.clearFocus();
                payRentDayEt.clearFocus();
                finish();
            }
        });
        myRentLocationLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payRentPriceEt.clearFocus();
                payRentDayEt.clearFocus();
                RentCityFragment rentCityFragment = new RentCityFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("citiesPosition", citiesPosition);
                bundle.putInt("provincePosition", provincePosition);
                if (citiesPosition == 1 && provincePosition == 11) {
                    bundle.putString("city", CommonUtils.getMaskInfo().getCity());
                    bundle.putString("province", CommonUtils.getMaskInfo().getProvince());
                }
                rentCityFragment.setArguments(bundle);
                rentCityFragment.show(getSupportFragmentManager(), "myRentCityFragment");
            }
        });
        myRentAllSelectLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payRentPriceEt.clearFocus();
                payRentDayEt.clearFocus();
                if (isAllSelect) {
                    myRentAllSelectIm.setImageDrawable(getResources().getDrawable(R.drawable.user_read_default));
                    isAllSelect = false;
                    map.clear();
                    list.clear();
                    payRentPriceTv.setText("￥0.00");
                    payRentNumTv.setText(list.size() + "");
                    initListNet(firstCode);
                } else {
                    map.clear();
                    list.clear();
                    myRentAllSelectIm.setImageDrawable(getResources().getDrawable(R.drawable.user_read));
                    isAllSelect = true;
                    count = districts.size();
                    for (int i = 0; i < districts.size(); i++) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("code", districts.get(i).getCode());
                        myRentPresenter.getLocationNet(CommonUtils.getUserInfo().getToken(), "streets", map);
                        if (i == (districts.size() - 1)) {
                            isRunStatus = true;
                        }
                    }
                    myRentHeadAdapter.setThisPosition(0);
                    //嫑忘记刷新适配器
                    myRentHeadAdapter.notifyDataSetChanged();

                }
            }
        });
        payRentSureTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payRentPriceEt.clearFocus();
                payRentDayEt.clearFocus();
                if (list.size() < 1) {
                    ToastUtils.showShort("请先选择街道");
                    return;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("areaCodes", list);
                map.put("cityCode", cityCode);
                map.put("days", payRentDayEt.getText().toString().trim());
                map.put("price", payRentPriceEt.getText().toString().trim());
                RequestBody requestBaseUpdateBody = RequestBody.create(MediaType.parse("Content-Type,application/json; charset=utf-8"), new JSONObject(map).toString());
                myRentPresenter.postSeekRentNt(CommonUtils.getUserInfo().getToken(), requestBaseUpdateBody);
            }
        });
        initHeadChildData();
    }

    /**
     * 初始化头部的区域数据
     */
    private void initHeadChildData() {
        city = getIntent().getStringExtra("city");
        cityCode = getIntent().getStringExtra("cityCode");
        citiesPosition = getIntent().getIntExtra("citiesPosition", 1);
        provincePosition = getIntent().getIntExtra("provincePosition", 11);
        myRentLocationTv.setText(city);
        Map<String, Object> map = new HashMap<>();
        map.put("code", cityCode);
        myRentPresenter.getLocationNet(CommonUtils.getUserInfo().getToken(), "districts", map);
    }

    /**
     * 加载头部的区域数据
     */
    private void initHeadChildList(List<ProvincesModel.DataBean> pDataBeanList) {
        districts = pDataBeanList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myRentHeadChildRv.setLayoutManager(linearLayoutManager);
        myRentHeadAdapter = new MyRentHeadAdapter(pDataBeanList, this);
        myRentHeadAdapter.isFirstOnly(false);
        myRentHeadChildRv.setAdapter(myRentHeadAdapter);
        myRentHeadAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener<ProvincesModel.DataBean>() {
            @Override
            public void onItemClick(BaseQuickAdapter<ProvincesModel.DataBean, ? extends BaseViewHolder> baseQuickAdapter, View view, int i) {
                payRentPriceEt.clearFocus();
                payRentDayEt.clearFocus();
                //拿适配器调用适配器内部自定义好的setThisPosition方法（参数写点击事件的参数的position）
                myRentHeadAdapter.setThisPosition(i);
                //嫑忘记刷新适配器
                myRentHeadAdapter.notifyDataSetChanged();
                judgeDistricts(pDataBeanList.get(i).getCode());
            }
        });
    }


    /**
     * 判断当前选中区域的选中的街道
     */
    private void judgeDistricts(String code) {
        if (map.containsKey(code)) {
            initMyRentList(map.get(code));
            myRentAdapter.notifyDataSetChanged();
        } else {
            initListNet(code);
        }

    }

    /**
     * 加载出租的接到数据
     */
    private void initMyRentList(List<ProvincesModel.DataBean> pDataBeanList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myRentRv.setLayoutManager(linearLayoutManager);
        myRentAdapter = new MyRentAdapter(pDataBeanList, this);
        myRentAdapter.isFirstOnly(false);
        myRentRv.setAdapter(myRentAdapter);
        myRentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener<ProvincesModel.DataBean>() {
            @Override
            public void onItemClick(BaseQuickAdapter<ProvincesModel.DataBean, ? extends BaseViewHolder> baseQuickAdapter, View view, int i) {
                if (TextUtils.isEmpty(payRentPriceEt.getText().toString().trim()) && TextUtils.isEmpty(payRentDayEt.getText().toString().trim())) {
                    ToastUtils.showShort("请先输入单价和天数");
                    return;
                }
                payRentPriceEt.clearFocus();
                payRentDayEt.clearFocus();
                judgeDistrictsCodes(pDataBeanList.get(i), pDataBeanList);
                myRentAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 判断是否选中当前街道
     *
     * @return
     */
    private void judgeDistrictsCodes(ProvincesModel.DataBean db, List<ProvincesModel.DataBean> pDataBeanList) {
        if (pDataBeanList.size() > 0) {
            Iterator<ProvincesModel.DataBean> it = pDataBeanList.iterator();
            while (it.hasNext()) {
                ProvincesModel.DataBean userObj = it.next();
                if (!userObj.isSelected() && db.getCode().equals(userObj.getCode())) {
                    //选中
                    list.add(db.getCode());
                    userObj.setSelected(true);
                    break;
                } else if (userObj.isSelected() && db.getCode().equals(userObj.getCode())) {
                    //取消选中
                    userObj.setSelected(false);
                    if (list.contains(db.getCode())) {
                        list.remove(db.getCode());
                    }
                    break;
                }
            }
            price = new BigDecimal(payRentPriceEt.getText().toString().trim());
            day = new BigDecimal(payRentDayEt.getText().toString().trim());
            BigDecimal allPrice = new BigDecimal(list.size()).multiply(price).multiply(day);
            DecimalFormat df = new DecimalFormat("0.00");
            payRentPriceTv.setText("￥" + df.format(allPrice));
            payRentNumTv.setText(list.size() + "");
            //数量*单价*天数
            payRentPriceTv.setText("￥" + allPrice);
            map.put(pDataBeanList.get(0).getAreaCode(), pDataBeanList);
        }
    }

    /**
     * 选择城市
     *
     * @param refreshModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMyRentCityEventBus(RefreshModel refreshModel) {
        myRentAllSelectIm.setImageDrawable(getResources().getDrawable(R.drawable.user_read_default));
        isAllSelect = false;
        map.clear();
        list.clear();
        firstCode = "";
        payRentPriceTv.setText("￥0.00");
        payRentNumTv.setText(list.size() + "");
        isFlag = false;
        cityCode = refreshModel.getCityCode();
        myRentLocationTv.setText(refreshModel.getCityName());
        Map<String, Object> map = new HashMap<>();
        map.put("code", refreshModel.getCityCode());
        myRentPresenter.getLocationNet(CommonUtils.getUserInfo().getToken(), "districts", map);
    }

    /**
     * 求租页面回调刷新
     *
     * @param baseResult
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveQzEventBus(CrowdPayRefreshModel baseResult) {
        finish();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_rent;
    }

    @Override
    public void getLocationSuccess(ProvincesModel provincesModel) {
        if (provincesModel.getCode() == 200) {
            if (isAllSelect) {
                for (int i = 0; i < provincesModel.getData().size(); i++) {
                    provincesModel.getData().get(i).setSelected(true);
                    list.add(provincesModel.getData().get(i).getCode());
                }
                map.put(provincesModel.getData().get(0).getAreaCode(), provincesModel.getData());
                resultCount++;
                if (isRunStatus && resultCount == count) {
                    price = new BigDecimal(payRentPriceEt.getText().toString().trim());
                    day = new BigDecimal(payRentDayEt.getText().toString().trim());
                    BigDecimal allPrice = new BigDecimal(list.size()).multiply(price).multiply(day);
                    DecimalFormat df = new DecimalFormat("0.00");
                    payRentPriceTv.setText("￥" + df.format(allPrice));
                    payRentNumTv.setText(list.size() + "");
                    //数量*单价*天数
                    payRentPriceTv.setText("￥" + allPrice);
                    isRunStatus = false;
                    count = 0;
                    resultCount = 0;
                    initMyRentList(map.get(firstCode));
                    myRentAdapter.notifyDataSetChanged();
                }
            } else {
                if (!isFlag) {
                    initHeadChildList(provincesModel.getData());
                    if (firstCode == "") {
                        firstCode = provincesModel.getData().get(0).getCode();
                    }
                    initListNet(provincesModel.getData().get(0).getCode());
                } else {
                    map.put(provincesModel.getData().get(0).getAreaCode(), provincesModel.getData());
                    initMyRentList(provincesModel.getData());
                }
            }
        } else {
            ToastUtils.showShort(provincesModel.getMsg());
        }
    }

    private void initListNet(String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        myRentPresenter.getLocationNet(CommonUtils.getUserInfo().getToken(), "streets", map);
        isFlag = true;
    }


    @Override
    public void getLocationFailed(int netCode, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void postSeekRentSuccess(PayInfoModel payInfoModel) {
        if (payInfoModel.getCode() == 0) {
            ARouter.getInstance()
                    .build(AppConfig.ACTIVITY_URL_TRANSACTION_RENT_TYPE)
                    .withSerializable("wxModel", payInfoModel.getData())
                    .withString("price", payRentPriceTv.getText().toString().trim())
                    //进入动画
                    .withTransition(R.anim.bottom_in, R.anim.bottom_out)
                    .navigation();
        } else {
            ToastUtils.showShort(payInfoModel.getMessage());
        }

    }

    @Override
    public void postSeekRentFailed(int netCode, String msg) {

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

    private TextWatcher textWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            temp = charSequence;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String editStr = editable.toString().trim();
            if (editStr.length() > 0) {
                if (CommonUtils.isNumeric(editStr)) {
                    editStart = payRentPriceEt.getSelectionStart();
                    editEnd = payRentPriceEt.getSelectionEnd();
                    if (payRentPriceEt.getText().toString().trim().length() > 5 || new BigDecimal(editStr).compareTo(new BigDecimal(10000)) > 0) {//输入字数限制
                        payRentPriceEt.setText("10000");
                        int tempSelection = editStart;
                        payRentPriceEt.setSelection(tempSelection);//光标焦点设置在行末
                        ToastUtils.showShort("单价不能超过10000");
                    }
                } else {
                    int posDot = editStr.indexOf(".");
                    //不允许输入3位小数,超过三位就删掉
                    if (posDot < 0) {
                        return;
                    }
                    if (editStr.length() - posDot - 1 > 2) {
                        editable.delete(posDot + 3, posDot + 4);
                    }
                    if (editStr.length() > 5) {//输入字数限制
                        payRentPriceEt.setText("10000");
                        int tempSelection = editStart;
                        payRentPriceEt.setSelection(tempSelection);//光标焦点设置在行末
                        ToastUtils.showShort("单价不能超过10000");
                    }

                }
                price = new BigDecimal(payRentPriceEt.getText().toString().trim());
                day = new BigDecimal(payRentDayEt.getText().toString().trim());
                BigDecimal allPrice = new BigDecimal(list.size()).multiply(price).multiply(day);
                DecimalFormat df = new DecimalFormat("0.00");
                payRentPriceTv.setText("￥" + df.format(allPrice));
                payRentNumTv.setText(list.size() + "");
                //数量*单价*天数
                payRentPriceTv.setText("￥" + allPrice);
            }

        }
    };
    private TextWatcher dayTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            temp = charSequence;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String editStr = editable.toString().trim();
            if (editStr.length() > 0) {
                if (CommonUtils.isNumeric(editStr)) {
                    editStart = payRentDayEt.getSelectionStart();
                    editEnd = payRentDayEt.getSelectionEnd();
                    if (payRentDayEt.getText().toString().trim().length() > 4 || new BigDecimal(editStr).compareTo(new BigDecimal(365)) > 0) {//输入字数限制
                        payRentDayEt.setText("365");
                        int tempSelection = editStart;
                        payRentDayEt.setSelection(tempSelection);//光标焦点设置在行末
                        ToastUtils.showShort("天数不能超过365");
                    }
                }
                price = new BigDecimal(payRentPriceEt.getText().toString().trim());
                day = new BigDecimal(payRentDayEt.getText().toString().trim());
                BigDecimal allPrice = new BigDecimal(list.size()).multiply(price).multiply(day);
                DecimalFormat df = new DecimalFormat("0.00");
                payRentPriceTv.setText("￥" + df.format(allPrice));
                payRentNumTv.setText(list.size() + "");
                //数量*单价*天数
                payRentPriceTv.setText("￥" + allPrice);
            }

        }
    };


}
