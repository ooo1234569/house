package com.example.myapplication9.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication9.Activity.ChangeNameActivity;
import com.example.myapplication9.Activity.ChangePhoneActivity;
import com.example.myapplication9.Activity.EditPasswordActivity;
import com.example.myapplication9.Activity.EditProfileActivity;
import com.example.myapplication9.Changephone;
import com.example.myapplication9.Person;
import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;
import com.example.myapplication9.SelectView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/8/1.
 */
public class EditProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<String> strings;
    private Context context;
    private View view;
    private PositionListener positionListener;
    private String[] s;
    public EditProfileAdapter(ArrayList<String> strings, Context context,View view,String[] s){
        this.strings=strings;
        this.context=context;
        this.view=view;
        this.s=s;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile,parent,false);
        final Item item=new Item(view);
        item.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=item.getAdapterPosition();
                switch(position)
                {
                    case 0:
                        positionListener.onArticleSelected(0);
                        setwindow();
                       // Toast.makeText(context,"功能尚未完成，敬请等待",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        context.startActivity(new Intent(context,ChangeNameActivity.class));
                        break;

                    case 2:
                        context.startActivity(new Intent(context,EditPasswordActivity.class));
                        break;

                    case 3:
                        context.startActivity(new Intent(context,ChangePhoneActivity.class));
                        break;
                    default:
                        System.out.println("default");
                        break;
                }
            }
        });
        return item;
    }
    public  void setP(PositionListener positionListener){
        this.positionListener=positionListener;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch(position)
        {
            case 0:
                ((Item)holder).imageView.setVisibility(View.VISIBLE);
                ((Item)holder).textView2.setVisibility(View.GONE);
                break;

            case 1:
                //((Item)holder).textView2.setText(DataSupport.find(Person.class,DataSupport.count(Person.class)).getNickname());
                ((Item)holder).textView2.setText(s[0]);

                break;

            default:

                System.out.println("default");

                break;

        }
        ((Item)holder).textView.setText(strings.get(position));
    }
    @Override
    public int getItemCount() {
        return strings.size();
    }
     class Item extends RecyclerView.ViewHolder {
         ImageView imageView;
         TextView textView;
         TextView textView2;
         RelativeLayout relativeLayout;
         public Item(View itemView) {
             super(itemView);
             imageView=(ImageView)itemView.findViewById(R.id.touxiang);
             textView=(TextView)itemView.findViewById(R.id.text);
             textView2=(TextView)itemView.findViewById(R.id.textView2);
             relativeLayout=(RelativeLayout)itemView.findViewById(R.id.rl);
         }
     }
    private  void setwindow() {
        SelectView selectView = new SelectView(context);
        selectView.setP(positionListener);
        selectView.setview();
        selectView.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
