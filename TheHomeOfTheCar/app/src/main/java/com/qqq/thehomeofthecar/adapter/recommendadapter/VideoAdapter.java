package com.qqq.thehomeofthecar.adapter.recommendadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.bean.VideoFragmentBean;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 秦谦谦 on 16/5/16 09:03.
 */
public class VideoAdapter extends BaseAdapter {
    private Context context;
    private VideoFragmentBean videoFragmentBean;

    public void setVideoFragmentBean(VideoFragmentBean videoFragmentBean) {
        this.videoFragmentBean = videoFragmentBean;
        notifyDataSetChanged();
    }

    public VideoAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return videoFragmentBean == null ? 0 : videoFragmentBean.getResult().getList().size();
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
        MyViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_picture_one, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }

        holder.oneTitle.setText(videoFragmentBean.getResult().getList().get(position).getTitle());
        holder.oneReplyCount.setText(videoFragmentBean.getResult().getList().get(position).getPlaycount()+ " 播放");
        getImage(holder.oneSmallPic,videoFragmentBean.getResult().getList().get(position).getSmallimg());
        holder.oneTime.setText(videoFragmentBean.getResult().getList().get(position).getTime());


        return convertView;
    }

    class MyViewHolder {
        ImageView oneSmallPic;
        TextView oneTitle;
        TextView oneTime;
        TextView oneReplyCount;

        public MyViewHolder(View itemView) {
            oneSmallPic = (ImageView) itemView.findViewById(R.id.one_smallpic);
            oneTitle = (TextView) itemView.findViewById(R.id.one_title);
            oneTime = (TextView) itemView.findViewById(R.id.one_time);
            oneReplyCount = (TextView) itemView.findViewById(R.id.one_replycount);
        }

    }

    public void getImage(ImageView imageView, String path) {
        Picasso.with(context).load(path).placeholder(R.mipmap.ahlib_common_main_filter_icon_f).error(R.mipmap.ahlib_common_main_filter_icon_p).into(imageView);
    }
}