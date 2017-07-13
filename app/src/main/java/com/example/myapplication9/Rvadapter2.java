package com.example.myapplication9;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ss.com.bannerslider.views.BannerSlider;

/**
 * Created by fengbingnan on 2017/7/5.
 */

public class Rvadapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int HEADER0 = 0;
    private int HEADER1 = 1;
    private int HEADER2 = 2;
    private int HEADER3 = 3;
    private int HEADER4=4;
    private View headView0;
    private View headView1;
    private View headView2;
    private View headView3;
    private View headView4;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == HEADER0) {
            return new Rvadapter2.Search(headView0);
        } else if (viewType == HEADER1) {
            return new Rvadapter2.Banner(headView1);
        }
        else if (viewType==HEADER4){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shoucangitem,viewGroup,false);
            return new Rvadapter2.RvHolder(view);
        }

        else if(viewType==HEADER3){
            return new Rvadapter2.cainilike(headView3);}
        else if(viewType==HEADER2){
            return new Rvadapter2.Space(headView2);
        }
        else{
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shoucangitem,viewGroup,false);
            return new Rvadapter2.RvHolder(view);
        }

    }
    public void  addHeadView0(View view) {
        this.headView0 = view;
    }
    public void addHeaderView1(View v) {
        this.headView1 = v;
    }
    public void addHeaderView2(View v){
        this.headView2=v;
    }
    public void addHeaderView3(View v){
        this.headView3=v;
    }
    public void addHeaderView4(View v){
        this.headView4=v;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }
    @Override
    public int getItemCount() {
        return 4+20;
    }
    public int getItemViewType(int position) {
        if (position == 0 && headView0 != null) {
            return HEADER0;
        }else if(position==1&&headView1!=null) {
            return HEADER1;
        }
        else if(position==2&&headView2!=null) {
            return HEADER2;
        }else if (position==3&&headView3!=null){
            return HEADER3;
        }else if(position==4&&headView4!=null) {
            return HEADER4;
        }
        else return HEADER4;

    }
    class Search extends RecyclerView.ViewHolder  {
        public Search(View itemView) {
            super(itemView);
        }
}
    class Banner extends RecyclerView.ViewHolder  {
        public Banner(View itemView) {
            super(itemView);
        }
    }
    class cainilike extends RecyclerView.ViewHolder{
        public cainilike(View parent){
            super(parent);
        }
    }
    class Space extends RecyclerView.ViewHolder{
        public Space(View p){
            super(p);
        }
    }
    class RvHolder extends RecyclerView.ViewHolder{
        public RvHolder(View parent) {
            super(parent);
        }
    }
}
