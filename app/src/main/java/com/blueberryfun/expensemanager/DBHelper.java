package com.blueberryfun.expensemanager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails(_id Integer primary key autoincrement, type TEXT, amount TEXT, date TEXT, remark TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails");
    }
    public Boolean insertUserData(String type, String amount, String  date, String remark)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", type);
        contentValues.put("amount", amount);
        contentValues.put("date", date);
        contentValues.put("remark", remark);
        long result=DB.insert("UserDetails", null, contentValues);
        return result != -1;
    }
    public Boolean updateUserData(String type, String amount, String date, String remark)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", type);
        contentValues.put("amount", amount);
        contentValues.put("date", date);
        contentValues.put("remark", remark);
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from UserDetails where type = ?", new String[]{type});
        if (cursor.getCount() > 0) {
            long result = DB.update("UserDetails", contentValues, "type=?", new String[]{type});
            return result != -1;
        } else {
            return false;
        }
    }
    public Boolean deleteData(String type)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from UserDetails where type = ?", new String[]{type});
        if (cursor.getCount() > 0) {
            long result = DB.delete("UserDetails", "type=?", new String[]{type});
            return result != -1;
        } else {
            return false;
        }
    }

    public Cursor getData ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from UserDetails", null);
    }
}