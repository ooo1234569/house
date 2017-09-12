package com.example.myapplication9.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import com.example.myapplication9.Else;
import com.example.myapplication9.GetmsgTask;
import com.example.myapplication9.MyApplication;
import com.example.myapplication9.Person;
import com.example.myapplication9.R;
import com.example.myapplication9.Session;
import com.example.myapplication9.SignupTask;

import org.litepal.crud.DataSupport;

/**
 * Created by bingnanfeng02 on 2017/8/18.
 */
public class ChangePhoneActivity extends BackActivity {
    private EditText phone;
    private EditText msg;
    private RelativeLayout getmsg;
    private RelativeLayout sure;
    private TextView suretext;
    private TextView time;
    private Handler handler;
    private int flag =0;
    private int flag2=0;
    private String s;
    private String cookie;
    final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000,1000);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        initback("修改绑定手机号");
        phone=(EditText)findViewById(R.id.phonenum);
        msg=(EditText)findViewById(R.id.message);
        getmsg=(RelativeLayout)findViewById(R.id.rl);
        sure=(RelativeLayout)findViewById(R.id.sure);
        time=(TextView)findViewById(R.id.time);
        suretext=(TextView)findViewById(R.id.suretext);
        phone.addTextChangedListener(textWatcher1);
        msg.addTextChangedListener(textWatcher2);
        getmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone.getText().toString().equals("")){
                    Toast.makeText(ChangePhoneActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }else {
                    s=phone.getText().toString();
                    if(!Else.isMobile(s)){
                        Toast.makeText(ChangePhoneActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    }else {
                        GetmsgTask getmsgTask=new GetmsgTask( DataSupport.find(Person.class,DataSupport.count(Person.class)).getPhone(),handler,null);
                        getmsgTask.execute();
                    }
                }
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(msg.getText().toString().equals("")){
                    Toast.makeText(ChangePhoneActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                }else {
                    SignupTask signupTask=new SignupTask(s,msg.getText().toString(),cookie, DataSupport.find(Person.class,DataSupport.count(Person.class)).getId2(),handler);
                    signupTask.execute();
                }

            }
        });

        handler= new Handler() {
            @Override
            public void handleMessage(Message msg_main) {
                Log.d("dasdasd","asdad"+msg_main.arg1);
                switch(msg_main.arg1){
                    default:
                        System.out.println("打印默认值");
                        break;
                    case 1:
                        myCountDownTimer.start();
                        if(String.valueOf(msg_main.arg2).toCharArray().length==5){
                            Toast.makeText(ChangePhoneActivity.this,"code："+"0"+msg_main.arg2,Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(ChangePhoneActivity.this,"code："+msg_main.arg2,Toast.LENGTH_LONG).show();
                        }
                        cookie=(String)msg_main.obj;
                        break;
                    case 2:
                        Toast.makeText(ChangePhoneActivity.this,"手机号已被注册",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Person person=new Person();
                        person.setPhone(s);
                        person.update(DataSupport.count(Person.class));
                        Toast.makeText(ChangePhoneActivity.this,"绑定成功",Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case 4:
                        Toast.makeText(ChangePhoneActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(ChangePhoneActivity.this,"服务器错误",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }
    private TextWatcher textWatcher1 = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            if(!s.toString().equals("")) {
                flag=1;
                if (flag+flag2==2){
                    sure.setBackgroundColor(Color.parseColor("#71aaff"));
                    suretext.setTextColor(Color.parseColor("#ffffff"));
                }else {
                    sure.setBackgroundColor(Color.parseColor("#e5e5e5"));
                    suretext.setTextColor(Color.parseColor("#acacac"));
                }
            }else {
                flag=0;
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
            if(!s.toString().equals("")) {
                flag2=1;
                if (flag+flag2==2){
                    sure.setBackgroundColor(Color.parseColor("#71aaff"));
                    suretext.setTextColor(Color.parseColor("#ffffff"));
                }else {
                    sure.setBackgroundColor(Color.parseColor("#e5e5e5"));
                    suretext.setTextColor(Color.parseColor("#acacac"));
                }
            }else {
                flag2=0;
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
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            getmsg.setClickable(false);
            time.setText(l/1000+"s后重新获取");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            time.setText("重新获取验证码");
            //设置可点击
            getmsg.setClickable(true);
        }
    }
}
