package com.example.myapplication9.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication9.MyApplication;
import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;
import com.example.myapplication9.SelectSpecificationView;
import com.example.myapplication9.adapter.MultiFragmentPagerAdapter;
import com.example.myapplication9.fragment.*;

import java.util.ArrayList;


/**
 * Created by bingnanfeng02 on 2017/7/28.
 */
public class DetailActivity extends AppCompatActivity implements PositionListener{
    private FragmentManager fragmentManager;
    private MultiFragmentPagerAdapter multiFragmentPagerAdapter;
    private RelativeLayout relativeLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private GoodsDetailFragment fuwudetail;
    private Nullfg2 nullfg2;
    private Nullfg3 nullfg3;
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private RelativeLayout collection;
    private RelativeLayout gouwuche;
    private RelativeLayout shoppingcarrl;
    private TextView num;
    private RelativeLayout shopingcar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail2);
        relativeLayout=(RelativeLayout)findViewById(R.id.lijigoumai);
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout)findViewById(R.id.sliding_tabs);
        collection=(RelativeLayout)findViewById(R.id.collection);
        gouwuche=(RelativeLayout)findViewById(R.id.jiarugouwuche);
        shopingcar=(RelativeLayout)findViewById(R.id.shoppingcar);
        shoppingcarrl=(RelativeLayout)findViewById(R.id.shoppingcarrl);
        num=(TextView)findViewById(R.id.num);
        nullfg2=new Nullfg2();
        nullfg3=new Nullfg3();
        fuwudetail=new GoodsDetailFragment();
        fragments.add(fuwudetail);
        fragments.add(nullfg2);
        fragments.add(nullfg3);
        fragmentManager=getSupportFragmentManager() ;
        multiFragmentPagerAdapter =new MultiFragmentPagerAdapter(fragmentManager,this,fragments);
        mViewPager.setAdapter(multiFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        one = mTabLayout.getTabAt(0);
        one.setText("服务详情");
        two = mTabLayout.getTabAt(1);
        two.setText("收费标准");
        three = mTabLayout.getTabAt(2);
        three.setText("案例介绍");
        initshoppingcar();
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SetAlpha(0);
               SetWindow(1);
            }
        });
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
            }
        });
        gouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetAlpha(0);
                SetWindow(0);
            }
        });
        shopingcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, ShoppingCarActivity.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initshoppingcar();
    }
    void initshoppingcar(){
        int size=((MyApplication)getApplication()).services.size();
        if(size>0){
            shoppingcarrl.setVisibility(View.VISIBLE);
            num.setText(size+"");
        }else {
            shoppingcarrl.setVisibility(View.GONE);
        }
    }
    @Override
    public void onArticleSelected(int position) {

        if (position==0){
            SetAlpha(0);
        }else if(position==1){
            SetAlpha(1);
            initshoppingcar();
        }
    }
    public void SetWindow(int flag){
        SelectSpecificationView selectView = new SelectSpecificationView(DetailActivity.this,flag);
        selectView.setview();
        selectView.setP(DetailActivity.this);
        selectView.showAtLocation(findViewById(R.id.rl), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    public void SetAlpha(int flag){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        if(flag==0){
            lp.alpha = 0.5f;
        }else {
            lp.alpha=1;
        }
        getWindow().setAttributes(lp);
    }
}
