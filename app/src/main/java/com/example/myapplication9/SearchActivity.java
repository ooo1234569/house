package com.example.myapplication9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    private EditText input;
    private TextView cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchactivty);
        input=(EditText)findViewById(R.id.input);
        cancel=(TextView)findViewById(R.id.cancel) ;
        int kuan= View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        cancel.measure(kuan,kuan);
        int kuan2=cancel.getMeasuredWidth();
        Log.d(cancel.getMeasuredHeight()+"",kuan2+"");
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
        RelativeLayout.LayoutParams inputlParams = (RelativeLayout.LayoutParams)input.getLayoutParams();
        inputlParams.width=width-(int)(20*metric.density+0.5f)-kuan2;
        input.setLayoutParams(inputlParams);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this,MainActivity.class));
            }
        });
    }
}
