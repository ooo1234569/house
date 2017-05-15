package com.example.myapplication9;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private RecyclerView recyclerView;
    private Rvadapter rvadapter;
    private View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shouyefg, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rvadapter=new Rvadapter();
        initbanner();
        initgridmenu();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvadapter);System.out.println("hh");
        return  view;
    }
    public  void initbanner(){
        View bannerView = View.inflate(getContext(), R.layout.banner, null);
        BannerSlider bannerSlider;
        bannerSlider = (BannerSlider)bannerView.findViewById(R.id.banner_slider1);
        bannerSlider.addBanner(new RemoteBanner("https://pics.javbus.info/cover/5zte_b.jpg"));
        bannerSlider.addBanner(new RemoteBanner("https://pics.javbus.info/cover/5x1t_b.jpg"));
        rvadapter.addHeadView0(bannerView);
    }
    public void  initgridmenu(){
        View gridmenu = View.inflate(getContext(), R.layout.gridmnu, null);
        GridView gridview;
        gridview = (GridView) gridmenu.findViewById(R.id.gridView);
        String[] titles=new String[]{
                "维修","教学","搬运","安装","清洁","会计","房建","其他"
        };
        Integer[] images={
                R.drawable.fix,R.drawable.jiaoxue,
                R.drawable.banjia,R.drawable.anzhuang,
                R.drawable.qingjie,R.drawable.kuaiji,R.drawable.jianzhu,R.drawable.qita
        };
        gridview.setAdapter(new Gridviewadpter(titles,images,getContext()));
        rvadapter.addHeaderView1(gridmenu);
    }
    public  void initrv(){
        View rv=View.inflate(getContext(),R.layout.tujian,null);
        RecyclerView recyclerView;
        recyclerView=(RecyclerView)rv.findViewById(R.id.shangjiarv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Rvadaptershangjia rvadaptershang;
        recyclerView.setAdapter(rvadaptershang);
        rvadapter.addHeaderView3(recyclerView);
    }
}
