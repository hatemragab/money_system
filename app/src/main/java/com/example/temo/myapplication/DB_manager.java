package com.example.temo.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DB_manager extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String DATE = "date";
    public static final String COST = "cost";
    public static final String METHOD = "method";
    public static final String TABLE_NAME = "tabe";
    public static final String TABLE_DETELS = "tabedetels";
    public static final String DETELS_NEWCOST = "newcost";
    public static final String DETELS_DAT = "newdate";
    public static final String DETELS_ID = "newid";

    Context context;

    public DB_manager(Context context) {

        super(context, "hatem", null, 1);

        sqLiteDatabase = getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT," + PHONE + " TEXT," + DATE + " TEXT ," + COST + " TEXT ," + METHOD + " TEXT ); ");
        //  db.execSQL("CREATE table " + TABLE_DETELS + "( " + DETELS_ID + " INTEGER," + DETELS_NEWCOST + " TEXT," + DETELS_DAT + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*public int getId2() {
        String com[] = new String[]{DETELS_ID};
        Cursor cursor = sqLiteDatabase.query(TABLE_DETELS, com, null, null, null, null, null);
        int idd = 100;


        idd = cursor.getInt(cursor.getColumnIndex(DETELS_ID));


        return idd;
    }*/

    public void insert(String name, String phone, String date, String cost, String method) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(PHONE, phone);
        contentValues.put(DATE, date);
        contentValues.put(COST, cost);
        contentValues.put(METHOD, method);
       sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

    }

    public ArrayList<Mitem> getArrayList() {

        ArrayList<Mitem> arrayList = new ArrayList<>();
        String[] com = new String[]{ID, NAME, PHONE, DATE, COST, METHOD};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, com, null, null, null, null, null);

        while (cursor.moveToNext()) {
            arrayList.add(new Mitem(cursor.getInt(cursor.getColumnIndex(ID)), cursor.getString(cursor.getColumnIndex(NAME)), cursor.getString(cursor.getColumnIndex(PHONE)), cursor.getString(cursor.getColumnIndex(DATE)), cursor.getString(cursor.getColumnIndex(COST)), cursor.getString(cursor.getColumnIndex(METHOD))));
        }


        return arrayList;
    }

    public void delete(String s) {
        String arg[] = {s};
        sqLiteDatabase.delete(TABLE_NAME, ID + "=?", arg);//////////
    }

    public void deleteAll() {
        sqLiteDatabase.delete(TABLE_NAME, null, null);
    }

    public void update(String name, String phone, String cost, String method, String id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(PHONE, phone);
        contentValues.put(COST, cost);
        contentValues.put(METHOD, method);
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + "=?", new String[]{id});
    }

    public void updateDate(String newDate, String id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, newDate);
        String[] arg = new String[]{id};
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + "=?", arg);
    }


}
