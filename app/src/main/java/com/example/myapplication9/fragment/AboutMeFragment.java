package com.example.myapplication9.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication9.Person;
import com.example.myapplication9.R;
import com.example.myapplication9.adapter.AboutMeAdpater;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;


/**
 * Created by 炳楠 on 2017/4/22.
 */
public class AboutMeFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AboutMeAdpater wodeadpater;
    private String[] arrayList={"收藏","历史记录","常用地址","支付设置","帮助","关于我们","其他设置"};
    private String[]s={""};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_me, container, false);
        s[0]= DataSupport.find(Person.class,DataSupport.count(Person.class)).getNickname();
        recyclerView=(RecyclerView)view.findViewById(R.id.rv);
        linearLayoutManager=new LinearLayoutManager(getContext());
        wodeadpater=new AboutMeAdpater(arrayList,getContext(),s);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(wodeadpater);
        Log.d("asdasda","creat");
        return view;
    }
    public void onResume(){
        super.onResume();
        s[0]= DataSupport.find(Person.class,DataSupport.count(Person.class)).getNickname();
        wodeadpater.notifyDataSetChanged();
    }

}
