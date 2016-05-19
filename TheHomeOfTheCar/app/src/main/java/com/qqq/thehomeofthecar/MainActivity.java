package com.qqq.thehomeofthecar;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qqq.thehomeofthecar.adapter.DrawerAdapter;
import com.qqq.thehomeofthecar.adapter.DrawerAllBrandAdapter;
import com.qqq.thehomeofthecar.base.BaseActivity;
import com.qqq.thehomeofthecar.fragment.FindCarFragment;
import com.qqq.thehomeofthecar.fragment.FindFragment;
import com.qqq.thehomeofthecar.fragment.ForumFragment;
import com.qqq.thehomeofthecar.fragment.MyselfFragment;
import com.qqq.thehomeofthecar.fragment.RecommendFragment;
import com.qqq.thehomeofthecar.util.BroadcastValue;
import com.qqq.thehomeofthecar.util.CharacterParser;
import com.qqq.thehomeofthecar.util.PinyinComparator;
import com.qqq.thehomeofthecar.util.SideBar;
import com.qqq.thehomeofthecar.util.SortModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, DrawerLayout.DrawerListener {
    private int[] ids = {R.id.recommend_btn, R.id.forum_btn, R.id.findcar_btn, R.id.find_btn, R.id.myself_btn};
    private DrawerLayout drawerLayout;
    // private ListView mainlist;
    private MyBroadcast myBroadcast;
    private ListView drawerLv;
    private TextView drawerClose;
    private LinearLayout drawer;
    private DrawerAdapter drawerAdapter;
    private RelativeLayout relativeLayout;
    private ListView listView;
    private SideBar sideBar;
    private TextView dialog;//点击显示字母
    private DrawerAllBrandAdapter drawerAllBrandAdapter;//全部品牌的adapter
    /**************************/
    private CharacterParser characterParser;
    private List<SortModel> sourceDateList;
    //用拼音来排列ListView里面的数据
    private PinyinComparator pinyinComparator;


    /**************************/

    @Override
    protected int getLayout() {

        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //全部品牌的点击事件
        TextView pinpai = bindView(R.id.drawerlayout_brand);
        //放置渗透才写的监听,并没有实际意义
        bindView(R.id.drawer_view).setOnClickListener(this);
        pinpai.setOnClickListener(this);


        drawerAdapter = new DrawerAdapter(this);
        drawerLayout = bindView(R.id.drawerLayout);
        //抽屉
        //索引条
        sideBar = bindView(R.id.sidrbar);
        dialog = bindView(R.id.dialog);
        /*sideBar.setTextView(dialog);*/

        drawerClose = bindView(R.id.drawerlayout_close);
        //抽屉 全部品牌
        relativeLayout = bindView(R.id.drawer_brand_layout);
        listView = bindView(R.id.drawer_brand_lv);


        drawerLv = bindView(R.id.drawerlayout_lv);
        drawerLv.setAdapter(drawerAdapter);
        //     mainlist=bindView(R.id.main_lv);
        for (int i = 0; i < ids.length; i++) {
            bindView(ids[i]).setOnClickListener(this);
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.main_fragment, new RecommendFragment());
        ft.commit();

    }

    //设置右侧触摸监听
    private void sideBarOnTouchingLetterChangedListener() {
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                sideBar.setTextView(dialog);
                Log.d("MainActivity", "sideBar.getId():" + sideBar.getId());
                //该字母首次出现的位置
                int position = drawerAllBrandAdapter.getPositionForSection(s.charAt(0));
                //              dialog.setVisibility(View.VISIBLE);
//               dialog.setText(sideBar.b[position%(sideBar.b.length)]);
                Log.d("MainActivity", "position:" + position);
                if (position != -1) {
                    listView.setSelection(position);
                    Log.d("MainActivity", "sideBar.getId():" + sideBar.getId());

                }
            }
        });
    }

    @Override
    protected void initData() {

        //广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BroadcastValue.OPEN_DRAWLAYOUT_ALLBRADND);
        myBroadcast = new MyBroadcast();
        registerReceiver(myBroadcast, intentFilter);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.setDrawerListener(this);
        /******/
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        switch (v.getId()) {
            case R.id.recommend_btn:
                ft.replace(R.id.main_fragment, new RecommendFragment());
                break;
            case R.id.forum_btn:
                ft.replace(R.id.main_fragment, new ForumFragment());
                break;
            case R.id.findcar_btn:
                ft.replace(R.id.main_fragment, new FindCarFragment());
                break;
            case R.id.find_btn:
                ft.replace(R.id.main_fragment, new FindFragment());
                break;
            case R.id.myself_btn:
                ft.replace(R.id.main_fragment, new MyselfFragment());
                break;


        }
        ft.commit();
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    //内部接收广播
    class MyBroadcast extends BroadcastReceiver implements AdapterView.OnItemClickListener {

        @Override
        public void onReceive(Context context, Intent intent) {
            //drawer.setVisibility(View.VISIBLE);
            drawerLayout.openDrawer(Gravity.RIGHT);
            String value = intent.getStringExtra("value");
            //点击关闭按钮
            drawerClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
            });

            switch (value) {
                case "allBrand":
                    sourceDateList = filledData(getResources().getStringArray(R.array.date));
                    Collections.sort(sourceDateList, pinyinComparator);
                    drawerAllBrandAdapter = new DrawerAllBrandAdapter(sourceDateList, MainActivity.this);
                    drawerLv.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    sideBarOnTouchingLetterChangedListener();
                    listView.setAdapter(drawerAdapter);
                    drawerAllBrandAdapter.setList(sourceDateList);
                    listView.setAdapter(drawerAllBrandAdapter);
                    listView.setOnItemClickListener(this);
                    break;
                case "allClass":
                    relativeLayout.setVisibility(View.GONE);
                    drawerLv = bindView(R.id.drawerlayout_lv);
                    drawerLv.setVisibility(View.VISIBLE);
                    drawerLv.setAdapter(drawerAdapter);
                    List<String> listClass = new ArrayList<>();
                    listClass.add("秦谦谦");
                    listClass.add("梁多");
                    listClass.add("文静");
                    drawerAdapter.setList(listClass);
                    drawerLv.setOnItemClickListener(this);
                    break;
                case "allVideo":
                    relativeLayout.setVisibility(View.GONE);
                    drawerLv = bindView(R.id.drawerlayout_lv);
                    drawerLv.setVisibility(View.VISIBLE);
                    drawerLv.setAdapter(drawerAdapter);
                    List<String> listVideo = new ArrayList<>();
                    listVideo.add("视频");
                    listVideo.add("原创");
                    listVideo.add("实拍");
                    listVideo.add("试车");
                    listVideo.add("花边");
                    listVideo.add("事件");
                    listVideo.add("新车");
                    listVideo.add("广告");
                    listVideo.add("技术");
                    listVideo.add("二手车");


                    drawerAdapter.setList(listVideo);
                    drawerLv.setOnItemClickListener(this);
                    break;
            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, "点击成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myBroadcast);
        super.onDestroy();
    }

    //为list填充数据
    private List<SortModel> filledData(String[] data) {
        List<SortModel> mSortList = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(data[i]);
            //汉字转换为拼音
            String pinyin = characterParser.getSelling(data[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            //正则表达式,判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }
}
