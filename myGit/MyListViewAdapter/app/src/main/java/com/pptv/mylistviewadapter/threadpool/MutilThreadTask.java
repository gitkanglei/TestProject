package com.pptv.mylistviewadapter.threadpool;

import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @anthor LeiKang
 */
public class MutilThreadTask
{

    private ArrayList<Future<Object>> myList = new ArrayList<>();

    private ArrayList<Person> personArrayList = new ArrayList<>();

    public void startTask() throws ExecutionException, InterruptedException
    {

        for (int i = 0; i < 2; i++)
        {
            personArrayList.add(new Person());
        }
        for (int i = 0; i < personArrayList.size(); i++)
        {
            final Person aa = personArrayList.get(i);
            myList.add(ThreadPoolManager.addTask(new Runnable()
            {
                @Override
                public void run()
                {
                     aa.name++;
                }
            }, aa));
        }

        for (int i = 0; i < myList.size(); i++)
        {
            Person a = ((Person) myList.get(i).get());
            System.out.println("KL Test" + a.name);
        }

    }

    private static class MyRunnable implements Runnable
    {
        final Person p;

        private MyRunnable(Person p)
        {
            this.p = p;
        }

        @Override
        public void run()
        {
            p.name++;
        }
    }

}
