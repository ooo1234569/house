package com.example.myapplication9.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication9.AddAddressTask;
import com.example.myapplication9.Address;
import com.example.myapplication9.Person;
import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;
import com.example.myapplication9.TextListener;
import com.example.myapplication9.adapter.AddNewAdressAdpater;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import okhttp3.MediaType;

/**
 * Created by bingnanfeng02 on 2017/7/27.
 */
public class AddAddressActivity extends AppCompatActivity implements TextListener{

    private RecyclerView recyclerView;
    private TextView done;
    private RelativeLayout relativeLayout;
    private LinearLayoutManager linearLayoutManager;
    private AddNewAdressAdpater addNewAdressAdpater;
    private int flag=0;
    private String[]strings ={"联系人","联系电话","省","市","区","路","详细地址"};
    private String[]strings2={"","","","","","","" };
    private String[]json={"userId","isDefault","contacts","phone","province","city","district","road","detailed"};
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl);
        done = (TextView) findViewById(R.id.done);
        linearLayoutManager = new LinearLayoutManager(this);
        addNewAdressAdpater = new AddNewAdressAdpater(strings, this, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(addNewAdressAdpater);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 7) {
                    JSONObject jsonObj = new JSONObject();
                    try {
                        jsonObj.put("userId", DataSupport.find(Person.class, DataSupport.count(Person.class)).getId2());
                        jsonObj.put("isDefault", false);
                        for (int i = 0; i < 7; i++) {
                            jsonObj.put(json[i + 2], strings2[i]);
                        }
                        AddAddressTask addAddressTask = new AddAddressTask(jsonObj, handler);
                        addAddressTask.execute();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        handler= new Handler() {
            @Override
            public void handleMessage(Message msg_main) {
                switch(msg_main.what){
                    default:
                        break;
                    case 1:
                        Toast.makeText(AddAddressActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case 2:
                        Toast.makeText(AddAddressActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    @Override
    public void onArticleSelected(int position, String s,int tag) {
        if(position==1){
            flag++;
        }else if(position==0){
            flag--;
        }
        strings2[tag]=s;
        if(flag==7){
            relativeLayout.setBackgroundColor(Color.parseColor("#71aaff"));
            done.setTextColor(Color.parseColor("#ffffff"));
        }else {
            relativeLayout.setBackgroundColor(Color.parseColor("#e5e5e5"));
            done.setTextColor(Color.parseColor("#acacac"));
        }
        Log.d("flag",flag+"");
    }

}
