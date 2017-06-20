package com.pptv.mylistviewadapter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * @anthor LeiKang
 */
public class TestView extends View
{
    private Paint mPaint;

    public TestView(Context context)
    {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mPaint = new Paint();
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#e5e5e5"));
        //保存的画布大小为全屏幕大小
        canvas.save();
        //这个矩形会在上一个画布上进行draw 也就是（0，0）位置
        canvas.clipRect(new Rect(100, 100, 600, 600));
        //这个画布在前一个矩形大小为500,500的位置
        canvas.drawColor(Color.GREEN);
        //保存画布大小为Rect(100, 100, 600, 600)
//        canvas.save();
//        canvas.restore();
        //这个会在上一个矩形为500，500的位置上
        canvas.clipRect(new Rect(200, 200, 700, 700));
        canvas.drawColor(Color.BLUE);
        //保存画布大小为Rect(200, 200, 700, 700)
//        canvas.save();
        //这个会在原点为(200,200)大小为500的我、canvas上
        canvas.clipRect(new Rect(300, 300, 600, 600));
        canvas.drawColor(Color.BLACK);
        //保存画布大小为Rect(300, 300, 600, 600)
//        canvas.save();
        //这个会在原点为（300,300）为原点大小为300的位置上进行canvas
        canvas.clipRect(new Rect(400, 400, 500, 500));
        canvas.drawColor(Color.YELLOW);
    }
}
