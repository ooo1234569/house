package com.example.myapplication9.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication9.R;
import com.example.myapplication9.ShoppingCar;
import com.example.myapplication9.adapter.ShoppingCarAdapter;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/8/29.
 */
public class ShoppingCarActivity extends BackActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ShoppingCarAdapter shoppingCarAdapter;
    private ArrayList<ShoppingCar> shoppingCars=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        linearLayoutManager=new LinearLayoutManager(this);
        initdata();
        shoppingCarAdapter=new ShoppingCarAdapter(shoppingCars);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shoppingCarAdapter);
    }
    void initdata(){
        for (int i=0;i<5;i++){
            ShoppingCar shoppingCar =new ShoppingCar();
            shoppingCar.setName("服务1"+i);
            shoppingCar.setNum("x1");
            shoppingCar.setPay("52.6"+i);
            shoppingCar.setTime("9月1日 9:00"+i);
            shoppingCars.add(shoppingCar);
        }
    }
}

