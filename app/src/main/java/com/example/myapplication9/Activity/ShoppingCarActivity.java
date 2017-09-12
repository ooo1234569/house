package com.example.myapplication9.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication9.R;
import com.example.myapplication9.fragment.ShoppingCarFragment;


/**
 * Created by bingnanfeng02 on 2017/8/29.
 */
public class ShoppingCarActivity extends AppCompatActivity{
   private ShoppingCarFragment shoppingCarFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        shoppingCarFragment=new ShoppingCarFragment();
        fragmentManager=getSupportFragmentManager() ;
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fl,shoppingCarFragment);
        transaction.commit();
    }

}

