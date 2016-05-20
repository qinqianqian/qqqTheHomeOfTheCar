package com.qqq.thehomeofthecar.adapter.findcaradapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.bean.findcar.BrandListViewBean;
import com.qqq.thehomeofthecar.bean.findcar.HotBrandBean;
import com.qqq.thehomeofthecar.userdefined.FindCarBrandDetailListView;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 秦谦谦 on 16/5/19 20:04.
 */
public class BrandAdapter extends BaseExpandableListAdapter {
    private Context context;

    public BrandAdapter(Context context) {
        this.context = context;
    }

    private BrandListViewBean brandListViewBean;

    public void setBrandListViewBean(BrandListViewBean brandListViewBean) {
        this.brandListViewBean = brandListViewBean;
        Log.d("BrandAdapter", "brandListViewBean:" + brandListViewBean.getResult().getBrandlist().size());
        notifyDataSetChanged();
    }


    @Override
    public int getGroupCount() {
        return brandListViewBean.getResult().getBrandlist().size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return brandListViewBean.getResult().getBrandlist().get(groupPosition).getList().size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return brandListViewBean.getResult().getBrandlist().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return brandListViewBean.getResult().getBrandlist().get(groupPosition).getList().get(childPosition);

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        MyTvViewHolder tvHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_findcar_brand_listview_detail_tv, null);
            tvHolder = new MyTvViewHolder(convertView);
            convertView.setTag(tvHolder);
        }
        tvHolder = (MyTvViewHolder) convertView.getTag();
        tvHolder.textView.setText(brandListViewBean.getResult().getBrandlist().get(groupPosition).getLetter());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        MyImgViewHolder imgHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_findcar_brand_listview_detail_img, null);
            imgHolder = new MyImgViewHolder(convertView);
            convertView.setTag(imgHolder);
        } else {
            imgHolder = (MyImgViewHolder) convertView.getTag();
        }
        imgHolder.tv.setText(brandListViewBean.getResult().getBrandlist().get(groupPosition).getList().get(childPosition).getName());
        setImage(imgHolder.img,brandListViewBean.getResult().getBrandlist().get(groupPosition).getList().get(childPosition).getImgurl());
        //子listview的点击事件111111

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ("点击事件" + childPosition + brandListViewBean.getResult().getBrandlist().get(groupPosition).getList().get(childPosition).getName()), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    //子条目需要响应onclick事件,必须返回true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class MyTvViewHolder {
        TextView textView;

        public MyTvViewHolder(View itemView) {
            textView = (TextView) itemView.findViewById(R.id.findCar_brand_firstTv);
        }
    }

    class MyImgViewHolder {
        TextView tv;
        ImageView img;

        public MyImgViewHolder(View itemView) {
            tv = (TextView) itemView.findViewById(R.id.secondLv_name);
            img = (ImageView) itemView.findViewById(R.id.secondLv_imgurl);
        }
    }

    //毕加索
    private void setImage(ImageView image, String path) {
        Picasso.with(context).load(path).placeholder(R.mipmap.ahlib_carback).error(R.mipmap.ahlib_page_icon_empty).into(image);
    }






}
