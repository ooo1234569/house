package com.example.myapplication9.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication9.Msgitem;
import com.example.myapplication9.R;
import com.example.myapplication9.Rvmsg;
import com.example.myapplication9.ZhuyeHeader;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by fengbingnan on 2017/7/13.
 */

public class MessageFragment extends Fragment {
    private TextView weidu;
    private RecyclerView  recyclerView;
    private ArrayList<Msgitem> msgitems;
    private Rvmsg rvmsg;
    private PtrFrameLayout mPtrFrame;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        weidu=(TextView)view.findViewById(R.id.weidu);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv);
        mPtrFrame =(PtrFrameLayout)view.findViewById(R.id.store_house_ptr_frame);
        MsgRefresh();
        msgitems=new ArrayList<>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
         for (int i=0;i<4;i++){
             Msgitem msgitem=new Msgitem();
             msgitem.time="17/4/30";
             msgitem.more="hi";
             msgitem.name="Bingnan";
             msgitems.add(msgitem);
         }
         rvmsg=new Rvmsg(msgitems,getContext());
         recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvmsg);
        return view;
    }
    private void MsgRefresh() {
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        mPtrFrame.setPullToRefresh(false);
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        ZhuyeHeader header = new ZhuyeHeader(getContext());
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);
        mPtrFrame.disableWhenHorizontalMove(true);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 1800);
            }
        });
    }
}
