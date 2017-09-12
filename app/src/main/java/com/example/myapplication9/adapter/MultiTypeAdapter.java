package com.example.myapplication9.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication9.R;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/8/26.
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<View> views=new ArrayList<>();
    int id;
    int num;
    int p;
    View view;
     public void  addView(View view) {
        views.add(view);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(id,parent,false);
        if(viewType!=p){
            if(viewType<p){
                return new MultiTypeClass(views.get(viewType));
            }else {
                return new MultiTypeClass(views.get(viewType-num));
            }
        }else {
            return initviewholder();
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return views.size()+num;
    }
    public int getItemViewType(int position) {
        if(position<p||position>p+num-1){
            return position;
        }else {
            return p;
        }
    }
    final class  MultiTypeClass extends RecyclerView.ViewHolder{
        public MultiTypeClass(View parent) {
            super(parent);
        }
    }
    class MultiTypeClass2 extends RecyclerView.ViewHolder{
        public MultiTypeClass2(View parent) {
            super(parent);
        }
    }
    public void addRepeatView(int position,int num,int id){
        this.num=num;
        this.p=position;
        this.id=id;
    }
     RecyclerView.ViewHolder initviewholder(){
        return null;
    }
}
