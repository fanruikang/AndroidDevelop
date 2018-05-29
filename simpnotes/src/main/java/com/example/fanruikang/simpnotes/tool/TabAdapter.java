package com.example.fanruikang.simpnotes.tool;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 项目名称：AndroidDevelop
 * 类描述：
 * 创建人：FanRuikang
 * 创建时间：2018/5/28 0028 16:23
 * 修改人：FanRuikang
 * 修改时间：2018/5/28 0028 16:23
 * 修改备注：
 */

public class TabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public TabAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }



    @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

//        //设置tablayout标题
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return MainActivity.tabTitle[position];
//
//        }
    }

