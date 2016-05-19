package com.qqq.thehomeofthecar.fragment.forumfragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.base.BaseFragment;

/**
 * Created by 秦谦谦 on 16/5/10 08:37.
 */
public class SelectionedForumFragment extends BaseFragment{
    private RadioButton selectedAll;
    @Override
    public int setLayout() {
        return R.layout.fragment_forum_selectioned;
    }

    @Override
    protected void initView(View view) {
        selectedAll = bindView(R.id.select_radio_all);
    }

    @Override
    protected void initData() {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frgment_forum_all, new SelectionedForumAllFragment());
        ft.commit();
    }
}
