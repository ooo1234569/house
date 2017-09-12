package com.example.myapplication9;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by bingnanfeng02 on 2017/8/19.
 */
public class ChangeHeadImgTask extends AsyncTask {
    File file;
    String id;
    Handler handler;
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public ChangeHeadImgTask(File file,String id,Handler handler){
        this.file=file;
        this.id=id;
        this.handler=handler;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        OkHttpClient client = new OkHttpClient();
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                       .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"userId\""),
                        RequestBody.create(null, id))
                       .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"avatar\"; filename=\"" + "headicon" + "\""),
                        RequestBody.create(MEDIA_TYPE_PNG, file))
                       .build();

        Request request = new Request.Builder()
                .url("http://60.205.201.102:8081/best4house/user/updateAvatar")
                .post(requestBody)
                .build();

        try {
            Response response=client.newCall(request).execute();
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
        }catch (NullPointerException e){
            msg.what=2;
        }
        //handler.sendMessage(msg);
    }
}
