package com.hrsoftbd.rz.restaurentsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class LocalDatabase extends SQLiteOpenHelper {

    Context context;


    public LocalDatabase(Context context1, String db_name) {
        super(context1, db_name, null, 1);
        context = context1;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE tbl_product(id int,Name TEXT,Description TEXT,Price TEXT,Photo BLOB,Last_Update VARCHAR)");
        sqLiteDatabase.execSQL("CREATE TABLE tbl_categories(Category_id int,Name_bn TEXT,Name_en TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE tbl_temporary_order(id int,Name TEXT,Price TEXT,Quantity int)");
        Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




}
