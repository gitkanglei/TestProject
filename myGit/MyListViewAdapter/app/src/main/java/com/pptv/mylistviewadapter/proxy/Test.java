package com.pptv.mylistviewadapter.proxy;

/**
 * @anthor LeiKang
 */
public class Test
{

    public static void main(String[] args)
    {

        Person student = new Student();

        MyProxyHandler proxyHandler = new MyProxyHandler(student);
        ((Person) proxyHandler.newInstance()).walk();

    }
}
