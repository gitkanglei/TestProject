package com.pptv.mylistviewadapter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @anthor LeiKang
 */
public class ConnectingView extends View
{
    private Paint paint;

    private Paint paint2;

    private Context mContext;

    boolean isChanged = false;

    public ConnectingView(Context context)
    {
        super(context);
        mContext = context;
    }

    public ConnectingView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public void initView()
    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(DisplayUtil.dip2px(mContext, 3));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        }
        else
        {
            width = getSuggestedMinimumWidth();
        }
        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        }
        else
        {
            height = getSuggestedMinimumHeight();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#005474"));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
        RectF rectF = new RectF(DisplayUtil.dip2px(mContext, 17), DisplayUtil.dip2px(mContext, 9),
                DisplayUtil.dip2px(mContext, 36), DisplayUtil.dip2px(mContext, 28));
        RectF rectBottom1 = new RectF(DisplayUtil.dip2px(mContext, 9), DisplayUtil.dip2px(mContext, 17),
                DisplayUtil.dip2px(mContext, 28), DisplayUtil.dip2px(mContext, 36));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DisplayUtil.dip2px(mContext, 3));
        if (!isChanged)
        {
            paint.setColor(Color.WHITE);
            paint2.setColor(Color.parseColor("#00aeef"));
        }
        else
        {
            paint2.setColor(Color.WHITE);
            paint.setColor(Color.parseColor("#00aeef"));
        }
        canvas.drawArc(rectBottom1, 45, 270, false, paint);
        canvas.drawArc(rectF, -135, 270, false, paint2);
        isChanged = !isChanged;
        postInvalidateDelayed(600);
    }
}
