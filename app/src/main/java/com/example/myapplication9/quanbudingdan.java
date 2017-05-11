package com.example.myapplication9;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.lang.reflect.Field;

/**
 * Created by 炳楠 on 2017/4/27.
 */
public class quanbudingdan extends Fragment{
        private ListView listView;
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view=inflater.inflate(R.layout.quanbudingdan,container,false);
            listView=(ListView)view.findViewById(R.id.listView2);
            //listView.setAdapter(new dingdanadpter(getContext()));
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
