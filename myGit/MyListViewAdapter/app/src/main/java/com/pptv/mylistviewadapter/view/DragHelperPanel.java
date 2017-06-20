package com.pptv.mylistviewadapter.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * @author LeiKang
 * @time 2017/3/2
 */
public class DragHelperPanel extends LinearLayout
{
    private ViewDragHelper mDragHelper;

    private ListView myDragView;

     private View view;

    public DragHelperPanel(Context context)
    {
        super(context);
        initView();
    }

    public DragHelperPanel(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView();
    }

    // @Override
    // protected void onLayout(boolean changed, int l, int t, int r, int b)
    // {
    // // 子view的坐标是相对父布局的(layout)
    // view.layout(l,t,r,view.getMeasuredHeight());
    // myDragView.layout(l, view.getMeasuredHeight(), r, b);
    // Log.e("TAG", "onLayout");
    // }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
                resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
    }

    private void initView()
    {
        mDragHelper = ViewDragHelper.create(this, callback);
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_TOP);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        myDragView = (ListView) getChildAt(1);
         view = getChildAt(0);
        Log.e("TAG", "initView");

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
            boolean positive = ViewCompat.canScrollVertically(mTarget, 1);
            boolean negative = ViewCompat.canScrollVertically(mTarget, -1);
            return negative;
        }
    }

    public void released()
    {
        // smoothSlideViewTo 之后需要重新绘制view invalidate
        mDragHelper.smoothSlideViewTo(myDragView, myDragView.getLeft(), 0);
        invalidate();
    }

    // invalidate 会调用draw方法 draw方法 又会调用computeScroll方法
    @Override
    public void computeScroll()
    {
        if (mDragHelper.continueSettling(true))
        {
            invalidate();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        canChildScrollUp(myDragView);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        // 解决滑动冲突
        if (canChildScrollUp(myDragView))
        {
            return false;
        }
        else
        {
            return mDragHelper.shouldInterceptTouchEvent(ev);
        }
        // return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    ViewDragHelper.Callback callback = new ViewDragHelper.Callback()
    {
        @Override
        public boolean tryCaptureView(View child, int pointerId)
        {
            return true;
        }

        // 手指释放后的回调
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel)
        {
            Log.e("TAG", "height :" + releasedChild.getHeight() + " Top :" + releasedChild.getTop());
            float movePrecent = (releasedChild.getTop()) / (float) releasedChild.getHeight();
            int finalTop = (xvel >= 0 && movePrecent > 0.5f) ? releasedChild.getHeight() : view.getBottom();
            // 最终定位到那个位置(view)
            mDragHelper.settleCapturedViewAt(releasedChild.getLeft(), finalTop);
            invalidate();
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy)
        {
            Log.e("TAG", top + "");
            if (top <= view.getBottom())
            {
                return view.getBottom();
            }
            else
            {
                return Math.min(top, myDragView.getHeight());
            }

        }

        // 防止子view吃掉事件 使之滑动不了(很重要)
        @Override
        public int getViewVerticalDragRange(View child)
        {
            return 1;
        }

    };
}
