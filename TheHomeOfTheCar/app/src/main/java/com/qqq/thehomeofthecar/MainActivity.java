package com.qqq.thehomeofthecar;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.qqq.thehomeofthecar.base.BaseActivity;
import com.qqq.thehomeofthecar.fragment.FindCarFragment;
import com.qqq.thehomeofthecar.fragment.FindFragment;
import com.qqq.thehomeofthecar.fragment.ForumFragment;
import com.qqq.thehomeofthecar.fragment.MyselfFragment;
import com.qqq.thehomeofthecar.fragment.RecommendFragment;
import com.qqq.thehomeofthecar.util.BroadcastValue;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private int[] ids={R.id.recommend_btn,R.id.forum_btn,R.id.findcar_btn,R.id.find_btn,R.id.myself_btn};
    private DrawerLayout drawerLayout;
   // private ListView mainlist;
    private MyBroadcast myBroadcast;

    @Override
    protected int getLayout() {

        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        drawerLayout = bindView(R.id.drawerLayout);
   //     mainlist=bindView(R.id.main_lv);
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
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(BroadcastValue.OPEN_DRAWLAYOUT);
        myBroadcast=new MyBroadcast();
        registerReceiver(myBroadcast,intentFilter);
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

    //内部接收广播
    class MyBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myBroadcast);
        super.onDestroy();
    }
}
