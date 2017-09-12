package com.example.myapplication9.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication9.R;
import com.example.myapplication9.adapter.CommentAdapter;

import java.lang.reflect.Field;


/**
 * Created by bingnanfeng02 on 2017/7/29.
 */
public class CommentFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CommentAdapter commentadapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_comment,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv);
        linearLayoutManager=new LinearLayoutManager(getContext());
        commentadapter=new CommentAdapter(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(commentadapter);
        return view;
    }
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
