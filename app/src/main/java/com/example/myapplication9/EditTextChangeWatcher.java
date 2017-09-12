package com.example.myapplication9;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by bingnanfeng02 on 2017/8/21.
 */
public class EditTextChangeWatcher extends EditTextWatcher {
    private int iniflag=0;
    @Override
    public void afterTextChanged(Editable s) {
          if(!s.toString().equals("")){
              if(flag==0){
                  if(iniflag==1){
                      textListener.onArticleSelected(3,s.toString(),tag);
                  }
                  iniflag=1;
              }else {
                  textListener.onArticleSelected(1,s.toString(),tag);
                  flag=0;
              }
          }else {
              textListener.onArticleSelected(0,s.toString(),tag);
              flag=1;
          }
    }

}
