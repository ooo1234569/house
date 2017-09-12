package com.example.myapplication9;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by bingnanfeng02 on 2017/7/25.
 */
public class ZhuyeHeader extends LinearLayout implements PtrUIHandler {
    View view;
    TextView textView;
    ImageView imageView;
    AnimationDrawable anim;
    Context context;
    ImageView imageView2;
    public ZhuyeHeader(Context context) {
        super(context);
        this.context=context;
        view = LayoutInflater.from(context).inflate(R.layout.view_refresh_header_layout, this, false);
        addView(view);
        textView=(TextView)view.findViewById(R.id.refresh);
        imageView=(ImageView)view.findViewById(R.id.refreshanim);
        Glide.with(context).load(R.drawable.down).into(imageView);
        imageView2=(ImageView)view.findViewById(R.id.loaing);
        imageView2.setBackgroundResource(R.drawable.loading);
        anim=(AnimationDrawable)imageView2.getBackground();
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
      Log.d("re","re");

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
          imageView.setVisibility(GONE);
          anim.start();
          imageView2.setVisibility(VISIBLE);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
             textView.setText("下拉刷新");
             imageView.setVisibility(VISIBLE);
             anim.stop();
             imageView2.setVisibility(GONE);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
         if(ptrIndicator.getCurrentPosY()>ptrIndicator.getOffsetToRefresh()){
             textView.setText("释放刷新");
         }else {
             textView.setText("下拉刷新");
         }

    }

}
