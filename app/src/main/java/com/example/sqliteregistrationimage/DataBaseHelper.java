package com.example.sqliteregistrationimage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="databese.db";
    public static final String TABLE_NAME="student";

    public static final String COL_ID="Id";
    public static final String COL_NAME="Name";
    public static final String COL_IMAGE="Image";



    public DataBaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Image BLOG)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean InsertData(String name, byte[] image){
        SQLiteDatabase db=getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_IMAGE,image);
        contentValues.put(COL_NAME,name);
        long result=  db.insert(TABLE_NAME,null,contentValues);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }


    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }
    public Integer deletedata(String id){
        SQLiteDatabase db=this.getReadableDatabase();
        return  db.delete(TABLE_NAME,"id=?",new String[]{id});
    }
}
