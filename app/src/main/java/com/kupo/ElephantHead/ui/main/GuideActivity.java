package com.kupo.ElephantHead.ui.main;


import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kupo.ElephantHead.AppConfig;
import com.kupo.ElephantHead.R;
import com.kupo.ElephantHead.base.BaseActivity;
import com.kupo.ElephantHead.ui.main.adapter.GuideAdapter;
import com.kupo.ElephantHead.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首次进入的引导页
 */
@Route(path = AppConfig.ACTIVITY_URL_MAIN_GUIDE)
public class GuideActivity extends BaseActivity {


    @BindView(R.id.lead_viewPager)
    ViewPager leadViewPager;
    @BindView(R.id.lead_btn)
    TextView leadBtn;
    @BindView(R.id.lead_ll)
    LinearLayout leadLl;

    @Override
    protected void init(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onInitPresenters() {
        StatusBarUtil.setTranslucentStatus(this);
        initViewPate();
        //为button添加监听事件,点击后切换到主界面
        leadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance()
                        .build(AppConfig.ACTIVITY_URL_MAIN_REGISTER)
                        //进入动画
                        .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        .navigation();
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lead;
    }

    private void initViewPate() {
        //List存放3张图片
        List<View> list = new ArrayList<View>();
        ImageView img1 = new ImageView(this);
        //设置这几张图片的源
        img1.setImageResource(R.drawable.guide_1);
        img1.setScaleType(ImageView.ScaleType.FIT_END);
        list.add(img1);
        ImageView img2 = new ImageView(this);
        img2.setImageResource(R.drawable.guide_2);
        img2.setScaleType(ImageView.ScaleType.FIT_END);
        list.add(img2);
        ImageView img3 = new ImageView(this);
        img3.setImageResource(R.drawable.guide_3);
        img3.setScaleType(ImageView.ScaleType.FIT_END);
        list.add(img3);
        ImageView img4 = new ImageView(this);
        img4.setImageResource(R.drawable.guide_4);
        img4.setScaleType(ImageView.ScaleType.FIT_END);
        list.add(img4);

        GuideAdapter myAdapter = new GuideAdapter(list);
        leadViewPager.setAdapter(myAdapter);

        //前两张没有"立即体验"的按钮，等到第三张才有
        //设置pager的监听器
        leadViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //如果是第三张图片，显示btn
                if (position == 3) leadLl.setVisibility(View.VISIBLE);
                else leadLl.setVisibility(View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
