package com.pptv.mylistviewadapter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * @anthor LeiKang
 */
public class SearchingView extends View
{
    private Paint mPaint;

    private Paint mBackPaint;

    private Context mContext;

    private Path p1, p2, p3, p4;

    boolean isChanged = false;

    public SearchingView(Context context)
    {
        this(context, null);
    }

    public SearchingView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        init();
    }

    public void init()
    {
        mBackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackPaint.setStyle(Paint.Style.FILL);
        mBackPaint.setColor(Color.parseColor("#005474"));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DisplayUtil.dip2px(mContext, 3));
        p1 = new Path();
        p2 = new Path();
        p3 = new Path();
        p4 = new Path();
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
        // 先画一个背景
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mBackPaint);

        p1.reset();
        p2.reset();
        p3.reset();
        p4.reset();
        // 画两个path
        if (!isChanged)
        {
            // 上面的path
            p1.moveTo(DisplayUtil.dip2px(mContext, 23.5), DisplayUtil.dip2px(mContext, 9));
            p1.lineTo(DisplayUtil.dip2px(mContext, 12), DisplayUtil.dip2px(mContext, 19.8));
            p1.lineTo(DisplayUtil.dip2px(mContext, 35), DisplayUtil.dip2px(mContext, 19.8));
            canvas.drawPath(p1, mPaint);

            // 下面的path
            p2.moveTo(DisplayUtil.dip2px(mContext, 9), DisplayUtil.dip2px(mContext, 26.5));
            p2.lineTo(DisplayUtil.dip2px(mContext, 32), DisplayUtil.dip2px(mContext, 26.5));
            p2.lineTo(DisplayUtil.dip2px(mContext, 21.5), DisplayUtil.dip2px(mContext, 36));
            canvas.drawPath(p2, mPaint);
        }
        else
        {
            p3.moveTo(DisplayUtil.dip2px(mContext, 21.5), DisplayUtil.dip2px(mContext, 9));
            p3.lineTo(DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 19.8));
            p3.lineTo(DisplayUtil.dip2px(mContext, 33), DisplayUtil.dip2px(mContext, 19.8));
            canvas.drawPath(p3, mPaint);

            p4.moveTo(DisplayUtil.dip2px(mContext, 11), DisplayUtil.dip2px(mContext, 26.5));
            p4.lineTo(DisplayUtil.dip2px(mContext, 34), DisplayUtil.dip2px(mContext, 26.5));
            p4.lineTo(DisplayUtil.dip2px(mContext, 23.5), DisplayUtil.dip2px(mContext, 36));
            canvas.drawPath(p4, mPaint);
        }

        isChanged = !isChanged;
        postInvalidateDelayed(600);

    }
}
