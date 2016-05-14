package com.qqq.thehomeofthecar.adapter.recommendadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.base.BaseActivity;
import com.qqq.thehomeofthecar.bean.BullectionDetailBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 秦谦谦 on 16/5/14 10:07.
 */
public class BullectionDetailAdapter extends BaseAdapter {
    private Context context;

    public BullectionDetailAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    private BullectionDetailBean bullectionDetailBean;

    public void setBullectionDetailBean(BullectionDetailBean bullectionDetailBean) {
        this.bullectionDetailBean = bullectionDetailBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bullectionDetailBean == null ? 0 : bullectionDetailBean.getResult().getMessagelist().size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bullection_detial, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }

      //  Log.d("BullectionDetailAdapter", bullectionDetailBean.getResult().getMessagelist().get(position).getContent());
        holder.authorName.setText(bullectionDetailBean.getResult().getMessagelist().get(position).getAuthorname());
        holder.content.setText(bullectionDetailBean.getResult().getMessagelist().get(position).getContent());
        holder.publishTime.setText(bullectionDetailBean.getResult().getMessagelist().get(position).getPublishtime());
        setImage(holder.heading, bullectionDetailBean.getResult().getMessagelist().get(position).getHeadimg());
      setImage(holder.picurl, bullectionDetailBean.getResult().getMessagelist().get(position).getAttachments().get(0).getPicurl());

        return convertView;
    }

    class MyViewHolder {
        ImageView heading, picurl;
        TextView authorName, publishTime, content;

        public MyViewHolder(View itemView) {
            heading = (ImageView) itemView.findViewById(R.id.bullection_headings);
            picurl = (ImageView) itemView.findViewById(R.id.bullection_list_heading);
            authorName = (TextView) itemView.findViewById(R.id.bullection_newsauthoridSmall);
            publishTime = (TextView) itemView.findViewById(R.id.bullection_createtimeSmall);
            content = (TextView) itemView.findViewById(R.id.bullection_summary);

        }
    }

    public void setImage(ImageView image, String path) {
        Picasso.with(context).load(path).placeholder(R.mipmap.ahlib_common_main_filter_icon_f).error(R.mipmap.ahlib_common_main_filter_icon_p).into(image);
    }
}
