package com.example.myapplication9.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication9.ChangeHeadImgTask;
import com.example.myapplication9.ChangeNameTask;
import com.example.myapplication9.R;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by bingnanfeng02 on 2017/8/19.
 */
public class TakePhotoActivity extends AppCompatActivity {
    ImageView imageView;
    RelativeLayout relativeLayout;
    Bitmap bitmap;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        imageView=(ImageView)findViewById(R.id.iv);
        relativeLayout=(RelativeLayout)findViewById(R.id.rl);
        path=getIntent().getStringExtra("uri");
        displayImage(path);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();

                ChangeHeadImgTask changeHeadImgTask=new ChangeHeadImgTask(saveBitmapFile(bitmap),5+"",null);
                changeHeadImgTask.execute();
                Log.d("asasas","assaas");
            }
        });
//        Uri uri=Uri.parse(getIntent().getStringExtra("uri"));
//        try {
//            Bitmap bitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
//            imageView.setImageBitmap(bitmap);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
    public File saveBitmapFile(Bitmap bitmap){
        //store to sd card
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] photoBytes = baos.toByteArray();
        File aFile = new File("/sdcard","head.png");
        if (aFile.exists()) {
            aFile.delete();
        }else {
            try {
                aFile.createNewFile(); //need add permission to manifest
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //f.mkdirs();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(aFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(photoBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aFile;
    }

}
