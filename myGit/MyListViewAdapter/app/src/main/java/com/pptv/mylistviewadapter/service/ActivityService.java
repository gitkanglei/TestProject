package com.pptv.mylistviewadapter.service;

import java.util.HashMap;
import java.util.Map;
import com.pptv.mylistviewadapter.R;
import com.pptv.mylistviewadapter.retrofit.ApiService;
import com.pptv.mylistviewadapter.retrofit.FirstJson;
import com.pptv.mylistviewadapter.retrofit.RetrofitHttpUtil;
import com.pptv.mylistviewadapter.verifyCodeView.RippleView;
import com.pptv.mylistviewadapter.verifyCodeView.verifyCodeView;
import com.pptv.mylistviewadapter.view.MyView;
import com.pptv.mylistviewadapter.view.leftSwipeLayout;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @anthor LeiKang
 */
public class ActivityService extends Activity
{
    MyService.MyBinder binder;

    private TextView tx;

    private verifyCodeView verifyCodeView;

    private RippleView rippleView;

    private leftSwipeLayout myView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        // threadLog();
        // startService(new Intent(this,MyService.class));
        // DownLoadManager.addTask("http://d.koudai.com/com.koudai.weishop/1000f/weishop_1000f.apk",
        // new IDwonLoadInterface()
        // {
        // @Override
        // public void onpress(int len)
        // {
        // Log.e("TAG", "current onPress:" + len);
        // }
        //
        // @Override
        // public void onToatalLength(int len)
        // {
        //
        // }
        // });
        threadLog();
        verifyCodeView = (verifyCodeView) findViewById(R.id.verify);
        tx = (TextView) findViewById(R.id.tx);
        myView = (leftSwipeLayout) findViewById(R.id.code_view);
        rippleView = (RippleView) findViewById(R.id.ripple);
        tx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                rippleView.startAnimation();
            }
        });
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG","被点击了。。。。");
            }
        });
    }

    public void scaleAnimation()
    {
        PropertyValuesHolder xValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0.8f, 1f);
        PropertyValuesHolder yValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0.8f, 1f);
        ObjectAnimator objectAnimator =ObjectAnimator.ofPropertyValuesHolder(verifyCodeView,xValuesHolder,yValuesHolder);
        objectAnimator.setDuration(1000);
        objectAnimator.start();

    }

    public void scaleAnimation1()
    {
        PropertyValuesHolder xValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0f);
        PropertyValuesHolder yValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0f);
        ObjectAnimator objectAnimator =ObjectAnimator.ofPropertyValuesHolder(myView,xValuesHolder,yValuesHolder);
        objectAnimator.setDuration(1000);
        objectAnimator.start();

    }
    @Override
    protected void onResume()
    {
        super.onResume();
        // 显示出来后做一个先缩小在放大的动画
        scaleAnimation();
//        scaleAnimation1();
    }

    ServiceConnection serviceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            // 把 service 中binder 转换成activity中binder
            binder = (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            // 打印堆栈信息
            Log.e("TAG", Log.getStackTraceString(new Throwable()));
        }
    };

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.e("TAG", "onPause");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.e("TAG", "onStop");
    }

    public void threadLog()
    {
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                // 推荐电台
//                try
//                {
//                    Map<String, String> paramsmap = new HashMap<String, String>();
//                    paramsmap.put("platform", "aph");
//                    paramsmap.put("appid", "com.pplive.androidphone");
//                    paramsmap.put("appver", "6.3.3");
//                    paramsmap.put("appplt", "aph");
//                    paramsmap.put("businessid", "1");
//                    // 不会自动取消订阅
//                    Subscription subscription = RetrofitHttpUtil.retrofit("http://fans.mobile.pptv.com/tab/v1/").create(
//                            ApiService.class).getFrontpage(paramsmap).
//                    // 请求完成之后在主线程中进行
//                    observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeOn(
//                            Schedulers.newThread()).subscribe(new Observer<FirstJson>()
//                            {
//                                @Override
//                                public void onCompleted()
//                                {
//
//                                }
//                                @Override
//                                public void onError(Throwable e)
//                                {
//                                    // listener.loadFailed();
//                                    Log.e("TAG", e.toString());
//                                }
//                                @Override
//                                public void onNext(final FirstJson frontpageBean)
//                                {
//                                    Log.e("TAG", Log.getStackTraceString(new Throwable()));
//                                    Log.e("TAG", Thread.currentThread() == Looper.getMainLooper().getThread() ? "主线程"
//                                            : "子线程");
//                                    tx.setText(frontpageBean.toString());
//
//                                }
//                            });
//
//                  Log.e("TAG","是订阅已取消"+subscription.isUnsubscribed());
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                // 推荐电台
                try
                {

                    Subscription subscription = RetrofitHttpUtil.retrofit("http://www.2dfan.com/").create(
                            ApiService.class).getFrontpage1("7186").
                            // 请求完成之后在主线程中进行
                                    observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeOn(
                                    Schedulers.newThread()).subscribe(new Observer<String>()
                            {
                                @Override
                                public void onCompleted()
                                {

                                }
                                @Override
                                public void onError(Throwable e)
                                {
                                    // listener.loadFailed();
                                    Log.e("TAG", e.toString());
                                }
                                @Override
                                public void onNext(final String frontpageBean)
                                {
                                    Log.e("TAG", Log.getStackTraceString(new Throwable()));
                                    Log.e("TAG", Thread.currentThread() == Looper.getMainLooper().getThread() ? "主线程"
                                            : "子线程");
                                    tx.setText(frontpageBean);

                                }
                            });

                    Log.e("TAG","是订阅已取消"+subscription.isUnsubscribed());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }).start();

    }

}
