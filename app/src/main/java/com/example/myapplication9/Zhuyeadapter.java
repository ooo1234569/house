package com.example.myapplication9;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;





/**
 * Created by 炳楠 on 2017/1/23.
 */
public class Zhuyeadapter extends ArrayAdapter {
    private Context context;
    private TextView textView;
     private ImageView imageView;
    private int[] strings;
    private String[] strings2;

    public Zhuyeadapter(Context context, int []strings,String []strings2){
        super(context,R.layout.item,strings2);
        this.context = context;
        this.strings=strings;
        this.strings2=strings2;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = View.inflate(context,R.layout.item,null);
        }
        imageView=(ImageView)convertView.findViewById(R.id.imageView2) ;
        textView=(TextView)convertView.findViewById(R.id.textView3);
        imageView.setImageResource(strings[position]);
        textView.setText(strings2[position]);
        return convertView;
    }
}