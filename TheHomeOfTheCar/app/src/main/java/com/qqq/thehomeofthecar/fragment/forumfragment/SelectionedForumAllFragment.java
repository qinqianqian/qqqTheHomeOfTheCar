package com.qqq.thehomeofthecar.fragment.forumfragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.qqq.thehomeofthecar.DetailActivity;
import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.adapter.forumadapter.AllAdapter;
import com.qqq.thehomeofthecar.base.BaseFragment;
import com.qqq.thehomeofthecar.bean.ForumAllBean;
import com.qqq.thehomeofthecar.util.PathValue;
import com.qqq.thehomeofthecar.util.VolleySingle;

/**
 * Created by 秦谦谦 on 16/5/18 21:37.
 */
public class SelectionedForumAllFragment extends BaseFragment {
    private ForumAllBean forumAllBean;
    private AllAdapter allAdapter;
    private ListView listView;
    @Override
    public int setLayout() {
        return R.layout.fragment_forum_selectioned_all;
    }

    @Override
    protected void initView(View view) {
        listView=bindView(R.id.forum_selectioned_all_lv);
        allAdapter=new AllAdapter(context);
        listView.setAdapter(allAdapter);
    }

    @Override
    protected void initData() {
        VolleySingle.addRequest(PathValue.FORUM_ALL_HOME_PAGE, ForumAllBean.class, new Response.Listener<ForumAllBean>() {
            @Override
            public void onResponse(ForumAllBean response) {
                Log.d("SelectionedForumAllFrag", "论坛_全部 解析成功");
                forumAllBean=response;
                allAdapter.setForumAllBean(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SelectionedForumAllFrag", "论坛_全部 解析成功");
            }
        });
        listViewSetOnClick();
    }

    //点击进入全部的详情页面
    private void listViewSetOnClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //详情的链接http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t%@-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json
                int ids=forumAllBean.getResult().getList().get(position).getTopicid();
                String path="http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t"+ids+"-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json\n";
                Log.d("SelectionedForumAllFrag", path);
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("path",path);
                startActivity(intent);
            }
        });
    }

}
