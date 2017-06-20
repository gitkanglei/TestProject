package com.pptv.mylistviewadapter

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View

import com.pptv.mylistviewadapter.boardcast.BoardCastReciever

/**
 * @anthor LeiKang
 */
class BroadCastReciverActivity : Activity() {
    internal var reciever: BoardCastReciever ? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_reciver)
        val intentFilter = IntentFilter()
        reciever = BoardCastReciever()
        intentFilter.addAction("android_MY_reciver")
        this.registerReceiver(reciever, intentFilter)
        findViewById(R.id.tx).setOnClickListener { sendReciver() }

    }

    fun sendReciver() {
        Thread(Runnable {
            Log.e("TAG", if (Thread.currentThread() === Looper.getMainLooper().thread) "主线程" else "不是主线程")
            sendBroadcast(Intent("android_MY_reciver"))
        }).start()

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(reciever)
    }
}
