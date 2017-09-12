package com.example.myapplication9.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication9.AddOrderTask;
import com.example.myapplication9.Person;
import com.example.myapplication9.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by bingnanfeng02 on 2017/9/2.
 */
public class SubmitOrderActivity extends AppCompatActivity {
    private String[] strings={"userId","userAdressId","serviceIds","startDate","advancePayment","paymentMethod","remarks"};
    private String[] strings2={"1","1","1,2,3","34654615431","40","0","麻烦带瓶酱油"};
    private Handler mhandler= new Handler() {
        @Override
        public void handleMessage(Message msg_main) {
            Log.d("dasdasd","asdad"+msg_main.what);
            switch(msg_main.what){
                default:
                    System.out.println("打印默认值");
                    break;
                case 1:

                    Toast.makeText(SubmitOrderActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case 2:
                    Toast.makeText(SubmitOrderActivity.this,"提交失败",Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(SubmitOrderActivity.this,"服务器错误",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        JSONObject jsonObject=new JSONObject();
        try {
        for(int i=0;i<7;i++){
               if(!strings[i].equals("Date")){
                   jsonObject.put(strings[i],strings2[i]);
               }else{
                   Date date=new Date();
                   jsonObject.put("Date",date.getTime());
               }
        }}
        catch (JSONException e) {
                e.printStackTrace();
        }
        AddOrderTask addOrderTask=new AddOrderTask(jsonObject,mhandler);
        addOrderTask.execute();
    }
}

