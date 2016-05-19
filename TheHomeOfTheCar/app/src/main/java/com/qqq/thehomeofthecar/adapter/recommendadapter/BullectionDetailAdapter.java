package com.qqq.thehomeofthecar.adapter.recommendadapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

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
        holder.publishTime.setText(bullectionDetailBean.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 /**/getResult().getMessagelist().get(position).getPublishtime());
        setImage(holder.heading, bullectionDetailBean.getResult().getMessagelist().get(position).getHeadimg());
        if (bullectionDetailBean.getResult().getMessagelist().get(position).getAttachments().get(0).getAttachtype()==0){
            holder.headingVideo.setVisibility(View.GONE);
            if (bullectionDetailBean.getResult().getMessagelist().get(position).getAttachments().size()==1){
                holder.headingPic.setVisibility(View.GONE);
                holder.headingPic2.setVisibility(View.GONE);
            }else if (bullectionDetailBean.getResult().getMessagelist().get(position).getAttachments().size()==2){
                holder.headingPic2.setVisibility(View.GONE);
                holder.headingPic.setVisibility(View.VISIBLE);
                setImage(holder.headingPic,bullectionDetailBean.getResult().getMessagelist().get(position).getAttachments().get(1).getPicurl());
            }else {
                holder.headingPic2.setVisibility(View.VISIBLE);
                holder.headingPic.setVisibility(View.VISIBLE);
                setImage(holder.headingPic,bullectionDetailBean.getResult().getMessagelist().get(position).getAttachments().get(1).getPicurl());
               setImage(holder.headingPic2,bullectionDetailBean.getResult().getMessagelist().get(position).getAttachments().get(2).getPicurl());

            }
            holder.picurl.setVisibility(View.VISIBLE);
            setImage(holder.picurl, bullectionDetailBean.getResult().getMessagelist().get(position).getAttachments().get(0).getPicurl());
        }else {
            holder.headingPic2.setVisibility(View.GONE);
            holder.picurl.setVisibility(View.GONE);
            holder.headingPic.setVisibility(View.GONE);
            holder.headingVideo.setVisibility(View.VISIBLE);
            holder.headingVideo.setMediaController(new MediaController(context));
            holder.headingVideo.setVideoURI(Uri.parse(bullectionDetailBean.getResult().getMessagelist().get(position).getAttachments().get(0).getVideourl()));
            holder.headingVideo.start();
            holder.headingVideo.requestFocus();
        }
        if (bullectionDetailBean.getResult().getMessagelist().get(position).getCommentlist().get(0).getHeadimg().length()!=0){
            setImage(holder.head1,bullectionDetailBean.getResult().getMessagelist().get(position).getCommentlist().get(0).getHeadimg());
        }else {
            holder.head1.setImageResource(R.mipmap.photot);
        }


        if (bullectionDetailBean.getResult().getMessagelist().get(position).getCommentlist().get(1).getHeadimg().length()!=0){

            setImage(holder.head2,bullectionDetailBean.getResult().getMessagelist().get(position).getCommentlist().get(1).getHeadimg());
        }else {
            holder.head2.setImageResource(R.mipmap.photot);
        }
        if (bullectionDetailBean.getResult().getMessagelist().get(position).getCommentlist().get(2).getHeadimg().length()!=0){

            setImage(holder.head3,bullectionDetailBean.getResult().getMessagelist().get(position).getCommentlist().get(2).getHeadimg());
        }else {
            holder.head3.setImageResource(R.mipmap.photot);
        }

        holder.content1.setText(bullectionDetailBean.getResult().getMessagelist().get(position)
        .getCommentlist().get(0).getContent());
        holder.content2.setText(bullectionDetailBean.getResult().getMessagelist().get(position)
        .getCommentlist().get(1).getContent());
        holder.content3.setText(bullectionDetailBean.getResult().getMessagelist().get(position)
        .getCommentlist().get(2).getContent());

        holder.useName1.setText(bullectionDetailBean.getResult().getMessagelist().get(position
        ).getCommentlist().get(0).getUsername());
        holder.useName2.setText(bullectionDetailBean.getResult().getMessagelist().get(position
        ).getCommentlist().get(1).getUsername());
        holder.useName3.setText(bullectionDetailBean.getResult().getMessagelist().get(position
        ).getCommentlist().get(2).getUsername());

        holder.upCount.setText(bullectionDetailBean.getResult().getMessagelist()
        .get(position).getUpcount()+"");
        holder.commentReplyCount.setText(bullectionDetailBean.getResult().getMessagelist().get(position
        ).getReplycount()+"");



        return convertView;
    }

    class MyViewHolder {
        ImageView heading, picurl, headingPic, head1, head2, head3,headingPic2;
        TextView authorName, publishTime, content, upCount, useName1, useName2, useName3, content1, content2, content3,commentReplyCount;
        VideoView headingVideo;

        public MyViewHolder(View itemView) {
            heading = (ImageView) itemView.findViewById(R.id.bullection_headings);
            picurl = (ImageView) itemView.findViewById(R.id.bullection_list_heading);
            authorName = (TextView) itemView.findViewById(R.id.bullection_newsauthoridSmall);
            publishTime = (TextView) itemView.findViewById(R.id.bullection_createtimeSmall);
            content = (TextView) itemView.findViewById(R.id.bullection_summary);

            headingPic = (ImageView) itemView.findViewById(R.id.bullection_list_headingpic);
            head1 = (ImageView) itemView.findViewById(R.id.bullection_comment_heading1);
            head2 = (ImageView) itemView.findViewById(R.id.bullection_comment_heading2);
            head3 = (ImageView) itemView.findViewById(R.id.bullection_comment_heading3);
            upCount = (TextView) itemView.findViewById(R.id.bullection_upcount_tv);
            useName1 = (TextView) itemView.findViewById(R.id.bullection_comment_usename1);
            useName2 = (TextView) itemView.findViewById(R.id.bullection_comment_usename2);
            useName3 = (TextView) itemView.findViewById(R.id.bullection_comment_usename3);
            content1 = (TextView) itemView.findViewById(R.id.bullection_commment_content1);
            content2 = (TextView) itemView.findViewById(R.id.bullection_commment_content2);
            content3 = (TextView) itemView.findViewById(R.id.bullection_commment_content3);
            headingVideo = (VideoView) itemView.findViewById(R.id.bullection_list_headingvideo);
            commentReplyCount= (TextView) itemView.findViewById(R.id.bullection_comment_replycount);
            headingPic2= (ImageView) itemView.findViewById(R.id.bullection_list_headingpic2);
        }
    }

    public void setImage(ImageView image, String path) {
        Picasso.with(context).load(path).placeholder(R.mipmap.ahlib_carback).error(R.mipmap.ahlib_page_icon_empty).into(image);
    }
}
