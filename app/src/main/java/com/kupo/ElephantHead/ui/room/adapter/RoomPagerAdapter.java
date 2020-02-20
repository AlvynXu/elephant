package com.kupo.ElephantHead.ui.room.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.blankj.utilcode.util.LogUtils;
import com.kupo.ElephantHead.ui.factory.FragmentFactory;
import com.kupo.ElephantHead.ui.room.model.CategoryModel;

import java.util.List;

/**
 * Created by G400 on 2019/8/8.
 * 功能：自定义tabLayout的页面
 */
public class RoomPagerAdapter extends FragmentStatePagerAdapter {
    List<CategoryModel.DataBean> titles;

    public RoomPagerAdapter(FragmentManager fm, List<CategoryModel.DataBean> titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createForArchives(titles.get(position).getId());
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getTitle();
    }
}
