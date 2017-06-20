package com.pptv.mylistviewadapter.observer;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @anthor LeiKang
 */
public class EventSubject implements EventSubjectInterface
{

    public static EventSubject instance;

    public Set<WeakReference<EventObserver>> observableList = new HashSet<>();

    //订阅者的一个单例(被观察者)
    public static EventSubject getInstance()
    {
        synchronized (instance)
        {
            if (instance == null)
            {
                instance = new EventSubject();
            }
        }

        return instance;
    }

    @Override
    public void registerEventObserver(EventObserver observer)
    {
        observableList.add(new WeakReference<EventObserver>(observer));
    }

    @Override
    public void unRegisterEventObserver()
    {
        Iterator iterator = observableList.iterator();
        while (iterator.hasNext())
        {
            iterator.remove();
        }
    }

    @Override
    public void notifyDataChange()
    {
        for (WeakReference<EventObserver> observerWeakReference : observableList)
        {
            observerWeakReference.get().dispatchange();
        }
    }
}
