package com.example.myapplication9.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myapplication9.fragment.*;


/**
 * Created by bingnanfeng02 on 2017/7/29.
 */
public class BussinessDetailAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"tab1","tab2","tab3"};
    private Context context;
    private GoodsFragment fuwuxiangmufg;
    private BusinessDetailFragment shangjiajianjiefg;
    private CommentFragment commentfg;

    public BussinessDetailAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        fuwuxiangmufg =new GoodsFragment();
        shangjiajianjiefg=new BusinessDetailFragment();
        commentfg=new CommentFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return fuwuxiangmufg;
            case 1:
                return shangjiajianjiefg;
            case 2:
                return commentfg;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
