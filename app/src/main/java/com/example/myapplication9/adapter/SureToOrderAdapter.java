package com.example.myapplication9.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication9.R;
import com.example.myapplication9.Service;
import com.example.myapplication9.Time;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/9/7.
 */

public class SureToOrderAdapter extends MultiTypeAdapter {
    ArrayList<Service> services;
    public SureToOrderAdapter(ArrayList<Service> services){
        this.services=services;
    }

    @Override
    RecyclerView.ViewHolder initviewholder() {
        Order order=new Order(view);
        return order;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         if(holder instanceof Order){
             ((Order)holder).time.setText(services.get(position-p).date+" ("+services.get(position-p).months+")   "+services.get(position-p).time);
             ((Order)holder).num.setText("x"+services.get(position-p).num);
             ((Order)holder).howmuch.setText(services.get(position-p).howmuch+"/单位");
             ((Order)holder).total.setText(services.get(position-p).total+"元");
         }
    }
    class Order extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView num;
        private TextView howmuch;
        private TextView total;
        public Order(View itemView) {
            super(itemView);
            time=(TextView)itemView.findViewById(R.id.time);
            num=(TextView)itemView.findViewById(R.id.num);
            howmuch=(TextView)itemView.findViewById(R.id.howmuch);
            total=(TextView)itemView.findViewById(R.id.total);
        }
    }
}
