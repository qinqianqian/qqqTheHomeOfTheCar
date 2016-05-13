package com.qqq.thehomeofthecar.cycleimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.print.PageRange;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.qqq.thehomeofthecar.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;


/**
 * Created by 秦谦谦 on 16/5/12 16:17.
 */
public class CycleAdapter extends PagerAdapter {
    private List<String> views;
    private Context context;

    public CycleAdapter(List<String> views, Context context) {
        this.views = views;
        this.context = context;
    }

    @Override
    public int getCount() {
   // Log.d("CycleAdapter", "views.size():" + views.size());
        return views == null ? 0 : Integer.MAX_VALUE;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem( ViewGroup container,  int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.cycle_view_banner, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.cycle_image);
        //获取图片
        Picasso.with(context).load(views.get(position % views.size())).placeholder(R.mipmap.ahlib_carback).error(R.mipmap.ahlib_page_icon_empty).into(imageView);

        //ViewParent vp=view.getParent();
        //if语句必须加上,如果不加的话滑到最后一个页面的时候就会产生这个异常
        //原因是我们试图把一个有父组件的View添加到另一个组件
        /*if (vp!=null){
            ViewGroup parent= (ViewGroup) vp;
            parent.removeView(view);
        }
*/
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }


}
