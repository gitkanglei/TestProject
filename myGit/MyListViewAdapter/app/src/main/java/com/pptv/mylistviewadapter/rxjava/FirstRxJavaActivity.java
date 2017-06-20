package com.pptv.mylistviewadapter.rxjava;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @anthor LeiKang
 */
public class FirstRxJavaActivity
{

    public void demoFun()
    {
        // 1 先创建观察者
        Observer<String> observer = new Observer<String>()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onNext(String o)
            {
                Log.e("TAG", "o");
            }
        };

        // 2 创建被观察者
        Observable observable = Observable.create(new Observable.OnSubscribe<String>()
        {
            @Override
            public void call(Subscriber<? super String> subscriber)
            {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        observable.subscribe(observer);

        Observable.just("aa").subscribeOn(Schedulers.io()).subscribe(new Subscriber<String>()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onNext(String s)
            {

            }
        });

    }
}
