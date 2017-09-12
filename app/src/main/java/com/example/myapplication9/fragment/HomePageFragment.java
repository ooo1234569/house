package com.example.myapplication9.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.example.myapplication9.Activity.ClassifyActivity;
import com.example.myapplication9.Activity.LocationActivity;
import com.example.myapplication9.Activity.SearchActivity;
import com.example.myapplication9.Activity.SearchResultActivity;
import com.example.myapplication9.Business;
import com.example.myapplication9.GetServiceTypeTask;
import com.example.myapplication9.GlideImageLoader;
import com.example.myapplication9.LocationHistory;
import com.example.myapplication9.MyApplication;
import com.example.myapplication9.Person;
import com.example.myapplication9.R;
import com.example.myapplication9.Shangjia;
import com.example.myapplication9.ZhuyeHeader;
import com.example.myapplication9.adapter.GridviewAdapter;
import com.example.myapplication9.adapter.HomepageAdapter;
import com.example.myapplication9.adapter.HomepageAdapter2;
import com.youth.banner.Banner;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import pw.h57.popupbuttonlibrary.PopupButton;
import pw.h57.popupbuttonlibrary.adapter.PopupAdapter;


/**
 * Created by 炳楠 on 2017/4/15.
 */
public class HomePageFragment extends Fragment implements AMapLocationListener {
    private RecyclerView recyclerView;
    //private HomepageAdapter rvadapter;
    private HomepageAdapter2 rvadapter;
    private Banner banner;
    private View view;
    private FrameLayout search;
    private ImageView dingwei;
    private boolean loading=false;
    private PtrFrameLayout mPtrFrame;
    private ArrayList<Business> shangjias=new ArrayList<>();
    private MyApplication myApplication;
    private TextView textView;
    public AMapLocationClientOption mLocationOption = null;
    public AMapLocationClient mlocationClient = null;
    private RelativeLayout rl;
    private ArrayList<String> images=new ArrayList<>();
    private boolean islocation=false;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.mFilterContentView);
        search = (FrameLayout) view.findViewById(R.id.input);
        mPtrFrame =(PtrFrameLayout)view.findViewById(R.id.store_house_ptr_frame);
        textView=(TextView)view.findViewById(R.id.locationtext);
        rl=(RelativeLayout)view.findViewById(R.id.location);
        dingwei=(ImageView)view.findViewById(R.id.photo);
        Glide.with(this).load(R.drawable.dingwei).into(dingwei);
        myApplication=(MyApplication)getActivity().getApplication();
        initpremission();
        ZhuyeRefresh();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        initdata();
        rvadapter = new HomepageAdapter2(shangjias,3,getContext());
        initbanner();
        initgridmenu();
        intitspace();
        initrepeatview();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvadapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

