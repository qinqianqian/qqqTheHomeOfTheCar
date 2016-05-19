package com.qqq.thehomeofthecar.fragment.recommendfragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.qqq.thehomeofthecar.DetailActivity;
import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.adapter.recommendadapter.VideoAdapter;
import com.qqq.thehomeofthecar.base.BaseFragment;
import com.qqq.thehomeofthecar.bean.VideoFragmentBean;
import com.qqq.thehomeofthecar.util.BroadcastValue;
import com.qqq.thehomeofthecar.util.PathValue;
import com.qqq.thehomeofthecar.util.VolleySingle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 秦谦谦 on 16/5/9 17:29.
 */
public class VideoFragment extends BaseFragment implements  AdapterView.OnItemClickListener {
    private VideoAdapter videoAdapter;
    private ListView listView;
    private VideoFragmentBean videoFragmentBean;
    private LinearLayout allLayout;

    @Override
    public int setLayout() {
        return R.layout.fragment_recommend_video;
    }

    @Override
    protected void initView(View view) {
        videoFragmentBean=new VideoFragmentBean();
        //全部的点击事件
        allLayout = bindView(R.id.recommend_select);
        allLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BroadcastValue.OPEN_DRAWLAYOUT_ALLBRADND);
                intent.putExtra("value","allVideo");
                context.sendBroadcast(intent);
            }
        });

        videoAdapter = new VideoAdapter(context);
        listView = bindView(R.id.video_lv);
        listView.setOnItemClickListener(this);
        listView.setAdapter(videoAdapter);
    }

    @Override
    protected void initData() {
        //解析数据
        VolleySingle volleySingle=new VolleySingle();
        volleySingle.addRequest(PathValue.VIDEO_FRAGMENT_HOME_PAGE,VideoFragmentBean.class, new Response.Listener<VideoFragmentBean>() {
            @Override
            public void onResponse(VideoFragmentBean response) {
                Log.d("VideoFragment", "解析成功");
                    videoAdapter.setVideoFragmentBean(response);
                videoFragmentBean=response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VideoFragment", "解析失败");
            }
        });
    }

    //点击详情的点击事件

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int ids=videoFragmentBean.getResult().getList().get(position).getId();
        String path ="http://v.autohome.com.cn/v_4_"+ids+".html";
        Intent intent=new Intent(getContext(), DetailActivity.class);
        intent.putExtra("path",path);
        startActivity(intent);
    }
}
