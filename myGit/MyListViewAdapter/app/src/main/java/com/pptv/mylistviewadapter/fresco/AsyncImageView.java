package com.pptv.mylistviewadapter.fresco;

import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

/**
 * Created by Egos on 16/8/19.
 */
public class AsyncImageView extends SimpleDraweeView
{
    public AsyncImageView(Context context)
    {
        super(context);
    }

    public AsyncImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public AsyncImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public AsyncImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AsyncImageView(Context context, GenericDraweeHierarchy hierarchy)
    {
        super(context, hierarchy);
    }

    /**
     * 设置图片
     */
    public void setImageUrl(String url)
    {
        setImageUrl(url, -1);
    }

    public void setImageUrl(String url, int defaultRes)
    {
        setImageUrl(url, defaultRes, -1);
    }

    public void setImageUrl(String url, int defaultRes, int errorRes)
    {
        if (getHierarchy() != null)
        {
            if (errorRes != -1)
            {
                getHierarchy().setFailureImage(errorRes);
            }
            if (defaultRes != -1)
            {
                getHierarchy().setPlaceholderImage(defaultRes);
            }
        }

        setImageURI(url);
    }

    private String mUrl;

    public String getUrl()
    {
        return mUrl;
    }
    
    @Override
    public void setImageURI(String uriString)
    {
        // 还需要判断是不是本地的视频
        boolean isAllowShow = true;
        mUrl = uriString;
        if (!isAllowShow)
        {
            uriString = null;
        }
        super.setImageURI(uriString);
    }

    @Override
    public void setImageURI(Uri uri, Object callerContext)
    {
        DraweeController controller = ((PipelineDraweeControllerBuilder) getControllerBuilder()).setCallerContext(
                callerContext).setUri(uri).setAutoPlayAnimations(true).setOldController(getController()).build();
        setController(controller);
    }

    public void setRoundCornerImageUrl(String url)
    {
        setRoundCornerImageUrl(url, 8.0f);
    }

    /**
     * 设置圆角图片,gif图片目前不支持
     */
    public void setRoundCornerImageUrl(String url, float radius)
    {
        setRoundCornerImageUrl(url, radius, -1);
    }

    public void setRoundCornerImageUrl(String url, float radius, int defaultRes)
    {
        setRoundCornerImageUrl(url, radius, defaultRes, -1);
    }

    public void setRoundCornerImageUrl(String url, float radius, int defaultRes, int errorRes)
    {
        if (getHierarchy() != null)
        {
            if (errorRes != -1)
            {
                getHierarchy().setFailureImage(errorRes);
            }
            if (defaultRes != -1)
            {
                getHierarchy().setPlaceholderImage(defaultRes);
            }
            if (getHierarchy().getRoundingParams() != null && radius > 0)
            {
                getHierarchy().getRoundingParams().setCornersRadius(radius);
            }
            else if (radius > 0)
            {
                RoundingParams roundingParams = new RoundingParams();
                roundingParams.setCornersRadius(radius);
                getHierarchy().setRoundingParams(roundingParams);
            }
        }
        setImageURI(url);
    }

    /**
     * 设置圆形图片,gif图片目前不支持
     */
    public void setCircleImageUrl(String url)
    {
        setCircleImageUrl(url, -1);
    }

    public void setCircleImageUrl(String url, int defaultRes)
    {
        setCircleImageUrl(url, defaultRes, -1);
    }

    public void setCircleImageUrl(String url, int defaultRes, int errorRes)
    {
        if (getHierarchy() != null)
        {
            if (errorRes != -1)
            {
                getHierarchy().setFailureImage(errorRes);
            }
            if (defaultRes != -1)
            {
                getHierarchy().setPlaceholderImage(defaultRes);
            }
            if (getHierarchy().getRoundingParams() != null)
            {
                getHierarchy().getRoundingParams().setRoundAsCircle(true);
            }
            else
            {
                RoundingParams roundingParams = new RoundingParams();
                roundingParams.setRoundAsCircle(true);
                // roundingParams.setRoundingMethod(RoundingParams.RoundingMethod.OVERLAY_COLOR);
                // roundingParams.setOverlayColor(R.color.white);
                getHierarchy().setRoundingParams(roundingParams);
            }
        }
        setImageURI(url);
    }

    public void setFadeInImageUrl(String url)
    {
        setFadeInImageUrl(url, 600);
    }

    public void setFadeInImageUrl(String url, int durationMillis)
    {
        setFadeInImageUrl(url, durationMillis, -1);
    }

    public void setFadeInImageUrl(String url, int durationMillis, int defaultRes)
    {
        setFadeInImageUrl(url, durationMillis, defaultRes, -1);
    }

    public void setFadeInImageUrl(String url, int durationMillis, int defaultRes, int errorRes)
    {
        if (getHierarchy() != null)
        {
            if (errorRes != -1)
            {
                getHierarchy().setFailureImage(errorRes);
            }
            if (defaultRes != -1)
            {
                getHierarchy().setPlaceholderImage(defaultRes);
            }

            getHierarchy().setFadeDuration(durationMillis);
        }
        setImageURI(url);
    }
}
