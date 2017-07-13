package com.example.myapplication9;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.iasii.app.citylist.activity.DingweiActivity;

import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * Created by 炳楠 on 2017/4/15.
 */
public class Shouyefg extends Fragment  {
    private RecyclerView recyclerView;
    private Rvadapter2 rvadapter;
    private ImageView imageView;
    private View view;
    private TextView search;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shouyefg, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.mFilterContentView);
        search=(TextView)view.findViewById(R.id.input);
        imageView=(ImageView)view.findViewById(R.id.photo);
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        RelativeLayout.LayoutParams inputlParams = (RelativeLayout.LayoutParams)search.getLayoutParams();
        int width = metric.widthPixels;
        inputlParams.width=width-(int)(43*metric.density+0.5f);
        search.setLayoutParams(inputlParams);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rvadapter=new Rvadapter2();
        initbanner();
        initgridmenu();
        intitspace();
        initcainilike();
        //intfl();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvadapter);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),SearchActivity.class));
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Dingwei.class));
            }
        });
        return  view;
    }
    public  void initbanner(){
        View bannerView = View.inflate(getContext(), R.layout.banner, null);
        BannerSlider bannerSlider;
        bannerSlider = (BannerSlider)bannerView.findViewById(R.id.banner_slider1);
        bannerSlider.addBanner(new DrawableBanner(R.drawable.qq));
        bannerSlider.addBanner(new DrawableBanner(R.drawable.qq2));
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
//        Integer[] images={
//                R.drawable.fix,R.drawable.fix,
//                R.drawable.fix,R.drawable.fix,
//                R.drawable.fix,R.drawable.fix,R.drawable.fix,R.drawable.fix
//        };
        gridview.setAdapter(new Gridviewadpter(titles,images,getContext()));
        rvadapter.addHeaderView1(gridmenu);
    }
    public void intitspace(){
        View space =View.inflate(getContext(),R.layout.space,null);
        rvadapter.addHeaderView2(space);
    }
    public void initcainilike(){
        View cainilike=View.inflate(getContext(),R.layout.cainilike,null);
        rvadapter.addHeaderView3(cainilike);
    }
//    public  void intfl(){
//        View fl=View.inflate(getContext(),R.layout.filter,null);
//        dropDownMenu=(DropDownMenu)fl.findViewById(R.id.filterDropDownView);
//        rvadapter.addHeaderView3(fl);
//    }


//    public void onFilterDone(int position, String positionTitle, String urlValue) {
//        dropDownMenu.close();
//    }
}
