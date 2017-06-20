package com.pptv.mylistviewadapter.handlerThread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.pptv.mylistviewadapter.R;

/**
 * @anthor LeiKang
 */
public class HandlerThreadActivity extends Activity
{
    private HandlerThread mHandlerThread;

    private Handler mThreadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandlerThread = new HandlerThread("check-message-coming");
        mHandlerThread.start();
        mThreadHandler = new Handler(mHandlerThread.getLooper())
        {
            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                Log.e("TAG",Thread.currentThread()== Looper.getMainLooper().getThread() ? "主线程":"不是主线程");
                mThreadHandler.sendEmptyMessageDelayed(0x11, 1000);
            }
        };
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mThreadHandler.sendEmptyMessage(0x11);
    }
}
