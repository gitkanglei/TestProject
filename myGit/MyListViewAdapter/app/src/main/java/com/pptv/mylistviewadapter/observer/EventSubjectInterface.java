package com.pptv.mylistviewadapter.observer;

/**
 * @anthor LeiKang
 */
public interface EventSubjectInterface
{
    void registerEventObserver(EventObserver observer);

    void unRegisterEventObserver();

    // 通知更新的方法
    void notifyDataChange();
}
