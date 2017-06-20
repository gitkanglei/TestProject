package com.pptv.mylistviewadapter.boardcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

/**
 * @anthor LeiKang
 */
public class BoardCastReciever extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.e("TAG",Thread.currentThread()== Looper.getMainLooper().getThread() ? "主线程":"不是主线程");
    }
}
