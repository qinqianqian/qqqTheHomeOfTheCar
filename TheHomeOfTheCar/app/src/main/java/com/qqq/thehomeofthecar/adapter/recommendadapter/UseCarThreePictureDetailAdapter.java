package com.qqq.thehomeofthecar.adapter.recommendadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qqq.thehomeofthecar.R;
import com.qqq.thehomeofthecar.UseCarThreePictureDetailActivity;
import com.qqq.thehomeofthecar.bean.UseCarThreePictureDetailBean;
import com.qqq.thehomeofthecar.util.BroadcastValue;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 秦谦谦 on 16/5/17 17:00.
 */
public class UseCarThreePictureDetailAdapter extends PagerAdapter {
    private UseCarThreePictureDetailBean useCarThreePictureDetailBean;
    private Context context;
    //判断图片的点击事件
    private boolean isChecked = false;
    //判断图片点击了几次
    private static boolean isExit = false;
    private ImageView imageView;
    public UseCarThreePictureDetailAdapter(UseCarThreePictureDetailBean useCarThreePictureDetailBean, Context context) {
        this.useCarThreePictureDetailBean = useCarThreePictureDetailBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return useCarThreePictureDetailBean == null ? 0 : useCarThreePictureDetailBean.getResult().getImages().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_usecar_threepicturedetail, container ,false);
        imageView = (ImageView) view.findViewById(R.id.usecar_image);



        setImage(imageView, useCarThreePictureDetailBean.getResult().getImages().get(position).getImgurl());





        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BroadcastValue.USECAR_THREEPICTERE_CLOSE);
                intent.putExtra("isChecked", isChecked);
                if (isChecked) {
                    isChecked = false;
                } else {
                    isChecked = true;
                }
                context.sendBroadcast(intent);
                //双击图片


            }
        });


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //双击图片
                if (event.getAction() == event.ACTION_POINTER_2_DOWN){
                    scaleAnim();
                }



                return false;
            }
        });

        container.addView(view);
        return view;
    }


    public void setImage(ImageView imageView, String path) {
        Picasso.with(context).load(path).placeholder(R.mipmap.ahlib_common_main_filter_icon_f).error(R.mipmap.ahlib_common_main_filter_icon_p).into(imageView);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    //图片的缩放
    public void scaleAnim() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 2, 1, 2,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        Log.d("UseCarThreePictureDetai", "缩放");

        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);

        imageView.startAnimation(scaleAnimation);


    }

}
