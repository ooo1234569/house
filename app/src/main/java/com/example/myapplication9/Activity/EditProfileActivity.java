package com.example.myapplication9.Activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication9.Person;
import com.example.myapplication9.PositionListener;
import com.example.myapplication9.R;
import com.example.myapplication9.adapter.EditProfileAdapter;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by bingnanfeng02 on 2017/8/1.
 */
public class EditProfileActivity extends AppCompatActivity implements PositionListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EditProfileAdapter editadapter;
    private ArrayList<String> strings=new ArrayList<>();
    private View view;
    private int flag=0;
    private Uri uri;
    private String[] s={""};
    private TextView tuichu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        tuichu=(TextView)findViewById(R.id.tuichu);
        recyclerView=(RecyclerView)findViewById(R.id.rv);

        view=findViewById(R.id.ll);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        strings.add("头像");
        strings.add("用户名");
        strings.add("账户密码");
        strings.add("绑定手机号");
        s[0]= DataSupport.find(Person.class,DataSupport.count(Person.class)).getNickname();
        editadapter=new EditProfileAdapter(strings,this,view,s);
        editadapter.setP(this);
        recyclerView.setAdapter(editadapter);
        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditProfileActivity.this,"功能尚未完成，敬请等待",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onArticleSelected(int position) {
        if (position==0){
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.alpha = 0.5f;
            getWindow().setAttributes(lp);
            flag=1;
        }else if(position==1){
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.alpha = 1;
            flag=0;
            getWindow().setAttributes(lp);
        }else if(position==2){
            File file=new File(this.getExternalCacheDir(),"output_image.jpg");
            if(file.exists()){
                file.delete();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(Build.VERSION.SDK_INT>=24){
                uri= FileProvider.getUriForFile(this,"hhh",file);
            }else {
                uri= Uri.fromFile(file);
            }
            Intent intent =new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
            startActivityForResult(intent,1);
        }else if(position==3){
             openalbum();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
          Intent intent=new Intent(this,TakePhotoActivity.class);
            Log.d("uri",uri.toString());
            intent.putExtra("uri",uri.toString());
            startActivity(intent);
        }else if(requestCode ==2){
            if(Build.VERSION.SDK_INT>=19){
                handleimage(data);
            }
        }
    }
    protected void onResume(){
        super.onResume();
        s[0]=DataSupport.find(Person.class,DataSupport.count(Person.class)).getNickname();
        editadapter.notifyDataSetChanged();
    }
    public void openalbum(){
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,2);
    }
    @TargetApi(19)
    public void handleimage(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        Intent intent=new Intent(this,TakePhotoActivity.class);
        intent.putExtra("uri",imagePath);
        startActivity(intent); //
    }
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}
