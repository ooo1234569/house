package com.example.myapplication9.Activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication9.R;

/**
 * Created by bingnanfeng02 on 2017/8/27.
 */
public class BackActivity extends AppCompatActivity {
    public Toolbar toolbar;
    void initback(String titile){
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(com.iasii.app.citylist.R.id.toolbar_title);
        mTitle.setText(titile);
        toolbar.setTitle("");
        //setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
