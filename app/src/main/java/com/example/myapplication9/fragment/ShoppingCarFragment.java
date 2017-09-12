package com.example.myapplication9.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication9.MyApplication;
import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;
import com.example.myapplication9.Service;
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
    private ArrayList<Service> shoppingCars=new ArrayList<>();
    private ImageView quanxuan;
    private int flag=0;
    private int num=0;
    private TextView howmuch;
    private TextView servicesnum;
    private float howmuch_float=0;
    private RelativeLayout rl;
    private TextView deleteall;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_shopping_car,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv);
        quanxuan=(ImageView)view.findViewById(R.id.quanxuan);
        pay=(RelativeLayout)view.findViewById(R.id.pay);
        howmuch=(TextView)view.findViewById(R.id.howmuch);
        servicesnum=(TextView)view.findViewById(R.id.servicesnum);
        howmuch=(TextView)view.findViewById(R.id.howmuch);
        rl=(RelativeLayout)view.findViewById(R.id.rl);
        deleteall=(TextView)view.findViewById(R.id.deleteall) ;
        linearLayoutManager=new LinearLayoutManager(getContext());
        initdata();
        shoppingCarAdapter=new ShoppingCarAdapter(shoppingCars,getContext());
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
        deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog =new AlertDialog.Builder(getContext());
                dialog.setMessage("确定清空购物车？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        shoppingCars.clear();
                        shoppingCarAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(),"清空完成",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initdata();
        shoppingCarAdapter.notifyDataSetChanged();
    }

    void transform(){
        howmuch_float=(float)(Math.round(howmuch_float*10))/10;
    }
    void initdata(){
        shoppingCars=((MyApplication)getActivity().getApplication()).services;
    }
    void update(boolean p){
        for (int i=0;i<shoppingCars.size();i++){
            shoppingCars.get(i).setSelect(p);
            if(p==true){
                howmuch_float+=Float.valueOf(shoppingCars.get(i).total);
            }
        }
        shoppingCarAdapter.notifyDataSetChanged();
    }
    @Override
    public void serviceselect(int position, boolean s) {
        if(s){
            num++;
            howmuch_float+=Float.valueOf(shoppingCars.get(position).total);
        }else {
            num--;
            howmuch_float-=Float.valueOf(shoppingCars.get(position).total);
        }
        transform();
        howmuch.setText("¥"+howmuch_float+"");
        servicesnum.setText("结算("+num+")");
    }
}
