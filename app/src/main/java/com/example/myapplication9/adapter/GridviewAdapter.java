package com.example.myapplication9.adapter;

/**
 * Created by 炳楠 on 2017/4/9.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication9.R;

import java.util.ArrayList;
import java.util.List;

public class GridviewAdapter extends BaseAdapter{
    private Context context;
    private int color1[][]={{0xff,0x5d,0x64},{0x71,0xaa,0xff},{0x8c,0xdb,0x45},{0xe1,0x59,0xcd},{0x8c,0xdb,0x45},{0xff,0xd0,0x45},{0x93,0x8e,0xea},{0xfe,0x5d,0x64}};
    private List<Picture> pictures=new ArrayList<Picture>();
    //private
    public GridviewAdapter(String[] titles, Integer[] images, Context context) {
        super();
        this.context = context;


        for (int i = 0; i < images.length; i++) {
            Picture picture = new Picture(titles[i], images[i]);
            pictures.add(picture);
        }

    }

    @Override
    public int getCount() {

        if (null != pictures) {
            return pictures.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {

        return pictures.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            // 获得容器
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_gridview, null);

            // 初始化组件
            viewHolder.image = (ImageView) convertView.findViewById(R.id.itemImage);
            viewHolder.title = (TextView) convertView.findViewById(R.id.itemText);
            // 给converHolder附加一个对象
            convertView.setTag(viewHolder);
        } else {
            // 取得converHolder附加的对象
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 给组件设置资源
        Picture picture = pictures.get(position);

        Glide.with(context).load(picture.getImageId()).into(viewHolder.image);
        viewHolder.title.setText(picture.getTitle());
        GradientDrawable myGrad = (GradientDrawable)viewHolder.image.getBackground();
        myGrad.setSize(70,70);
        myGrad.setColor(Color.argb(0xff,color1[position][0],color1[position][1],color1[position][2]));
        return convertView;
    }

    class ViewHolder {
        public TextView title;
        public ImageView image;
    }

    class Picture {

        private String title;
        private int imageId;

        public Picture(String title, Integer imageId) {
            this.imageId = imageId;
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public int getImageId() {
            return imageId;
        }

    }
}
