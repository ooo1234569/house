package com.example.myapplication9.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication9.Activity.EditProfileActivity;
import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/8/19.
 */
public class SelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<String> arrayList;
    PositionListener positionListener;
     Uri uri;
    public SelectAdapter(Context context, ArrayList<String> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select,parent,false);
        final Select select=new Select(view);
        select.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=select.getAdapterPosition();
                if(i==0){
                    positionListener.onArticleSelected(2);
            }else {
                    positionListener.onArticleSelected(3);
                }
            }
        });
        return select;
        }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Select){
            ((Select)holder).textView.setText(arrayList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
    public void setPositionListener(PositionListener positionListener){
        this.positionListener=positionListener;
    }
    class Select extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView textView;
        public Select(View itemView) {
            super(itemView);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.rl);
            textView=(TextView)itemView.findViewById(R.id.selecttext);
        }
    }
}


