package com.kupo.ElephantHead.ui.home.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kupo.ElephantHead.ui.factory.FragmentFactory;

import java.util.List;

/**
 * Created by G400 on 2019/8/8.
 * 功能：自定义首页tabLayout的页面
 * 作者：
 */
public class TeamItemPagerAdapter extends FragmentStatePagerAdapter {
    List<String> titles;

    public TeamItemPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createForMessgae(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
