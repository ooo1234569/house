package com.example.myapplication9.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication9.Activity.DetailActivity;
import com.example.myapplication9.Activity.SearchResultActivity;
import com.example.myapplication9.Business;
import com.example.myapplication9.R;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/9/6.
 */

public class HomepageAdapter2 extends MultiTypeAdapter {
    private int viewitem;
    private ArrayList<Business> shangjias;
    private Context context;
    public HomepageAdapter2(ArrayList<Business> shangjias, int viewitem, Context context){
        this.shangjias=shangjias;
        this.viewitem=viewitem;
        this.context=context;
    }

    @Override
    RecyclerView.ViewHolder initviewholder() {
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

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof Shangjiainfo){
            Business business=shangjias.get(position-viewitem);
            ((Shangjiainfo) viewHolder).type.setText(business.type);
            ((Shangjiainfo) viewHolder).name1.setText(business.name1);
            ((Shangjiainfo) viewHolder).name2.setText(business.name2);
            ((Shangjiainfo) viewHolder).name3.setText(business.name3);
            Glide.with(context).load(R.drawable.touxiang).asBitmap().into( ((Shangjiainfo) viewHolder).imageView1);
            Glide.with(context).load(R.drawable.touxiang).asBitmap().into( ((Shangjiainfo) viewHolder).imageView2);
            Glide.with(context).load(R.drawable.touxiang).asBitmap().into( ((Shangjiainfo) viewHolder).imageView3);
        }
    }
    class Shangjiainfo extends MultiTypeClass2{
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
