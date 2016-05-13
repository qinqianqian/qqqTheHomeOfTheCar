package com.qqq.thehomeofthecar.adapter.recommendadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.bean.BullectionFragmetBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 秦谦谦 on 16/5/13 09:35.
 */
public class BullectionAdapter extends BaseAdapter {
    private BullectionFragmetBean bullectionFragmetBean;
    private Context context;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        notifyDataSetChanged();
    }

    public BullectionAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    public void setBullectionFragmetBean(BullectionFragmetBean bullectionFragmetBean) {
        this.bullectionFragmetBean = bullectionFragmetBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bullectionFragmetBean==null?0:bullectionFragmetBean.getResult().getList().size();
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
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_bullection,parent,false);
            holder=new MyViewHolder(convertView);
            convertView.setTag(holder);

        }else {
            holder= (MyViewHolder) convertView.getTag();
        }

        holder.createTime.setText(bullectionFragmetBean.getResult().getList().get(position).getCreatetime());
        holder.title.setText(bullectionFragmetBean.getResult().getList().get(position).getTitle());
        holder.typeName.setText(bullectionFragmetBean.getResult().getList().get(position).getTypename());
        holder.reviewCount.setText(bullectionFragmetBean.getResult().getList().get(position).getReviewcount()+" 人浏览");
        loadImage(holder.bgImage,bullectionFragmetBean.getResult().getList().get(position).getBgimage());

        return convertView;
    }
    class MyViewHolder{
        TextView title,typeName,reviewCount,createTime;
        ImageView bgImage;
        public MyViewHolder(View itemView){
            title= (TextView) itemView.findViewById(R.id.bullection_title);
            typeName= (TextView) itemView.findViewById(R.id.bullection_typename);
            reviewCount= (TextView) itemView.findViewById(R.id.bullection_reviewcount);
            createTime= (TextView) itemView.findViewById(R.id.bullection_createtime);
            bgImage= (ImageView) itemView.findViewById(R.id.bullection_bgimage);
        }
    }
    public void loadImage(ImageView image ,String url){
        Picasso.with(context).load(url).placeholder(R.mipmap.ahlib_carback).error(R.mipmap.ahlib_page_icon_empty).into(image);
    }
    public  interface OnClickListener {
        public void onClickL( int id);
    }
}
