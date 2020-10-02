package com.example.sqliteregistrationimage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper db;
    ImageView imageView;
    EditText etname;
    Button btnrst;
    Bitmap bitmap;
    Uri imageuri;
    String imagedata;
    private  static final  int PICKE_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DataBaseHelper(getApplicationContext());
        imageView=findViewById(R.id.Image_P);
        etname=findViewById(R.id.Et_Name);
        btnrst=findViewById(R.id.Btn_Rst);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select image"),PICKE_IMAGE);

            }
        });
        btnrst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etname.getText().toString();
                if (imageuri==null){
                    Toast.makeText(MainActivity.this, "choose image", Toast.LENGTH_SHORT).show();
                }
               else if (name.isEmpty()){
                    Toast.makeText(MainActivity.this, "enter name", Toast.LENGTH_SHORT).show();

                }
               else {
                   db.InsertData(name,ImageTostring(imageView));
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICKE_IMAGE&& resultCode==RESULT_OK){
            imageuri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(imageuri);
                bitmap= BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    private byte[] ImageTostring(ImageView image){
        bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[]imagebyte=outputStream.toByteArray();
       // String encodeimage= Base64.encodeToString(imagebyte,Base64.DEFAULT);
        return imagebyte;
    }

    public void login(View view) {
        Intent intent=new Intent(getApplicationContext(),Login_Activity.class);
        startActivity(intent);
    }
}