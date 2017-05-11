package com.example.myapplication9;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * Created by 炳楠 on 2017/4/15.
 */
public class Shouyefg extends Fragment {
    private GridView gridview;
    private BannerSlider bannerSlider;
    private String[] titles=new String[]{
            "维修","教学","搬运","安装","清洁","会计","房建","其他"
    };
    private Integer[] images={
            R.drawable.fix,R.drawable.jiaoxue,
            R.drawable.banjia,R.drawable.anzhuang,
            R.drawable.qingjie,R.drawable.kuaiji,R.drawable.jianzhu,R.drawable.qita
    };
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.shouyefg,container,false);
        bannerSlider = (BannerSlider)view.findViewById(R.id.banner_slider1);
        bannerSlider.addBanner(new RemoteBanner("https://pics.javbus.info/cover/5zte_b.jpg"));
        bannerSlider.addBanner(new RemoteBanner("https://pics.javbus.info/cover/5x1t_b.jpg"));
        gridview = (GridView) view.findViewById(R.id.gridView);
        gridview.setAdapter(new Gridviewadpter(titles,images,getContext()));
        return view;
    }
}
