package com.pptv.mylistviewadapter.textureView;

import com.pptv.mylistviewadapter.R;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @anthor LeiKang
 */
public class MyTextureView extends LinearLayout implements TextureView.SurfaceTextureListener
{
    private TextureView mVideoView;

    private ImageView mCoverImageView;

    private MediaPlayer mMediaPlayer;

    private Surface surface;

    private int mLastX;

    private int mLastY;

    public MyTextureView(Context context)
    {
        super(context);
        initView();
    }

    public MyTextureView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView();
    }

    private void initView()
    {
        inflate(getContext(), R.layout.view_pager_play_item, this);
        mVideoView = (TextureView) findViewById(R.id.textureview_paly);
        mCoverImageView = (ImageView) findViewById(R.id.cover_image);
        mCoverImageView.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
        mVideoView.setSurfaceTextureListener(MyTextureView.this);
        // mCoverImageView.setOnClickListener(new OnClickListener()
        // {
        // @Override
        // public void onClick(View v)
        // {
        // Log.e("TAG","imageView 被点击了");
        //
        // }
        // });
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height)
    {
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
                    mCoverImageView.setVisibility(GONE);
                    mMediaPlayer.start();
                    // ObjectAnimator animator =
                    // ObjectAnimator.ofFloat(mVideoView,"translationY",0f,-200);
                    // animator.setDuration(1000);
                    // animator.start();
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        int nowX = (int) ev.getX();
        int nowY = (int) ev.getY();
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mLastX = nowX;
                mLastY = nowY;
                break;
            case MotionEvent.ACTION_MOVE:
                int dex = nowX - mLastX;
                int dey = nowY = mLastY;

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height)
    {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface)
    {
        surface = null;
        mMediaPlayer.stop();
        mMediaPlayer.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface)
    {

    }
}
