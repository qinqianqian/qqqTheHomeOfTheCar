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
import com.qqq.thehomeofthecar.bean.UseCarBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 秦谦谦 on 16/5/16 11:28.
 */
public class UseCarAdapter extends BaseAdapter {
    private Context context;
    private OneViewHolder oneViewHolder;
    private ThreeViewHolder threeViewHolder;

    public UseCarAdapter(Context context) {
        this.context = context;
    }

    private UseCarBean useCarBean;

    public void setUseCarBean(UseCarBean useCarBean) {
        this.useCarBean = useCarBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return useCarBean.getResult().getNewslist().get(position).getMediatype();
    }

    @Override
    public int getCount() {
        return useCarBean==null?0:useCarBean.getResult().getNewslist().size();
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
        int type = getItemViewType(position);

        switch (type) {

            case 0:
            oneViewHolder = null;
                if (convertView == null||convertView.getTag()!=oneViewHolder) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_picture_one, null);
                    oneViewHolder = new OneViewHolder(convertView);
                    convertView.setTag(oneViewHolder);
                } else {
                    oneViewHolder = (OneViewHolder) convertView.getTag();
                }
                oneViewHolder.oneReplyCount.setText(useCarBean.getResult().getNewslist().get(position).getReplycount()+"评论");
                oneViewHolder.oneTime.setText(useCarBean.getResult().getNewslist().get(position).getTime());
                oneViewHolder.oneTitle.setText(useCarBean.getResult().getNewslist().get(position).getTitle());
                setImage(oneViewHolder.oneSmallPic, useCarBean.getResult().getNewslist().get(position).getSmallpic());
                break;
            default:
                threeViewHolder = null;
                if ((convertView == null) || (convertView.getTag()!=threeViewHolder)) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_pictice_three, null);
                    threeViewHolder = new ThreeViewHolder(convertView);
                    convertView.setTag(threeViewHolder);
                } else {
                    threeViewHolder = (ThreeViewHolder) convertView.getTag();
                }
                String path=useCarBean.getResult().getNewslist().get(position).getIndexdetail();
                String [] arg=path.split("㊣");
                setImage(threeViewHolder.threeSmallPic, arg[0]);
                setImage(threeViewHolder.threeIndexDetialOne,arg[1]);
                setImage(threeViewHolder.getThreeIndexDetialTwo,arg[2]);
                threeViewHolder.threeTime.setText(useCarBean.getResult().getNewslist().get(position).getTime());
                threeViewHolder.threeTitle.setText(useCarBean.getResult().getNewslist().get(position).getTitle());
                threeViewHolder.threeReplyCount.setText(useCarBean.getResult().getNewslist().get(position).getReplycount()+"评论");
                break;
        }
        return convertView;
    }

    class OneViewHolder {
        ImageView oneSmallPic;
        TextView oneTitle;
        TextView oneTime;
        TextView oneReplyCount;

        public OneViewHolder(View itemView) {

            oneSmallPic = (ImageView) itemView.findViewById(R.id.one_smallpic);
            oneTitle = (TextView) itemView.findViewById(R.id.one_title);
            oneTime = (TextView) itemView.findViewById(R.id.one_time);
            oneReplyCount = (TextView) itemView.findViewById(R.id.one_replycount);


        }

    }

    class ThreeViewHolder {
        TextView threeTitle;
        TextView threeTime;
        TextView threeReplyCount;
        ImageView threeSmallPic;
        ImageView threeIndexDetialOne;
        ImageView getThreeIndexDetialTwo;

        public ThreeViewHolder(View itemView) {

            threeTitle = (TextView) itemView.findViewById(R.id.three_title);
            threeTime = (TextView) itemView.findViewById(R.id.three_time);
            threeReplyCount = (TextView) itemView.findViewById(R.id.three_replycount);
            threeSmallPic = (ImageView) itemView.findViewById(R.id.three_smallpic);
            threeIndexDetialOne = (ImageView) itemView.findViewById(R.id.three_indexdatail_one);
            getThreeIndexDetialTwo = (ImageView) itemView.findViewById(R.id.three_indexdatail_two);
        }
    }

    public void setImage(ImageView image, String path) {
        Picasso.with(context).load(path).placeholder(R.mipmap.ahlib_carback).error(R.mipmap.ahlib_page_icon_empty).into(image);
    }
}