package com.example.myapplication9.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication9.Dingdan;
import com.example.myapplication9.R;
import com.example.myapplication9.ZhuyeHeader;
import com.example.myapplication9.adapter.OrderAdapter;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by bingnanfeng02 on 2017/7/24.
 */
public class AllOrderFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private OrderAdapter dingdanrvadapter;
    private PtrFrameLayout mPtrFrame;
    private ArrayList<Dingdan> dingdanArrayList=new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.order,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv);
        mPtrFrame =(PtrFrameLayout)view.findViewById(R.id.store_house_ptr_frame);
        linearLayoutManager=new LinearLayoutManager(getContext());
        initdata();
        DingdanRefresh();
        dingdanrvadapter=new OrderAdapter(dingdanArrayList,getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(dingdanrvadapter);
        return view;
    }
    void initdata(){
        for (int i=0;i<9;i++){
            Dingdan dingdan=new Dingdan();
            dingdan.dingdanname="好记周庄"+i;
            dingdan.goods="单人套餐"+i;
            dingdan.num="x"+i;
            dingdanArrayList.add(dingdan);
        }
    }
    private void DingdanRefresh() {
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
