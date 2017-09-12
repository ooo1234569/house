package com.example.myapplication9.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication9.R;
import com.example.myapplication9.Service;
import com.example.myapplication9.adapter.SureToOrderAdapter;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/7/31.
 */
public class OrderActivity extends BackActivity {
     private RecyclerView recyclerView;
     private LinearLayoutManager linearLayoutManager;
    private SureToOrderAdapter multiTypeAdapter;
    private RelativeLayout relativeLayout;
    private ArrayList<Service> services;
    private TextView total;
    private float total_price=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        initback("确认下单");
        services= (ArrayList<Service>) getIntent().getSerializableExtra("service");
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        total=(TextView)findViewById(R.id.total) ;
        relativeLayout=(RelativeLayout)findViewById(R.id.payinstance);
        linearLayoutManager=new LinearLayoutManager(this);
        multiTypeAdapter=new SureToOrderAdapter(services);
        recyclerView.setLayoutManager(linearLayoutManager);
        initaddress();
        initgoods();
        initpayment();
        recyclerView.setAdapter(multiTypeAdapter);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderActivity.this,SubmitOrderActivity.class));
            }
        });
        Calculate_the_total_price();
        total.setText(total_price+"元");
    }
    void initaddress(){
        View address=View.inflate(this, R.layout.view_item_address_from_order, null);
        multiTypeAdapter.addView(address);
    }
    void initgoods(){
        multiTypeAdapter.addRepeatView(2,services.size(),R.layout.view_item_goodsdetail_from_order);
    }
    void initpayment(){
        View detail=View.inflate(this, R.layout.view_payment, null);
        multiTypeAdapter.addView(detail);
    }
    void Calculate_the_total_price(){
        for (int i=0;i<services.size();i++){
            total_price+=services.get(i).total;
        }
    }
}
