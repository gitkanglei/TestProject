package com.pptv.mylistviewadapter;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.pptv.mylistviewadapter.threadpool.MutilThreadTask;

import java.util.concurrent.ExecutionException;

/**
 * @anthor LeiKang
 */
public class ConnectViewActity extends Activity
{

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_view);
        img = (ImageView) findViewById(R.id.img);
        AnimationDrawable animationDrawable = (AnimationDrawable) img.getDrawable();
        animationDrawable.start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                MutilThreadTask task = new MutilThreadTask();
                try
                {
                    task.startTask();
                }
                catch (ExecutionException e)
                {
                    e.printStackTrace();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
