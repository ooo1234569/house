package com.example.myapplication9.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication9.R;
import com.example.myapplication9.Shangjia;
import com.example.myapplication9.ZhuyeHeader;
import com.example.myapplication9.adapter.HomepageAdapter;
import com.example.myapplication9.adapter.SearchResultAdapter;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import pw.h57.popupbuttonlibrary.PopupButton;
import pw.h57.popupbuttonlibrary.adapter.PopupAdapter;

public class SearchResultActivity extends BackActivity {
    private RecyclerView recyclerView;
    private SearchResultAdapter rvadapter2;
    private PopupButton btn;
    private String fuwu;
    private PtrFrameLayout mPtrFrame;
    private ArrayList<Shangjia> shangjias=new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
       Intent intent=getIntent();
        fuwu=intent.getStringExtra("yemian");
        initback(fuwu);
        btn = (PopupButton) findViewById(R.id.btn);
        mPtrFrame=(PtrFrameLayout)findViewById(R.id.store_house_ptr_frame) ;
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        initdata();
        ZhuyeRefresh();
        rvadapter2=new SearchResultAdapter(shangjias,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvadapter2);
        initfl();
    }
    public void initdata(){
        for (int i=0;i<25;i++){
            Shangjia shangjia=new Shangjia();
            shangjia.name="服务"+i;
            shangjia.juli=40+i+"";
            shangjia.xiaoliang=666+i+"";
            shangjia.shuoming="服务说明服务说明服务说明"+i;
            shangjias.add(shangjia);
        }
    }
    private void initfl(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.shaixuanlistview, null);
        ListView listView = (ListView) view.findViewById(R.id.listview);
        final String[] arr = {"item00", "item02", "item03", "item04", "item05", "item06", "item07", "item08", "item09", "item10"};
        final PopupAdapter adapter = new PopupAdapter(this, R.layout.popup_item2 , arr, R.drawable.jiantou, R.drawable.jinru);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                smoothMoveToPosition(recyclerView,0);
                adapter.setPressPostion(position);
                btn.setText(arr[position]);
                btn.hidePopup();
            }
        });
        btn.setPopupView(view);
        WindowManager manager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.la);
        LinearLayout.LayoutParams inputlParams = (LinearLayout.LayoutParams)linearLayout.getLayoutParams();
        inputlParams.width = display.getWidth();
        linearLayout.setLayoutParams(inputlParams);
    }
    private void ZhuyeRefresh() {
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        mPtrFrame.setPullToRefresh(false);
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        ZhuyeHeader header = new ZhuyeHeader(this);
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);
        mPtrFrame.disableWhenHorizontalMove(true);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 1800);
            }
        });
    }
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));

        if (position < firstItem) {
            // 如果跳转位置在第一个可见位置之前，就smoothScrollToPosition可以直接跳转
            mRecyclerView.smoothScrollToPosition(position);
            Log.d("dshfjs",1+"");
        } else if (position <= lastItem) {
            // 跳转位置在第一个可见项之后，最后一个可见项之前
            // smoothScrollToPosition根本不会动，此时调用smoothScrollBy来滑动到指定位置
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
                Log.d("dshfjs",2+"");
            }
            Log.d("dshfjs",3+"");
        }else {
            // 如果要跳转的位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);
            Log.d("dshfjs",3+"");
//            mToPosition = position;
//            mShouldScroll = true;
        }
    }
}
