package com.qqq.thehomeofthecar.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by 秦谦谦 on 16/5/17 11:43.
 */
public class MyApplication extends Application {
    //Application创建的原因我们需要一个属于自己的大环境Context;
    public  static Context context;


    //第一个生命周期中我们队context赋值
    @Override
    public void onCreate() {
        super.onCreate();
        //this代表当前的环境
        context=this;
    }
    //对外提供一个方法:这个方法就是让别的类获取自己的context对象
    public static Context getContext(){
        return context;
    }
}
