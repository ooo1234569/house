package com.example.myapplication9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * Created by fengbingnan on 2017/5/23.
 */

public class Shouyefg2 extends Fragment {
    private BannerSlider bannerSlider;
    private GridView gridView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouyefg3, container, false);
        gridView=(GridView)view.findViewById(R.id.gv);
        bannerSlider=(BannerSlider) view.findViewById(R.id.banner_slider1);
        bannerSlider.addBanner(new RemoteBanner("https://assets.materialup.com/uploads/05e9b7d9-ade2-4aed-9cb4-9e24e5a3530d/preview.jpg"));
        bannerSlider.addBanner(new RemoteBanner("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png"));
        String[] titles=new String[]{
                "维修","教学","搬运","安装","清洁","会计","房建","其他"
        };
        Integer[] images={
                R.drawable.fix,R.drawable.jiaoxue,
                R.drawable.banjia,R.drawable.anzhuang,
                R.drawable.qingjie,R.drawable.kuaiji,R.drawable.jianzhu,R.drawable.qita
        };
        gridView.setAdapter(new Gridviewadpter(titles,images,getContext()));
        return view;
    }
}
