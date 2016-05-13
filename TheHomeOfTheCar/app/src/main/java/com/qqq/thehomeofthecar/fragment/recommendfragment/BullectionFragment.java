package com.qqq.thehomeofthecar.fragment.recommendfragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.qqq.thehomeofthecar.DetailActivity;
import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.adapter.recommendadapter.BullectionAdapter;
import com.qqq.thehomeofthecar.base.BaseFragment;
import com.qqq.thehomeofthecar.bean.BullectionFragmetBean;
import com.qqq.thehomeofthecar.util.BroadcastValue;
import com.qqq.thehomeofthecar.util.GsonRequest;
import com.qqq.thehomeofthecar.util.PathValue;

/**
 * Created by 秦谦谦 on 16/5/9 17:28.
 */
public class BullectionFragment extends BaseFragment implements View.OnClickListener {
    private ListView listView;
    private BullectionFragmetBean bullectionFragmetBean;
    private BullectionAdapter bullectionAdapter;
    private RelativeLayout allBrand;

    @Override
    public int setLayout() {
        return R.layout.fragment_recommend_bullection;
    }

    @Override
    protected void initView(View view) {
        listView = bindView(R.id.bullection_lv);
        allBrand=bindView(R.id.all_brand);
        allBrand.setOnClickListener(this);
        bullectionAdapter = new BullectionAdapter(context);
        listView.setAdapter(bullectionAdapter);

    }

    @Override
    protected void initData() {
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        GsonRequest<BullectionFragmetBean> gsonRequest=new GsonRequest<>(Request.Method.GET, PathValue.BULLECTION_FRAGMENT_HOME_PAGE, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BullectionFragment", "解析不成功");
            }
        }, new Response.Listener<BullectionFragmetBean>() {
            @Override
            public void onResponse(final BullectionFragmetBean response) {
                bullectionAdapter.setBullectionFragmetBean(response);
               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       String path = "http://cont.app.autohome.com.cn/autov5.0.0/content/News/fastnewscontent-n"+response.getResult().getList().get(position).getId()+"-lastid0-o1.json";
                       //Log.d("BullectionFragment", path);
                       Intent intent=new Intent();
                       intent.putExtra("path",path);
                       intent.setClass(getContext(), DetailActivity.class);
                       startActivity(intent);
                   }
               });
            }
        },BullectionFragmetBean.class);
        requestQueue.add(gsonRequest);



    }

//点击全部品牌
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(BroadcastValue.OPEN_DRAWLAYOUT);
        context.sendBroadcast(intent);
    }
}
