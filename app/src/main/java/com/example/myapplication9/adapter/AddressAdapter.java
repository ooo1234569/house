package com.example.myapplication9.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication9.Activity.UpdateAddressActivity;
import com.example.myapplication9.Address;
import com.example.myapplication9.R;

import java.util.List;

/**
 * Created by bingnanfeng02 on 2017/7/26.
 */
public class AddressAdapter extends ArrayAdapter{
    int resource;
    Context context;
    List<Address> arrayList;
    TextView name;
    TextView phonenumber;
    TextView addresst;
    RelativeLayout edit;
    ImageView defult;
    ImageView editimg;
    ImageView delete;
    public AddressAdapter(Context context, int resource, List<Address> arrayList) {
        super(context, resource);
        this.resource=resource;
        this.arrayList=arrayList;
        this.context=context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView==null){
            view = LayoutInflater.from(context).inflate(resource,parent,false);
        }
        else {
         view=convertView;
        }
        name=(TextView)view.findViewById(R.id.name1);
        phonenumber=(TextView)view.findViewById(R.id.phonenumber);
        addresst=(TextView)view.findViewById(R.id.address);
        edit=(RelativeLayout)view.findViewById(R.id.editrl);
        defult=(ImageView)view.findViewById(R.id.defult);
        editimg=(ImageView)view.findViewById(R.id.editphoto);
        delete=(ImageView)view.findViewById(R.id.deleteimg);
        Glide.with(context).load(R.drawable.yuan).into(defult);
        Glide.with(context).load(R.drawable.delete).into(delete);
        Glide.with(context).load(R.drawable.edit).into(editimg);
        final Address address=arrayList.get(position);
        name.setText(address.getContacts());
        phonenumber.setText(address.getPhone());
        addresst.setText(address.getProvince()+address.getCity()+address.getDistrict()+address.getRoad()+address.getDetailed());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UpdateAddressActivity.class);
                intent.putExtra("id",address.getId()+"");
                String[] strings=new String[7];
                strings[0]=address.getContacts();
                strings[1]=address.getPhone();
                strings[2]=address.getProvince();
                strings[3]=address.getCity();
                strings[4]=address.getDistrict();
                strings[5]=address.getRoad();
                strings[6]=address.getDetailed();
                intent.putExtra("address",strings);
                context.startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

}
