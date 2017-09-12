package com.example.myapplication9.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;
import com.example.myapplication9.Time;
import com.example.myapplication9.TimeObject;
import com.example.myapplication9.adapter.SelectTimeAdapter;
import com.example.myapplication9.adapter.SelectTimeGridViewAdapter;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/9/7.
 */

public class SelectTimeActivity  extends BackActivity implements PositionListener{
    RecyclerView date;
    GridView date2;
    LinearLayoutManager linearLayoutManager;
    SelectTimeAdapter selectTimeAdapter;
    SelectTimeGridViewAdapter selectTimeGridViewAdapter;
    ArrayList<Time> arrayList=new ArrayList<>();
    ArrayList<String> dates=new ArrayList<>();
    String[] strings={"周一","周二","周三","周四","周五","周六","周日"};
    FrameLayout sure;
    TimeObject timeObject=new TimeObject();
    DateObject dateObject=new DateObject();
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);
        flag=getIntent().getIntExtra("flag",1);
        initback("选择时间");
        date=(RecyclerView)findViewById(R.id.rv);
        sure=(FrameLayout)findViewById(R.id.sure);
        date2=(GridView)findViewById(R.id.gv);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        initdata();
        timeObject.times=arrayList;
        dateObject.strings=dates;
        selectTimeAdapter=new SelectTimeAdapter(timeObject,this);
        selectTimeGridViewAdapter=new SelectTimeGridViewAdapter(this,dateObject);
        selectTimeAdapter.setp(this);
        date.setAdapter(selectTimeAdapter);
        date2.setAdapter(selectTimeGridViewAdapter);
        date.setLayoutManager(linearLayoutManager);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0){
                    finish();
                }else {
                    startActivity(new Intent(SelectTimeActivity.this,OrderActivity.class));
                }
            }
        });
        date2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dateObject.p=position;
                selectTimeGridViewAdapter.notifyDataSetChanged();
            }
        });
    }
    void initdata(){
        int temp;
        int temp2=9;
        for(int i=0;i<10;i++){
            Time time=new Time();
            temp=7+i;
            if(temp>=10){
                time.date="09-"+temp;
            }else {
                time.date="09-"+"0"+temp;
            }
            time.months=strings[(i+3)%7];
            arrayList.add(time);
        }
        for(int i=0;i<9;i++){
            dates.add(i+9+":00");
            dates.add(i+9+":30");
        }
    }

    @Override
    public void onArticleSelected(int position) {
        timeObject.position=position;
        selectTimeAdapter.notifyDataSetChanged();
    }
}
