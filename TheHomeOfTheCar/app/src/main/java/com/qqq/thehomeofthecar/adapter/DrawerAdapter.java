package com.qqq.thehomeofthecar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qqq.thehomeofthecar.R;

import java.util.List;

/**
 * Created by 秦谦谦 on 16/5/16 15:05.
 */
public class DrawerAdapter extends BaseAdapter {
    List<String> list;
private Context context;

    public DrawerAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_detail_drawer,null);
            holder=new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (MyViewHolder) convertView.getTag();
        }
        holder.textView.setText(list.get(position));
        return convertView;
    }
    class MyViewHolder{
        TextView textView;
        public MyViewHolder(View itemView){
            textView= (TextView) itemView.findViewById(R.id.drawer_list_tv);
        }
    }
}
