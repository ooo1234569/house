package com.example.myapplication9;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication9.Activity.MainActivity;
import com.example.myapplication9.fragment.LoginJson;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by bingnanfeng02 on 2017/8/12.
 */
public class TestSignupFragment extends Fragment {
    private String data;
    private EditText phonenum;
    private EditText message;
    private EditText password;
    private RelativeLayout getmessage;
    private RelativeLayout relativeLayout;
    private TextView time;
    private String loginphone;
    private String loginmsg;
    private String loginmsg2;
    private String loginpassword;
    private MyApplication myApplication;
    final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000,1000);
    private String cookie="";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Handler mainHandler;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
         phonenum=(EditText)view.findViewById(R.id.phonenum);
        message=(EditText)view.findViewById(R.id.message);
        password=(EditText)view.findViewById(R.id.getpassword);
        relativeLayout=(RelativeLayout)view.findViewById(R.id.rl2);
        getmessage=(RelativeLayout)view.findViewById(R.id.rl);
        time=(TextView)view.findViewById(R.id.time);
        getmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=phonenum.getText().toString();
                if (phonenum.getText().toString().equals("")){
                    Toast.makeText(getContext(),"请输入手机号",Toast.LENGTH_SHORT).show();
                }else {
                    if (Else.isMobile(s)){
                        sendrequest(s,null,null,1);
                    }else {
                        Toast.makeText(getContext(),"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginphone=phonenum.getText().toString();
                if (phonenum.getText().toString().equals("")){
                    Toast.makeText(getContext(),"请输入手机号",Toast.LENGTH_SHORT).show();
                }else {
                    if (Else.isMobile(loginphone)){
                        loginmsg=message.getText().toString();
                        if (loginmsg.equals("")){
                            Toast.makeText(getContext(),"请输入验证码",Toast.LENGTH_SHORT).show();
                        }else {
                            loginpassword=password.getText().toString();
                            if(loginpassword.equals("")){
                                Toast.makeText(getContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                            }else {
                                if (loginpassword.toCharArray().length<6||loginpassword.toCharArray().length>12){
                                    Toast.makeText(getContext(),"密码应大于6位小于12位",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                   sendrequest(loginphone,loginmsg,loginpassword,2);
                                }
                            }
                        }
                    }else {
                        Toast.makeText(getContext(),"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        mainHandler= new Handler() {
            @Override
            public void handleMessage(Message msg_main) {
                Log.d("dasdasd","asdad"+msg_main.what);
                switch(msg_main.what){
                    default:
                        System.out.println("打印默认值");
                        break;
                    case 1:
                        Toast.makeText(getContext(),"code："+loginmsg2,Toast.LENGTH_LONG).show();
                        myCountDownTimer.start();
                        break;
                    case 2:
                        Toast.makeText(getContext(),"手机号已被注册",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getContext(),"注册成功",Toast.LENGTH_SHORT).show();
                        Session session=new Session();
                        session.setFlag(0);
                        if (session.save()){
                            myApplication=(MyApplication)getActivity().getApplication();
                            myApplication.flag=1;
                            Log.d("成功","save成功");
                        }else {
                            Log.d("失败","失败");
                        }
                        getContext().startActivity(new Intent(getContext(), MainActivity.class));
                        break;
                    case 4:
                        Toast.makeText(getContext(),"验证码错误",Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getContext(),"服务器出错",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        return view;
    }
    public  void sendrequest(final String phone, final String message, final String password, final int mode){
        new Thread(new Runnable(){
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                Request request;
                RequestBody requestBody1=new FormBody.Builder().build();
                switch(mode){
                    case 1:

                        request = new Request.Builder()
                                .url("http://60.205.201.102:8081/best4house/user/sendSMS?phone="+phone)
                                .post(requestBody1)
                                .build();
                        break;
                    case 2:
                         request = new Request.Builder()
                        .url("http://60.205.201.102:8081/best4house/user/register?inputPhone="+phone+"&password="+password+"&inputCode="+message)
                        .addHeader("cookie",cookie)
                        .post(requestBody1)
                        .build();
                        Log.d(phone,message);
                        break;
                    default:
                        request = new Request.Builder()
                                .url("http://60.205.201.102:8081/best4house/user/sendSMS?phone="+phone)
                                .build();
                        break;
                 }
                try {
                    Response response=okHttpClient.newCall(request).execute();
                    if (cookie.equals("")){
                        cookie=response.header("Set-Cookie");
                    }
                    Log.d("cookie",cookie+"cookie");
                    data=response.body().string();
                    parsejsonwhithgson(data,mode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public  void parsejsonwhithgson(String json,int mode){
        Gson gson=new Gson();
        Log.d("所有Json"+mode,json+"所有Json");
        Message msg=new Message();
        switch(mode){
            case 1:
                try {
                TestLoginJson testLoginJson=gson.fromJson(json,TestLoginJson.class);
                if(!testLoginJson.getCode().equals("")){
                    loginmsg2=testLoginJson.getCode();
                    msg.what=1;
                }else {
                    msg.what=2;
                }
                }catch (NullPointerException e){
                    msg.what=2;
                }
                catch (IllegalStateException e){
                    msg.what=5;
                }
                break;
            case 2:
                try {
                    Person person=gson.fromJson(json,Person.class);
                    if(!(person.getId()==0)){
                        person.setId2(person.getId()+"");
                        person.save();
                        msg.what=3;
                    }else {
                        msg.what=4;
                    }
                }catch (NullPointerException e){
                    msg.what=4;
                }
                catch (IllegalStateException e){
                    msg.what=5;
                }

                break;
            default:
                System.out.println("打印默认值");
                break;
        }
        mainHandler.sendMessage(msg);
    }

    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            getmessage.setClickable(false);
            time.setText(l/1000+"s后重新获取");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            time.setText("重新获取验证码");
            //设置可点击
            getmessage.setClickable(true);
        }
    }
}
