package com.qqq.thehomeofthecar.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.adapter.ForumAdapter;
import com.qqq.thehomeofthecar.base.BaseFragment;
import com.qqq.thehomeofthecar.fragment.forumfragment.BBSForumFragment;
import com.qqq.thehomeofthecar.fragment.forumfragment.SelectionedForumFragment;
import com.qqq.thehomeofthecar.fragment.forumfragment.TopPostsForumFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 秦谦谦 on 16/5/9 15:45.
 */
public class ForumFragment extends BaseFragment{
    private List<Fragment> fragments;
    private ForumAdapter forumAdapter;
    @Override
    public int setLayout() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void initView(View view) {
        ViewPager viewPager=bindView(R.id.forum_vp);
        TabLayout tabLayout=bindView(R.id.forum_tab);
        forumAdapter=new ForumAdapter(getChildFragmentManager());

        viewPager.setAdapter(forumAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(0x99555555,getResources().getColor(R.color.colorTab));
        //设置引导线的颜色
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorTab));

    }

    @Override
    protected void initData() {
        fragments=new ArrayList<>();
        fragments.add(new SelectionedForumFragment());
        fragments.add(new TopPostsForumFragment());
        fragments.add(new BBSForumFragment());
        forumAdapter.setFragments(fragments);

    }
}
