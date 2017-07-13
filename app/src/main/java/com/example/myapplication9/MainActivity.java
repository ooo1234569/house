package com.example.myapplication9;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar bottomNavigationBar;
    private Dingdanfg dingdanfg;
    private Shouyefg shouyefg2;
    private Wodefg wodefg;
    private Msgfg msgfg;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager2;
    private FragmentTransaction transaction2;
    private TelephonyManager tm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shouyefg2=new Shouyefg();
        dingdanfg=new Dingdanfg();
        wodefg=new Wodefg();
        msgfg=new Msgfg();
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
        bottomNavigationBar.setActiveColor("#000000")
                .setInActiveColor("#000000")
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBarBackgroundColor("#FFFFFF")
                .addItem(new BottomNavigationItem(R.drawable.shouye, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.dingdan, "订单"))
                .addItem(new BottomNavigationItem(R.drawable.xiaoxi, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.wode, "我的"))
                .setFirstSelectedPosition(0)
                .setTabSelectedListener(this)
                .initialise();
        fragmentManager=getSupportFragmentManager() ;
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,shouyefg2);
        transaction.commit();
    }
    public void onTabSelected(int position) {
        fragmentManager2=getSupportFragmentManager() ;
        transaction2=fragmentManager2.beginTransaction();
        switch (position) {
            case 0:
                if(shouyefg2 ==null) {
                    shouyefg2=new Shouyefg();
                }
                if(!shouyefg2.isAdded()) {
                    transaction2.add(R.id.fragment_container, shouyefg2);
                }
                transaction2.hide(dingdanfg).hide(wodefg).hide(msgfg).show(shouyefg2).commit();
                break;
            case 1:
                if(dingdanfg ==null) {
                    dingdanfg =new Dingdanfg();
                }
                if(!dingdanfg.isAdded()) {
                    transaction2.add(R.id.fragment_container, dingdanfg);
                }
                transaction2.hide(shouyefg2).hide(wodefg).hide(msgfg).show(dingdanfg).commit();
                break;
            case 2:
                if(msgfg ==null) {
                    msgfg =new Msgfg();
                }
                if(!msgfg .isAdded()) {
                    transaction2.add(R.id.fragment_container,msgfg );
                }
                transaction2.hide(shouyefg2).hide(dingdanfg).hide(wodefg).show(msgfg ).commit();
                break;
            case 3:
                if(wodefg ==null) {
                    wodefg =new Wodefg();
                }
                if(!wodefg.isAdded()) {
                    transaction2.add(R.id.fragment_container,wodefg);
                }
                transaction2.hide(shouyefg2).hide(dingdanfg).hide(msgfg).show(wodefg).commit();
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
