package com.qqq.thehomeofthecar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.util.SortModel;

import java.util.List;

/**
 * Created by 秦谦谦 on 16/5/16 19:51.
 */
public class DrawerAllBrandAdapter extends BaseAdapter implements SectionIndexer {
    private List<SortModel> list=null;
    private Context context;

    public DrawerAllBrandAdapter(List<SortModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<SortModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        SortModel mContent=list.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_drawer_allbrand, null);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            holder.firstWord.setText(mContent.getSortLetters());
            holder.view.setVisibility(View.VISIBLE);
            holder.firstWord.setVisibility(View.VISIBLE);
        }else {
            holder.firstWord.setVisibility(View.GONE);

            holder.view.setVisibility(View.GONE);
        }
        holder.allWord.setText(this.list.get(position).getName());
        return convertView;
    }


    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i=0;i<getCount();i++){
            String sortStr=list.get(i).getSortLetters();
            char firstChar=sortStr.toUpperCase().charAt(0);
           if (firstChar==sectionIndex){
               return i;
           }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    class MyViewHolder {
        TextView firstWord, allWord;
        View view;

        public MyViewHolder(View itemView) {
            firstWord = (TextView) itemView.findViewById(R.id.drawer_allbrand_firstword);
            allWord = (TextView) itemView.findViewById(R.id.drawer_allbrand_allword);
            view = itemView.findViewById(R.id.drawer_allbrand_view);
        }
    }



}
