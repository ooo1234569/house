package com.example.myapplication9;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by bingnanfeng02 on 2017/8/21.
 */
public class UpdateAddressTask extends AsyncTask {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Handler handler;
    private JSONObject jsonObject;
    public UpdateAddressTask(JSONObject jsonObject, Handler handler){
        this.jsonObject=jsonObject;
        this.handler=handler;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody requestBody=RequestBody.create(JSON,jsonObject.toString());
        Log.d("log",jsonObject.toString());
        Request request=new Request.Builder()
                .url("http://60.205.201.102:8081/best4house/user/updateAdress")
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
        Message msg=new Message();
        Log.d("所有Json",json+"所有Json");
        try {
            JSONObject jsonObject=new JSONObject(json);
             if (!jsonObject.getString("updateNumber").equals("")){
                 msg.what=1;
            }else {
                msg.what=2;
            }
        }catch (NullPointerException e){
            msg.what=2;
        } catch (JSONException e) {
            e.printStackTrace();
            msg.what=2;
        }
        handler.sendMessage(msg);
    }
}
