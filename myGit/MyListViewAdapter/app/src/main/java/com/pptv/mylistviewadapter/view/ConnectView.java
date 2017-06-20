package com.pptv.mylistviewadapter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * @anthor LeiKang
 */
public class ConnectView extends View
{

    public ConnectView(Context context)
    {
        super(context);
    }

    public ConnectView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widhtSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;
        if (widthMode == MeasureSpec.AT_MOST)
        {
            // wrap_content 或者给一个默认的宽度
            width = getSuggestedMinimumWidth();
        }
        else
        {
            width = widhtSize;
        }

        if (heightMode == MeasureSpec.AT_MOST)
        {
            height = getSuggestedMinimumHeight();
        }
        else
        {
            height = heightSize;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
    }
}
