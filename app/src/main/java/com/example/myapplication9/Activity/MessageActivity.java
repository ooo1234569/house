package com.example.myapplication9.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication9.Msg;
import com.example.myapplication9.R;
import com.example.myapplication9.adapter.GridviewAdapter;
import com.example.myapplication9.adapter.MessageAdapter;

import java.util.ArrayList;



public class MessageActivity extends AppCompatActivity {
    private TextView shangjianxinxi;
    private ImageView more;
    private ImageView back;
    private RecyclerView recyclerView;
    private MessageAdapter rvmsgadapter;
    private EditText input;
    private ImageView photo;
    private GridView gridView;
    private Animation animation;
    private int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        shangjianxinxi=(TextView)findViewById(R.id.shangjianame);
        more=(ImageView)findViewById(R.id.more);
        back=(ImageView)findViewById(R.id.back);
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        input=(EditText)findViewById(R.id.input);
        photo=(ImageView)findViewById(R.id.photo);
        gridView=(GridView)findViewById(R.id.gv);
        Glide.with(this).load(R.drawable.plus).into(photo);
        Glide.with(this).load(R.drawable.more).into(more);
        animation=new TranslateAnimation(30.0f, -80.0f, 30.0f, 300.0f);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<Msg> arrayList=new ArrayList<>();
        for(int i=0;i<30;i++){
            Msg msg=new Msg();
            msg.content="你好啊实打实大所大所多";
            msg.leixing=0;
            arrayList.add(msg);
            Msg msg2=new Msg();
            msg2.content="你好啊水电费水电费水电费水电费";
            msg2.leixing=1;
            arrayList.add(msg2);
        }
        rvmsgadapter=new MessageAdapter(arrayList,this);
        recyclerView.setAdapter(rvmsgadapter);
        Log.d("ksjskxj",width+"");
        RelativeLayout.LayoutParams inputlParams = (RelativeLayout.LayoutParams)input.getLayoutParams();
        inputlParams.width=width-(int)(55*metric.density+0.5f);
        input.setLayoutParams(inputlParams);
        final String[] titles=new String[]{
                "照片","视频","位置"
        };
        Integer[] images={
                R.drawable.fix,R.drawable.jiaoxue,
                R.drawable.banjia
        };
        final GridviewAdapter gridviewadpter=new GridviewAdapter(titles,images,this);
        gridView.setAdapter(gridviewadpter);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)gridView.getLayoutParams();
                if (flag==0){
                    lParams.height = 230;// 当控件的高强制设成75象素
                    gridView.setLayoutParams(lParams);
//                    gridView.setVisibility(View.VISIBLE);
                    flag=1;
                }else {
                    lParams.height = 0;// 当控件的高强制设成75象素
                    gridView.setLayoutParams(lParams);
//                    gridView.setVisibility(View.GONE);
                    flag=0;
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MessageActivity.this, MainActivity.class));
            }
        });
    }
}
