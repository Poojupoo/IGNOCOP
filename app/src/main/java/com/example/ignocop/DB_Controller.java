package com.example.ignocop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,"IGNOCOP.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE REGISTER(DEVICE_ID TEXT PRIMARY KEY,PASSWORD TEXT );");
        sqLiteDatabase.execSQL("CREATE TABLE TRUSTED ( NAME TEXT, PHONE TEXT PRIMARY KEY );");
        sqLiteDatabase.execSQL("CREATE TABLE POLICE ( NAME TEXT, PHONE TEXT PRIMARY KEY );");
        sqLiteDatabase.execSQL("CREATE TABLE PEOPLE ( ID TEXT, NAME TEXT, PHONE TEXT PRIMARY KEY );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS REGISTER;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TRUSTED;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS POLICE;");
        onCreate(sqLiteDatabase);
    }

    public void insert(String device_id, String password){
        ContentValues contentValues=new ContentValues();
        contentValues.put("DEVICE_ID",device_id);
        contentValues.put("PASSWORD",password);

        this.getWritableDatabase().insertOrThrow("REGISTER","",contentValues);
    }

    public boolean checkUser(String id, String password){
        String[]  columns = {"DEVICE_ID"};
        SQLiteDatabase db = getReadableDatabase();
        String selection = "DEVICE_ID" + "=?" + " and " + "PASSWORD" + "=?";
        String[] selectionArgs = {id,password};
        Cursor cursor = db.query("REGISTER",columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if(count>0)
            return true;
        else
            return false;

    }

    public void trust(String name, String phone){
        ContentValues contentValues=new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("PHONE",phone);
        this.getWritableDatabase().insertOrThrow("TRUSTED","",contentValues);
    }

    public void del_Trusted(String name, String phone){
        this.getWritableDatabase().delete("TRUSTED","NAME='" + name + "'",null);

    }

    public void update(String old_number, String n_number){
        this.getWritableDatabase().execSQL("UPDATE TRUSTED SET PHONE='"+n_number+"' WHERE PHONE='"+old_number+"'");
    }


    public void police(String station_name, String phone){
        ContentValues contentValues=new ContentValues();
        contentValues.put("NAME",station_name);
        contentValues.put("PHONE",phone);
        this.getWritableDatabase().insertOrThrow("POLICE","",contentValues);
    }

    public void del_police(String name, String phone){
        this.getWritableDatabase().delete("POLICE","NAME='" + name + "'",null);

    }

    public void del_device(String name, String phone){
        this.getWritableDatabase().delete("POLICE","NAME='" + name + "'",null);

    }

    public void update_police(String old_number, String n_number){
        this.getWritableDatabase().execSQL("UPDATE POLICE SET PHONE='"+n_number+"' WHERE PHONE='"+old_number+"'");
    }


    public void insert_ppl(String id, String name, String phone){
        ContentValues contentValues=new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("NAME",name);
        contentValues.put("PHONE",phone);
        this.getWritableDatabase().insertOrThrow("PEOPLE","",contentValues);
    }

    public void del_ppl(String id, String name, String phone){
        this.getWritableDatabase().delete("PEOPLE","NAME='" + name + "'",null);

    }

    public void update_ppl(String name, String n_number){
        this.getWritableDatabase().execSQL("UPDATE POLICE SET PHONE='"+n_number+"' WHERE NAME='"+name+"'");
    }


}
