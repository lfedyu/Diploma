package com.fedyushko.lilia.p0031_firstprogect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lilia on 11.05.2017.
 */

public class DBHelper extends SQLiteOpenHelper{


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "usersDb";
    public static final String TABLE_USERS = "users";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIL = "mail";
    public static final String KEY_PASSWORD = "password";

    //public static final String DATABASE2_NAME = "placesDb";
    public static final String TABLE_PLACES = "places";
    public static final String KEY_PLACE_ID = "_id";
    public static final String KEY_PLACE_NAME = "name";
    public static final String KEY_PLACE_WIDTH = "width";
    public static final String KEY_PLACE_HIEGHT = "height";

    public static final String TABLE_ROUTES = "routes";
    public static final String KEY_ROUTE_ID = "_id";
    public static final String KEY_ROUTE_NAME = "rout";
    public static final String KEY_ROUTE_USER_ID = "length";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS + "(" + KEY_ID
                + " integer primary key," + KEY_NAME + " text," + KEY_MAIL + " text,"  + KEY_PASSWORD + " text" + ")");

        db.execSQL("create table " + TABLE_PLACES + "(" + KEY_PLACE_ID
                + " integer primary key," + KEY_PLACE_NAME + " text," + KEY_PLACE_WIDTH + " float,"  + KEY_PLACE_HIEGHT + " float" + ")");

        db.execSQL("create table " + TABLE_ROUTES + "(" + KEY_ROUTE_ID
                + " integer primary key," + KEY_ROUTE_NAME + " text," + KEY_ROUTE_USER_ID + " integer"  + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_USERS);
        db.execSQL("drop table if exists " + TABLE_PLACES);
        db.execSQL("drop table if exists " + TABLE_ROUTES);
        onCreate(db);
    }
}
