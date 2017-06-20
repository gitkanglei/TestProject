package com.pptv.mylistviewadapter.leak;

import android.content.Context;
import android.widget.Toast;

/**
 * @anthor LeiKang
 */
public class LeakIntance
{

    public static LeakIntance leakIntance;

    public Context context;

    public static LeakIntance getInstance()
    {
        if (leakIntance == null)
        {
            leakIntance = new LeakIntance();
        }

        return leakIntance;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    public void Toast()
    {
        Toast.makeText(context, "haha", Toast.LENGTH_LONG).show();
    }
}
