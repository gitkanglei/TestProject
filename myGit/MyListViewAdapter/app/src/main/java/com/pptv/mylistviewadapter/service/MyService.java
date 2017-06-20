package com.pptv.mylistviewadapter.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.File;

/**
 * @anthor LeiKang
 */
public class MyService extends Service
{
    private BroadcastReceiver receiver;

    private MyBinder binder;

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        // intent activity启动service时来传过来的Intent

        receiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/myApp.apk")),
                        "application/vnd.android.package-archive");
                startActivity(intent);
                stopSelf();
            }
        };
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        startDownload();
        return startId;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return binder;
    }

    @Override
    public void onDestroy()
    {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
    // 启动另一个下载

    private void startDownload()
    {
        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse("http://d.koudai.com/com.koudai.weishop/1000f/weishop_1000f.apk"));
        request.setMimeType("application/vnd.android.package-archive");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "myApp.apk");
        long enqueue = dm.enqueue(request);
    }

    static  class MyBinder extends Binder
    {


    }
}
