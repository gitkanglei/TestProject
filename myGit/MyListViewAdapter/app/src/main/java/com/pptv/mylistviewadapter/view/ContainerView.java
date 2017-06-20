package com.pptv.mylistviewadapter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.pptv.mylistviewadapter.R;

/**
 * @anthor LeiKang
 */
public class ContainerView extends LinearLayout
{
    public ContainerView(Context context)
    {
        this(context,null);
    }

    public ContainerView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context)
    {
        View mHeadView = LayoutInflater.from(context).inflate(R.layout.zoomview_layout, null, false);
        addView(mHeadView);
    }
}
