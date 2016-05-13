package com.qqq.thehomeofthecar.adapter.recommendadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.bean.NewsFragmentBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 秦谦谦 on 16/5/10 15:41.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 汽车之家 新闻界面 一共有两个布局 mediaType10和mediaType0
     *
     * @param parent
     * @param viewType
     * @return
     */
    Context context;
    private NewsFragmentBean newsFragmentBean;
    private LayoutInflater inflater;
    private RequestQueue requestQueue;
    //内部接口,实现详情的点击事件
    OnClickListenerRecycler onClickListenerRecycler;


    public void setOnClickListenerRecycler(OnClickListenerRecycler onClickListenerRecycler) {
        this.onClickListenerRecycler = onClickListenerRecycler;
        notifyDataSetChanged();
    }

    public NewsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setNewsFragmentBean(NewsFragmentBean newsFragmentBean) {
        this.newsFragmentBean = newsFragmentBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return newsFragmentBean.getResult().getNewslist().get(position).getMediatype();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 0:
                viewHolder = new ZeroViewHolder(inflater.inflate(R.layout.item_picture_one, parent, false));
                break;
            case 10:
                viewHolder = new TenViewHolder(inflater.inflate(R.layout.item_pictice_three, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        final RecyclerView.ViewHolder viewHolder=holder;
        switch (viewType) {
            case 0:
                ZeroViewHolder zeroViewHolder = (ZeroViewHolder) holder;
                zeroViewHolder.zeroTitle.setText(newsFragmentBean.getResult().getNewslist().get(position).getTitle());
                zeroViewHolder.zeroTime.setText(newsFragmentBean.getResult().getNewslist().get(position).getTime());
                zeroViewHolder.zeroReplyCount.setText(newsFragmentBean.getResult().getNewslist().get(position).getReplycount() + " 评论");
                //传入图片的名称和网址
                loadImage(zeroViewHolder.zeroSmallPic, newsFragmentBean.getResult().getNewslist().get(position).getSmallpic());
                if (onClickListenerRecycler!=null){
                    //设置点击事件
                            zeroViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                            int pos=viewHolder.getLayoutPosition();
                            Log.d("NewsAdapter", "pos:" + pos);
                            int id=newsFragmentBean.getResult().getNewslist().get(pos).getId();
                            onClickListenerRecycler.onClickRecycler(id);
                        }
                    });
                }
                break;
            case 10:
                TenViewHolder tenViewHolder = (TenViewHolder) holder;
                tenViewHolder.tenTitle.setText(newsFragmentBean.getResult().getNewslist().get(position).getTitle());
                tenViewHolder.tenTime.setText(newsFragmentBean.getResult().getNewslist().get(position).getTime());
                tenViewHolder.tenReplyCount.setText(newsFragmentBean.getResult().getNewslist().get(position).getReplycount() + " 评论");
                String image=newsFragmentBean.getResult().getNewslist().get(position).getIndexdetail();
                String[] path = (image.split("㊣"));
                loadImage(tenViewHolder.tenSmallPic, path[0]);
                loadImage(tenViewHolder.tenIndexDetialOne, path[1]);
                loadImage(tenViewHolder.getTenIndexDetialTwo, path[2]);
                if (onClickListenerRecycler!=null){
                    //设置点击事件
                    tenViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            int pos=viewHolder.getLayoutPosition();
                            Log.d("NewsAdapter", "pos:" + pos);
                            int id=newsFragmentBean.getResult().getNewslist().get(pos).getId();
                            onClickListenerRecycler.onClickRecycler(id);
                        }
                    });
                }
                break;
        }

    }

    //加载图片的另一种方式,picasso
    public void loadImage(ImageView image ,String url){
        Picasso.with(context).load(url).placeholder(R.mipmap.ahlib_carback).error(R.mipmap.ahlib_page_icon_empty).into(image);
    }

   /* public void volley(ImageView imageView, String path) {
        requestQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(path, listener);
    }*/


    @Override
    public int getItemCount() {
        return newsFragmentBean == null ? 0 : newsFragmentBean.getResult().getNewslist().size();
    }


    class ZeroViewHolder extends RecyclerView.ViewHolder {
        ImageView zeroSmallPic;
        TextView zeroTitle;
        TextView zeroTime;
        TextView zeroReplyCount;

        public ZeroViewHolder(View itemView) {
            super(itemView);
            zeroSmallPic = (ImageView) itemView.findViewById(R.id.one_smallpic);
            zeroTitle = (TextView) itemView.findViewById(R.id.one_title);
            zeroTime = (TextView) itemView.findViewById(R.id.one_time);
            zeroReplyCount = (TextView) itemView.findViewById(R.id.one_replycount);


        }
    }

    class TenViewHolder extends RecyclerView.ViewHolder {
        TextView tenTitle;
        TextView tenTime;
        TextView tenReplyCount;
        ImageView tenSmallPic;
        ImageView tenIndexDetialOne;
        ImageView getTenIndexDetialTwo;

        public TenViewHolder(View itemView) {
            super(itemView);
            tenTitle = (TextView) itemView.findViewById(R.id.three_title);
            tenTime = (TextView) itemView.findViewById(R.id.three_time);
            tenReplyCount = (TextView) itemView.findViewById(R.id.three_replycount);
            tenSmallPic = (ImageView) itemView.findViewById(R.id.three_smallpic);
            tenIndexDetialOne = (ImageView) itemView.findViewById(R.id.three_indexdatail_one);
            getTenIndexDetialTwo = (ImageView) itemView.findViewById(R.id.three_indexdatail_two);
        }
    }

    //内部接口 实现新闻详情页面的点击事件
    public interface OnClickListenerRecycler{
        void onClickRecycler(int id);
    }
}
