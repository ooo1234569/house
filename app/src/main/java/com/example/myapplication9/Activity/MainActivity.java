package com.example.myapplication9.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.myapplication9.MyApplication;
import com.example.myapplication9.R;
import com.example.myapplication9.fragment.*;

import java.util.ArrayList;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar bottomNavigationBar;
    private OrderFragment dingdanfg;
    private HomePageFragment shouyefg2;
    private AboutMeFragment wodefg;
    private MessageFragment msgfg;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private MyApplication myApplication;
    private ShoppingCarFragment shoppingCarFragment;
    private ArrayList<Fragment> views=new ArrayList<>();
    int ut=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         myApplication=(MyApplication)getApplication();
        if (myApplication.flag==0){
            startActivity(new Intent(this,LoginActivity.class));
        }
        setContentView(R.layout.activity_main);
        shouyefg2=new HomePageFragment();
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
        initbottombar();
        fragmentManager=getSupportFragmentManager() ;
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,shouyefg2);
        transaction.commit();
    }

    void initbottombar(){
        bottomNavigationBar.setActiveColor("#71aaff")
                .setInActiveColor("#929292")
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBarBackgroundColor("#FFFFFF")
                .addItem(new BottomNavigationItem(R.drawable.shouye, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.dingdan, "订单"))
                .addItem(new BottomNavigationItem(R.drawable.gouwuche, "购物车"))
                .addItem(new BottomNavigationItem(R.drawable.xiaoxi, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.wode, "我的"))
                .setFirstSelectedPosition(0)
                .setTabSelectedListener(this)
                .initialise();
    }

    public void onTabSelected(int position) {
        fragmentManager=getSupportFragmentManager() ;
        transaction=fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                if(shouyefg2 ==null) {
                    shouyefg2=new HomePageFragment();
                    Log.d("asda",position+"");
                }
                if(!shouyefg2.isAdded()){
                    transaction.add(R.id.fragment_container,shouyefg2);
                    views.add(shouyefg2);
                }
                for(ut=0;ut<views.size();ut++){
                    transaction.hide(views.get(ut));
                }
                transaction.show(shouyefg2).commit();
                break;
            case 1:
                if(dingdanfg ==null) {
                    dingdanfg =new OrderFragment();
                    Log.d("asda",position+"");
                }
                if(!dingdanfg.isAdded()){
                    transaction.add(R.id.fragment_container,dingdanfg);
                    views.add(dingdanfg);
                }
                for(ut=0;ut<views.size();ut++){
                    transaction.hide(views.get(ut));
                }
                transaction.show(dingdanfg).commit();
                break;
            case 3:
                if(msgfg ==null) {
                    msgfg =new MessageFragment();
                    Log.d("asda",position+"");
                }
                if(!msgfg.isAdded()){
                    transaction.add(R.id.fragment_container,msgfg);
                    views.add(msgfg);
                }
                for(ut=0;ut<views.size();ut++){
                    transaction.hide(views.get(ut));
                }
                transaction.show(msgfg).commit();
                break;
            case 2:
                if(shoppingCarFragment ==null) {
                    shoppingCarFragment =new ShoppingCarFragment();
                    Log.d("asda",position+"");
                }
                if(!shoppingCarFragment.isAdded()){
                    transaction.add(R.id.fragment_container,shoppingCarFragment);
                    views.add(shoppingCarFragment);
                }
                for(ut=0;ut<views.size();ut++){
                    transaction.hide(views.get(ut));
                }
                transaction.hide(shouyefg2);
                transaction.show(shoppingCarFragment).commit();
                break;
            case 4:
                if(wodefg ==null) {
                    wodefg =new AboutMeFragment();
                    Log.d("asda",position+"");
                }
                if(!wodefg.isAdded()){
                    transaction.add(R.id.fragment_container,wodefg);
                    views.add(wodefg);
                }
                for(ut=0;ut<views.size();ut++){
                    transaction.hide(views.get(ut));
                }
                transaction.show(wodefg).commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int i) {

    }

    @Override
    public void onTabReselected(int i) {

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            this.finish();
        }
        return true;
    }

}
