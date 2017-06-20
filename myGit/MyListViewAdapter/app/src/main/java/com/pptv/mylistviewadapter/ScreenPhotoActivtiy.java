package com.pptv.mylistviewadapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author LeiKang
 * @time 2017/3/2
 */
public class ScreenPhotoActivtiy extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_photo_layout);
        findViewById(R.id.img).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ScreenPhotoActivtiy.this.onClick(v);
            }
        });
    }

    // public boolean takeScreenShot(String imagePath){
    //
    //
    //
    // if(imagePath.equals("" )){
    // imagePath = Environment.getExternalStorageDirectory()+ File.
    // separator+"Screenshot.png" ;
    // }
    //
    // Bitmap mScreenBitmap;
    // WindowManager mWindowManager;
    // DisplayMetrics mDisplayMetrics;
    // Display mDisplay;
    //
    // mWindowManager = (WindowManager)
    // this.getSystemService(Context.WINDOW_SERVICE);
    // mDisplay = mWindowManager.getDefaultDisplay();
    // mDisplayMetrics = new DisplayMetrics();
    // mDisplay.getRealMetrics(mDisplayMetrics);
    //
    // float[] dims = {mDisplayMetrics.widthPixels ,
    // mDisplayMetrics.heightPixels };
    //// mScreenBitmap = Surface.((int) dims[0], ( int) dims[1]);
    //
    // if (mScreenBitmap == null) {
    // return false ;
    // }
    //
    // try {
    // FileOutputStream out = new FileOutputStream(imagePath);
    // mScreenBitmap.compress(Bitmap.CompressFormat. PNG, 100, out);
    //
    // } catch (Exception e) {
    //
    //
    // return false ;
    // }
    //
    // return true ;
    // }

    public void onClick(View v)
    {

        SimpleDateFormat sdf = new SimpleDateFormat(

                "yyyy-MM-dd_HH-mm-ss", Locale.US);

        String fname = "/sdcard/" + sdf.format(new Date()) + ".png";

        View view = v.getRootView();

        view.setDrawingCacheEnabled(true);

        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        if (bitmap != null)
        {

            try
            {

                FileOutputStream out = new FileOutputStream(fname);

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

            }
            catch (Exception e)
            {

                e.printStackTrace();

            }

        }
        else
        {

        }

    }

}