//                if (recyclerView.getAdapter() != null && recyclerView.getLayoutManager() != null) {
//                    int lastVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
//                    int itemCount = recyclerView.getAdapter().getItemCount();
//                    if (dy > 0 && itemCount != 0 && lastVisiblePosition == itemCount - 1&&!loading ) {
////                        Log.i(itemCount+"","加载更多");
//                        rvadapter.progressBar.setVisibility(View.VISIBLE);
//                    }
//                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LocationActivity.class));
            }
        });
        return view;
    }
    @Override
    public  void onPause(){
        super.onPause();
        banner.stopAutoPlay();
    }
    @Override
   public  void onResume(){
       super.onResume();
        banner.startAutoPlay();
        if(myApplication.city !=null) {
            if (!myApplication.city.equals(textView.getText().toString())) {
                textView.setText(myApplication.city);
                LocationHistory locationHistory=new LocationHistory();
                locationHistory.setCity(textView.getText().toString());
                locationHistory.setStreet("");
                if(DataSupport.count(LocationHistory.class)==0){
                    locationHistory.save();
                }else {
                    locationHistory.update(DataSupport.count(LocationHistory.class));
                }
            }
        }
   }

    void initpremission(){
        if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            this.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }else {
            initlocation();
        }
    }
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        if (requestCode==1){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                initlocation();
            }else {
               setcity();
            }
        }
    }

    void setcity(){
        if(DataSupport.count(LocationHistory.class)!=0){
            if(DataSupport.find(LocationHistory.class,DataSupport.count(LocationHistory.class)).getStreet()!=null){
                textView.setText(DataSupport.find(LocationHistory.class,DataSupport.count(LocationHistory.class)).getCity()+DataSupport.find(LocationHistory.class,DataSupport.count(LocationHistory.class)).getStreet());
            }else {
                textView.setText(DataSupport.find(LocationHistory.class,DataSupport.count(LocationHistory.class)).getCity());
            }
            myApplication.street=DataSupport.find(LocationHistory.class,DataSupport.count(LocationHistory.class)).getStreet();
            myApplication.city=DataSupport.find(LocationHistory.class,DataSupport.count(LocationHistory.class)).getCity();
        }else {
            LocationHistory locationHistory=new LocationHistory();
            locationHistory.setCity("北京市");
            locationHistory.save();
            textView.setText("北京市");
            myApplication.city="北京市";
        }
        Toast.makeText(getContext(), "未授予定位权限，请开启定位权限", Toast.LENGTH_SHORT).show();
    }
    void  initlocation(){
        mLocationOption = new AMapLocationClientOption();
        mlocationClient = new AMapLocationClient(getContext());
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位监听
        mlocationClient.setLocationListener(this);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
        // mLocationOption.setInterval(2000);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();
        //mlocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }

    public void initbanner() {
        View bannerView = View.inflate(getContext(), R.layout.view_banner2, null);
        images.add("https://gw.alicdn.com/tfs/TB1K9tEQVXXXXauaXXXXXXXXXXX-1035-500.png");
        images.add("https://img.alicdn.com/bao/uploaded/TB1ad98SXXXXXc_XFXXSutbFXXX.jpg");
        banner=(Banner)bannerView.findViewById(R.id.banner_slider1);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        banner.start();
        rvadapter.addView(bannerView);
    }
    public void initdata(){
        for (int i=0;i<10;i++){
            Business business=new Business();
            business.type="类别"+i;
            business.name1="服务"+1;
            business.name2="服务"+2;
            business.name3="服务"+3;
            shangjias.add(business);
        }
    }

    public void initgridmenu() {
        View gridmenu = View.inflate(getContext(), R.layout.view_gridview, null);
        GridView gridview;
        gridview = (GridView) gridmenu.findViewById(R.id.gridView);
        final String[] titles = new String[]{
                "维修", "教学", "搬运", "安装", "清洁", "会计", "房建", "其他"
        };
        Integer[] images = {
                R.drawable.fix, R.drawable.jiaoxue,
                R.drawable.banjia, R.drawable.anzhuang,
                R.drawable.qingjie, R.drawable.kuaiji, R.drawable.jianzhu, R.drawable.qita
        };
        gridview.setAdapter(new GridviewAdapter(titles, images, getContext()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {    if (position!=7) {
                Intent intent = new Intent(getContext(), SearchResultActivity.class);
                intent.putExtra("yemian", titles[position]);
                startActivity(intent);
              }else {
                startActivity(new Intent(getContext(), ClassifyActivity.class));
            }
            }
        });
        rvadapter.addView(gridmenu);
    }

    public void intitspace() {
        View space = View.inflate(getContext(), R.layout.view_space2, null);
        rvadapter.addView(space);
    }
    public void initrepeatview(){
        rvadapter.addRepeatView(3,10,R.layout.item_collection2);
    }

    private void ZhuyeRefresh() {
        mPtrFrame.setResistance(2);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.1f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        mPtrFrame.setPullToRefresh(false);
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        ZhuyeHeader header = new ZhuyeHeader(getContext());
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);
        mPtrFrame.disableWhenHorizontalMove(true);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 1800);
            }
        });
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                //amapLocation.getLatitude();//获取纬度
                Log.d(amapLocation.getProvince(),amapLocation
                        .getCity()+amapLocation.getDistrict()+ amapLocation.getStreet());//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                textView.setText(amapLocation.getStreet());
                myApplication.city=amapLocation.getCity();
                myApplication.street=amapLocation.getStreet();
                LocationHistory locationHistory=new LocationHistory();
                locationHistory.setStreet(amapLocation.getStreet());
                locationHistory.setCity(amapLocation.getCity());
                mlocationClient.stopLocation();
                if(DataSupport.count(LocationHistory.class)==0){
                    locationHistory.save();
                }else {
                    locationHistory.update(DataSupport.count(LocationHistory.class));
                }

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError","location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
                mlocationClient.stopLocation();
                if(!islocation){
                     setcity();
                    islocation=true;
                }else {

                }

            }
        }
    }
}
