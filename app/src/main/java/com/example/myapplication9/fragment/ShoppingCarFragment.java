package com.example.myapplication9.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;
import com.example.myapplication9.ShoppingCar;
import com.example.myapplication9.ShoppingCarSelectListener;
import com.example.myapplication9.adapter.ShoppingCarAdapter;
import com.hp.hpl.sparta.Text;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/9/5.
 */

public class ShoppingCarFragment extends Fragment implements ShoppingCarSelectListener{
    private RecyclerView recyclerView;
    private RelativeLayout pay;
    private LinearLayoutManager linearLayoutManager;
    private ShoppingCarAdapter shoppingCarAdapter;
    private ArrayList<ShoppingCar> shoppingCars=new ArrayList<>();
    private ImageView quanxuan;
    private int flag=0;
    private int num=0;
    private TextView howmuch;
    private TextView servicesnum;
    private float howmuch_float=0;
    private RelativeLayout rl;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_shopping_car,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv);
        quanxuan=(ImageView)view.findViewById(R.id.quanxuan);
        pay=(RelativeLayout)view.findViewById(R.id.pay);
        howmuch=(TextView)view.findViewById(R.id.howmuch);
        servicesnum=(TextView)view.findViewById(R.id.servicesnum);
        howmuch=(TextView)view.findViewById(R.id.howmuch);
        rl=(RelativeLayout)view.findViewById(R.id.rl);
        linearLayoutManager=new LinearLayoutManager(getContext());
        initdata();
        shoppingCarAdapter=new ShoppingCarAdapter(shoppingCars);
        shoppingCarAdapter.setP(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shoppingCarAdapter);
        quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0){
                    quanxuan.setBackgroundResource(R.drawable.yuan3);
                    flag=1;
                    howmuch_float=0;
                    update(true);
                    transform();
                    howmuch.setText("¥"+howmuch_float+"");
                    num=shoppingCars.size();
                    servicesnum.setText("结算("+num+")");
                }else {
                    quanxuan.setBackgroundResource(R.drawable.yuan2);
                    flag=0;
                    update(false);
                    num=0;
                    howmuch_float=0;
                    howmuch.setText(howmuch_float+"");
                    servicesnum.setText("结算("+num+")");
                }
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num==0){
                    Toast.makeText(getContext(),"你还没选择任何服务哦",Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
    void transform(){
        howmuch_float=(float)(Math.round(howmuch_float*10))/10;
    }
    void initdata(){
        for (int i=0;i<5;i++){
            ShoppingCar shoppingCar =new ShoppingCar();
            shoppingCar.setName("服务1"+i);
            shoppingCar.setNum("x1");
            shoppingCar.setPay("52.6");
            shoppingCar.setTime("9月1日 9:00"+i);
            shoppingCars.add(shoppingCar);
        }
    }
    void update(boolean p){
        for (int i=0;i<5;i++){
            shoppingCars.get(i).setSelect(p);
            if(p==true){
                howmuch_float+=Float.valueOf(shoppingCars.get(i).getPay());
            }
        }
        shoppingCarAdapter.notifyDataSetChanged();
    }
    void updatenum(){

    }
    @Override
    public void serviceselect(int position, boolean s) {
        if(s){
            num++;
            howmuch_float+=Float.valueOf(shoppingCars.get(position).getPay());
        }else {
            num--;
            howmuch_float-=Float.valueOf(shoppingCars.get(position).getPay());
        }
        transform();
        howmuch.setText("¥"+howmuch_float+"");
        servicesnum.setText("结算("+num+")");
    }
}
