package com.example.myapplication9.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication9.Msg;
import com.example.myapplication9.R;

import java.util.ArrayList;

/**
 * Created by fengbingnan on 2017/7/7.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private ArrayList<Msg> msgArrayList;
    private View my;
    private View others;
    private Context context;
    public MessageAdapter(ArrayList<Msg> arrayList, Context context){
        this.msgArrayList=arrayList;
        this.context=context;
    }
    static  class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout relativeLayout;
        private RelativeLayout relativeLayout2;
        TextView textView;
        TextView textView2;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout=(RelativeLayout) itemView.findViewById(R.id.myrl);
            relativeLayout2=(RelativeLayout) itemView.findViewById(R.id.otrl);
            textView=(TextView)itemView.findViewById(R.id.my);
            textView2=(TextView)itemView.findViewById(R.id.other);
            imageView=(ImageView)itemView.findViewById(R.id.touxiang);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mymsg,viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Msg msg=msgArrayList.get(i);
        if (msg.leixing==0) {
            viewHolder.relativeLayout.setVisibility(View.VISIBLE);
            viewHolder.relativeLayout2.setVisibility(View.GONE);
            viewHolder.textView.setText(msg.content);
        } else if (msg.leixing==1) {
            viewHolder.relativeLayout2.setVisibility(View.VISIBLE);
            viewHolder.relativeLayout.setVisibility(View.GONE);
            viewHolder.textView2.setText(msg.content);
            Glide .with(context).load(R.drawable.touxiang).into(viewHolder.imageView);
        }
    }
    @Override
    public int getItemCount() {
        return msgArrayList.size();
    }


}
