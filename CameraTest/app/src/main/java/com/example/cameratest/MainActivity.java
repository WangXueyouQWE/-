package com.example.cameratest;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int TAKE_PHOTO=1;
    public Button button,choose;
    private ImageView imageView,album_image;
    private Uri ImageUri;
    public static final int CHOOSE_PHOTO=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getView();

    }

    private void getView() {
        button.setOnClickListener(this);
        choose.setOnClickListener(this);
    }

    private void init() {
        button=findViewById(R.id.take_photo);
        imageView=findViewById(R.id.image_take);
        choose=findViewById(R.id.choose_photo);
        album_image=findViewById(R.id.album_imageView);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_photo:
                File Output_Image =new File(getExternalCacheDir(),"OutPut_Image.jpg");
                try {
                    if (Output_Image.exists()){
                        Output_Image.delete();
                    }
                    Output_Image.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >=24){
                    ImageUri= FileProvider.getUriForFile(MainActivity.this,
                            "com.example.cameratest",Output_Image);
                }else{
                    ImageUri=Uri.fromFile(Output_Image);
                }
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,ImageUri);
                startActivityForResult(intent,TAKE_PHOTO);
                break;
            case R.id.choose_photo:
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum();
                }
                break;
                default:
                    break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode==RESULT_OK){
                    try {
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(ImageUri));
                        MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"jj","jj");
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (requestCode == RESULT_OK){
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19){
                        //4.4和以上系统使用这个方法处理图片
                        handleImageKitKat(data);
                    }else{
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void handleImageBeforeKitKat(Intent data) {

        Uri uri=data.getData();
        String imagePath=getImagepath(uri,null);
        displayImage(imagePath);
    }

    private void openAlbum(){
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @TargetApi(19)
    private void handleImageKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)) {
            //如果是document类型的Uri，则通过ducument id进行处理.
            String docId = DocumentsContract.getDocumentId(uri);

            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];   //正则表达式，解析数字格式的ID;
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagepath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagepath(contentUri, null);
             }
            }
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                //如果是content类型的uri，则使用普通方式处理
                imagePath = getImagepath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                //如果是file类型的uri，则直接获取图片路劲即可
                imagePath = uri.getPath();
            }
            displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);

            album_image.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this,"获取照片失败",Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagepath(Uri uri,String selection){
        String path=null;
        //通过Uri和selection来获取真实的路径
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                }else{
                    Toast.makeText(this,"没有权限操作",Toast.LENGTH_SHORT);
                }
                break;
                default:
                    break;
        }
    }
}
