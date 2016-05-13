package com.qqq.thehomeofthecar.adapter;

import android.content.Context;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 秦谦谦 on 16/5/9 17:07.
 */
public class RecommendAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }


    public final String[] titles = {"最新", "快报", "视频", "新闻", "用车","最新", "快报", "视频", "新闻", "用车"};

    public RecommendAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
