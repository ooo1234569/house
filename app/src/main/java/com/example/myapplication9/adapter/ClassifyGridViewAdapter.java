package com.example.myapplication9.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication9.R;
import com.example.myapplication9.SmallService;
import java.util.List;

/**
 * Created by bingnanfeng02 on 2017/8/4.
 */
public class ClassifyGridViewAdapter extends BaseAdapter {
    private Context context;

    private List<SmallService> smallServiceList;
    //private
    public ClassifyGridViewAdapter(List<SmallService> smallServiceList,  Context context) {
        super();
        this.context = context;
        this.smallServiceList=smallServiceList;

    }

    @Override
    public int getCount() {
        return smallServiceList.size();
    }

    @Override
    public Object getItem(int position) {
        return smallServiceList.get(position);
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
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_classify_gridview, null);
            // 初始化组件
            viewHolder.image = (ImageView) convertView.findViewById(R.id.itemImage);
            viewHolder.title = (TextView) convertView.findViewById(R.id.itemText);
            // 给converHolder附加一个对象
            convertView.setTag(viewHolder);
        } else {
            // 取得converHolder附加的对象
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //viewHolder.image.setImageResource(picture.getImageId());
        viewHolder.title.setText(smallServiceList.get(position).getServiceName());
        return convertView;
    }

    class ViewHolder {
        public TextView title;
        public ImageView image;
    }


}
