package com.qqq.thehomeofthecar;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.qqq.thehomeofthecar.adapter.recommendadapter.BullectionAdapter;
import com.qqq.thehomeofthecar.adapter.recommendadapter.BullectionDetailAdapter;
import com.qqq.thehomeofthecar.base.BaseActivity;
import com.qqq.thehomeofthecar.bean.BullectionDetailBean;
import com.qqq.thehomeofthecar.util.GsonRequest;

import it.sephiroth.android.library.picasso.Picasso;

import static com.qqq.thehomeofthecar.R.id.bullection_img;

/**
 * Created by 秦谦谦 on 16/5/13 16:44.
 */
public class BullectionActivity extends BaseActivity {
    private TextView newsTypeName, title, newsAuthorid, reviewCount, newsAuthoridSmall, createTime, summary;
    private ImageView img, heading;
    private ListView listView;
    private BullectionDetailAdapter bullectionDetailAdapter;
    private View headerView;

    @Override
    protected int getLayout() {
        return R.layout.activity_bullection;
    }

    @Override
    protected void initView() {

        listView = bindView(R.id.bullection_detial_lv);
        headerView = getLayoutInflater().inflate(R.layout.item_bullection_head,null);
        newsTypeName = (TextView) headerView.findViewById(R.id.bullection_newstypename);
        title = (TextView) headerView.findViewById(R.id.bullection_title);
        newsAuthorid = (TextView) headerView.findViewById(R.id.bullection_newsauthorid);
        reviewCount = (TextView) headerView.findViewById(R.id.bullection_review);
        newsAuthoridSmall = (TextView) headerView.findViewById(R.id.bullection_newsauthoridSmalls);
        createTime = (TextView) headerView.findViewById(R.id.bullection_createtimeSmalls);
        heading = (ImageView) headerView.findViewById(R.id.bullection_headingSmall);
        summary= (TextView) headerView.findViewById(R.id.bullection_summary);
        img= (ImageView) headerView.findViewById(bullection_img);
        listView.addHeaderView(headerView);

        bullectionDetailAdapter=new BullectionDetailAdapter(this);
        listView.setAdapter(bullectionDetailAdapter);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        request(path);

    }

    private void request(String path) {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        GsonRequest<BullectionDetailBean> gsonBean = new GsonRequest<>(Request.Method.GET, path, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BullectionActivity", "解析失败");
            }
        }, new Response.Listener<BullectionDetailBean>() {
            @Override
            public void onResponse(BullectionDetailBean response) {

                setData(response);
                bullectionDetailAdapter.setBullectionDetailBean(response);

            }
        }, BullectionDetailBean.class);
        requestQueue.add(gsonBean);
    }

    public void setData(BullectionDetailBean bullectionDetailBean) {
        Log.d("BullectionActivity", bullectionDetailBean.getResult().getNewsdata().getNewstypeanme());
        newsTypeName.setText(bullectionDetailBean.getResult().getNewsdata().getNewstypeanme());
        title.setText(bullectionDetailBean.getResult().getNewsdata().getTitle());
        newsAuthorid.setText(bullectionDetailBean.getResult().getNewsdata().getNewsauthorid() + "");
        reviewCount.setText(bullectionDetailBean.getResult().getNewsdata().getReviewcount() + "");
        newsAuthoridSmall.setText(bullectionDetailBean.getResult().getNewsdata().getNewsauthor());
        createTime.setText(bullectionDetailBean.getResult().getNewsdata().getCreatetime());
        summary.setText(bullectionDetailBean.getResult().getNewsdata().getSummary());
        setImage(img, bullectionDetailBean.getResult().getNewsdata().getImg());
        setImage(heading, bullectionDetailBean.getResult().getNewsdata().getHeadimg());
    }

    public void setImage(ImageView image, String path) {
        Picasso.with(this).load(path).placeholder(R.mipmap.ahlib_common_main_filter_icon_f).error(R.mipmap.ahlib_common_main_filter_icon_p).into(image);
    }
}
