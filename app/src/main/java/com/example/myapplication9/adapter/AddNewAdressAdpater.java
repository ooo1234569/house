package com.example.myapplication9.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication9.Address;
import com.example.myapplication9.EditTextChangeWatcher;
import com.example.myapplication9.EditTextWatcher;
import com.example.myapplication9.R;
import com.example.myapplication9.TextListener;

/**
 * Created by bingnanfeng02 on 2017/8/20.
 */
public class AddNewAdressAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] strings;
    Context context;
    TextListener positionListener;
    int flag=0;
    String[] strings2;
    String[]hint;
    public AddNewAdressAdpater(String[] strings, Context context, TextListener positionListener){
        this.strings=strings;
        this.context=context;
        this.positionListener=positionListener;
    }
    public AddNewAdressAdpater(String[] strings,String[] strings2,TextListener positionListener,String[] hint){
        this.strings=strings;
        this.strings2=strings2;
        this.positionListener=positionListener;
        this.hint=hint;
        this.flag=1;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_address1,parent,false);
        final Item item=new Item(view);
        item.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.editText.setFocusable(true);
                item.editText.setFocusableInTouchMode(true);
                item.editText.requestFocus();
                item.editText.findFocus();
            }
        });
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Item)holder).textView.setText(strings[position]);
        if (flag==0){
            ((Item)holder).editText.setHint("请输入"+strings[position]);
            ((Item)holder).editTextWatcher.sett(position);
        }else {
            ((Item)holder).editText.setHint("请输入"+hint[position]);
            ((Item)holder).editText.setText(strings2[position]);
            ((Item)holder).editTextChangeWatcher.sett(position);
        }

    }

    @Override
    public int getItemCount() {
        return strings.length;
    }
    class Item extends RecyclerView.ViewHolder {
        TextView textView;
        EditText editText;
        RelativeLayout relativeLayout;
        EditTextWatcher editTextWatcher;
        EditTextChangeWatcher editTextChangeWatcher;
        public Item(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.namet);
            editText=(EditText)itemView.findViewById(R.id.name);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.rl1);
            if (flag==0){
                editTextWatcher=new EditTextWatcher();
                editTextWatcher.setp(positionListener);
                editText.addTextChangedListener(editTextWatcher);
            }else {
                editTextChangeWatcher=new EditTextChangeWatcher();
                editTextChangeWatcher.setp(positionListener);
                editText.addTextChangedListener(editTextChangeWatcher);
            }

        }
    }
}
