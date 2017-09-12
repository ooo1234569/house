package com.example.myapplication9;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by bingnanfeng02 on 2017/8/18.
 */
public class SignupTask extends AsyncTask {
    String phone;
    String message;
    String id;
    Handler handler;
    String cookie;
    public SignupTask(String phone, String message,String cookie,String id, Handler handler){
        this.phone=phone;
        this.message=message;
        this.handler=handler;
        this.cookie=cookie;
        this.id=id;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request;
        RequestBody requestBody1=new FormBody.Builder().build();
        request = new Request.Builder()
                .url("http://60.205.201.102:8081/best4house/user/updatePhone?userId="+id+"&newPhone="+phone+"&inputCode="+message)
                .addHeader("cookie",cookie)
                .post(requestBody1)
                .build();
        Log.d("msg",message);
        Log.d("phone",phone);
        Log.d("id",id);
        Log.d("cooike",cookie);
        try {
            Response response=okHttpClient.newCall(request).execute();
            parsejsonwhithgson(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public  void parsejsonwhithgson(String json){
        Gson gson=new Gson();
        Log.d("所有Json",json+"所有Json");
        Message msg=new Message();
        try {
            Changephone changephone=gson.fromJson(json,Changephone.class);
            if(!changephone.getPhone().equals("")){
                msg.arg1=3;
            }else {
                msg.arg1=4;
            }
        }catch (NullPointerException e){
            msg.arg1=4;
        }catch (IllegalStateException e){
            msg.arg1=5;
        }
        handler.sendMessage(msg);
    }
}
