package com.example.myapplication9;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.myapplication9.adapter.SelectAdapter;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/8/19.
 */
public class SelectView extends PopupWindow {
    private Context context;
    private PositionListener positionListener;
    private ArrayList<String> arrayList=new ArrayList<>();
    public  void setP(PositionListener positionListener){
        this.positionListener=positionListener;
    }
    public SelectView(Context context){
        this.context=context;

    }
    public void setview(){
        arrayList.add("拍照");
        arrayList.add("相册");
        final View view1= LayoutInflater.from(context).inflate(R.layout.view_select, null);
        RecyclerView recyclerView=(RecyclerView)view1.findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        SelectAdapter selectAdapter=new SelectAdapter(context,arrayList);
        selectAdapter.setPositionListener(positionListener);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(selectAdapter);
        view1.setOnTouchListener(new View.OnTouchListener() {// 如果触摸位置在窗口外面则销毁

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int height = view1.findViewById(R.id.rv).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        this.setContentView(view1);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
    }
    public void dismiss() {
        super.dismiss();
        positionListener.onArticleSelected(1);
    }
}
