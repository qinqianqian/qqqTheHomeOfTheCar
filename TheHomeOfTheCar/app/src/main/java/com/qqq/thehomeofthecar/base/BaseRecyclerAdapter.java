package com.qqq.thehomeofthecar.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by 秦谦谦 on 16/5/10 16:36.
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return getIC();
    }

    protected abstract int getIC();
}
