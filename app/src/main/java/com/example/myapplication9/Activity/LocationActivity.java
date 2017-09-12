package com.example.myapplication9.Activity;

import com.example.myapplication9.MyApplication;
import com.iasii.app.citylist.TouchListener;
import com.iasii.app.citylist.activity.DingweiActivity;

public class LocationActivity extends DingweiActivity implements TouchListener{
    private MyApplication myApplication;
    @Override
    public  void initapplication(){
        myApplication=(MyApplication)getApplication();
       locationcity=myApplication.city;
    }
    @Override
    public  void initlistener(){
        cityListAdapter.setp(this);
    }
    @Override
    public void city(String s) {
        myApplication.city=s;
        myApplication.street=s;
        finish();
    }
}
