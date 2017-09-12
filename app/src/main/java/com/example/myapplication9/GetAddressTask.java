package com.example.myapplication9;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by bingnanfeng02 on 2017/8/20.
 */
public class GetAddressTask extends AsyncTask {
    private String id;
    private Handler handler;
    public GetAddressTask (String id,Handler handler){
        this.id=id;
        this.handler=handler;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        OkHttpClient okHttpClient= new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().build();
        Request request=new Request.Builder()
                .url("http://60.205.201.102:8081/best4house/user/getAdresses?userId="+id)
                .post(requestBody)
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
            List<Address>  addresses=gson.fromJson(json,new TypeToken<List<Address>>(){}.getType());
            if (addresses.size()==0){
                msg.what=0;
            }else {
                if (!addresses.get(0).getPhone().equals("")){
                    msg.what=1;
                    msg.obj=addresses;
                }else {
                    msg.what=2;
                }
            }
        }catch (NullPointerException e){
            msg.what=2;
        }
        handler.sendMessage(msg);
    }
}
