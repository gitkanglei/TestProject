package com.pptv.mylistviewadapter.retrofit;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @anthor LeiKang
 */
public interface ApiService
{

    /**
     * 首页轮播图
     * <p/>
     * http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5
     * .8.1.0&channel=ppzs&operator=3&method=baidu.ting.plaza.index&cuid=
     * 89CF1E1A06826F9AB95A34DC0F6AAA14"
     */
    @GET("list")
    Observable<FirstJson> getFrontpage(@QueryMap Map<String, String> map);

    /**
     * http://www.2dfan.com/subjects/7186
     */
    @GET("subjects/{newsId}")
    Observable<String> getFrontpage1(@Path("newsId") String newsId);
}
