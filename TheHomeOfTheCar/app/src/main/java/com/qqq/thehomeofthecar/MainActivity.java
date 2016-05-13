package com.qqq.thehomeofthecar;



import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.qqq.thehomeofthecar.base.BaseActivity;
import com.qqq.thehomeofthecar.fragment.FindCarFragment;
import com.qqq.thehomeofthecar.fragment.FindFragment;
import com.qqq.thehomeofthecar.fragment.ForumFragment;
import com.qqq.thehomeofthecar.fragment.MyselfFragment;
import com.qqq.thehomeofthecar.fragment.RecommendFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private int[] ids={R.id.recommend_btn,R.id.forum_btn,R.id.findcar_btn,R.id.find_btn,R.id.myself_btn};
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        for (int i=0;i<ids.length;i++){
            bindView(ids[i]).setOnClickListener(this);
        }
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction ft=manager.beginTransaction();
        ft.replace(R.id.main_fragment,new RecommendFragment());
        ft.commit();

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction ft=manager.beginTransaction();

        switch (v.getId()){
            case R.id.recommend_btn:
                ft.replace(R.id.main_fragment,new RecommendFragment());
                break;
            case R.id.forum_btn:
                ft.replace(R.id.main_fragment,new ForumFragment());
                break;
            case R.id.findcar_btn:
                ft.replace(R.id.main_fragment,new FindCarFragment());
                break;
            case R.id.find_btn:
                ft.replace(R.id.main_fragment,new FindFragment());
                break;
            case R.id.myself_btn:
                ft.replace(R.id.main_fragment,new MyselfFragment());
                break;



        }
        ft.commit();
    }
}
