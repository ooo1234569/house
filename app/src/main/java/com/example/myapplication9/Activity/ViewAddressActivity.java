package com.example.myapplication9.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication9.Address;
import com.example.myapplication9.Dizhi;
import com.example.myapplication9.GetAddressTask;
import com.example.myapplication9.Person;
import com.example.myapplication9.R;
import com.example.myapplication9.SpaceHeader;
import com.example.myapplication9.adapter.AddressAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by bingnanfeng02 on 2017/7/26.
 */
public class ViewAddressActivity extends BackActivity {
    private ListView listView;
    private PtrFrameLayout mPtrFrame;
    private RelativeLayout relativeLayout;
    private List<Address> dizhis;
    private RelativeLayout rl0;
    private TextView none;
    private Handler handler= new Handler() {
        @Override
        public void handleMessage(Message msg_main) {
            switch(msg_main.what){
                default:
                    break;
                case 1:
                    dizhis=(List< Address>)msg_main.obj;
                    addressAdapter=new AddressAdapter(ViewAddressActivity.this,R.layout.item_add_address,dizhis);
                    listView.setAdapter(addressAdapter);
                    listView.setDividerHeight(0);
                    break;
                case 2:
                    Toast.makeText(ViewAddressActivity.this,"获取地址列表失败...",Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    none.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    private AddressAdapter addressAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initback("常用地址");
        listView=(ListView)findViewById(R.id.lv);
        relativeLayout=(RelativeLayout)findViewById(R.id.rl);
        relativeLayout=(RelativeLayout)findViewById(R.id.rl0);
        none=(TextView)findViewById(R.id.none);
        mPtrFrame=(PtrFrameLayout)findViewById(R.id.store_house_ptr_frame);
       initdata();
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(ViewAddressActivity.this, AddAddressActivity.class));
            }
        });
        ZhuyeRefresh();
    }
    void initdata(){
        GetAddressTask getAddressTask=new GetAddressTask(DataSupport.find(Person.class,DataSupport.count(Person.class)).getId2(),handler);
        getAddressTask.execute();
    }
    private void ZhuyeRefresh() {
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        mPtrFrame.setPullToRefresh(false);
        mPtrFrame.setKeepHeaderWhenRefresh(false);
        SpaceHeader header = new SpaceHeader(this);
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
    protected void onResume(){
        super.onResume();
        initdata();
        if (addressAdapter!=null){
            addressAdapter.notifyDataSetChanged();
        }

    }

}
