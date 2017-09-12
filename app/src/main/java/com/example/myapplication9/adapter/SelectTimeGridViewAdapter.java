package com.example.myapplication9.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myapplication9.Activity.DateObject;
import com.example.myapplication9.R;

/**
 * Created by bingnanfeng02 on 2017/9/7.
 */

public class SelectTimeGridViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    Context context;
    DateObject dateObject;
    public SelectTimeGridViewAdapter(Context context, DateObject dateObject){
        this.context=context;
        inflater = LayoutInflater.from(this.context);
        this.dateObject=dateObject;
    }
    @Override
    public int getCount() {
        return dateObject.strings.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_gridview_from_select_time, null);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        FrameLayout frameLayout=(FrameLayout)convertView.findViewById(R.id.fl);
        time.setText(dateObject.strings.get(position));
        if(position!=dateObject.p){
            frameLayout.setBackgroundResource(R.drawable.biankuang9);
            time.setTextColor(Color.parseColor("#999999"));
        }else {
            frameLayout.setBackgroundResource(R.drawable.biankuang8);
            time.setTextColor(Color.parseColor("#71aaff"));
        }
        return convertView;
    }

}
