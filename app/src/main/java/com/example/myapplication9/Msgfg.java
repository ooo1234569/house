package com.example.myapplication9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fengbingnan on 2017/7/13.
 */

public class Msgfg extends Fragment {
    private TextView weidu;
    private RecyclerView  recyclerView;
    private ArrayList<Msgitem> msgitems;
    private Rvmsg rvmsg;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msgfg, container, false);
        weidu=(TextView)view.findViewById(R.id.weidu);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv);
        msgitems=new ArrayList<>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
         for (int i=0;i<4;i++){
             Msgitem msgitem=new Msgitem();
             msgitem.time="17/4/30";
             msgitem.more="hi";
             msgitem.name="Bingnan";
             msgitems.add(msgitem);
         }
         rvmsg=new Rvmsg(msgitems);
         recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvmsg);
        return view;
    }
}
