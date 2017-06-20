package com.pptv.mylistviewadapter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.pptv.mylistviewadapter.R;

/**
 * @anthor LeiKang
 */
public class leftSwipeLayout extends FrameLayout
{
    private Scroller mScroller;

    private Context mContext;

    private int mLastX;

    private int mLasY;

    private View contentView;

    private View leftView;

    public leftSwipeLayout(Context context)
    {
        super(context);
        initView();
    }

    public leftSwipeLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView();
    }

    private void initView()
    {
        mContext = getContext();
        inflate(mContext, R.layout.my_swipe_layout, this);
        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,DisplayUtil.dip2px(mContext, 100)));
        mScroller = new Scroller(mContext);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        contentView = getChildAt(getChildCount() - 1);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {

        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) ev.getRawX();
                mLasY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (ev.getRawX() - mLastX);
                // 用layout方法可以很好实现 view的滑动效果
//                contentView.layout(contentView.getLeft() + dx, contentView.getTop(), contentView.getRight() + dx,
//                        contentView.getBottom());
//                leftView .layout(leftView.getLeft(),leftView.getTop(),leftView.getRight(),leftView.getBottom());
                // if (dx > DisplayUtil.dip2px(mContext, 50))
                // {
                // mScroller.startScroll(0, getScrollY(),
                // -DisplayUtil.dip2px(mContext, 50), 0, 1000);
                // }
                // else
                // {
                // mScroller.startScroll(0, 0, -dx, 0, 1000);
                // }
                contentView.scrollBy(-dx,0);
//                mScroller.startScroll(getScrollX(), getScrollY(),
//                         -dx, 0, 1000);
                mLastX = (int) ev.getRawX();
                 invalidate();
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll()
    {
        super.computeScroll();
        if (mScroller.computeScrollOffset())
        {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
