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

import com.example.myapplication9.EditPasswordTask;
import com.example.myapplication9.Person;
import com.example.myapplication9.R;

import org.litepal.crud.DataSupport;

/**
 * Created by bingnanfeng02 on 2017/8/18.
 */
public class EditPasswordActivity extends BackActivity {
    private EditText oldpassword;
    private EditText newpassword;
    private EditText surepassword;
    private RelativeLayout sure;
    private TextView suretext;
    private int flagnum =3;
    private int flag1=0;
    private int flag2=0;
    private int flag3=0;
    private String oldpasswords;
    private String newpasswords;
    private String surepasswords;
    private Handler mhandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        initback("修改密码");
        oldpassword=(EditText)findViewById(R.id.oldpassword);
        newpassword=(EditText)findViewById(R.id.newpassword);
        surepassword=(EditText)findViewById(R.id.surepassword);
        sure=(RelativeLayout)findViewById(R.id.sure);
        suretext=(TextView)findViewById(R.id.suretext);
        oldpassword.addTextChangedListener(textWatcher);
        newpassword.addTextChangedListener(textWatcher2);
        surepassword.addTextChangedListener(textWatcher3);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((flag1+flag2+flag3)==3){
                    oldpasswords=oldpassword.getText().toString();
                    newpasswords=newpassword.getText().toString();
                    surepasswords=surepassword.getText().toString();
                    if (newpasswords.toCharArray().length<6){
                        Toast.makeText(EditPasswordActivity.this,"密码长度应大于6位",Toast.LENGTH_SHORT).show();
                    }else if(!newpasswords.equals(surepasswords)){
                        Toast.makeText(EditPasswordActivity.this,"新密码与确认新密码不相同",Toast.LENGTH_SHORT).show();
                    }else {
                        String id=DataSupport.find(Person.class,DataSupport.count(Person.class)).getId2();
                        EditPasswordTask editPasswordTask=new EditPasswordTask(id,oldpasswords,newpasswords,mhandler);
                        editPasswordTask.execute();
                    }
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
                        Toast.makeText(EditPasswordActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        Person person=new Person();
                        person.setPassword(newpasswords);
                        person.update(DataSupport.count(Person.class));
                        finish();
                        break;
                    case 2:
                        Toast.makeText(EditPasswordActivity.this,"原密码错误",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(EditPasswordActivity.this,"服务器错误",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().equals("")){
                flag1=1;
            }else {
                flag1=0;
            }
            if((flag1+flag2+flag3)==flagnum){
                sure.setBackgroundColor(Color.parseColor("#71aaff"));
                suretext.setTextColor(Color.parseColor("#ffffff"));
            }else {
                sure.setBackgroundColor(Color.parseColor("#e5e5e5"));
                suretext.setTextColor(Color.parseColor("#acacac"));
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
    private TextWatcher textWatcher2 = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().equals("")){
                flag2=1;
            }else {
                flag2=0;
            }
            if((flag1+flag2+flag3)==flagnum){
                sure.setBackgroundColor(Color.parseColor("#71aaff"));
                suretext.setTextColor(Color.parseColor("#ffffff"));
            }else {
                sure.setBackgroundColor(Color.parseColor("#e5e5e5"));
                suretext.setTextColor(Color.parseColor("#acacac"));
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
    private TextWatcher textWatcher3 = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().equals("")){
                flag3=1;
            }else {
                flag3=0;
            }
            if((flag1+flag2+flag3)==flagnum){
                sure.setBackgroundColor(Color.parseColor("#71aaff"));
                suretext.setTextColor(Color.parseColor("#ffffff"));
            }else {
                sure.setBackgroundColor(Color.parseColor("#e5e5e5"));
                suretext.setTextColor(Color.parseColor("#acacac"));
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
