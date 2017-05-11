package com.example.myapplication9;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar bottomNavigationBar;
    private Shouyefg shouyefg;
    private Dingdanfg dingdanfg;
    private Wodefg wodefg;
    private Nullfg nullfg;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager2;
    private FragmentTransaction transaction2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shouyefg=new Shouyefg();
        dingdanfg=new Dingdanfg();
        wodefg=new Wodefg();
        nullfg=new Nullfg();
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
        transaction.replace(R.id.fragment_container,shouyefg);
        transaction.commit();
    }
    public void onTabSelected(int position) {
        fragmentManager2=getSupportFragmentManager() ;
        transaction2=fragmentManager2.beginTransaction();
        switch (position) {
            case 0:
                if(!shouyefg.isAdded()) {
                    transaction2.add(R.id.fragment_container, shouyefg);
                }
                transaction2.hide(dingdanfg).hide(wodefg).hide(nullfg).show(shouyefg).commit();
                break;
            case 1:
                if(dingdanfg ==null) {
                    dingdanfg =new Dingdanfg();
                }
                if(!dingdanfg.isAdded()) {
                    transaction2.add(R.id.fragment_container, dingdanfg);
                }
                transaction2.hide(shouyefg).hide(wodefg).hide(nullfg).show(dingdanfg).commit();
                break;
            case 2:
                if(nullfg ==null) {
                    nullfg =new Nullfg();
                }
                if(!nullfg .isAdded()) {
                    transaction2.add(R.id.fragment_container,nullfg );
                }
                transaction2.hide(shouyefg).hide(dingdanfg).hide(wodefg).show(nullfg ).commit();
                break;
            case 3:
                if(wodefg ==null) {
                    wodefg =new Wodefg();
                }
                if(!wodefg.isAdded()) {
                    transaction2.add(R.id.fragment_container,wodefg);
                }
                transaction2.hide(shouyefg).hide(dingdanfg).hide(nullfg).show(wodefg).commit();
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
}
