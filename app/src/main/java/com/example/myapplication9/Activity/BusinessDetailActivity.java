package com.example.myapplication9.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.myapplication9.R;
import com.example.myapplication9.adapter.BussinessDetailAdapter;


/**
 * Created by bingnanfeng02 on 2017/7/29.
 */
public class BusinessDetailActivity extends BackActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private BussinessDetailAdapter shangjiadetailad;
    FragmentManager childFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shangjiadetail);
        initback("三鼎家政");
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout)findViewById(R.id.sliding_tabs);
        childFragmentManager=getSupportFragmentManager();
        shangjiadetailad=new BussinessDetailAdapter(childFragmentManager,this);
        mViewPager.setAdapter(shangjiadetailad);
        mTabLayout.setupWithViewPager(mViewPager);
        one = mTabLayout.getTabAt(0);
        one.setText("服务项目");
        two = mTabLayout.getTabAt(1);
        two.setText("商家简介");
        three = mTabLayout.getTabAt(2);
        three.setText("评价");
    }
}
