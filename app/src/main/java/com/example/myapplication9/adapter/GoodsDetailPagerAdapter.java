package com.example.myapplication9.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myapplication9.fragment.AllOrderFragment;
import com.example.myapplication9.fragment.GoodsDetailFragment;
import com.example.myapplication9.fragment.Nullfg;
import com.example.myapplication9.fragment.Nullfg2;
import com.example.myapplication9.fragment.Nullfg3;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/8/4.
 */
public class GoodsDetailPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"tab1","tab2","tab3"};
    private Context context;
    private GoodsDetailFragment fuwudetail;
    private Nullfg2 nullfg2;
    private Nullfg3 nullfg3;
    private ArrayList<Fragment> fragments;
    public GoodsDetailPagerAdapter(FragmentManager fm, Context context, ArrayList<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragments=fragments;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
