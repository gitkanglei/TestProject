package com.pptv.mylistviewadapter.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @anthor LeiKang
 */
public class DBopenhelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "kl.db";

    private static final int code = 1;

    private static final String TABLE_NAME ="pptv";

    public DBopenhelper(Context context)
    {
        super(context, DB_NAME, null, code);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // 创建一个表
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + "id integer primary key, " + "msg varchar"
                + ")";
        db.execSQL(sql);
        Log.e("TAG","Create TABLE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + "id integer primary key, " + "msg varchar"
                + ")";
        db.execSQL(sql);
    }
}
