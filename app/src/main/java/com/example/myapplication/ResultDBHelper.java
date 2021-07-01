package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ResultDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "results";
    public static final String TABLE_RESULT = "result";

    public static final String KEY_ID = "_id";
    public static final String KEY_TIME = "time";
    public static final String KEY_COUNTTOTAL = "counttotal";
    public static final String KEY_PASSED = "passed";
    public static final String KEY_PERCENT = "percent";

    public ResultDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_RESULT + "(" + KEY_ID + " integer primary key," +
        KEY_TIME + " text," + KEY_COUNTTOTAL + " integer," + KEY_PASSED + " integer," + KEY_PERCENT + " integer" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_RESULT);
        onCreate(db);
    }
}