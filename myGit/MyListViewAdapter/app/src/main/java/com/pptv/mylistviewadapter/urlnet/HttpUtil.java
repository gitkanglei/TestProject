package com.pptv.mylistviewadapter.urlnet;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @anthor LeiKang
 */
public class HttpUtil
{
    //封住connection 的GET的请求
    public static void httpGet(String url)
    {
        try
        {
            URL myUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("content-type", "text/html");
            conn.connect();
            InputStream inputStream = conn.getInputStream();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
