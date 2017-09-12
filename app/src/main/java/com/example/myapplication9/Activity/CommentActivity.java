package com.example.myapplication9.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.myapplication9.R;
import com.example.myapplication9.fragment.CommentFragment;

/**
 * Created by bingnanfeng02 on 2017/8/4.
 */
public class CommentActivity extends BackActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private CommentFragment commentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initback("评论详情");
        commentFragment=new CommentFragment();
        fragmentManager=getSupportFragmentManager() ;
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fl,commentFragment);
        transaction.commit();
    }
}
