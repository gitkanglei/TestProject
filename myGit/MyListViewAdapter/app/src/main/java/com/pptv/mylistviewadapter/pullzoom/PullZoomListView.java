package com.pptv.mylistviewadapter.pullzoom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * @anthor LeiKang
 */
public class PullZoomListView extends PullZoomBase<ListView>
{

    private LinearLayout mHeaderContainer;

    private int mHeaderHeight;

    private SmoothRestore mSmoothRestore;

    public static Interpolator mInterpolator = new Interpolator()
    {
        @Override
        public float getInterpolation(float input)
        {
            float f = input - 1.0F;
            return 1.0F + f * (f * (f * (f * f)));
        }
    };

    public PullZoomListView(Context context)
    {
        this(context, null);
    }

    public PullZoomListView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public PullZoomListView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mSmoothRestore = new SmoothRestore();
    }

    @Override
    public ListView initRootView(Context context, AttributeSet set)
    {
        ListView listview = new ListView(context, set);
        return listview;
    }

    @Override
    public void initHeader()
    {
        mHeaderContainer = new LinearLayout(getContext());
        if (mZoomView != null)
        {
            mHeaderContainer.addView(mZoomView);
        }
        if (mHeadView != null)
        {
            mHeaderContainer.addView(mHeadView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        mRootView.addHeaderView(mHeaderContainer);
        mHeaderContainer.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 200));
        mHeaderHeight = 200;
    }

    public void setAdapter(BaseAdapter adapter)
    {
        mRootView.setAdapter(adapter);
    }

    public void setHeaderLayoutParams(AbsListView.LayoutParams params)
    {
        if (mHeaderContainer != null)
        {
            mHeaderContainer.setLayoutParams(params);
            mHeaderHeight = params.height;
        }
    }

    public void updateHeader()
    {
        if (mHeaderContainer != null)
        {
            mRootView.removeHeaderView(mHeaderContainer);
            mHeaderContainer.removeAllViews();
            if (mZoomView != null)
            {
                mHeaderContainer.addView(mZoomView);
            }
            if (mHeadView != null)
            {
                mHeaderContainer.addView(mHeadView);
            }
            mHeaderHeight = mHeaderContainer.getHeight();
            mRootView.addHeaderView(mHeaderContainer);
        }
    }

    @Override
    public boolean allowStart()
    {
        return isFirstItemVisiable()&& mHeaderContainer.getHeight()!=50;
    }

    private boolean isFirstItemVisiable()
    {
        Adapter adapter = mRootView.getAdapter();
        if (null == adapter || adapter.isEmpty())
        {
            return true;
        }
        else
        {
            if (mRootView.getFirstVisiblePosition() <= 1)
            {
                View view = mRootView.getChildAt(0);
                if (view != null)
                {
                    Log.e("TAG","View.getTop"+view.getTop()+ "    mRootView.getTop():"+ mRootView.getTop());
                    return view.getTop() >= mRootView.getTop();
                }
            }
        }
        return false;
    }

    @Override
    public void pull(int value)
    {
        // if (mSmoothRestore != null && !mSmoothRestore.isFinish())
        // {
        // mSmoothRestore.abort();
        // }
        AbsListView.LayoutParams params = (AbsListView.LayoutParams) mHeaderContainer.getLayoutParams();
        // mHeaderHeight =params.height;
        if(value >0 )
        {
            return;
        }
        int hegiht = value +mHeaderHeight;
        if(hegiht <=0)
        {
            hegiht = 50 ;
        }
        params.height = hegiht;
        mHeaderContainer.setLayoutParams(params);
        Log.e("TAG","mHeaderHeight :"+mHeaderContainer.getHeight() +"value :" +value + "height :"+hegiht);
//        mHeaderContainer.setLayoutParams(params);
    }

    @Override
    public void smoothRestore()
    {
        mHeaderHeight = mHeaderContainer.getHeight();
        Log.e("TAG","mHeaderHeight :"+mHeaderHeight);
        // mSmoothRestore.start(0);
        // final ViewGroup.LayoutParams params =
        // mHeaderContainer.getLayoutParams();
        // ValueAnimator valueAnimator = ValueAnimator.ofInt(params.height,
        // 0);
        // valueAnimator.setDuration(1000);
        // valueAnimator.addUpdateListener(new
        // ValueAnimator.AnimatorUpdateListener()
        // {
        // @Override
        // public void onAnimationUpdate(ValueAnimator animation)
        // {
        // params.height = (int) animation.getAnimatedValue();
        // mHeaderContainer.setLayoutParams(params);
        // }
        // });
        // valueAnimator.start();
    }

    class SmoothRestore implements Runnable
    {
        protected long duration;

        protected boolean isFinished;

        protected float scale;

        protected long starttime;

        SmoothRestore()
        {
        }

        public void abort()
        {
            isFinished = true;
        }

        public boolean isFinish()
        {
            return isFinished;
        }

        public void start(long d)
        {
            if (mZoomView != null)
            {
                starttime = SystemClock.currentThreadTimeMillis();
                duration = d;
                scale = (float) mHeaderContainer.getBottom() / mHeaderHeight;
                isFinished = false;
                post(this);
            }
        }

        @Override
        public void run()
        {
            if (mZoomView != null)
            {
                float f2;
                ViewGroup.LayoutParams params;

                if (!isFinished && scale > 1.0D)
                {
                    float f1 = ((float) SystemClock.currentThreadTimeMillis() - (float) starttime) / (float) duration;
                    f2 = scale - (scale - 1.0F) * PullZoomListView.mInterpolator.getInterpolation(f1);
                    params = mHeaderContainer.getLayoutParams();
                    if (f2 > 1.0F)
                    {
                        params.height = (int) (f2 * mHeaderHeight);
                        mHeaderContainer.setLayoutParams(params);
                        post(this);
                        return;
                    }
                    isFinished = true;
                }

            }
        }
    }
}
