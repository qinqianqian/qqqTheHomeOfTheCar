package com.qqq.thehomeofthecar.fragment.recommendfragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.qqq.thehomeofthecar.BullectionActivity;
import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.adapter.recommendadapter.BullectionAdapter;
import com.qqq.thehomeofthecar.base.BaseFragment;
import com.qqq.thehomeofthecar.bean.BullectionFragmetBean;
import com.qqq.thehomeofthecar.util.BroadcastValue;
import com.qqq.thehomeofthecar.util.GsonRequest;
import com.qqq.thehomeofthecar.util.PathValue;

/**
 * Created by 秦谦谦 on 16/5/9 17:28.
 * 快报详情页
 */
public class BullectionFragment extends BaseFragment implements View.OnClickListener, AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {
    private ListView listView;
    private BullectionFragmetBean bullectionFragmetBean;
    private BullectionAdapter bullectionAdapter;
    private RelativeLayout allBrand;
    private SwipeRefreshLayout refreshLayout;
    private RequestQueue requestQueue;
    private RelativeLayout allClass;

    @Override
    public int setLayout() {
        return R.layout.fragment_recommend_bullection;
    }

    @Override
    protected void initView(View view) {
        listView = bindView(R.id.bullection_lv);
        allBrand = bindView(R.id.all_brand);
        allClass = bindView(R.id.all_class);
        allBrand.setOnClickListener(this);
        allClass.setOnClickListener(this);
        bullectionAdapter = new BullectionAdapter(context);
        listView.setAdapter(bullectionAdapter);
        listView.setOnScrollListener(this);
        refreshLayout = bindView(R.id.refresh_bullection);
        refreshLayout.setOnRefreshListener(this);

    }

    @Override
    protected void initData() {
        requestQueue = Volley.newRequestQueue(context);
        GsonRequest<BullectionFragmetBean> gsonRequest = new GsonRequest<>(Request.Method.GET, PathValue.BULLECTION_FRAGMENT_HOME_PAGE, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BullectionFragment", "解析不成功");
            }
        }, new Response.Listener<BullectionFragmetBean>() {
            @Override
            public void onResponse(final BullectionFragmetBean response) {
                bullectionAdapter.setBullectionFragmetBean(response);
                bullectionFragmetBean = response;

                //点击页面跳转进入详情页

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String path = "http://cont.app.autohome.com.cn/autov5.0.0/content/News/fastnewscontent-n" + response.getResult().getList().get(position).getId() + "-lastid0-o1.json";
                        Intent intent = new Intent();
                        intent.putExtra("path", path);
                        intent.setClass(getContext(), BullectionActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }, BullectionFragmetBean.class);
        requestQueue.add(gsonRequest);


    }

    //点击全部品牌
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(BroadcastValue.OPEN_DRAWLAYOUT_ALLBRADND);
        switch (v.getId()){
            case R.id.all_brand:
                intent.putExtra("value","allBrand");
                context.sendBroadcast(intent);
                break;
            case R.id.all_class:
                intent.putExtra("value","allClass");
                context.sendBroadcast(intent);
                break;
        }
    }

    //上拉加载
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

       // Log.d("BullectionFragmentqqq", "bullectionFragmetBean.getResult().getList().size():" + bullectionFragmetBean.getResult().getList().size());
        if (view.getLastVisiblePosition() == bullectionFragmetBean.getResult().getList().size() - 1) {
            String lastId = bullectionFragmetBean.getResult().getList().get(view.getLastVisiblePosition()).getLastid();
           // Log.d("BullectionFragmentqq", "lastId:" + lastId);
            String frashPath = "http://app.api.autohome.com.cn/autov5.0.0/news/fastnewslist-pm2-b0-l0-s20-lastid" + lastId + ".json";
            GsonRequest<BullectionFragmetBean> gsonRequest = new GsonRequest<>(Request.Method.GET, frashPath, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("BullectionFragment", "解析不成功");
                }
            }, new Response.Listener<BullectionFragmetBean>() {
                @Override
                public void onResponse(BullectionFragmetBean response) {
                    Log.d("BullectionFragment", "解析成功了");
                    //bullectionAdapter.addBullectionFragmetBean(response);
                    BullectionFragmetBean bullectionFragmetBean1 = response;
                    for (int i = 0; i < bullectionFragmetBean1.getResult().getList().size(); i++) {
                        bullectionFragmetBean.getResult().getList().add(bullectionFragmetBean1.getResult().getList().get(i));
                    }
                    bullectionAdapter.setBullectionFragmetBean(bullectionFragmetBean);


                }
            }, BullectionFragmetBean.class);
            requestQueue.add(gsonRequest);

        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    //下拉刷新的方法
    @Override
    public void onRefresh() {
        GsonRequest<BullectionFragmetBean> gsonRequest = new GsonRequest<>(
                Request.Method.GET, PathValue.BULLECTION_FRAGMENT_HOME_PAGE, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<BullectionFragmetBean>() {
            @Override
            public void onResponse(final BullectionFragmetBean response) {
//                bullectionAdapter.setBullectionFragmetBean(response);
                bullectionFragmetBean = response;


            }
        }, BullectionFragmetBean.class);
        requestQueue.add(gsonRequest);
        if (bullectionFragmetBean.getResult().getList() != null) {
            bullectionAdapter.setBullectionFragmetBean(bullectionFragmetBean);
            refreshLayout.setRefreshing(false);

        } else {
            refreshLayout.setRefreshing(false);
        }

    }
}
