package com.pptv.mylistviewadapter.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * @anthor LeiKang
 */
public class JellyScrollView extends ScrollView
{
    private View inner;// 子View

    private float y;// 点击时y坐标

    private Rect normal = new Rect();// 矩形(这里只是个形式，只是用于判断是否需要动画.)

    private boolean isCount = false;// 是否开始计算

    private boolean isMoving = false;// 是否开始移动.

    private int top;// 拖动时时高度。

    private int mTouchSlop;// 系统最少滑动距离

    private ObjectAnimator animator;

    public JellyScrollView(Context context)
    {
        super(context);
    }

    public JellyScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public JellyScrollView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        if (getChildCount() > 0)
        {
            inner = getChildAt(0);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        if (inner != null)
        {
            int action = ev.getAction();
            switch (action)
            {
                case MotionEvent.ACTION_DOWN:
                    y = ev.getY();
                    top = 0;
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    // 手指松开.
                    isMoving = false;
                    if (isNeedAnimation())
                    {
                        animation();
                    }
                    break;

                case MotionEvent.ACTION_MOVE:
                    final float preY = y;// 按下时的y坐标
                    float nowY = ev.getY();// 每时刻y坐标
                    int deltaY = (int) (nowY - preY);// 滑动距离
//                    if (!isCount)
//                    {
//                        deltaY = 0; // 在这里要归0.
//                    }

                    // 当滚动到最上或者最下时就不会再滚动，这时移动布局
                    isNeedMove();

                    if (isMoving)
                    {
                        // 初始化头部矩形
                        if (normal.isEmpty())
                        {
                            // 保存正常的布局位置
                            normal.set(inner.getLeft(), inner.getTop(), inner.getRight(), inner.getBottom());
                        }

                        // 移动布局
                        inner.layout(inner.getLeft(), inner.getTop() + deltaY / 3, inner.getRight(),
                                inner.getBottom() + deltaY / 3);
                        Log.e("TAG", " ScrollY:" + inner.getScrollY());
                        top += (deltaY / 6);
                    }

                    isCount = true;
                    y = nowY;
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /***
     * 回缩动画
     */
    public void animation()
    {
        // 开启移动动画
//         TranslateAnimation ta = new TranslateAnimation(0, 0,
//         inner.getTop(), normal.top);
//         ta.setDuration(200);
//         inner.startAnimation(ta);
        if(animator!=null)
        {
            animator.cancel();
        }
        animator = ObjectAnimator.ofFloat(inner, "translationY", inner.getTop(), 0);
        animator.setDuration(2000);
        animator.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                super.onAnimationEnd(animation);
                Log.e("TAG","getTop:"+inner.getTop()+" translationY:"+inner.getTranslationY());
            }
        });
        animator.start();
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();
        // 设置回到正常的布局位置
        //

        // 手指松开要归0.
        isCount = false;
        y = 0;
    }

    // 是否需要开启动画
    public boolean isNeedAnimation()
    {
        return !normal.isEmpty();
    }

    /***
     * 是否需要移动布局 inner.getMeasuredHeight():获取的是控件的总高度 getHeight()：获取的是屏幕的高度
     *
     * @return
     */
    public void isNeedMove()
    {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // scrollY == 0是顶部
        // scrollY == offset是底部
        if (scrollY == 0 || scrollY == offset)
        {
            isMoving = true;
        }
    }
}
