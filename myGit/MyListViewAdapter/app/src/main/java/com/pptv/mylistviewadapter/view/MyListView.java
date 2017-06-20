package com.pptv.mylistviewadapter.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * @author LeiKang
 * @time 2017/3/6
 */
public class MyListView extends ListView
{
    public MyListView(Context context)
    {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        if (canChildScrollUp(this))
        {
            return super.dispatchTouchEvent(ev);
        }
        else
        {
            requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean canChildScrollUp(ListView mTarget)
    {

        if (android.os.Build.VERSION.SDK_INT < 14)
        {

            if (mTarget instanceof AbsListView)
            {

                final AbsListView absListView = (AbsListView) mTarget;

                return absListView.getChildCount() > 0 &&

                        (absListView.getFirstVisiblePosition() > 0 ||

                                absListView.getChildAt(0).getTop() < absListView.getPaddingTop());

            }
            else
            {

                return ViewCompat.canScrollVertically(mTarget, -1) || mTarget.getScrollY() > 0;

            }

        }
        else
        {

            return ViewCompat.canScrollVertically(mTarget, -1);

        }
    }
}
