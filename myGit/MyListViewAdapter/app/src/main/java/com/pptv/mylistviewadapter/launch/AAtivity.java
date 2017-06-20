package com.pptv.mylistviewadapter.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pptv.mylistviewadapter.R;

/**
 * @anthor LeiKang
 */
public class AAtivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TextView tv = new TextView(this);
        // tv.setText("A");
        // tv.setLayoutParams(
        // new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        // ViewGroup.LayoutParams.MATCH_PARENT));
        // setContentView(tv);
        // tv.setOnClickListener(new View.OnClickListener()
        // {
        // @Override
        // public void onClick(View v)
        // {
        // startActivity(new Intent(AAtivity.this, BAtivity.class));
        // }
        // });
        Log.e("TAG", "OnCreate_A");
        setContentView(R.layout.fragment_layout);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e("TAG", "onButton");
            }
        });

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.e("TAG", "onStart_A");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.e("TAG", "onResume_A");
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        Log.e("TAG", "onNewIntent");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", data.getStringExtra("name"));
    }
}
