package com.example.myapplication9;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/5/15.
 */
public class Rvadaptershangjia  extends RecyclerView.Adapter<Rvadaptershangjia.ViewHolder> implements View.OnClickListener {
    private ArrayList<String> arrayList1;
    private ArrayList<String> arrayList2;
    private ArrayList<String> arrayList3;
    private ArrayList<String> arrayList4;
    private ArrayList<String> arrayList5;
     public  Rvadaptershangjia (ArrayList<String> arrayList1,ArrayList<String> arrayList2,ArrayList<String> arrayList3,ArrayList<String> arrayList4,ArrayList<String> arrayList5 ){
         this.arrayList1=arrayList1;
         this.arrayList2=arrayList2;
         this.arrayList3=arrayList3;
         this.arrayList4=arrayList4;
         this.arrayList5=arrayList5;
     }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        public ViewHolder(View itemView) {
            super(itemView);
            textView1=(TextView)itemView.findViewById(R.id.textView8);
            textView2=(TextView)itemView.findViewById(R.id.textView9);
            textView3=(TextView)itemView.findViewById(R.id.mact1);
            textView4=(TextView)itemView.findViewById(R.id.textView11);
            textView5=(TextView)itemView.findViewById(R.id.textView10);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.shoucangitem,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
          holder.textView1.setText(arrayList1.get(position));
        holder.textView2.setText(arrayList2.get(position));
        holder.textView3.setText(arrayList3.get(position));
        holder.textView4.setText(arrayList4.get(position));
        holder.textView5.setText(arrayList5.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }

    @Override
    public void onClick(View v) {

    }
}
