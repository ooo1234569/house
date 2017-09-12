package com.example.myapplication9;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by bingnanfeng02 on 2017/8/20.
 */
public class AddAddressTask extends AsyncTask{
    private JSONObject jsonObject;
    private Handler handler;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public AddAddressTask(JSONObject jsonObject,Handler handler){
        this.jsonObject=jsonObject;
        this.handler=handler;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody requestBody=RequestBody.create(JSON,jsonObject.toString());
        Request request=new Request.Builder()
                .url("http://60.205.201.102:8081/best4house/user/addAdress")
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
            Address address;
            address=gson.fromJson(json,Address.class);
            if (!address.getPhone().equals("")){
                msg.what=1;
                address.setId2(address.getId()+"");
                //address.save();
            }else {
                msg.what=2;
            }
        }catch (NullPointerException e){
            msg.what=2;
        }
        handler.sendMessage(msg);
    }
}
