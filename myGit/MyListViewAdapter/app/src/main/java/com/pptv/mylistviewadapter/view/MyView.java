package com.pptv.mylistviewadapter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @anthor LeiKang
 */
public class MyView extends View
{
    public MyView(Context context)
    {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void initView()
    {
        Log.e("BAG", "initView");

    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        Log.e("BAG", "onFinishInflate");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("BAG", "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("BAG", "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Log.e("BAG", "onDraw");
    }
}
