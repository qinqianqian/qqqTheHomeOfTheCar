package com.qqq.thehomeofthecar.fragment.forumfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.base.BaseFragment;

import java.util.List;

/**
 * Created by 秦谦谦 on 16/5/10 08:39.
 */
public class TopPostsForumFragment extends BaseFragment implements View.OnClickListener {


    @Override
    public int setLayout() {
        return R.layout.fragment_forum_topposts;
    }

    @Override
    protected void initView(View view) {

       // selectedAll.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
