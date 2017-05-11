package com.example.myapplication9;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 炳楠 on 2017/4/27.
 */
public class dingdanadpter extends ArrayAdapter {
    private Context context;
    private String[] imageUrls;
    private String[] imageUrls2;
    private String[] imageUrls3;
    private String[] imageUrls4;
    public dingdanadpter(Context context, String[] imageUrls, String[] imageUrls2, String[] imageUrls3, String[] imageUrls4){
        super(context,R.layout.dingdanitem,imageUrls);
        this.context = context;
        this.imageUrls = imageUrls;
        this.imageUrls2=imageUrls2;
        this.imageUrls3= imageUrls3;
        this.imageUrls4=imageUrls4;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = View.inflate(context,R.layout.dingdanitem,null);
        }
        ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView5 );
        TextView textView=(TextView)convertView.findViewById(R.id.textView2);
        TextView textView1=(TextView)convertView.findViewById(R.id.textView5);
        TextView textView2=(TextView)convertView.findViewById(R.id.textView6);
        textView.setText(imageUrls2[position]);
        textView1.setText(imageUrls3[position]);
        textView2.setText(imageUrls4[position]);

        return convertView;
    }
}
