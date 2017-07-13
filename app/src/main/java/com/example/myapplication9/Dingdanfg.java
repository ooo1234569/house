package com.example.myapplication9;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.lang.reflect.Field;


/**
 * Created by 炳楠 on 2017/4/15.
 */
public class Dingdanfg extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Pager pager;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TextView test;
    FragmentManager childFragmentManager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.dingdanlayout,container,false);
        mViewPager= (ViewPager) view.findViewById(R.id.viewpager);
        test=(TextView)view.findViewById(R.id.test);
        childFragmentManager = getChildFragmentManager();
        pager = new Pager(childFragmentManager,getContext());
        mViewPager.setAdapter(pager);
        mTabLayout = (TabLayout)view.findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        one = mTabLayout.getTabAt(0);
        one.setText("全部订单");
        two = mTabLayout.getTabAt(1);
        two.setText("未完成订单");
        three = mTabLayout.getTabAt(2);
        three.setText("未评价订单");
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),msgActivity.class));
            }
        });
        return view;
    }
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = android.support.v4.app.Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
