package com.example.myapplication9.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication9.Activity.MainActivity;
import com.example.myapplication9.Else;
import com.example.myapplication9.MyApplication;
import com.example.myapplication9.Person;
import com.example.myapplication9.Person2;
import com.example.myapplication9.R;
import com.example.myapplication9.Session;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by bingnanfeng02 on 2017/8/12.
 */
public class LoginFragment extends Fragment {
    private EditText phone;
    private EditText password;
    private RelativeLayout relativeLayout;
    private String data="";
    private Person person=new Person();
    private MyApplication myApplication;
    private Handler mainHandler= new Handler() {
        @Override
        public void handleMessage(Message msg_main) {
            if (msg_main.what == 1){
                Session session=new Session();
                session.setFlag(0);
                if (session.save()){
                    myApplication=(MyApplication)getActivity().getApplication();
                    myApplication.flag=1;
                    Log.d("成功","save成功");
                }else {
                    Log.d("失败","失败");
                }
                Toast.makeText(getContext(),"登录成功",Toast.LENGTH_SHORT).show();
                getContext().startActivity(new Intent(getContext(), MainActivity.class));
            }else if(msg_main.what==0){
                Toast.makeText(getContext(),"用户名或密码错误",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(),"服务器错误",Toast.LENGTH_SHORT).show();
            }
        }
    };
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        phone=(EditText)view.findViewById(R.id.phone);
        password=(EditText)view.findViewById(R.id.getpassword);
        relativeLayout=(RelativeLayout)view.findViewById(R.id.login);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data="";
                String s=phone.getText().toString();
                if (s.equals("")){
                    Toast.makeText(getContext(),"请输入手机号",Toast.LENGTH_SHORT).show();
                }else {
                    if (Else.isMobile(s)){
                        String password1=password.getText().toString();
                        if(password1.equals("")){
                            Toast.makeText(getContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                        }else {
                            if (password1.toCharArray().length<6||password1.toCharArray().length>12){
                                Toast.makeText(getContext(),"密码应大于6位小于12位",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                 sendrequest(s,password1);
                            }
                        }
                    }else {
                        Toast.makeText(getContext(),"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
    public  void sendrequest(final String phone,final String password){
        new Thread(new Runnable(){
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                RequestBody requestBody=new FormBody.Builder().build();
                Request request = new Request.Builder()
                        .url("http://60.205.201.102:8081/best4house/user/login?phone="+phone+"&password="+password)
                        .post(requestBody)
                        .build();
                try {
                    Response response=okHttpClient.newCall(request).execute();
                   // Log.d("logincooike",response.header("Set-Cookie"));
                    data=response.body().string();
                    Log.d("sdsf",data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("登录",data);
                parsejsonwhithgson(data);
            }
        }).start();
    }
    public  void parsejsonwhithgson(String json){
        Gson gson=new Gson();
        Message msg=new Message();
        person.setNickname("");
        try {
            person=gson.fromJson(json,Person.class);
            if(person.getNickname().equals("")){
                msg.what=0;
            }else {
                msg.what=1;
                person.setId2(person.getId()+"");
                if(person.save()){
                    Log.d("qwfd","cg");
                }else {
                    Log.d("qwfd","sb");
                }
            }
        }catch (NullPointerException e){
            msg.what=0;
        }catch (JsonSyntaxException e){
            Log.d("qwfd","sb");
            msg.what=-1;
        }
        mainHandler.sendMessage(msg);
    }
}
