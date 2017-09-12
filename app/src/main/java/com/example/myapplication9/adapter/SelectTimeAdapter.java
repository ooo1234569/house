package com.example.myapplication9.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;
import com.example.myapplication9.Time;
import com.example.myapplication9.TimeObject;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/9/7.
 */

public class SelectTimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    ArrayList<Time> arrayList;
    Context context;
    boolean loading=false;
    TimeObject timeObject;
    PositionListener positionListener;
    public SelectTimeAdapter(TimeObject timeObject, Context context){
        this.arrayList=timeObject.times;
        this.context=context;
        this.timeObject=timeObject;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_date,parent,false);
        final SelectTime selectTime=new SelectTime(view);
        selectTime.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionListener.onArticleSelected(selectTime.getAdapterPosition());
            }
        });
        return selectTime;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("ud","ud");
            Time time=arrayList.get(position);
            ((SelectTime)holder).months.setText(time.months);
            ((SelectTime)holder).date.setText(time.date);
        if(timeObject.position==position){
            ((SelectTime)holder).divider.setVisibility(View.VISIBLE);
        }else {
            ((SelectTime)holder).divider.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class SelectTime extends RecyclerView.ViewHolder {
        TextView months;
        TextView date;
        View divider;
        RelativeLayout rl;
        public SelectTime(View itemView) {
            super(itemView);
            months=(TextView)itemView.findViewById(R.id.month);
            date=(TextView)itemView.findViewById(R.id.date);
            divider=itemView.findViewById(R.id.divider);
            rl=(RelativeLayout)itemView.findViewById(R.id.rl);
        }
    }
    public void setp(PositionListener listener){
        this.positionListener=listener;
    }
}
