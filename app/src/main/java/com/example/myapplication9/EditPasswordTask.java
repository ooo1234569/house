package com.example.myapplication9;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.myapplication9.fragment.LoginJson;
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
public class EditPasswordTask extends AsyncTask{
    String oldpassword;
    String newpassword;
    Handler mhandler;
    String id;
    public EditPasswordTask(String id,String oldpassword,String newpassword,Handler mhandler){
        this.oldpassword=oldpassword;
        this.newpassword=newpassword;
        this.mhandler=mhandler;
        this.id=id;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().build();
        Log.d("id",id+"");
        Request request=new Request.Builder()
                .url("http://60.205.201.102:8081/best4house/user/updatePassword?userId="+id+"&oldPassword="+oldpassword+"&newPassword="+newpassword)
                .post(requestBody)
                .build();
        Log.d(oldpassword,newpassword);
        try {
            Response response=okHttpClient.newCall(request).execute();
            String data=response.body().string();
            parsejsonwhithgson(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public  void parsejsonwhithgson(String json){
        Gson gson=new Gson();
        Message msg=new Message();
        Log.d("所有Json",json+"所有Json");
        try {
            Password password=gson.fromJson(json,Password.class);
            if (!password.getUpdateNumbers().equals("")){
                msg.what=1;
            }else {
                msg.what=2;
            }
        }catch (NullPointerException e){
            msg.what=2;
        }
        mhandler.sendMessage(msg);
    }
}
