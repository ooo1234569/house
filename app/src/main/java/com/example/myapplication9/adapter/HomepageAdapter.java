package com.example.myapplication9.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication9.Activity.DetailActivity;
import com.example.myapplication9.Activity.SearchResultActivity;
import com.example.myapplication9.Business;
import com.example.myapplication9.R;
import com.example.myapplication9.Shangjia;

import java.util.ArrayList;


/**
 * Created by fengbingnan on 2017/7/5.
 */

public class HomepageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
    private int viewitem;
    private ArrayList<Business> shangjias;
    private Context context;
    public ProgressBar progressBar;

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
    public HomepageAdapter(ArrayList<Business> shangjias, int viewitem, Context context){
        this.shangjias=shangjias;
        this.viewitem=viewitem;
        this.context=context;
    }
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == HEADER0) {
            return new HomepageAdapter.Search(headView0);
        } else if (viewType == HEADER1) {
            return new HomepageAdapter.Banner(headView1);
        }
        else if(viewType==HEADER2){
            return new HomepageAdapter.Space(headView2);
        }
        else if(viewType==HEADER3){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_collection2,viewGroup,false);
            final Shangjiainfo shangjiainfo=new Shangjiainfo(view);
            shangjiainfo.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, SearchResultActivity.class);
                    intent.putExtra("yemian",shangjiainfo.type.getText());
                    context.startActivity(intent);
                }
            });
            shangjiainfo.linearLayout.setOnClickListener(ma);
            shangjiainfo.linearLayout2.setOnClickListener(ma);
            shangjiainfo.linearLayout3.setOnClickListener(ma);
            return shangjiainfo;
        }
        else {
            View spaceview=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_space1,viewGroup,false);
            return new Foot(spaceview);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
          if(viewHolder instanceof Shangjiainfo){
              Business business=shangjias.get(i-viewitem);
              ((Shangjiainfo) viewHolder).type.setText(business.type);
              ((Shangjiainfo) viewHolder).name1.setText(business.name1);
              ((Shangjiainfo) viewHolder).name2.setText(business.name2);
              ((Shangjiainfo) viewHolder).name3.setText(business.name3);
              Glide.with(context).load(R.drawable.touxiang).into( ((Shangjiainfo) viewHolder).imageView1);
              Glide.with(context).load(R.drawable.touxiang).into( ((Shangjiainfo) viewHolder).imageView2);
              Glide.with(context).load(R.drawable.touxiang).into( ((Shangjiainfo) viewHolder).imageView3);
//              Shangjia shangjia=shangjias.get(i-viewitem);
//              ((Shangjiainfo) viewHolder).name.setText(shangjia.name);
//              ((Shangjiainfo) viewHolder).shuoming.setText(shangjia.shuoming);
//              ((Shangjiainfo) viewHolder).xiaoliang.setText(shangjia.xiaoliang);
//              ((Shangjiainfo) viewHolder).juli.setText(shangjia.juli);
//              ((Shangjiainfo) viewHolder).relativeLayout.setOnClickListener(new View.OnClickListener() {
//                  @Override
//                  public void onClick(View v) {
//                      context.startActivity(new Intent(context,DetailActivity.class));
//                  }
//              });
          }
    }
    @Override
    public int getItemCount() {
        return viewitem+shangjias.size()+1;
    }
    public int getItemViewType(int position) {
        if (position == 0 && headView0 != null) {
            return HEADER0;
        }else if(position==1&&headView1!=null) {
            return HEADER1;
        }
        else if(position==2&&headView2!=null) {
            return HEADER2;
        }else if (position<getItemCount()-1&&position>2){
            return HEADER3;
        }
        else return 100;
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
    class Space extends RecyclerView.ViewHolder{
        public Space(View p){
            super(p);
        }
    }
    class Progress extends RecyclerView.ViewHolder{
        public Progress(View parent) {
            super(parent);
        }
    }
    class Foot extends RecyclerView.ViewHolder{
        public Foot(View parent) {
            super(parent);
        }
    }
    class Shangjiainfo extends RecyclerView.ViewHolder{
        private TextView type;
        private TextView name1;
        private TextView name2;
        private TextView name3;
        private ImageView imageView1;
        private ImageView imageView2;
        private ImageView imageView3;
        private TextView juli;
        private RelativeLayout relativeLayout;
        private LinearLayout linearLayout;
        private LinearLayout linearLayout2;
        private LinearLayout linearLayout3;
        public Shangjiainfo(View parent) {
            super(parent);
            relativeLayout=(RelativeLayout)parent.findViewById(R.id.rl);
            type=(TextView)parent.findViewById(R.id.type);
            name1=(TextView)parent.findViewById(R.id.name1);
            name2=(TextView)parent.findViewById(R.id.name2);
            name3=(TextView)parent.findViewById(R.id.name3);
            linearLayout=(LinearLayout)parent.findViewById(R.id.name11);
            linearLayout2=(LinearLayout)parent.findViewById(R.id.name22);
            linearLayout3=(LinearLayout)parent.findViewById(R.id.name33);
            imageView1=(ImageView)parent.findViewById(R.id.image1);
            imageView2=(ImageView)parent.findViewById(R.id.image2);
            imageView3=(ImageView)parent.findViewById(R.id.image3);
        }
    }
   View.OnClickListener ma =new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           context.startActivity(new Intent(context,DetailActivity.class));
       }
   };
}
