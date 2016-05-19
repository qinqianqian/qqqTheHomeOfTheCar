package com.qqq.thehomeofthecar.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.adapter.RecommendAdapter;
import com.qqq.thehomeofthecar.base.BaseFragment;
import com.qqq.thehomeofthecar.fragment.recommendfragment.BullectionFragment;
import com.qqq.thehomeofthecar.fragment.recommendfragment.NewFragment;
import com.qqq.thehomeofthecar.fragment.recommendfragment.NewsFragment;
import com.qqq.thehomeofthecar.fragment.recommendfragment.UseCarFragment;
import com.qqq.thehomeofthecar.fragment.recommendfragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 秦谦谦 on 16/5/9 15:41.
 */
public class RecommendFragment extends BaseFragment {
    private RecommendAdapter recommendAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(View view) {
        viewPager = bindView(R.id.recommend_vp);
        tabLayout = bindView(R.id.recommend_tab);
        recommendAdapter=new RecommendAdapter(getChildFragmentManager());

    }

    @Override
    protected void initData() {
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new NewFragment());
        fragments.add(new BullectionFragment());
        fragments.add(new VideoFragment());
        fragments.add(new NewsFragment());
        fragments.add(new UseCarFragment());
        fragments.add(new UseCarFragment());
        fragments.add(new BullectionFragment());
        fragments.add(new VideoFragment());
        fragments.add(new NewsFragment());
        fragments.add(new UseCarFragment());
        recommendAdapter.setFragments(fragments);


        viewPager.setAdapter(recommendAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(0x99555555,getResources().getColor(R.color.colorTab));
        //设置引导线的颜色
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorTab));
    }
}
