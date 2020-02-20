package com.kupo.ElephantHead.widget;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G400 on 2019/8/4.
 * 功能：自定义底部菜单容器(配合CanGradualView使用实现底部菜单栏)
 * 作者：
 */
public class BaseLineInstructions extends LinearLayout {

    private ViewPager viewPager;
    private List<CanGradualView> canGradualViews;
    private int childCount;
    private int currentItem;
    private static final String STATE_INSTANCE = "instance_state";
    private static final String STATE_ITEM = "state_item";


    public BaseLineInstructions(Context context) {
        this(context, (AttributeSet) null);
    }

    public BaseLineInstructions(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLineInstructions(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.canGradualViews = new ArrayList<CanGradualView>();
        this.currentItem = 0;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        this.init();
    }

    private void init() {
        if (this.viewPager == null) {
            throw new IllegalArgumentException("viewPager参数不能为空");
        } else {
            this.childCount = this.getChildCount();
            if (this.viewPager.getAdapter().getCount() != this.childCount) {
                throw new IllegalArgumentException("LinearLayout的子View(CanGradualView)数量必须和ViewPager条目数量一致");
            } else {
                for (int i = 0; i < this.childCount; ++i) {
                    if (!(this.getChildAt(i) instanceof CanGradualView)) {
                        throw new IllegalArgumentException("AlphaIndicator的子View必须是AlphaView");
                    }
                    CanGradualView canGradualView = (CanGradualView) this.getChildAt(i);
                    this.canGradualViews.add(canGradualView);
                    canGradualView.setOnClickListener(new MyOnClickListener(i));
                }
                this.viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
                ((CanGradualView) this.canGradualViews.get(this.currentItem)).setIconAlpha(1.0F);
            }
        }
    }

    private void resetState() {
        for (int i = 0; i < this.childCount; ++i) {
            ((CanGradualView) this.canGradualViews.get(i)).setIconAlpha(0.0F);
        }

    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instance_state", super.onSaveInstanceState());
        bundle.putInt("state_item", this.currentItem);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.currentItem = bundle.getInt("state_item");
            this.resetState();
            ((CanGradualView) this.canGradualViews.get(this.currentItem)).setIconAlpha(1.0F);
            super.onRestoreInstanceState(bundle.getParcelable("instance_state"));
        } else {
            super.onRestoreInstanceState(state);
        }

    }

    private class MyOnClickListener implements OnClickListener {
        private int currentIndex;

        public MyOnClickListener(int i) {
            this.currentIndex = i;
        }

        public void onClick(View v) {
            BaseLineInstructions.this.resetState();
            ((CanGradualView) BaseLineInstructions.this.canGradualViews.get(this.currentIndex)).setIconAlpha(1.0F);
            BaseLineInstructions.this.viewPager.setCurrentItem(this.currentIndex, false);
            BaseLineInstructions.this.currentItem = this.currentIndex;
        }
    }

    private class MyOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {
        private MyOnPageChangeListener() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (positionOffset > 0.0F) {
                ((CanGradualView) BaseLineInstructions.this.canGradualViews.get(position)).setIconAlpha(1.0F - positionOffset);
                ((CanGradualView) BaseLineInstructions.this.canGradualViews.get(position + 1)).setIconAlpha(positionOffset);
            }

            BaseLineInstructions.this.currentItem = position;
        }
    }
}
