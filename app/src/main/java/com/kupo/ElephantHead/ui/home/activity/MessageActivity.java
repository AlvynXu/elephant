package com.kupo.ElephantHead.ui.home.activity;


import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.SlidingTabLayout;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.base.basemodel.BaseResult;
import com.kupo.ElephantHead.ui.home.adapter.TeamItemPagerAdapter;
import com.kupo.ElephantHead.ui.home.fragment.MessageItemFragment;
import com.kupo.ElephantHead.ui.mvp.model.RefreshModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 系统消息
 */
@Route(path = AppConfig.ACTIVITY_URL_MAIN_MESSAGE)
public class MessageActivity extends BaseActivity implements MessageItemFragment.OnFragmentInteractionListener {

    @BindView(R.id.title_return_linear)
    LinearLayout titleReturnLinear;
    @BindView(R.id.title_title_txt)
    TextView titleTitleTxt;
    @BindView(R.id.title_right_txt)
    TextView titleRightTxt;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private List<String> mTabTitles = new ArrayList<String>();

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitPresenters() {
        titleTitleTxt.setText("消息");
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.GONE);
        initTabPage();
        titleReturnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new RefreshModel("消息刷新"));
                finish();
            }
        });
    }

    /**
     * 初始化加载tab页面
     */
    private void initTabPage() {
        mTabTitles.add("未读");
        mTabTitles.add("已读");
        tabLayout.setIndicatorHeight(4);
        TeamItemPagerAdapter homePagerAdapter = new TeamItemPagerAdapter(getSupportFragmentManager(), mTabTitles);
        viewPager.setAdapter(homePagerAdapter);
        tabLayout.setViewPager(viewPager);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
