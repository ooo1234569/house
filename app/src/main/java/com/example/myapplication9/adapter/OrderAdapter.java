package com.example.myapplication9.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication9.Activity.BusinessDetailActivity;
import com.example.myapplication9.Activity.OrderActivity;
import com.example.myapplication9.Activity.OrderDetailActivity;
import com.example.myapplication9.Dingdan;
import com.example.myapplication9.R;

import java.util.ArrayList;


/**
 * Created by bingnanfeng02 on 2017/7/24.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private ArrayList<Dingdan> dingdanArrayList;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView goods;
        private TextView num;
        private TextView pay;
//        private ImageView imageView;
//        private ImageView enter;
//        private RelativeLayout shangjia;
        private RelativeLayout buyagain;
        public ViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.textView2);
            goods=(TextView)itemView.findViewById(R.id.textView5);
            num=(TextView)itemView.findViewById(R.id.textView6);
//            imageView=(ImageView)itemView.findViewById(R.id.imageView5);
//            enter=(ImageView)itemView.findViewById(R.id.imageView6);
//            shangjia=(RelativeLayout)itemView.findViewById(R.id.shangjiarl);
            buyagain=(RelativeLayout)itemView.findViewById(R.id.buyagain);
        }
    }

    public OrderAdapter(ArrayList<Dingdan> dingdanArrayList, Context context){
        this.dingdanArrayList=dingdanArrayList;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, OrderDetailActivity.class));
            }
        });
        ViewHolder viewHolder=new ViewHolder(view);
//        viewHolder.shangjia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(context, BusinessDetailActivity.class));
//            }
//        });
        viewHolder.buyagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, OrderActivity.class));
            }
        });
//        Glide.with(context).load(R.drawable.enter).into(viewHolder.enter);
        return viewHolder;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.name.setText(dingdanArrayList.get(position).dingdanname);
        holder.goods.setText(dingdanArrayList.get(position).goods);
        holder.num.setText(dingdanArrayList.get(position).num);
//        Glide.with(context).load(R.drawable.touxiang).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dingdanArrayList.size();
    }
}
