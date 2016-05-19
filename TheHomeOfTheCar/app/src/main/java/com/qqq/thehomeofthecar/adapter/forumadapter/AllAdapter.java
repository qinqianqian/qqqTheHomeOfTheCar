package com.qqq.thehomeofthecar.adapter.forumadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.bean.ForumAllBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 秦谦谦 on 16/5/19 15:00.
 */
public class AllAdapter extends BaseAdapter {
    private Context context;

    public AllAdapter(Context context) {
        this.context = context;
    }

    private ForumAllBean forumAllBean;

    public void setForumAllBean(ForumAllBean forumAllBean) {
        this.forumAllBean = forumAllBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return forumAllBean == null ? 0 : forumAllBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHoler holer;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_picture_one, parent, false);
            holer = new MyViewHoler(convertView);
            convertView.setTag(holer);
        } else {
            holer = (MyViewHoler) convertView.getTag();
        }
        holer.replyCount.setText(forumAllBean.getResult().getList().get(position).getReplycounts()+"回");
        holer.bbsName.setText(forumAllBean.getResult().getList().get(position).getBbsname());
        holer.title.setText(forumAllBean.getResult().getList().get(position).getTitle());
        setImage(holer.smallPic,forumAllBean.getResult().getList().get(position).getSmallpic());
        return convertView;
    }

    class MyViewHoler {
        TextView title, bbsName, replyCount;
        ImageView smallPic;

        public MyViewHoler(View itemView) {
            smallPic = (ImageView) itemView.findViewById(R.id.one_smallpic);
            title = (TextView) itemView.findViewById(R.id.one_title);
            bbsName = (TextView) itemView.findViewById(R.id.one_time);
            replyCount = (TextView) itemView.findViewById(R.id.one_replycount);
        }
    }
    private void setImage(ImageView image,String path){
        Picasso.with(context).load(path).placeholder(R.mipmap.ahlib_carback).
                error(R.mipmap
        .ahlib_page_icon_empty).into(image);
    }
}
