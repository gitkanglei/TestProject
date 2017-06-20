package com.pptv.mylistviewadapter.textureView;

import com.pptv.mylistviewadapter.R;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;

/**
 * @anthor LeiKang
 */
public class TextureViewActivity extends Activity implements TextureView.SurfaceTextureListener
{
    private TextureView textureView;

    private MediaPlayer mMediaPlayer;

    private Surface surface;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textureview_layout);
        textureView = (TextureView) findViewById(R.id.textureview);
        textureView.setSurfaceTextureListener(this);
        imageView = (ImageView) findViewById(R.id.video_image);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

            }
        }, 1000);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height)
    {
        // mCamera = Camera.open();
        // Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
        // textureView.setLayoutParams(
        // new FrameLayout.LayoutParams(previewSize.width, previewSize.height,
        // Gravity.CENTER));
        surface = new Surface(surfaceTexture);
        mMediaPlayer = new MediaPlayer();
        try
        {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource("rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov");
            mMediaPlayer.setSurface(surface);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
            {
                @Override
                public void onPrepared(MediaPlayer mp)
                {
//                    imageView.setVisibility(View.GONE);
                    mMediaPlayer.start();
                    ObjectAnimator animator = ObjectAnimator.ofFloat(textureView,"translationY",0f,-200);
                    animator.setDuration(1000);
                    animator.start();
                }
            });
            // mMediaPlayer.prepare();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("TAG", e.toString());
        }

        // try
        // {
        // mCamera.setPreviewTexture(surfaceTexture);
        // }
        // catch (IOException t)
        // {
        // }
        // mCamera.startPreview();
        // textureView.setAlpha(1.0f);
        // textureView.setRotation(90.0f);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height)
    {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture)
    {
        surfaceTexture = null;
        surface = null;
        mMediaPlayer.stop();
        mMediaPlayer.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface)
    {

    }

    private class PlayerVideo extends Thread
    {
        @Override
        public void run()
        {

        }
    }

}
