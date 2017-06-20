package com.pptv.mylistviewadapter.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @anthor LeiKang
 */
public class MyProxyHandler implements InvocationHandler
{

    // 动态代理
    Object mBse;

    MyProxyHandler(Object object)
    {
        mBse = object;
    }

    public Object newInstance()
    {
        return Proxy.newProxyInstance(mBse.getClass().getClassLoader(), mBse.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        // 执行被代理对象的方法(执行方法传人相应对象)
        Object result = method.invoke(mBse, args);
        return result;
    }
}
