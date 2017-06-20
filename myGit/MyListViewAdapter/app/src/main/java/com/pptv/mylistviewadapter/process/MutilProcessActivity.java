package com.pptv.mylistviewadapter.process;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.pptv.mylistviewadapter.R;

/**
 * @anthor LeiKang
 */
public class MutilProcessActivity extends Activity
{
    private int anInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                synchronized (MutilProcessActivity.this)
                {
                    anInt = 4;
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        synchronized (MutilProcessActivity.this)
        {
            Log.e("TAG", anInt + "");
        }

    }
}
