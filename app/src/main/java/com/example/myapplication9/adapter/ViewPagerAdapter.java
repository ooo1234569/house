package com.example.myapplication9.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



;import com.example.myapplication9.fragment.AllOrderFragment;
import com.example.myapplication9.fragment.Nullfg;
import com.example.myapplication9.fragment.Nullfg2;
import com.example.myapplication9.fragment.Nullfg3;

/**
 * Created by 炳楠 on 2017/2/24.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"tab1","tab2","tab3"};
    private Context context;
    private AllOrderFragment dingdanAll;
    private Nullfg nullfg;
    private Nullfg2 nullfg2;
    private Nullfg3 nullfg3;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
         nullfg =new Nullfg();
        nullfg2=new Nullfg2();
        nullfg3=new Nullfg3();
        dingdanAll=new AllOrderFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return dingdanAll;
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
