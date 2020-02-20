package com.kupo.ElephantHead.ui.main.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.kupo.ElephantHead.ui.transaction.CrowdFragment;
import com.kupo.ElephantHead.ui.home.HomeFragment;
import com.kupo.ElephantHead.ui.mine.MineFragment;
import com.kupo.ElephantHead.ui.room.ShowRoomFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MainAdapter
 * @Description: main的模块界面适配器
 * @Author:
 * @CreateDate: 2019/8/13 10:58
 * @Version: 1.0
 */
public class MainAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<Fragment>();

    public MainAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new HomeFragment());
        fragments.add(new ShowRoomFragment());
        fragments.add(new CrowdFragment());
        fragments.add(new MineFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
