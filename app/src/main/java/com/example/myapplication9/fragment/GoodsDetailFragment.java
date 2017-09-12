package com.example.myapplication9.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.myapplication9.Activity.BusinessDetailActivity;
import com.example.myapplication9.Activity.CommentActivity;
import com.example.myapplication9.Activity.DetailActivity;
import com.example.myapplication9.Activity.SelectTimeActivity;
import com.example.myapplication9.R;
import com.example.myapplication9.adapter.GoodsDetailAdapter;

import java.io.InputStream;


/**
 * Created by bingnanfeng02 on 2017/7/29.
 */
public class GoodsDetailFragment extends Fragment {
    private RecyclerView recyclerView;
    private GoodsDetailAdapter goodsdetailad;
    private LinearLayoutManager linearLayoutManager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_goods_detail,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv);
        goodsdetailad=new GoodsDetailAdapter();
        linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        initimg();
        initext();
        initime();
        initmiaoshu();
        initcomment();
        initdinggouxuzhi();
        initfoot();
        recyclerView.setAdapter(goodsdetailad);
        return view;
    }
    void initimg(){
        View space = View.inflate(getContext(), R.layout.view_img, null);
        ImageView imageView=(ImageView)space.findViewById(R.id.img);
        imageView.setImageResource(R.drawable.touxiang );
        goodsdetailad.addHeadView0(space);
    }
    void initime(){
        View time = View.inflate(getContext(), R.layout.item_select_time_from_goodsdetail, null);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailActivity)getActivity()).SetAlpha(0);
                ((DetailActivity)getActivity()).SetWindow(0);
            }
        });
        goodsdetailad.addHeaderView2(time);
    }
    void initext(){
        View text = View.inflate(getContext(), R.layout.view_goods_text, null);
        goodsdetailad.addHeaderView1(text);
    }
    void initmiaoshu(){
        View miaoshu = View.inflate(getContext(), R.layout.view_info, null);
        RelativeLayout relativeLayout=(RelativeLayout)miaoshu.findViewById(R.id.rl);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BusinessDetailActivity.class));
            }
        });
        goodsdetailad.addHeaderView3(miaoshu);
    }
    void initcomment(){
        View comment = View.inflate(getContext(), R.layout.fragment_goods_comment, null);
        ImageView imageView=(ImageView)comment.findViewById(R.id.touxiang);
        ImageView imageView1=(ImageView)comment.findViewById(R.id.enter);
        Glide.with(getContext()).load(R.drawable.touxiang).into(imageView);
        Glide.with(getContext()).load(R.drawable.enter).into(imageView1);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), CommentActivity.class));
            }
        });
        goodsdetailad.addHeaderView4(comment);
    }
    void initdinggouxuzhi(){
        View dinggouxuzhi = View.inflate(getContext(), R.layout.view_order_information, null);
        goodsdetailad.addHeaderView5(dinggouxuzhi);
    }
    void initfoot(){
        View foot = View.inflate(getContext(), R.layout.foot, null);
        goodsdetailad.addHeaderView6(foot);

    }
    public  void onDestroy(){
        super.onDestroy();
        Log.d("asdasd","rc");
    }
}
