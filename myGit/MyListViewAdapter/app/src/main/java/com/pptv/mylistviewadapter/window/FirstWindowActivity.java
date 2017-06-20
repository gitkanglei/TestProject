package com.pptv.mylistviewadapter.window;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.pptv.mylistviewadapter.R;
import com.pptv.mylistviewadapter.service.ActivityService;

/**
 * @anthor LeiKang
 */
public class FirstWindowActivity extends Activity
{
    private Button floatButton;

    WindowManager windowManager;

    private int x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showWindow();
        findViewById(R.id.tx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstWindowActivity.this, ActivityService.class));
            }
        });
    }

    private void showWindow()
    {
        windowManager = getWindowManager();
        floatButton = new Button(this);
        floatButton.setText("button");
        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0,
                PixelFormat.TRANSPARENT);
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        windowManager.addView(floatButton, layoutParams);
        floatButton.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int rawX = (int) event.getRawX();
                int rawy = (int) event.getRawY();
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        x = rawX;
                        y = rawy;
                        Log.e("TAG","rawX"+rawX +"  rawY:" +rawy);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("TAG","rawX"+(rawX-x) +"  rawY:" +(rawy-y));
                        // 原来乳齿
                        layoutParams.x += rawX-x;
                        layoutParams.y += rawy-y;
                        x = rawX;
                        y = rawy;
//                        Log.e("TAG","rawX"+rawX +"  rawY:" +rawy);
                        windowManager.updateViewLayout(floatButton,layoutParams);
                       // Log.e("TAG","X:"+layoutParams.x);
                        break;
                    default:
                        break;
                }

                return true;
            }
        });
    }
}
