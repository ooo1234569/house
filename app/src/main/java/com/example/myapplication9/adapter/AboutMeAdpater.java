package com.example.myapplication9.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication9.Activity.*;
import com.example.myapplication9.Person;
import com.example.myapplication9.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;



/**
 * Created by bingnanfeng02 on 2017/7/24.
 */
public class AboutMeAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private String[] strings;
    private Context context;
    private Intent intent;
    private String[]s;
    public AboutMeAdpater(String[] strings, Context context,String[]s){
        this.strings=strings;
        this.context=context;
        this.s=s;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head_portrait2,parent,false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, EditProfileActivity.class));
                }
            });
            return new touxiang(view);
        }else if(viewType ==1){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_about_me_else2,parent,false);
            final qita qita1=new qita(view);
            LinearLayout.LayoutParams inputlParams = (LinearLayout.LayoutParams)qita1.space.getLayoutParams();
            inputlParams.height=0;
            qita1.space.setLayoutParams(inputlParams);
            qita1.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p=qita1.getAdapterPosition();
                    if (p==3){
                        intent=new Intent(context, ViewAddressActivity.class);
                        intent.putExtra("which",p);
                        context.startActivity(intent);
                    }else if(p==1||p==2){
                        intent=new Intent(context, CollectionActivity.class);
                        intent.putExtra("which",p);
                        context.startActivity(intent);
                    }

                }
            });
            return qita1;
        }else {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_about_me_else2,parent,false);
            final qita qita1=new qita(view);
          qita1.space.setVisibility(View.VISIBLE);
            qita1.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p=qita1.getAdapterPosition();
                    if (p==3){
                        intent=new Intent(context, ViewAddressActivity.class);
                        intent.putExtra("which",p);
                        context.startActivity(intent);
                    }else if(p==1||p==2){
                        intent=new Intent(context, CollectionActivity.class);
                        intent.putExtra("which",p);
                        context.startActivity(intent);
                    }

                }
            });
            return qita1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof qita){
            ((qita) holder).textView.setText(strings[position-1]);
        }else {
            ((touxiang)holder).textView.setText(s[0]);
            Glide.with(context).load(R.drawable.touxiang).into( ((touxiang)holder).imageViewl);
        }
    }
    public int getItemViewType(int position) {
        if (position == 0 ) {
            return 0;
        } else  if(!(position==2||position==4)){
            return 1;
        }
        else return 20;
    }
    @Override
    public int getItemCount() {
        return strings.length+1;
    }
    class touxiang extends RecyclerView.ViewHolder {
        private ImageView imageViewl;
        private TextView textView;
        public touxiang(View itemView) {
            super(itemView);
            imageViewl=(ImageView)itemView.findViewById(R.id.imageView9);
            textView=(TextView)itemView.findViewById(R.id.name);

            imageViewl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ViewImageActivity.class);
                    Bundle bundle = new Bundle();
                    String[] urls = new String[2];
                    ArrayList<String> arrayList=new ArrayList<String>();
                    urls[0]="baidu";
                    urls[1]="baidu";
                    arrayList.add( urls[0]);
                    arrayList.add( urls[1]);
                    bundle.putStringArray("urls", urls);
                    bundle.putStringArrayList("urls",arrayList);
                    //bundle.putSerializable("movie", movie);
                    //bundle.putInt("position", holder.getAdapterPosition());
                    i.putExtras(bundle);
                    context.startActivity(i);
                }
            });
        }
    }
    class qita extends RecyclerView.ViewHolder {
        private TextView textView;
        private RelativeLayout relativeLayout;
        private View space;
        private ImageView icon;
        private ImageView enter;
        public qita(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.textView13);
            space=itemView.findViewById(R.id.space);
            icon=(ImageView)itemView.findViewById(R.id.heart);
            enter=(ImageView)itemView.findViewById(R.id.enter);
            Glide.with(context).load(R.drawable.heart).into(icon);
            Glide.with(context).load(R.drawable.enter).into(enter);
        AssetManager assets = context.getAssets();
        Typeface fromAsset = Typeface.createFromAsset(assets, "fonts/pingfang.ttf");
        textView.setTypeface(fromAsset);
            relativeLayout=(RelativeLayout) itemView.findViewById(R.id.rl);
        }
    }
}
