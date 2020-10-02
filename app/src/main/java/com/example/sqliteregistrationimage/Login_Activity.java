package com.example.sqliteregistrationimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Login_Activity extends AppCompatActivity {
    DataBaseHelper db;
   GridView gridView;
   ArrayList<Alldata_Modal>list;
  // Alldata_Adapter adapter=null;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        db=new DataBaseHelper(getApplicationContext());
        gridView=findViewById(R.id.Grid_view);
        recyclerView=findViewById(R.id.Rv_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        list=new ArrayList<>();
//        adapter=new Alldata_Adapter(this,R.layout.list_item,list);
//        gridView.setAdapter(adapter);
       Allview_Adapter adapter=new Allview_Adapter(list, getApplicationContext());
       recyclerView.setAdapter(adapter);
        Cursor cursor=db.getAllData();
        list.clear();
        while (cursor.moveToNext()){
            String id=cursor.getString(0);
            String name=cursor.getString(1);
            byte[]image=cursor.getBlob(2);
            list.add(new Alldata_Modal(id,name,image));
        }
       // adapter.notifyDataSetChanged();
    }
}