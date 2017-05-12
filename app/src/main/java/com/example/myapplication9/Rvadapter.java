package com.example.myapplication9;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 炳楠 on 2017/5/7.
 */
public class Rvadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private int HEADER0 = 0;
    private int HEADER1 = 1;
    private int HEADER2 = 2;
    private int HEADER3 = 3;
    private int HEADER4 = 4;
    private int HEADER5 = 5;
    private int NORMAL = 100;

    private View headView0;
    private View headView1;
    private View headView2;
    private View headView3;
    private View headView4;
    private View headView5;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER0) {
            return new BannerHolder(headView0);
        }else if(viewType==HEADER1){
            return new GridMenuHolder(headView1);
        }else if (viewType==HEADER2){
            return new TuijianHolder(headView2);
        }else if(viewType==HEADER3){
            return new SnapUpHolder(headView3);
        }else if(viewType==HEADER4){
            return new MiddleBannerHolder(headView4);
        }
        else {
            return new ShaixuanHolder(View.inflate(UIUtils.getContext(), R.layout.rv_item_normal, null));
        }    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        Log.d("alan","holder位置---》"+holder.getLayoutPosition());
        if (viewType == HEADER0 ) {
            return;
        }else if(viewType==HEADER1){
            return;
        }else if(viewType==HEADER2){
            return;
        }else if (viewType==HEADER3){
            return;
        }else if(viewType==HEADER4){
            return;
        }
        else if(viewType==NORMAL){
            ShaixuanHolder shaixuanHolder = (ShaixuanHolder) holder;
            final int realPostion=position-HEADER_CONUNT;//获取真正的位置

            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = realPostion;
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        int pos = realPostion;
                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                        return true;
                    }
                });
            }


//            Log.d("alan","位置-->"+position);
            shaixuanHolder.tv_title.setText(normalGoodsTitls.get(realPostion));
            shaixuanHolder.iv_goodsLeft.setOnClickListener(this);
            shaixuanHolder.iv_goodsLeft.setTag(realPostion);
            shaixuanHolder.iv_goodsRight.setOnClickListener(this);
            shaixuanHolder.iv_goodsRight.setTag(realPostion);

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0 && headView0 != null) {
            return HEADER0;
        }else if(position==1&&headView1!=null){
            return HEADER1;
        }else if(position==2&&headView2!=null){
            return HEADER2;
        }else if (position==3&&headView3!=null){
            return HEADER3;
        }else if (position==4&&headView4!=null){
            return HEADER4;
        }
        else {
            return NORMAL;
        }
    }

    @Override
    public void onClick(View v) {

    }
    public void  addHeadView0(View view) {
        this.headView0 = view;
    }
    public void addHeaderView1(View v) {
        this.headView1 = v;
    }
    public void addHeaderView2(View v){
        this.headView2=v;
    }
    public void addHeaderView3(View v){
        this.headView3=v;
    }
    public void addHeaderView4(View v){
        this.headView4=v;
    }

    class BannerHolder extends RecyclerView.ViewHolder {
        public BannerHolder(View itemView) {
            super(itemView);
        }
    }
    //10个子菜单
    class GridMenuHolder extends RecyclerView.ViewHolder{
        public GridMenuHolder(View itemView) {
            super(itemView);
        }
    }

    //普通的Holder
    class ShaixuanHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        public ShaixuanHolder(View itemView) {
            super(itemView);
        }
    }
    /**淘宝头条HOlder*/
    class TuijianHolder extends RecyclerView.ViewHolder{
        public TuijianHolder(View itemView) {
            super(itemView);
        }
    }
    /**抢购的Header(4个）*/
    class SnapUpHolder extends RecyclerView.ViewHolder{
        public SnapUpHolder(View itemView) {
            super(itemView);
        }
    }
    /**中间的banner*/
    class MiddleBannerHolder extends RecyclerView.ViewHolder{
        public MiddleBannerHolder(View itemView) {
            super(itemView);
            System.out.print("jj");
        }
    }


}
