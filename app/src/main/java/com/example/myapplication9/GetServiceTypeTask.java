package com.example.myapplication9;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bingnanfeng02 on 2017/8/24.
 */
public class GetServiceTypeTask extends AsyncTask{
    private Handler handler;
    public GetServiceTypeTask(Handler handler){
        this.handler=handler;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url("http://60.205.201.102:8081/best4house/facilitator/getServiceTypeAndService")
                .build();
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
        Message msg=new Message();
        Log.d("所有Json",json+"所有Json");
        try {
            List<BigService> bigServices=gson.fromJson(json,new TypeToken<List<BigService>>(){}.getType());
            msg.obj=bigServices;
            msg.what=1;
        }catch (NullPointerException e){
            msg.what=2;
        }
        handler.sendMessage(msg);
    }
}
