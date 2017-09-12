package com.example.myapplication9.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication9.BigService;
import com.example.myapplication9.Classify;
import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;

import java.util.List;


/**
 * Created by bingnanfeng02 on 2017/8/4.
 */
public class ListOneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    PositionListener  positionListener;
    java.util.List<BigService> bigServiceList;
    Context context;
    int p;
    Classify classify;
    public ListOneAdapter(Classify classify, Context context){
        this.bigServiceList=classify.strings;
        this.context=context;
        this.p=classify.p;
        this.classify=classify;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doublelist1,parent,false);
        List list=new List(view);
        return list;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((List)holder).textView.setText(bigServiceList.get(position).getServiceTypeName());
        ((List)holder).relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionListener.onArticleSelected(position);
            }
        });
        if (classify.p==position){
            ((List)holder).textView.setTextColor(Color.parseColor("#71aaff"));
            ((List)holder).relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            ((List)holder).relativeLayout1.setVisibility(View.VISIBLE);
        }else {
            ((List)holder).textView.setTextColor(Color.parseColor("#000000"));
            ((List)holder).relativeLayout.setBackgroundColor(Color.parseColor("#f2f3f5"));
            ((List)holder).relativeLayout1.setVisibility(View.GONE);
        }
    }
public  void setP(PositionListener positionListener){
    this.positionListener=positionListener;
}
    @Override
    public int getItemCount() {
        return bigServiceList.size();
    }
    class List extends RecyclerView.ViewHolder {
        TextView textView;
        RelativeLayout relativeLayout;
        RelativeLayout relativeLayout1;
        public List(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.type);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.rl1);
            relativeLayout1=(RelativeLayout)itemView.findViewById(R.id.rl);
        }
    }

}
