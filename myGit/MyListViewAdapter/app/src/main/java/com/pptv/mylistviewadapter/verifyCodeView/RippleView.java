package com.pptv.mylistviewadapter.verifyCodeView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @anthor LeiKang
 */
public class RippleView extends View
{
    private Paint paint;

    private Path path;

    boolean isFrist;

    private float mCycleFactorW;

    private float[] mYPositions, mResetOneYPositions;

    private int mTotalWidth, mTotalHeight;

    private int mXOneOffset;

    private float mHeight =100;

//    private DrawFilter mDrawFilter;

    public RippleView(Context context)
    {
        super(context);
        initView();
    }

    public RippleView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView();
    }

    public void initView()
    {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
//        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        path = new Path();
    }

    // draw 自己的背景
    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        // mCycleFactorW = (float) (2 * Math.PI / mTotalWidth);
        // mYPositions = new float[mTotalWidth - 1];
        // // 根据view总宽度得出所有对应的y值
        // for (int i = 0; i < mTotalWidth; i++)
        // {
        // mYPositions[i] = (float) (20 * Math.sin(mCycleFactorW * i));
        // }
        //
        // for (int i = 0; i < mTotalWidth; i++)
        // {
        // canvas.drawLine(i, getHeight() - mResetOneYPositions[i] - 400, i,
        // getHeight(), paint);
        //
        // }
        // if (isFrist)
        // {
        // canvas.drawPath(path, paint);
        // }
        // else
        // {
        // path.moveTo(0, getHeight() / 2);
        // path.cubicTo(getWidth() / 4, 3 * getHeight() / 4, 3 * getWidth() /
        // 4, getHeight() / 4, getWidth(),
        // getHeight() / 2);
        // canvas.drawPath(path, paint);
        // }
//        canvas.setDrawFilter(mDrawFilter);
        resetPositionY();
        for (int i = 0; i < mTotalWidth; i++)
        {
            // 绘制第一条水波纹
            canvas.drawLine(i, mTotalHeight - mResetOneYPositions[i] - mHeight, i, mTotalHeight, paint);
        }

        mXOneOffset += 7;

        if (mXOneOffset >= mTotalWidth)
        {
            mXOneOffset = 0;
        }
       invalidate();
        super.onDraw(canvas);
    }

    public void startAnimation()
    {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(100f, 500f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                mHeight = (float) animation.getAnimatedValue();
            }
        });
        valueAnimator.setDuration(10000);
        valueAnimator.start();
    }

    public void resetPositionY()
    {
        // mXOneOffset代表当前第一条水波纹要移动的距离
        int yOneInterval = mYPositions.length - mXOneOffset;
        // 使用System.arraycopy方式重新填充第一条波纹的数据
        System.arraycopy(mYPositions, mXOneOffset, mResetOneYPositions, 0, yOneInterval);
        System.arraycopy(mYPositions, 0, mResetOneYPositions, yOneInterval, mXOneOffset);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        // 记录下view的宽高
        mTotalWidth = w;
        mTotalHeight = h;
        // 用于保存原始波纹的y值
        mYPositions = new float[mTotalWidth];
        // 用于保存波纹一的y值
        mResetOneYPositions = new float[mTotalWidth];

        // 将周期定为view总宽度
        mCycleFactorW = (float) (2 * Math.PI / mTotalWidth);

        // 根据view总宽度得出所有对应的y值
        for (int i = 0; i < mTotalWidth; i++)
        {
            mYPositions[i] = (float) (20 * Math.sin(mCycleFactorW * i) + 0);
        }
    }
}
