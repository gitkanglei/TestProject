package com.pptv.mylistviewadapter.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @anthor LeiKang
 */
public class ThreadPoolManager
{
    private static final String CLASSTAG = ThreadPoolManager.class.getSimpleName();

    private static final int POOL_SIZE = 10;

    private static ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);

    public static void addTask(Runnable r)
    {
        executorService.submit(r);
    }

    public static Future<Object> addTask(Runnable r, Object result)
    {
        return executorService.submit(r, result);
    }
}
