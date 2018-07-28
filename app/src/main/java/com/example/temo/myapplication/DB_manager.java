package com.example.temo.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_manager extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String DATE = "date";
    public static final String COST = "cost";
    public static final String METHOD = "method";

    public DB_manager(Context context) {

        super(context, "hatem", null, 1);
        sqLiteDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
