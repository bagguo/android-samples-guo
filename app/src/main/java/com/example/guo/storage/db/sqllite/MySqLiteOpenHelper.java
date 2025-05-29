package com.example.guo.storage.db.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MySqLiteOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = MySqLiteOpenHelper.class.getSimpleName();
//    public static final String sql = "create table SqliteDemo (id integer primary key autoincrement, name text(4),address text(5))";
//    public static final String sql1 = "create table test1 (id integer primary key autoincrement, name text(4),address text(5))";

    //建表语句
    private static final String sql = "create table SqliteDemo(id integer primary key autoincrement, name test(4),address text(5))";
    private static final String sql1 = "create table test1 (id integer primary key autoincrement, name text(4),address text(5))";

    //创建数据库调用方法
    MySqLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //第一次创建数据库时，在这里可以建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate: ====");
        db.execSQL(sql);
    }

    //版本更新时
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade: ====");
        switch (oldVersion) {
            case 1:
                db.execSQL(sql1);
                break;
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        Log.i(TAG, "onDowngrade: ====");
    }
}
