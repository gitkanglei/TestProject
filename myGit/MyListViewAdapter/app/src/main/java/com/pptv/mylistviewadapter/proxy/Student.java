package com.pptv.mylistviewadapter.proxy;

import android.util.Log;

/**
 * @anthor LeiKang
 */
public class Student implements Person
{
    @Override
    public void walk()
    {
//
        System.out.print("walk");
    }

    @Override
    public void sayHello(String name)
    {
        Log.e("TAG", name);
    }
}
