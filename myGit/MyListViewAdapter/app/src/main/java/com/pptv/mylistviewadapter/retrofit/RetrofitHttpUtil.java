package com.pptv.mylistviewadapter.retrofit;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.pptv.mylistviewadapter.app.MyApplication;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @anthor LeiKang
 */
public class RetrofitHttpUtil
{
    // 有关Retrofit的配置
    public static Retrofit retrofit(String url)
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置缓存代码
        File cacheFile = new File(MyApplication.getContext().getExternalCacheDir(), "Cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request request = chain.request();
                if (!NetworkUtils.isConnectInternet(MyApplication.getContext()))
                {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isConnectInternet(MyApplication.getContext()))
                {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder().header("Cache-Control", "public, max-age=" + maxAge).removeHeader(
                            "WuXiaolong")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                }
                else
                {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder().header("Cache-Control",
                            "public, only-if-cached, max-stale=" + maxStale).removeHeader("nyn").build();
                }
                return response;
            }
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);
        // 设置公共参数
        // 公共参数
        Interceptor addQueryParameterInterceptor = new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request originalRequest = chain.request();
                Request request;
                String method = originalRequest.method();
                Headers headers = originalRequest.headers();
                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                        // Provide your custom parameter here
                        .addQueryParameter("platform", "android").addQueryParameter("version", "1.0.0").build();
                request = originalRequest.newBuilder().url(modifiedUrl).build();
                return chain.proceed(request);
            }
        };
        // 公共参数
        builder.addInterceptor(addQueryParameterInterceptor);
        Interceptor headerInterceptor = new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder().header("AppType", "TPOS").header(
                        "Content-Type", "application/json").header("Accept", "application/json").method(
                                originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        // 设置头
        builder.addInterceptor(headerInterceptor);
        // 设置超时
        builder.connectTimeout(1500, TimeUnit.SECONDS);
        builder.readTimeout(2000, TimeUnit.SECONDS);
        builder.writeTimeout(2000, TimeUnit.SECONDS);
        // 错误重连
        builder.retryOnConnectionFailure(true);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                // 设置 Json 转换器
                .addConverterFactory(GsonConverterFactory.create())
                // RxJava 适配器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(builder.build()).build();
        return retrofit;
    }

}
