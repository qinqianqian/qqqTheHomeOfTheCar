package com.qqq.thehomeofthecar.fragment.recommendfragment;

import android.content.Intent;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.qqq.thehomeofthecar.DetailActivity;
import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.UseCarThreePictureDetailActivity;
import com.qqq.thehomeofthecar.adapter.recommendadapter.UseCarAdapter;
import com.qqq.thehomeofthecar.base.BaseFragment;
import com.qqq.thehomeofthecar.bean.UseCarBean;
import com.qqq.thehomeofthecar.util.PathValue;
import com.qqq.thehomeofthecar.util.VolleySingle;

/**
 * Created by 秦谦谦 on 16/5/9 17:29.
 */
public class UseCarFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private UseCarBean useCarBean;
    private UseCarAdapter useCarAdapter;
    private ListView usecarLv;

    @Override
    public int setLayout() {
        return R.layout.fragment_recommend_usecar;
    }

    @Override
    protected void initView(View view) {
        useCarAdapter = new UseCarAdapter(context);
        usecarLv = bindView(R.id.usecar_lv);
        usecarLv.setAdapter(useCarAdapter);
        usecarLv.setOnItemClickListener(this);

    }

    @Override
    protected void initData() {

        VolleySingle.addRequest(PathValue.USECAR_FRAGMENT_HOME_PAGE, UseCarBean.class, new Response.Listener<UseCarBean>() {
            @Override
            public void onResponse(UseCarBean response) {
                useCarAdapter.setUseCarBean(response);
                useCarBean=response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

    }

    //详情的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int type=useCarBean.getResult().getNewslist().get(position).getMediatype();
        int ids=useCarBean.getResult().getNewslist().get(position).getId();
        switch (type){
            case 0:
                String pathOne="http://cont.app.autohome.com.cn/autov5.0.0/content/news/newscontent-n"+ids+"-t0.json";
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("path",pathOne);
                startActivity(intent);
                break;
            default:
                String pathThree="http://app.api.autohome.com.cn/autov5.0.0/news/newsdetailpicarticle-pm2-nid"+ids+".json";
                Log.d("UseCarFragment", pathThree);
                Intent intent1=new Intent(context, UseCarThreePictureDetailActivity.class);
                intent1.putExtra("path",pathThree);
                startActivity(intent1);
                break;
        }
    }
}
