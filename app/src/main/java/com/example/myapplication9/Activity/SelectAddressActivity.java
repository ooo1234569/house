package com.example.myapplication9.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication9.R;

/**
 * Created by bingnanfeng02 on 2017/9/11.
 */

public class SelectAddressActivity extends BackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initback("选择地址");
        setContentView(R.layout.activity_select_address);

    }
}
