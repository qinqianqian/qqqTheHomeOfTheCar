package com.qqq.thehomeofthecar.fragment.recommendfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.qqq.thehomeofthecar.DetailActivity;
import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.adapter.recommendadapter.NewsAdapter;
import com.qqq.thehomeofthecar.base.BaseFragment;
import com.qqq.thehomeofthecar.bean.NewsFragmentBean;
import com.qqq.thehomeofthecar.util.DividerItemDecoration;
import com.qqq.thehomeofthecar.util.GsonRequest;
import com.qqq.thehomeofthecar.util.PathValue;


/**
 * Created by 秦谦谦 on 16/5/9 17:29.
 * 新闻
 */
public class NewsFragment extends BaseFragment implements NewsAdapter.OnClickListenerRecycler {
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private WebView webView;

    @Override
    public int setLayout() {
        return R.layout.fragment_recommend_news;
    }

    @Override
    protected void initView(View view) {
        newsAdapter = new NewsAdapter(getContext());
        recyclerView = bindView(R.id.news_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.setOnClickListenerRecycler(this);
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        webView = new WebView(getContext());
    }

    @Override
    protected void initData() {
        //解析数据
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        //第一个参数是接口网址
        /*StringRequest request = new StringRequest("http://app.api.autohome.com.cn/autov5.0.0/news/newslist-pm1-c0-nt1-p1-s30-l0.json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NewsFragment", "成功了");
                        //拆分字符串:String [] args = "lsdjf".split("s");
                        Gson gson = new Gson();
                        NewsFragmentBean newsFragmentBean = gson.fromJson(response, NewsFragmentBean.class);
                        newsAdapter.setNewsFragmentBean(newsFragmentBean);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("NewsFragment", "解析失败了");
            }
        });*/
        GsonRequest<NewsFragmentBean> gsonRequest=new GsonRequest<>(Request.Method.GET, PathValue.NEWS_FRAGMEENT_HOME_PAGE, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<NewsFragmentBean>() {
            @Override
            public void onResponse(NewsFragmentBean response) {
                newsAdapter.setNewsFragmentBean(response);
            }
        },NewsFragmentBean.class);
        requestQueue.add(gsonRequest);

    }

    //点击recyclerView
    @Override
    public void onClickRecycler(int id) {
        String path = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n"+id +"-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";
        Intent intent=new Intent();
        intent.putExtra("path",path);
        intent.setClass(getContext(), DetailActivity.class);
        startActivity(intent);
        //详情
//http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n%@-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html
    }
}
