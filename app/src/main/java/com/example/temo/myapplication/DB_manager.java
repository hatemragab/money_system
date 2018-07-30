package com.example.temo.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class DB_manager extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String DATE = "date";
    private static final String COST = "cost";
    private static final String METHOD = "method";
    private static final String TABLE_NAME = "tabe";

    private Context context;

    public DB_manager(Context context) {

        super(context, "hatem", null, 1);

        sqLiteDatabase = getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT," + PHONE + " TEXT," + DATE + " TEXT ," + COST + " TEXT ," + METHOD + " TEXT ); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

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
        cursor.close();

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
