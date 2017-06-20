package com.pptv.mylistviewadapter.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.pptv.mylistviewadapter.R;

/**
 * @anthor LeiKang
 */
public class FavorView extends View
{
    private Paint paint;

    private Bitmap bitmap;

    public FavorView(Context context)
    {
        super(context);
        initView();
    }

    public FavorView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView();
    }

    public FavorView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView()
    {
        if (bitmap != null && !bitmap.isRecycled())
        {
            // 回收并且置为null
            bitmap.recycle();
            bitmap = null;
        }
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.love_red);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;

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
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int bitmapWidth = bitmap.getWidth();
        int bitmapheight = bitmap.getHeight();
        canvas.drawBitmap(bitmap, (getWidth() - bitmapWidth) / 2, (getHeight() - bitmapheight) / 2, paint);
        canvas.drawCircle(getWidth()/2,getHeight()/2,bitmapWidth/2+10,paint);
    }
}
