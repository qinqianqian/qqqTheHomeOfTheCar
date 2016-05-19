package com.qqq.thehomeofthecar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.qqq.thehomeofthecar.adapter.recommendadapter.UseCarThreePictureDetailAdapter;
import com.qqq.thehomeofthecar.base.BaseActivity;
import com.qqq.thehomeofthecar.bean.UseCarThreePictureDetailBean;
import com.qqq.thehomeofthecar.util.BroadcastValue;
import com.qqq.thehomeofthecar.util.VolleySingle;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by 秦谦谦 on 16/5/17 16:36.
 */
public class UseCarThreePictureDetailActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager viewPager;
    private UseCarThreePictureDetailAdapter useCarThreePictureDetailAdapter;
    private TextView title;
    private TextView content;
    private TextView replyCount;
    private TextView images;
    private TextView back;
    private UseCarThreePictureDetailBean useCarThreePictureDetailBean;

    //点击图片 平移tv
    private RelativeLayout relativeLayoutHeader;
    private RelativeLayout relativeLayoutTv;

//遮盖用的
    TextView recover;
    //广播
    private MyBroadcast myBroadcast;

    @Override
    protected int getLayout() {
        return R.layout.activity_usecar_threepicturedetail;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.usecar_viewpager);
        title = bindView(R.id.usecar_tv_title);
        content = bindView(R.id.usecar_tv_content);
        replyCount = bindView(R.id.usecar_replycount);
        images = bindView(R.id.usecar_count);
        back = bindView(R.id.usecar_retuen_tv);

        recover=bindView(R.id.usecar_retuen_recover);
        recover.setOnClickListener(this);
        //平移事物两个布局
        relativeLayoutHeader = bindView(R.id.usecar_relative_header);
        relativeLayoutTv = bindView(R.id.usecar_relative_tv);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        volley(path);
        viewPager.addOnPageChangeListener(this);
        back.setOnClickListener(this);
        //viewPager的点击事件
//       viewPager.setOnFocusChangeListener(this);
//        viewPager.setOnClickListener(this);

        //注册广播
        myBroadcast=new MyBroadcast();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(BroadcastValue.USECAR_THREEPICTERE_CLOSE);
        registerReceiver(myBroadcast,intentFilter);


    }


    //数据解析
    private void volley(String path) {
        VolleySingle.addRequest(path, UseCarThreePictureDetailBean.class, new Response.Listener<UseCarThreePictureDetailBean>() {
            @Override
            public void onResponse(UseCarThreePictureDetailBean response) {
                useCarThreePictureDetailBean = response;
                useCarThreePictureDetailAdapter = new UseCarThreePictureDetailAdapter(useCarThreePictureDetailBean, getApplicationContext());
                viewPager.setAdapter(useCarThreePictureDetailAdapter);
                title.setText(useCarThreePictureDetailBean.getResult().getTitle());
                content.setText(useCarThreePictureDetailBean.getResult().getImages().get(0).getDescription());
                replyCount.setText(useCarThreePictureDetailBean.getResult().getReplycount() + "");
                images.setText("1/" + (useCarThreePictureDetailBean.getResult().getImages().size()));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


    //监听页面的滑动
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        content.setText(useCarThreePictureDetailBean.getResult().getImages().get(position).getDescription());
        images.setText(position + 1 + "/" + (useCarThreePictureDetailBean.getResult().getImages().size()));

        //



    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //返回事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.usecar_retuen_tv:
                finish();
                break;
//            case R.id.usecar_viewpager:
//                Log.d("UseCarThreePictureDetai", "点击事件");
//                Toast.makeText(this, "点击事件", Toast.LENGTH_SHORT).show();
//                break;
        }

    }


    //点击图片
   /*  @Override
   public void onFocusChange(View v, boolean hasFocus) {
        Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
        Log.d("UseCarThreePictureDetai", "焦点事件");
        showTranslateAnimation();
    }*/


    //tv平移
    int time=1500;
    private void showTranslateAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 1);
        translateAnimation.setDuration(time);
        translateAnimation.setFillAfter(true);
        TranslateAnimation translateAnimation1 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -1);
        translateAnimation1.setDuration(time);
        translateAnimation1.setFillAfter(true);
        relativeLayoutTv.startAnimation(translateAnimation);
        relativeLayoutHeader.startAnimation(translateAnimation1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time+100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ;
            }
        }).start();
    }
    private void showTranslateAnimation1() {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_PARENT, 0);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        TranslateAnimation translateAnimation1 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_SELF,-1, Animation.RELATIVE_TO_PARENT, 0);
        translateAnimation1.setDuration(1000);
        translateAnimation1.setFillAfter(true);
        relativeLayoutTv.startAnimation(translateAnimation);
        relativeLayoutHeader.startAnimation(translateAnimation1);
    }


    //内部类接收广播 实现tv的打开和关闭
    class MyBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isChecked=intent.getBooleanExtra("isChecked",true);
            if (isChecked){
                showTranslateAnimation1();
                back.setVisibility(View.VISIBLE);
                recover.setVisibility(View.GONE);
            }else {
                showTranslateAnimation();
                recover.setVisibility(View.VISIBLE);

            }

        }

    }


    //取消广播

    @Override
    protected void onDestroy() {
       unregisterReceiver(myBroadcast);
        super.onDestroy();
    }
}
