package com.example.myapplication9.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication9.R;

/**
 * Created by bingnanfeng02 on 2017/7/29.
 */
public class CommentAdapter extends RecyclerView.Adapter{
    Context context;
    public CommentAdapter(Context context){
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_space2,parent,false);
            return new Fengexian(view);
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof VH){
            Glide.with(context).load(R.drawable.touxiang).into(((VH)holder).imageView);
        }
    }
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }else {
            return 100;
        }

    }
    @Override
    public int getItemCount() {
        return 5+1;
    }
    class VH extends RecyclerView.ViewHolder {
        ImageView imageView;
        public VH(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.touxiang);
        }
    }
    class Fengexian extends RecyclerView.ViewHolder {
        public Fengexian(View itemView) {
            super(itemView);
        }
    }
}
