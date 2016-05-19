package com.qqq.thehomeofthecar.fragment;

import android.support.annotation.FloatRange;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.adapter.FindCarAdapter;
import com.qqq.thehomeofthecar.base.BaseFragment;
import com.qqq.thehomeofthecar.fragment.findcarfragment.BrandFindCarFragment;
import com.qqq.thehomeofthecar.fragment.findcarfragment.DepreciateFindCarFragment;
import com.qqq.thehomeofthecar.fragment.findcarfragment.FindUesdCarFindCarFragment;
import com.qqq.thehomeofthecar.fragment.findcarfragment.ScreenFindCarFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 秦谦谦 on 16/5/9 15:46.
 */
public class FindCarFragment extends BaseFragment {
    private FindCarAdapter findCarAdapter;
    private List<Fragment> fragments;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public int setLayout() {
        return R.layout.fragment_findcar;
    }

    @Override
    protected void initView(View view) {
        viewPager = bindView(R.id.findcar_vp);
        tabLayout = bindView(R.id.findcar_tab);
        findCarAdapter=new FindCarAdapter(getChildFragmentManager());


    }

    @Override
    protected void initData() {
        fragments=new ArrayList<>();
        fragments.add(new BrandFindCarFragment());
        fragments.add(new ScreenFindCarFragment());
        fragments.add(new DepreciateFindCarFragment());
        fragments.add(new FindUesdCarFindCarFragment());
        findCarAdapter.setFragments(fragments);
        viewPager.setAdapter(findCarAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(0x99555555,getResources().getColor(R.color.colorTab));
        //设置引导线的颜色
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorTab));

    }
}
