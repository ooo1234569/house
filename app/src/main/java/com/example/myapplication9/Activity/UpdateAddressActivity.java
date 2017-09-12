package com.example.myapplication9.Activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication9.R;
import com.example.myapplication9.TextListener;
import com.example.myapplication9.UpdateAddressTask;
import com.example.myapplication9.adapter.AddNewAdressAdpater;


import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by bingnanfeng02 on 2017/8/21.
 */
public class UpdateAddressActivity extends AppCompatActivity implements TextListener{
    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TextView textView;
    private ImageView imageView;
    private String id;
    private String[] strings;
    private int flag=0;
    private int flag2=0;
    private String[]strings2={"","","","","","","" };
    private AddNewAdressAdpater addNewAdressAdpater;
    private String[]json={"contacts","phone","province","city","district","road","detailed"};
    private String[]head ={"联系人","联系电话","省","市","区","路","详细地址"};
    private Handler handler= new Handler() {
        @Override
        public void handleMessage(Message msg_main) {
            switch(msg_main.what){
                default:
                    break;
                case 1:
                    Toast.makeText(UpdateAddressActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case 2:
                    Toast.makeText(UpdateAddressActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        id=getIntent().getStringExtra("id");
        strings=getIntent().getStringArrayExtra("address");
        relativeLayout=(RelativeLayout)findViewById(R.id.rl);
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        imageView=(ImageView)findViewById(R.id.back);
        textView=(TextView)findViewById(R.id.done);
        setcan();
        addNewAdressAdpater=new AddNewAdressAdpater(head,strings,this,head);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(addNewAdressAdpater);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0){
                    try {
                        JSONObject jsonObject=new JSONObject();
                        jsonObject.put("id",id);
                        jsonObject.put("idDefault",true);
                        for(int o=0;o<7;o++){
                            if(!strings2[o].equals("")){
                                jsonObject.put(json[o],strings2[o]);
                            }
                        }
                        UpdateAddressTask updateAddressTask=new UpdateAddressTask(jsonObject,handler);
                        updateAddressTask.execute();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2==1){
                    ask();
                }
            }
        });
    }

    @Override
    public void onArticleSelected(int position, String s, int tag) {
            if(position==1){
                flag--;
                strings2[tag]=s;
                flag2=1;
            }else if(position==0){
                flag++;
                strings2[tag]=s;
                flag2=1;
            }else if(position==3){
                strings2[tag]=s;
                flag2=1;
            }
        //flag2=1;
        //strings2[tag]=s;
        for(int p=0;p<7;p++){
            Log.d("test",strings2[p]);
        }
        Log.d("change",position+"");
        if(flag!=0){
            setnotcant();
        }else {
            setcan();
        }
    }
    public  void setcan(){
        relativeLayout.setBackgroundColor(Color.parseColor("#71aaff"));
        textView.setTextColor(Color.parseColor("#ffffff"));
    }
    public void setnotcant(){
        relativeLayout.setBackgroundColor(Color.parseColor("#e5e5e5"));
        textView.setTextColor(Color.parseColor("#acacac"));
    }
    public void ask(){
        AlertDialog.Builder dialog =new AlertDialog.Builder(UpdateAddressActivity.this);
        dialog.setMessage("修改的信息尚未保存，确定现在返回吗？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if (flag2==1){
                ask();
            }else {
                finish();
            }
        }
        return true;
    }
}
