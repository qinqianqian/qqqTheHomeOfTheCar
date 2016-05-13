package com.qqq.thehomeofthecar;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qqq.thehomeofthecar.base.BaseActivity;

/**
 * Created by 秦谦谦 on 16/5/10 11:40.
 */
public class WelcomeActivity extends BaseActivity {
    private CountDownTimer timer;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        textView = bindView(R.id.welcome_tv);
        imageView = bindView(R.id.welcome_iv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击退出
                timer.cancel();
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        });

    }

    //倒计时
    @Override
    protected void initData() {
    timer=new CountDownTimer(5000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
        textView.setText("点击跳过");
        }

        @Override
        public void onFinish() {
        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
            finish();
        }
    }.start();
    }
}
