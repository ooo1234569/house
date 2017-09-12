package com.example.myapplication9.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication9.ChangeNameTask;
import com.example.myapplication9.Else;
import com.example.myapplication9.Person;
import com.example.myapplication9.R;

import org.litepal.crud.DataSupport;

/**
 * Created by bingnanfeng02 on 2017/8/18.
 */
public class ChangeNameActivity extends BackActivity {
    private EditText newname;
    private RelativeLayout relativeLayout;
    private TextView suretext;
    private int flag=0;
    private Handler mhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        initback("修改昵称");
        newname=(EditText)findViewById(R.id.newname);
        suretext=(TextView)findViewById(R.id.suretext);
        relativeLayout=(RelativeLayout)findViewById(R.id.sure);
        newname.addTextChangedListener(textWatcher);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==1){
                    ChangeNameTask changeNameTask=new ChangeNameTask(newname.getText().toString(), DataSupport.find(Person.class,DataSupport.count(Person.class)).getId2(),mhandler);
                    changeNameTask.execute();
                }
            }
        });
        mhandler= new Handler() {
            @Override
            public void handleMessage(Message msg_main) {
                Log.d("dasdasd","asdad"+msg_main.what);
                switch(msg_main.what){
                    default:
                        System.out.println("打印默认值");
                        break;
                    case 1:
                        Person person=new Person();
                        person.setNickname(newname.getText().toString());
                        person.update(DataSupport.count(Person.class));
                        Toast.makeText(ChangeNameActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case 2:
                        Toast.makeText(ChangeNameActivity.this,"昵称已存在",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(ChangeNameActivity.this,"服务器错误",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            if(!s.toString().equals("")) {
                String s1 = String.valueOf(s.toString().charAt(0));
                if ( s.toString().toCharArray().length >= 4 && s.toString().toCharArray().length <= 16 && (Else.iszimu(s1) || Else.ishanzi(s1))) {
                    flag = 1;
                    relativeLayout.setBackgroundColor(Color.parseColor("#71aaff"));
                    suretext.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    flag = 0;
                    relativeLayout.setBackgroundColor(Color.parseColor("#e5e5e5"));
                    suretext.setTextColor(Color.parseColor("#acacac"));
                }
            }
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            try {
            } catch (Exception e) {
            }
        }
    };

}
