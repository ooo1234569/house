package com.example.myapplication9.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication9.Activity.ClassifyActivity;
import com.example.myapplication9.Activity.SearchResultActivity;
import com.example.myapplication9.BigService;
import com.example.myapplication9.R;
import com.example.myapplication9.SmallService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingnanfeng02 on 2017/8/4.
 */
public class ListTwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BigService> bigServiceList ;
    private Context context;
    public ListTwoAdapter(List<BigService> bigServiceList, Context context){
        this.bigServiceList=bigServiceList;
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doublelist2,parent,false);
        final Gridview gridview=new Gridview(view);
        gridview.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {  int i=gridview.getAdapterPosition();
                Intent intent = new Intent(context, SearchResultActivity.class);
                intent.putExtra("yemian",  bigServiceList.get(i).getServices().get(position).getServiceName()  );
                context.startActivity(intent);
            }
        });
        return gridview;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ClassifyGridViewAdapter classifyGridViewAdapter=new ClassifyGridViewAdapter(bigServiceList.get(position).getServices(),context);
        ((Gridview)holder).type.setText(bigServiceList.get(position).getServiceTypeName());
        ((Gridview)holder).gridView.setAdapter(classifyGridViewAdapter);
        ((Gridview)holder).rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchResultActivity.class);
                intent.putExtra("yemian",  bigServiceList.get(position).getServiceTypeName());
                context.startActivity(intent);
            }
        });
        return;
    }

    @Override
    public int getItemCount() {
        return bigServiceList.size();
    }
    class Gridview extends RecyclerView.ViewHolder {
        GridView gridView;
        TextView type;
        RelativeLayout rl;
        public Gridview(View itemView) {
            super(itemView);
            gridView=(GridView)itemView.findViewById(R.id.gridView);
            type=(TextView)itemView.findViewById(R.id.type);
            rl=(RelativeLayout)itemView.findViewById(R.id.rl);
        }
    }
}
