package com.pptv.mylistviewadapter.download;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @anthor LeiKang
 */
public class DownLoadManager
{

    public static  void addTask(final String urlString,final IDwonLoadInterface iDwonLoadInterface)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    // conn.setRequestProperty("Range", "bytes=" +
                    // info.startLocation + "-" + dEntity.endLocation);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Charset", "UTF-8");
                    conn.setConnectTimeout(3000);
                    conn.setRequestProperty("User-Agent",
                            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
                    conn.setRequestProperty("Accept", "*/*");
                    conn.setReadTimeout(2000); // 设置读取流的等待时间,必须设置该参数
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    int totalLength = conn.getContentLength();
                    iDwonLoadInterface.onToatalLength(totalLength);
                    int len;
                    int cur = 0;
                    File file = new File(Environment.getExternalStorageDirectory(), "myApp.apk");
//                    if(!file.exists())
//                    {
//                        file.createNewFile();
//                    }
                    FileOutputStream outputStream = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    while ((len = is.read(buffer)) != -1)
                    {
                        outputStream.write(buffer, 0, len);
                        cur = cur + len;
                        //更新下载进度(用来给activityThread 来更新进程)
                        iDwonLoadInterface.onpress(cur);
                    }
                    outputStream.close();
                    is.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("TAG",e.toString());
                }

            }
        }).start();

    }
}
