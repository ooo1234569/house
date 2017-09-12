package com.example.myapplication9.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication9.Activity.UpdateAddressActivity;
import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;
import com.example.myapplication9.Service;
import com.example.myapplication9.ShoppingCar;
import com.example.myapplication9.ShoppingCarSelectListener;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/8/29.
 */
public class ShoppingCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    ArrayList<Service> shoppingCars;
    ShoppingCarSelectListener p;
    Context context;
    public ShoppingCarAdapter(ArrayList<Service> shoppingCars, Context context){
         this.shoppingCars=shoppingCars;
        this.context=context;
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
        shoppingCar.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog =new AlertDialog.Builder(context);
                dialog.setMessage("确定删除？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        shoppingCars.remove(shoppingCar.getAdapterPosition());
                        notifyDataSetChanged();
                        Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();

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
        return shoppingCar;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Service service=shoppingCars.get(position);
        if(!service.isSelect()){
            ((ShoppingCar)holder).select.setBackgroundResource(R.drawable.yuan2);
        }else {
            ((ShoppingCar)holder).select.setBackgroundResource(R.drawable.yuan3);
        }
        ((ShoppingCar)holder).name.setText("服务");
        ((ShoppingCar)holder).num.setText(service.num+"");
        ((ShoppingCar)holder).pay.setText(service.howmuch+"");
        ((ShoppingCar)holder).time.setText(service.date+" ("+service.months+")   "+service.time);
        ((ShoppingCar)holder).total.setText(service.total+"");
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
        private TextView total;
        private TextView delete;
        public ShoppingCar(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            num=(TextView)itemView.findViewById(R.id.num);
            pay=(TextView)itemView.findViewById(R.id.pay);
            time=(TextView)itemView.findViewById(R.id.time);
            select=(ImageView)itemView.findViewById(R.id.select_img);
            frameLayout=(FrameLayout)itemView.findViewById(R.id.select);
            total=(TextView)itemView.findViewById(R.id.total);
            delete=(TextView)itemView.findViewById(R.id.delete);
        }
    }
    public void setP(ShoppingCarSelectListener p){
         this.p=p;
    }
}
