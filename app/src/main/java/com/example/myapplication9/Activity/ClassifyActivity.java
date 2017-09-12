package com.example.myapplication9.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication9.BigService;
import com.example.myapplication9.Classify;
import com.example.myapplication9.GetServiceTypeTask;
import com.example.myapplication9.Person;
import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;
import com.example.myapplication9.adapter.ListOneAdapter;
import com.example.myapplication9.adapter.ListTwoAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingnanfeng02 on 2017/8/4.
 */
public class ClassifyActivity extends BackActivity implements PositionListener {
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private LinearLayoutManager linearLayoutManager1;
    private LinearLayoutManager linearLayoutManager2;
    private ListOneAdapter listOneAdapter;
    private ListTwoAdapter listTwoAdapter;
    private int flag=0;
    private Classify classify=new Classify();
    private List<BigService> bigServiceList;
    private static boolean isLoading=false;
    Integer[] images = {
            R.drawable.fix, R.drawable.jiaoxue,
            R.drawable.banjia, R.drawable.anzhuang,
            R.drawable.qingjie, R.drawable.kuaiji, R.drawable.jianzhu, R.drawable.qita,R.drawable.fix, R.drawable.jiaoxue,
    R.drawable.banjia, R.drawable.anzhuang,
    R.drawable.qingjie, R.drawable.kuaiji
    };
    private Handler handler= new Handler() {
        @Override
        public void handleMessage(Message msg_main) {
            switch(msg_main.what){
                default:
                    System.out.println("打印默认值");
                    break;
                case 1:
                    bigServiceList=(List)msg_main.obj;
                    initdata();
                    break;
                case 2:
                    Toast.makeText(ClassifyActivity.this,"访问服务器错误",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        initback("分类");
        recyclerView1=(RecyclerView)findViewById(R.id.rv1);
        recyclerView2=(RecyclerView)findViewById(R.id.rv2);
        linearLayoutManager1=new LinearLayoutManager(this);
        linearLayoutManager2=new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        GetServiceTypeTask getServiceTypeTask=new GetServiceTypeTask(handler);
        getServiceTypeTask.execute();
//        arrayList.add("家电维修");
//        arrayList.add("维护服务");
//        arrayList.add("房屋加建");
//        arrayList.add("机场接送");
//        arrayList.add("搬家服务");
//        arrayList.add("卫星安装");
//        arrayList.add("清洁服务");
//        arrayList.add("花园割草");
//        arrayList.add("教学");
//        arrayList.add("会计服务");
//        arrayList.add("门窗维修");
//        arrayList.add("视频监控");
//        arrayList.add("水管道");
//        arrayList.add("房子除湿");


    }
    void initdata(){
        classify.strings=bigServiceList;
        classify.p=0;
        listOneAdapter=new ListOneAdapter(classify,this);
        listOneAdapter.setP(this);
        listTwoAdapter=new ListTwoAdapter(bigServiceList,this);
        recyclerView1.setAdapter(listOneAdapter);
        recyclerView2.setAdapter(listTwoAdapter);
        recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==0){
                    isLoading=false;
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getAdapter() != null && recyclerView.getLayoutManager() != null&&!isLoading) {
                    classify.p = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    if (classify.p!=flag){
                        listOneAdapter.notifyDataSetChanged();
                        if (((LinearLayoutManager) recyclerView1.getLayoutManager()).findFirstVisibleItemPosition()>classify.p||((LinearLayoutManager) recyclerView1.getLayoutManager()).findLastVisibleItemPosition()<=classify.p){
                            smoothMoveToPosition(recyclerView1,classify.p);
                        }
                        flag=classify.p;
                    }
                }
            }
        });
    }

    @Override
    public void onArticleSelected(int position) {
        classify.p=position;
        if (classify.p!=flag){
            isLoading=true;
            listOneAdapter.notifyDataSetChanged();
            flag=classify.p;
        }
        smoothMoveToPosition(recyclerView2,position);
    }
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));

        if (position < firstItem) {
            // 如果跳转位置在第一个可见位置之前，就smoothScrollToPosition可以直接跳转
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在第一个可见项之后，最后一个可见项之前
            // smoothScrollToPosition根本不会动，此时调用smoothScrollBy来滑动到指定位置
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        }else {
            // 如果要跳转的位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);
//            mToPosition = position;
//            mShouldScroll = true;
        }
    }
}
