package com.example.myapplication9;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengbingnan on 2017/7/13.
 */

public class Rvmsg extends RecyclerView.Adapter<Rvmsg.ViewHolder> {
    private ArrayList<Msgitem> arrayList;
    public Rvmsg(ArrayList<Msgitem> arrayList){
        this.arrayList=arrayList;
    }
    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView more;
        ImageView imageView;
        private TextView time;
        private View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            imageView=(ImageView) itemView.findViewById(R.id.imageView9);
            name=(TextView) itemView.findViewById(R.id.textView14);
            more=(TextView)itemView.findViewById(R.id.textView15);
            time=(TextView)itemView.findViewById(R.id.textView16);
        }
    }
    @Override
    public Rvmsg.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.messageitem,parent,false);
        ViewHolder holder=new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               v.getContext().startActivity(new Intent(v.getContext(),msgActivity.class));
            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Rvmsg.ViewHolder holder, int position) {
        Msgitem msgitem=arrayList.get(position);
           //holder.imageView.setImageDrawable(msgitem.drawable);
        holder.name.setText(msgitem.name);
        holder.time.setText(msgitem.time);
        holder.more.setText(msgitem.more);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
