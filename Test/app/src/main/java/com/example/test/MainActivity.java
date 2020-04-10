package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        { //ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.INTERNET},123);

            Toast.makeText(getApplicationContext(),"You've Granted Permission Successfully", Toast.LENGTH_LONG).show();
        }
        else
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.INTERNET,Manifest.permission.READ_EXTERNAL_STORAGE},123);

        }


        final ImageView imageView=findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Intent.ACTION_PICK);
                File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                String s = file.getPath();
                Uri uri = Uri.parse(s);

                i1.setDataAndType(uri, "image/*");
                startActivityForResult(i1, 123);


            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 123 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
           String s= uri.getPath();
            InputStream inputStream;
            Cursor cursor = null;
            try {
                String[] arr = { MediaStore.Images.Media.DATA };
                cursor = getApplicationContext().getContentResolver().query(uri,  arr, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String p= cursor.getString(column_index);
                File file=new File(p);
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

// MultipartBody.Part is used to send also the actual file name
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("image", file.getName(), requestFile);

// add another part within the multipart request
                RequestBody fullName =
                        RequestBody.create(MediaType.parse("multipart/form-data"), "Your Name");
                Call<ResponseBody> call=RetrofitClient.getInstance().getApi().updateProfile(fullName,body);
call.enqueue(new Callback<ResponseBody>() {
    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Toast.makeText(getApplicationContext(),"Jay Balaji",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
    }
});



            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

//            Back_Pro b1 = new Back_Pro(this, uri);

//            b1.execute();
        }
    }

}
