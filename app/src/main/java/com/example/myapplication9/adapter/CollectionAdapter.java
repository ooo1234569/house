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
import com.example.myapplication9.Activity.DetailActivity;
import com.example.myapplication9.R;
import com.example.myapplication9.Shangjia;

import java.util.ArrayList;


/**
 * Created by bingnanfeng02 on 2017/8/1.
 */
public class CollectionAdapter extends RecyclerView.Adapter {
    private ArrayList<Shangjia> shangjias;
    private Context context;
    public CollectionAdapter(ArrayList<Shangjia> shangjias, Context context){
        this.shangjias=shangjias;
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection,parent,false);
        return new Shangjiainfo(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Shangjia shangjia=shangjias.get(position);
        ((Shangjiainfo) viewHolder).name.setText(shangjia.name);
        ((Shangjiainfo) viewHolder).shuoming.setText(shangjia.shuoming);
        ((Shangjiainfo) viewHolder).xiaoliang.setText("已服务"+shangjia.xiaoliang+"单");
        ((Shangjiainfo) viewHolder).relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,DetailActivity.class));
            }
        });
        Glide.with(context).load(R.drawable.touxiang).into(((Shangjiainfo) viewHolder).imageView);
    }

    @Override
    public int getItemCount() {
        return shangjias.size();
    }
    class Shangjiainfo extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView xiaoliang;
        private TextView shuoming;
        private RelativeLayout relativeLayout;
        private ImageView imageView;
        public Shangjiainfo(View parent) {
            super(parent);
            name=(TextView)parent.findViewById(R.id.textView8);
            xiaoliang=(TextView)parent.findViewById(R.id.textView9);
            shuoming=(TextView)parent.findViewById(R.id.textView10);
            relativeLayout=(RelativeLayout) parent.findViewById(R.id.rl);
            imageView=(ImageView)parent.findViewById(R.id.shoucangtouxiang);
        }
    }
}
