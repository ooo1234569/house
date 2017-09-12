package com.example.myapplication9.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication9.R;
import com.example.myapplication9.Shangjia;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/7/28.
 */
public class GoodsDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int HEADER0 = 0;
    private int HEADER1 = 1;
    private int HEADER2 = 2;
    private int HEADER3 = 3;
    private int HEADER4=4;
    private int HEADER5=5;
    private int HEADER6=6;
    private View headView0;
    private View headView1;
    private View headView2;
    private View headView3;
    private View headView4;
    private View headView5;
    private View headView6;
    private int viewitem=6+1;


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == HEADER0) {
            return new Imag(headView0);
        } else if (viewType == HEADER1) {
            return new Text(headView1);
        }
        else if(viewType==HEADER2){
            return new Fuwutime(headView2);
        }
        else if(viewType==HEADER3){
            return new Fuwumiaoshu(headView3);
        }
        else if (viewType==HEADER4){
            return new Comment(headView4);
        }
        else if(viewType==HEADER5){
            return new Dinggouxuzhi(headView5);
        }else if(viewType==HEADER6){
            return new Foot(headView6);
        }else {
            View spaceview=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_space1,viewGroup,false);
            return new Foot(spaceview);
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
    public void addHeaderView5(View v){
        this.headView5=v;
    }
    public void addHeaderView6(View v){
        this.headView6=v;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }
    @Override
    public int getItemCount() {
        return viewitem;
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
        }else if(position==5&&headView5!=null) {
            return HEADER5;
        }else if(position==6&&headView6!=null) {
            return HEADER6;
        }
        else return 20;
    }
    class Imag extends RecyclerView.ViewHolder  {
        public Imag(View itemView) {
            super(itemView);
        }
    }
    class Text extends RecyclerView.ViewHolder  {
        public Text(View itemView) {
            super(itemView);
        }
    }
    class Fuwutime extends RecyclerView.ViewHolder{
        public Fuwutime(View parent){
            super(parent);
        }
    }
    class Fuwumiaoshu extends RecyclerView.ViewHolder{
        public Fuwumiaoshu(View p){
            super(p);
        }
    }
    class Comment extends RecyclerView.ViewHolder{
        public Comment(View parent) {
            super(parent);
        }
    }
    class Dinggouxuzhi extends RecyclerView.ViewHolder{
        public Dinggouxuzhi(View parent) {
            super(parent);
        }
    }
    class Foot extends RecyclerView.ViewHolder{
        public Foot(View parent) {
            super(parent);
        }
    }

}
