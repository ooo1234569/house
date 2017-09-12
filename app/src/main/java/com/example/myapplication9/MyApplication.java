package com.example.myapplication9;

import android.app.Application;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;

/**
 * Created by bingnanfeng02 on 2017/8/14.
 */
public class MyApplication extends LitePalApplication {
    public int flag=0;
    public String city;
    public String street;
    public ArrayList<Service> services=new ArrayList<>();
    @Override
    public void onCreate()
    {
        super.onCreate();
         if(DataSupport.count(Session.class)==0){

         }else {
             flag=1;
         }
    }

}
