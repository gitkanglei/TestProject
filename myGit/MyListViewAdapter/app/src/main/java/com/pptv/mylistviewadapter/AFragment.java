package com.pptv.mylistviewadapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.pptv.mylistviewadapter.verifyCodeView.verifyCodeView;

/**
 * @author LeiKang
 * @time 2017/2/27
 */
public class AFragment extends Fragment
{
    private View rootView;

    private verifyCodeView verify;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState)

    {
        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.activity_main, container, false);
        }

        if (rootView.getParent() != null)
        {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }

        Log.e("TAG", "onCreateView");
        initView();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint())
        {
            Log.e("TAG", "Fragment可视");
            initView();
        }
        else
        {
            Log.e("TAG", "Fragment不可视");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Log.e("TAG", "onActivityCreated");
        // AnimationUtils.loadAnimation()
    }

    private void initView()
    {
        TextView tx = (TextView) rootView.findViewById(R.id.tx);
        String txs = getArguments().getString("tag");
        tx.setText(txs);
        verify = (verifyCodeView) rootView.findViewById(R.id.verify);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.image);
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(
                true).bitmapConfig(Bitmap.Config.RGB_565).showImageForEmptyUri(R.mipmap.ic_launcher).showImageOnFail(
                        R.mipmap.ic_launcher);
        DisplayImageOptions options = builder.build();

        ImageLoader.getInstance().displayImage("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
                imageView, options, null);
        rootView.findViewById(R.id.tx).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                verify.isEqualsIgnoreCase("");
            }
        });
    }

}
