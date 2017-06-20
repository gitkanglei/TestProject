package com.pptv.mylistviewadapter.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pptv.mylistviewadapter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @anthor LeiKang
 */
public class jelloyScrollActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jelly_scroll_view);
        findViewById(R.id.container).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        List<String> stringList =new ArrayList<>(4);
        stringList.add(0,"aa");
        stringList.add(1,"aaa");
        stringList.add(2,"xxx");
        stringList.add(2,"aaa");

        Log.e("TAG","size :"+stringList.get(2));
    }
}
