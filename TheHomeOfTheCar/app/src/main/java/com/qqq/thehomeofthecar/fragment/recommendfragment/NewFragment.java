package com.qqq.thehomeofthecar.fragment.recommendfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.qqq.thehomeofthecar.DetailActivity;
import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.adapter.recommendadapter.NewAdapter;
import com.qqq.thehomeofthecar.adapter.recommendadapter.NewsAdapter;
import com.qqq.thehomeofthecar.base.BaseFragment;
import com.qqq.thehomeofthecar.bean.NewFragmentBean;
import com.qqq.thehomeofthecar.cycleimage.CycleAdapter;
import com.qqq.thehomeofthecar.cycleimage.CycleViewPager;
import com.qqq.thehomeofthecar.util.DividerItemDecoration;
import com.qqq.thehomeofthecar.util.GsonRequest;
import com.qqq.thehomeofthecar.util.PathValue;
import com.qqq.thehomeofthecar.util.ViewFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by 秦谦谦 on 16/5/9 17:27.
 */
public class NewFragment extends BaseFragment implements NewAdapter.OnClickListenerRecycler {
    private RecyclerView recyclerView;
    private NewFragmentBean newFragmentBean;
    private NewAdapter newAdapter;
    private RequestQueue requestQueue;
    //轮播图
    private List<ImageView> views = new ArrayList<ImageView>();
    private CycleViewPager cycleViewPager;
    private List<String> infos = new ArrayList<>();
    private CycleAdapter cycleAdapter;
    private ViewPager viewPager;
    private RecyclerViewHeader header;
    private LinearLayout pointRoot;
    private Runnable rotateRunnable;// 轮播线程
    private Handler handler = new Handler(); // 轮播handler
    private static final int TIME = 3000;// 轮播间隔时间
    private boolean isRotate = false; // 是否轮播,默认false
    private String path;


    @Override
    public int setLayout() {
        return R.layout.fragment_recommend_new;
    }

    @Override
    protected void initView(View view) {

        newAdapter = new NewAdapter(getContext());
        recyclerView = bindView(R.id.new_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        header = RecyclerViewHeader.fromXml(context, R.layout.cycle_head_new);

        //头布局 加载部件
        viewPager = (ViewPager) header.findViewById(R.id.cycle_viewpager);
        pointRoot = (LinearLayout) header.findViewById(R.id.point_container);
        header.attachTo(recyclerView);

        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(newAdapter);
        newAdapter.setOnClickListenerRecycler(this);
    }


    @Override
    protected void initData() {
        requestQueue = Volley.newRequestQueue(getContext());
        GsonRequest<NewFragmentBean> gsonRequest = new GsonRequest<>(Request.Method.GET, PathValue.NEW_FRAGMENT_HOME_PAGE,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, new Response.Listener<NewFragmentBean>() {
            @Override
            public void onResponse(NewFragmentBean response) {
                //成功的时候执行的
                newAdapter.setNewFragmentBean(response);
                //initialize(response);
                handerWay(response);//方法加载数据

            }
        }, NewFragmentBean.class);
        requestQueue.add(gsonRequest);


    }

    private void handerWay(final NewFragmentBean newFragmentBean) {

        for (int i = 0; i < newFragmentBean.getResult().getFocusimg().size(); i++) {
            String imageUrl = newFragmentBean.getResult().getFocusimg().get(i).getImgurl();
            infos.add(imageUrl);
        }
        cycleAdapter = new CycleAdapter(infos, getContext());
        viewPager.setAdapter(cycleAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < infos.size(); i++) {
                    ImageView iv = (ImageView) pointRoot.getChildAt(i);
                    iv.setImageResource(R.mipmap.point_unpressed);
                }
                ImageView iv = (ImageView) pointRoot.getChildAt(position % infos.size());
                iv.setImageResource(R.mipmap.point_pressed);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置小点
        getPoint();
        //设置轮播
        startRotate();
    }

    /**
     * 开始轮播
     */
    protected void startRotate() {
        // 初始化线程对象
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                // 获得ViewPager当前页
                int nowIndex = viewPager.getCurrentItem();
                // 设置ViewPager的页数是当前页自增1
                // 这里要判断,轮播的下一张page不能超过viewpager的count
                // 否则会崩2
                viewPager.setCurrentItem(++nowIndex);
//                if (isRotate) {
                // handler延时发送线程,实现轮播
                handler.postDelayed(rotateRunnable, TIME);
//                }
            }
        };
        handler.postDelayed(rotateRunnable, TIME);
    }

    /**
     * 添加轮播图小点
     */
    private void getPoint() {
        for (int i = 0; i < infos.size(); i++) {
            ImageView pointIv = new ImageView(context);
            pointIv.setPadding(5, 5, 5, 5);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
            pointIv.setLayoutParams(lp);

            // 设置小点样式
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.point_unpressed);
            } else {
                pointIv.setImageResource(R.mipmap.point_pressed);
            }
            pointRoot.addView(pointIv);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isRotate = true;
    }

    // http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n%@-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html    @Override
    public void onClickRecycler(int id, int mediaType) {
        if (mediaType == 1) {
            path = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n" + id + "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";
        } else if (mediaType == 3) {
            path = "http://v.autohome.com.cn/v_4_" + id + ".html";
        } else if (mediaType == 5) {
            path = "http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t" + id + "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json";
        } else if (mediaType == 6) {
            path = "http://app.api.autohome.com.cn/autov5.0.0/news/newsdetailpicarticle-pm2-nid" + id + ".json";
        } else {
            path = null;
        }
        Intent intent = new Intent();
        intent.putExtra("path", path);
        intent.setClass(getContext(), DetailActivity.class);
        startActivity(intent);
    }


}
