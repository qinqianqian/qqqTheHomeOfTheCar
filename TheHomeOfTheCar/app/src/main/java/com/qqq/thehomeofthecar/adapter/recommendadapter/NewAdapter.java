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
import com.qqq.thehomeofthecar.bean.NewFragmentBean;
import com.qqq.thehomeofthecar.bean.NewsFragmentBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 秦谦谦 on 16/5/11 19:13.
 * 最新页面
 */
public class NewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private NewFragmentBean newFragmentBean;
    private LayoutInflater inflater;
    private RequestQueue requestQueue;
    //内部接口,实现详情的点击事件 首页的点击事件
    OnClickListenerRecycler onClickListenerRecycler;


    //内部接口的set方法
    public void setOnClickListenerRecycler(OnClickListenerRecycler onClickListenerRecycler) {
        this.onClickListenerRecycler = onClickListenerRecycler;
        notifyDataSetChanged();
    }

    //构造方法初始化context
    public NewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    //set 首页Bean的集合
    public void setNewFragmentBean(NewFragmentBean newFragmentBean) {
        this.newFragmentBean = newFragmentBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return newFragmentBean.getResult().getNewslist().get(position).getMediatype();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 6:
                viewHolder = new ThreeViewHolder(inflater.inflate(R.layout.item_pictice_three, parent, false));
                break;
            default:
                viewHolder = new OneViewHolder(inflater.inflate(R.layout.item_picture_one, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        final RecyclerView.ViewHolder viewHolder = holder;
        switch (viewType) {
            case 6:
                ThreeViewHolder threeViewHolder = (ThreeViewHolder) holder;
                threeViewHolder.threeTitle.setText(newFragmentBean.getResult().getNewslist().get(position).getTitle());
                threeViewHolder.threeTime.setText(newFragmentBean.getResult().getNewslist().get(position).getTime());
                threeViewHolder.threeReplyCount.setText(newFragmentBean.getResult().getNewslist().get(position).getReplycount() + " 图片");
                //小图设置隐藏
                threeViewHolder.picTv.setText("");
                threeViewHolder.picImage.setVisibility(View.GONE);
                String image = newFragmentBean.getResult().getNewslist().get(position).getIndexdetail();
                String[] args = image.split("㊣");
                String[] path = args[2].split(",");
                loadImage(threeViewHolder.threeSmallPic, path[0]);
                loadImage(threeViewHolder.threeIndexDetialOne, path[1]);
                loadImage(threeViewHolder.getThreeIndexDetialTwo, path[2]);
                if (onClickListenerRecycler != null) {
                    //设置点击事件
                    threeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = viewHolder.getLayoutPosition();
                            int id = newFragmentBean.getResult().getNewslist().get(pos).getId();
                            int media=newFragmentBean.getResult().getNewslist().get(pos).getMediatype();
                            onClickListenerRecycler.onClickRecycler(id,media);
                        }
                    });
                }
                break;
            default:
                //代表不同的页面 viewType类型;
                OneViewHolder oneViewHolder = (OneViewHolder) holder;
                oneViewHolder.oneTitle.setText(newFragmentBean.getResult().getNewslist().get(position).getTitle());
                oneViewHolder.oneTime.setText(newFragmentBean.getResult().getNewslist().get(position).getTime());
                int count=newFragmentBean.getResult().getNewslist().get(position).getReplycount();
                if (viewType==3){
                    oneViewHolder.oneReplyCount.setText(newFragmentBean.getResult().getNewslist().get(position).getReplycount() + " 播放");
                }
                else if (viewType==5){
                    oneViewHolder.oneReplyCount.setText(newFragmentBean.getResult().getNewslist().get(position).getReplycount() + " 回帖");
                }
                else if (viewType==1&&count==0){
                    oneViewHolder.oneReplyCount.setText("");
                }else {
                    oneViewHolder.oneReplyCount.setText(newFragmentBean.getResult().getNewslist().get(position).getReplycount() + " 评论");
                }
                //传入图片的名称和网址
                loadImage(oneViewHolder.oneSmallPic, newFragmentBean.getResult().getNewslist().get(position).getSmallpic());
                if (onClickListenerRecycler != null) {
                    //设置点击事件
                    oneViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = viewHolder.getLayoutPosition();
                            int id = newFragmentBean.getResult().getNewslist().get(pos).getId();
                            int mediaType=newFragmentBean.getResult().getNewslist().get(pos).getMediatype();
                            onClickListenerRecycler.onClickRecycler(id,mediaType);
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


   /* //加载图片的一种方式,使用ImageLoader加载
    public void volley(ImageView imageView, String path) {
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
    }
*/

    @Override
    public int getItemCount() {
        return newFragmentBean == null ? 0 : newFragmentBean.getResult().getNewslist().size();
    }

    class OneViewHolder extends RecyclerView.ViewHolder {
        ImageView oneSmallPic;
        TextView oneTitle;
        TextView oneTime;
        TextView oneReplyCount;

        public OneViewHolder(View itemView) {
            super(itemView);
            oneSmallPic = (ImageView) itemView.findViewById(R.id.one_smallpic);
            oneTitle = (TextView) itemView.findViewById(R.id.one_title);
            oneTime = (TextView) itemView.findViewById(R.id.one_time);
            oneReplyCount = (TextView) itemView.findViewById(R.id.one_replycount);
        }
    }

    class ThreeViewHolder extends RecyclerView.ViewHolder {
        TextView threeTitle;
        TextView threeTime;
        TextView threeReplyCount;
        ImageView threeSmallPic;
        ImageView threeIndexDetialOne;
        ImageView getThreeIndexDetialTwo;

        //小图设置隐藏
        ImageView picImage;
        TextView picTv;
        public ThreeViewHolder(View itemView) {
            super(itemView);
            threeTitle = (TextView) itemView.findViewById(R.id.three_title);
            threeTime = (TextView) itemView.findViewById(R.id.three_time);
            threeReplyCount = (TextView) itemView.findViewById(R.id.three_replycount);
            threeSmallPic = (ImageView) itemView.findViewById(R.id.three_smallpic);
            threeIndexDetialOne = (ImageView) itemView.findViewById(R.id.three_indexdatail_one);
            getThreeIndexDetialTwo = (ImageView) itemView.findViewById(R.id.three_indexdatail_two);
            //初始化小图
            picImage= (ImageView) itemView.findViewById(R.id.three_pic);
            picTv= (TextView) itemView.findViewById(R.id.three_pict_tv);
        }
    }

    //内部接口 实现新闻详情页面的点击事件
    public interface OnClickListenerRecycler {
        void onClickRecycler(int id,int mediaType);
    }

}
