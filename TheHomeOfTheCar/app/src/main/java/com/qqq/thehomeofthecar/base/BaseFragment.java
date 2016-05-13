package com.qqq.thehomeofthecar.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 秦谦谦 on 16/5/9 14:50.
 */
public abstract  class BaseFragment extends Fragment {
    protected Context context;
    //从依附的activity上或获取context对象
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    //初始化视图
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(setLayout(),container,false);
    }
    public abstract int setLayout();

    //初始化组件
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected abstract void initView(View view);


    //加载数据
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    //初始化组件
    protected <T extends View>T bindView(int id){
        return (T)getView().findViewById(id);
    }

}
