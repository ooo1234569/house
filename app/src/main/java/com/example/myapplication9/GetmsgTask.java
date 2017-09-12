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
public class GetmsgTask extends AsyncTask {
    private String phone;
    private Handler handler;
    private String cookie="";
    public GetmsgTask(String phone, Handler handler,String cookie){
        this.phone=phone;
        this.handler=handler;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody requestBody1=new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url("http://60.205.201.102:8081/best4house/user/sendSMS?phone="+phone)
                .post(requestBody1)
                .build();
        Log.d("getmsg",phone);
        try {
            Response response=okHttpClient.newCall(request).execute();
            cookie=response.header("Set-Cookie");
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
            TestLoginJson testLoginJson=gson.fromJson(json,TestLoginJson.class);
            if(!testLoginJson.getCode().equals("")){
                msg.arg1=1;
                msg.arg2=Integer.valueOf(testLoginJson.getCode());
                msg.obj=cookie;
                Log.d("cookieget",cookie);
            }else {
                msg.arg1=2;
            }
        }catch (NullPointerException e){
            msg.arg1=2;
        } catch (IllegalStateException e) {
           msg.arg1=3;
        }
        handler.sendMessage(msg);
    }
}
