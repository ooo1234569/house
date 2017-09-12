package com.example.myapplication9.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication9.R;
import com.example.myapplication9.Shangjia;
import com.example.myapplication9.adapter.CollectionAdapter;

import java.util.ArrayList;


/**
 * Created by bingnanfeng02 on 2017/8/1.
 */
public class CollectionActivity extends BackActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Shangjia> shangjias=new ArrayList<>();
    private CollectionAdapter shoucangadapter;
    private TextView textView;
    private int p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        p=getIntent().getIntExtra("which",1);
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        if (p==1){
            initback("收藏");
        }else {
            initback("历史记录");
        }
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        initdata();
        shoucangadapter=new CollectionAdapter(shangjias,this);
        recyclerView.setAdapter(shoucangadapter);
    }
    public void initdata(){
        for (int i=0;i<6;i++){
            Shangjia shangjia=new Shangjia();
            shangjia.name="好记周庄"+i;
            shangjia.juli=40+i+"";
            shangjia.xiaoliang=666+i+"";
            shangjia.shuoming="xxx"+i;
            shangjias.add(shangjia);
        }
    }
}
