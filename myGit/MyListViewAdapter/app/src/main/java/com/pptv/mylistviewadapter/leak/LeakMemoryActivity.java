package com.pptv.mylistviewadapter.leak;

import android.app.Activity;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.pptv.mylistviewadapter.R;
import com.pptv.mylistviewadapter.database.DBopenhelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @anthor LeiKang
 */
public class LeakMemoryActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LeakIntance.getInstance().setContext(this);
        LeakIntance.getInstance().Toast();
        DBopenhelper dBopenhelper = new DBopenhelper(this);
        // dBopenhelper.getWritableDatabase().execSQL("insert into pptv (msg)
        // values ('hongguang') ");
        // 数据库的使用
        SQLiteDatabase db = dBopenhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("msg", "msgaa");
        Long count = db.insert("pptv", "null", values);
        Log.e("TAG", count + "");
        // 索引查询器
        Cursor cursor = db.rawQuery("select * from pptv", null);
        // 这个是注册观察者(数据库的观察者模式)
        registerObserver(observer);
        if (cursor.getCount() != 0)
        {
            while (cursor.moveToNext())
            {
                String msg = cursor.getString(cursor.getColumnIndex("msg"));
                Log.e("TAG", msg + "");
                dispatChange();
            }
        }
        cursor.close();
    }

    // 注册观察者
    public void registerObserver(ContentObserver observer)
    {
        set.add(new WeakReference<ContentObserver>(observer));
    }
    //
    public void dispatChange()
    {
        for (WeakReference<ContentObserver> observer : set)
        {
            observer.get().dispatchChange(true);
        }

    }

    List<WeakReference<ContentObserver>> set = new ArrayList<>();

    // 数据库的观察者模式
    private ContentObserver observer = new ContentObserver(new Handler())
    {

        public void onChange(final boolean selfChange)
        {
            // 观察者更新
            Log.e("TAG", "observer");
        }
    };
}
