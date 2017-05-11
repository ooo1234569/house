package com.example.myapplication9;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

;

/**
 * Created by 炳楠 on 2017/2/24.
 */
public class Pager extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"tab1","tab2","tab3"};
    private Context context;
    private Nullfg nullfg;
    private Nullfg2 nullfg2;
    private Nullfg3 nullfg3;

    public Pager(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
         nullfg =new Nullfg();
        nullfg2=new Nullfg2();
        nullfg3=new Nullfg3();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return nullfg;
            case 1:
                return nullfg2;
            case 2:
                return nullfg3;
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
