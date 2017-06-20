package com.pptv.mylistviewadapter.pullzoom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pptv.mylistviewadapter.R;

/**
 * @anthor LeiKang
 */
public abstract class PullZoomBase<T extends View> extends LinearLayout implements IPullZoom
{
    /**
     * 根布局,用来装所有内容
     */
    protected T mRootView;

    /**
     * 定义的显示伸缩效果的View
     */
    protected View mZoomView;

    /**
     * 伸缩效果上展示的内容
     */
    protected View mHeadView;

    /**
     * 是否允许下拉
     */
    private boolean isPullEnable = true;

    private boolean isZooming;

    private boolean isHeadHide;

    private boolean isDragging;

    private float mLastX;

    private float mLastY;

    private float mInitX;

    private float mInitY;

    private int mTouchSlop;

    public PullZoomBase(Context context)
    {
        this(context, null);
    }

    public PullZoomBase(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public PullZoomBase(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        ViewConfiguration config = ViewConfiguration.get(context);
        mTouchSlop = config.getScaledTouchSlop();
        mRootView = initRootView(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        // TypedArray a = context.obtainStyledAttributes(attrs,
        // R.styleable.PullZoomView);
        mHeadView = inflater.inflate(R.layout.zoomview_layout, null, false);
        initHeader();
        addView(mRootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
    // 什么时候去拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {

        int action = ev.getAction();
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP)
        {
            isDragging = false;
            return false;
        }

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                isDragging = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (allowStart())
                {
                    isDragging = true;
                    // float x = ev.getX();
                    // float y = ev.getY();
                    // float diffX = x - mLastX;
                    // float diffY = y - mLastY;
                    // float diffYAds = Math.abs(diffY);
                    // if (diffYAds > mTouchSlop && diffYAds >
                    // Math.abs(diffX))
                    // {
                    // if (diffY >= 1 && allowStart())
                    // {
                    // mLastX = x;
                    // mLastY = y;
                    // isDragging = true;
                    // }
                    // }
                }
                else
                {
                    isDragging = false;
                }
                break;
        }

        Log.e("TAG",isDragging ? "true":"false");
        return isDragging;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = (int) (event.getY() - mLastY) / 2;
                pull(dy);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                smoothRestore();
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    public boolean isPullEnable()
    {
        return isPullEnable;
    }

    public void setIsPullEnable(boolean isPullEnable)
    {
        this.isPullEnable = isPullEnable;
    }

    public boolean isHeadHide()
    {
        return isHeadHide;
    }

    public void setIsHeadHide(boolean isHeadHide)
    {
        this.isHeadHide = isHeadHide;
    }

    /**
     * 创建根布局,例如ListView,GridView,RecycleView,ScrollView等等
     *
     * @param context
     * @param set
     * @return
     */
    public abstract T initRootView(Context context, AttributeSet set);

    /**
     * 判定是否允许开始滚动
     *
     * @return
     */
    public abstract boolean allowStart();

    /**
     * 传入一个计算值,用来对Header做放大缩小操作
     *
     * @param value
     */
    public abstract void pull(int value);

    /**
     *
     */
    public abstract void smoothRestore();
}
