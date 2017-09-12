package com.example.myapplication9;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.myapplication9.Activity.DateObject;
import com.example.myapplication9.Activity.DetailActivity;
import com.example.myapplication9.Activity.OrderActivity;
import com.example.myapplication9.Activity.SelectTimeActivity;
import com.example.myapplication9.adapter.SelectAdapter;
import com.example.myapplication9.adapter.SelectTimeAdapter;
import com.example.myapplication9.adapter.SelectTimeGridViewAdapter;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/9/11.
 */

public class SelectSpecificationView  extends PopupWindow  implements PositionListener{
    private Context context;
    private PositionListener positionListener;
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
    Service service;
    EditText num;
    public  void setP(PositionListener positionListener){
        this.positionListener=positionListener;
    }
    public SelectSpecificationView(Context context,int flag){
        this.context=context;
        this.flag=flag;
        service=new Service();
    }
    public void setview(){
        final View view1= LayoutInflater.from(context).inflate(R.layout.popuoview_select_time, null);
        date=(RecyclerView)view1.findViewById(R.id.rv);
        sure=(FrameLayout)view1.findViewById(R.id.sure);
        num=(EditText)view1.findViewById(R.id.num);
        date2=(GridView)view1.findViewById(R.id.gv);
        linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        initdata();
        timeObject.times=arrayList;
        dateObject.strings=dates;
        selectTimeAdapter=new SelectTimeAdapter(timeObject,context);
        selectTimeGridViewAdapter=new SelectTimeGridViewAdapter(context,dateObject);
        selectTimeAdapter.setp(this);
        date.setAdapter(selectTimeAdapter);
        date2.setAdapter(selectTimeGridViewAdapter);
        date.setLayoutManager(linearLayoutManager);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1){
                    Intent intent=new Intent(context,OrderActivity.class);
                    service.num=Integer.valueOf(num.getText().toString());
                    service.date=arrayList.get(timeObject.position).date;
                    service.months=arrayList.get(timeObject.position).months;
                    service.time=dates.get(dateObject.p);
                    service.howmuch=30;
                    service.total=service.howmuch*service.num;
                    ArrayList<Service> services=new ArrayList<>();
                    services.add(service);
                    intent.putExtra("service",services);
                    context.startActivity(intent);
                }else {
                    dismiss();
                    Toast.makeText(context,"加入购物车成功",Toast.LENGTH_SHORT).show();
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
        this.setContentView(view1);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        this.setHeight(wm.getDefaultDisplay().getHeight() * 7 / 10);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.mypopwindow_anim_style2);
    }
    public void dismiss() {
        super.dismiss();
        positionListener.onArticleSelected(1);
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
