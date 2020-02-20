package com.kupo.ElephantHead.ui.factory;

import androidx.fragment.app.Fragment;

import com.kupo.ElephantHead.ui.home.fragment.MessageItemFragment;
import com.kupo.ElephantHead.ui.room.fragment.RoomItemFragment;


/**
 * Created by G400 on 2019/8/8.
 * 功能：项目中所有fragment的集合(工厂)
 * 作者：
 */
public class FragmentFactory {

    public static Fragment createForArchives(int position) {
        switch (position) {
            case 1:
                return new RoomItemFragment(position);
            default:
                return new RoomItemFragment(position);
        }
    }

    public static Fragment createForMessgae(int position) {
        switch (position) {
            case 0:
                return new MessageItemFragment("未读");
            case 1:
                return new MessageItemFragment("已读");
            default:
                return new MessageItemFragment("003");
        }
    }

}
