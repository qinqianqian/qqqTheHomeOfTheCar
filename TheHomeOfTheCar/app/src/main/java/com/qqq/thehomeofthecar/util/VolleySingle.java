package com.qqq.thehomeofthecar.util;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.utils.L;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 秦谦谦 on 16/5/16 10:36.
 * 解析单例
 */
public class VolleySingle {
    private static  Context mContext;
    private RequestQueue queue;

    public VolleySingle() {
        //获取Application里的context
        //mContext = context.getApplicationContext();
        mContext=MyApplication.getContext();
        queue=getQueue();//初始化队列请求
    }

    private static VolleySingle ourInstance ;

    public static VolleySingle getInstance() {
        if (ourInstance==null){
            ourInstance = new VolleySingle();
        }
        return ourInstance;
    }


    //提供一个方法新建请求队列
    public RequestQueue getQueue(){
        if (queue==null){
            queue= Volley.newRequestQueue(mContext);
        }
        return queue;
    }
    public static final String TAG="VolleySingleton";
    //添加请求
    public <T> void _addRequest(Request<T> request){
        request.setTag(TAG);//将我的请求添加标签,方便管理
        queue.add(request);//将请求添加到队列中
    }
    public <T> void _addRequest(Request<T> request,Object tag){
        request.setTag(tag);//将我的请求添加标签,方便管理
        queue.add(request);//将请求添加到队列中
    }

    //String 类型
    public void _addRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener){
        StringRequest stringRequest=new StringRequest(url,listener,errorListener);
        _addRequest(stringRequest);
    }
    public <T> void  _addRequest(String url, Class<T> mClass, Response.Listener<T> listener, Response.ErrorListener errorListener){
        GsonRequest gsonRequest=new GsonRequest(Request.Method.GET,url,errorListener,listener,mClass);
        _addRequest(gsonRequest);

    }
    //这个方法是将请求队列移除队列
    public void removeRequest(Object tag){
        queue.cancelAll(tag);//根据不同的tag 移除出队列
    }

    //获取单例对象 调用添加请求的方法(这个是StringRequest 的请求) 只拉取数据
    public static void addRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener){
        //将gsonRequest请求添加单例的队列里
        getInstance()._addRequest(url,listener,errorListener);
    }

    //拉取数据加解析
    public  static <T> void  addRequest(String url, Class<T> mClass, Response.Listener<T> listener, Response.ErrorListener errorListener){
        getInstance()._addRequest(url,mClass,listener,errorListener);
    }

}

