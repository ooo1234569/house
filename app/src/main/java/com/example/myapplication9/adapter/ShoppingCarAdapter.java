package com.example.myapplication9.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;
import com.example.myapplication9.ShoppingCar;
import com.example.myapplication9.ShoppingCarSelectListener;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/8/29.
 */
public class ShoppingCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    ArrayList<com.example.myapplication9.ShoppingCar> shoppingCars;
    ShoppingCarSelectListener p;
    public ShoppingCarAdapter(ArrayList<com.example.myapplication9.ShoppingCar> shoppingCars){
         this.shoppingCars=shoppingCars;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_car,parent,false);
        final ShoppingCar shoppingCar=new ShoppingCar(view);
        shoppingCar.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=shoppingCar.getAdapterPosition();
                if(!shoppingCars.get(i).isSelect()){
                    shoppingCar.select.setBackgroundResource(R.drawable.yuan3);
                    shoppingCars.get(i).setSelect(true);
                    p.serviceselect(i,true);
                }else {
                    shoppingCar.select.setBackgroundResource(R.drawable.yuan2);
                    shoppingCars.get(i).setSelect(false);
                    p.serviceselect(i,false);
                }
            }
        });
        return shoppingCar;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        com.example.myapplication9.ShoppingCar shoppingCar=shoppingCars.get(position);
        if(!shoppingCar.isSelect()){
            ((ShoppingCar)holder).select.setBackgroundResource(R.drawable.yuan2);
        }else {
            ((ShoppingCar)holder).select.setBackgroundResource(R.drawable.yuan3);
        }
        ((ShoppingCar)holder).name.setText(shoppingCar.getName());
        ((ShoppingCar)holder).num.setText(shoppingCar.getNum());
        ((ShoppingCar)holder).pay.setText(shoppingCar.getPay());
        ((ShoppingCar)holder).time.setText(shoppingCar.getTime());
    }

    @Override
    public int getItemCount() {
        return shoppingCars.size();
    }
    class ShoppingCar extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView num;
        private TextView pay;
        private TextView time;
        private ImageView select;
        private FrameLayout frameLayout;
        public ShoppingCar(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            num=(TextView)itemView.findViewById(R.id.num);
            pay=(TextView)itemView.findViewById(R.id.pay);
            time=(TextView)itemView.findViewById(R.id.time);
            select=(ImageView)itemView.findViewById(R.id.select_img);
            frameLayout=(FrameLayout)itemView.findViewById(R.id.select);
        }
    }
    public void setP(ShoppingCarSelectListener p){
         this.p=p;
    }
}
