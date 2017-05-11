package com.example.myapplication9;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 炳楠 on 2017/4/22.
 */
public class Wodefg extends Fragment {
    private TextView textView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.airbnbwode, container, false);
//        textView=(TextView)view.findViewById(R.id.textView);
//        AssetManager assets = getContext().getAssets();
//        Typeface fromAsset = Typeface.createFromAsset(assets, "fonts/pingfang.ttf");
//        textView.setTypeface(fromAsset);
        return view;
    }
}
