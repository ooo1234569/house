package com.example.myapplication9;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by bingnanfeng02 on 2017/8/20.
 */
public class EditTextWatcher implements TextWatcher {
    public TextListener textListener;
    public int flag=0;
    public int tag;
    public EditTextWatcher(){
       // Log.d("sdssd","asdadad");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        Log.d("s", start+"");
//        Log.d("b", before+"");
    }

    @Override
    public void afterTextChanged(Editable s) {
         if(!s.toString().equals("")){
             if (flag==0){
                 textListener.onArticleSelected(1,s.toString(),tag);
                 flag=1;
             }else {
                 textListener.onArticleSelected(3,s.toString(),tag);
             }
         }else {
             textListener.onArticleSelected(0,s.toString(),tag);
             flag=0;
         }

    }
    public  void setp(TextListener positionListener){
        this.textListener=positionListener;
    }
    public  void sett(int tag){
        this.tag=tag;
        Log.d("tag",tag+"");
    }

}
